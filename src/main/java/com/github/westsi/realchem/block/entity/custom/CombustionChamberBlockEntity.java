package com.github.westsi.realchem.block.entity.custom;

import com.github.westsi.realchem.block.custom.CombustionChamberBlock;
import com.github.westsi.realchem.block.entity.ImplementedInventory;
import com.github.westsi.realchem.block.entity.ModBlockEntities;
import com.github.westsi.realchem.recipe.*;
import com.github.westsi.realchem.screen.custom.CombustionChamberScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CombustionChamberBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int OUTPUT_SLOT_1 = 2;
    public static final int OUTPUT_SLOT_2 = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    public CombustionChamberBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMBUSTION_CHAMBER_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CombustionChamberBlockEntity.this.progress;
                    case 1 -> CombustionChamberBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: CombustionChamberBlockEntity.this.progress = value;
                    case 1: CombustionChamberBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.realchem.combustion_chamber");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CombustionChamberScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("combustion_chamber.progress", progress);
        nbt.putInt("combustion_chamber.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("combustion_chamber.progress");
        maxProgress = nbt.getInt("combustion_chamber.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe() && canInsertIntoOutputSlot1()) {
            increaseCraftingProgress();
            world.setBlockState(pos, state.with(CombustionChamberBlock.LIT, true));
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            world.setBlockState(pos, state.with(CombustionChamberBlock.LIT, false));
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Optional<RecipeEntry<MultiItemCombustionRecipe>> recipe = getCurrentRecipe();
        // TODO: these counts will have to be changed if you want to use different quantities of stuff!
        this.removeStack(INPUT_SLOT_2, 1);
        this.removeStack(INPUT_SLOT_1, 1);
        this.setStack(OUTPUT_SLOT_1, new ItemStack(recipe.get().value().output().getItem(),
                this.getStack(OUTPUT_SLOT_1).getCount() + recipe.get().value().output().getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertIntoOutputSlot1() {
        return this.getStack(OUTPUT_SLOT_1).isEmpty() ||
                this.getStack(OUTPUT_SLOT_1).getCount() < this.getStack(OUTPUT_SLOT_1).getMaxCount();
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<MultiItemCombustionRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().getResult(null);
        return canInsertAmountIntoOutputSlot1(output.getCount()) && canInsertItemIntoOutputSlot1(output);
    }
    private Optional<RecipeEntry<MultiItemCombustionRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager()
                .getFirstMatch(ModRecipes.MULTI_COMBUSTION_TYPE, new MultiItemCombustionRecipeInput(inventory.get(INPUT_SLOT_2), inventory.get(INPUT_SLOT_1)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot1(ItemStack output) {
        return this.getStack(OUTPUT_SLOT_1).isEmpty() || this.getStack(OUTPUT_SLOT_1).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot1(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT_1).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT_1).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT_1).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
