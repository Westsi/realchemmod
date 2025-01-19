package com.github.westsi.realchem.chemistry.formula;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds information of the periodic table of elements.
 *
 * @author Felix Divo
 * <a href="https://gist.github.com/felixdivo/11159677">...</a>
 */
public enum Element {

    /* SYMBOL NAME ATOMIC_WEIGHT ELECTRONEGATIVITY */
    H("Hydrogen", 1.00794f, 2.1f), He("Helium", 4.0026f, 0), Li("Lithium",
            6.941f, 0.98f), Be("Beryllium", 9.01218f, 1.57f), B("Boron",
            10.811f, 2.04f), C("Carbon", 12.011f, 2.55f), N("Nitrogen",
            14.0067f, 3.04f), O("Oxygen", 15.9994f, 3.44f), F("Fluorine",
            18.9984f, 3.98f), Ne("Neon", 20.1797f, 0), Na("Sodium", 22.98977f,
            0.93f), Mg("Magnesium", 24.305f, 1.31f), Al("Aluminum", 26.98154f,
            1.61f), Si("Silicon", 28.0855f, 1.9f), P("Phosphorus", 30.97376f,
            2.19f), S("Sulfur", 32.066f, 2.58f), Cl("Chlorine", 35.4527f, 3.16f), Ar(
            "Argon", 39.948f, 0), K("Potassium", 39.0983f, 0.82f), Ca(
            "Calcium", 40.078f, 1), Sc("Scandium", 44.9559f, 1.36f), Ti(
            "Titanium", 47.88f, 1.54f), V("Vanadium", 50.9415f, 1.63f), Cr(
            "Chromium", 51.996f, 1.66f), Mn("Manganese", 54.938f, 1.55f), Fe(
            "Iron", 55.847f, 1.83f), Co("Cobalt", 58.9332f, 1.88f), Ni(
            "Nickel", 58.6934f, 1.91f), Cu("Copper", 63.546f, 1.9f), Zn("Zinc",
            65.39f, 1.65f), Ga("Gallium", 69.723f, 1.81f), Ge("Germanium",
            72.61f, 2.01f), As("Arsenic", 74.9216f, 2.18f), Se("Selenium",
            78.96f, 2.55f), Br("Bromine", 79.904f, 2.96f), Kr("Krypton", 83.8f,
            0), Rb("Rubidium", 85.4678f, 0.82f), Sr("Strontium", 87.62f, 0.95f), Y(
            "Yttrium", 88.9059f, 1.22f), Zr("Zirconium", 91.224f, 1.33f), Nb(
            "Niobium", 92.9064f, 1.6f), Mo("Molybdenum", 95.94f, 2.16f), Tc(
            "Technetium", 98, 1.9f), Ru("Ruthenium", 101.07f, 2.2f), Rh(
            "Rhodium", 102.9055f, 2.28f), Pd("Palladium", 106.42f, 2.2f), Ag(
            "Silver", 107.868f, 1.93f), Cd("Cadmium", 112.41f, 1.69f), In(
            "Indium", 114.82f, 1.78f), Sn("Tin", 118.71f, 1.96f), Sb(
            "Antimony", 121.757f, 2.05f), Te("Tellurium", 127.6f, 2.1f), I(
            "Iodine", 126.9045f, 2.66f), Xe("Xenon", 131.29f, 2.6f), Cs(
            "Cesium", 132.9054f, 0.79f), Ba("Barium", 137.33f, 0.89f), La(
            "Lanthanum", 138.9055f, 1.1f), Ce("Cerium", 140.12f, 1.12f), Pr(
            "Praseodymium", 140.9077f, 1.13f), Nd("Neodymium", 144.24f, 1.14f), Pm(
            "Promethium", 145, 1.13f), Sm("Samarium", 150.36f, 1.17f), Eu(
            "Europium", 151.965f, 1.2f), Gd("Gadolinium", 157.25f, 1.2f), Tb(
            "Terbium", 158.9253f, 1.1f), Dy("Dysprosium", 162.5f, 1.22f), Ho(
            "Holmium", 164.9303f, 1.23f), Er("Erbium", 167.26f, 1.24f), Tm(
            "Thulium", 168.9342f, 1.25f), Yb("Ytterbium", 173.04f, 1.1f), Lu(
            "Lutetium", 174.967f, 1.27f), Hf("Hafnium", 178.49f, 1.3f), Ta(
            "Tantalum", 180.9479f, 1.5f), W("Tungsten", 183.85f, 2.36f), Re(
            "Rhenium", 186.207f, 1.9f), Os("Osmium", 190.2f, 2.2f), Ir(
            "Iridium", 192.22f, 2.2f), Pt("Platinum", 195.08f, 2.28f), Au(
            "Gold", 196.9665f, 2.54f), Hg("Mercury", 200.59f, 2), Tl(
            "Thallium", 204.383f, 2.04f), Pb("Lead", 207.2f, 2.33f), Bi(
            "Bismuth", 208.9804f, 2.02f), Po("Polonium", 209, 2), At(
            "Astatine", 210, 2.2f), Rn("Radon", 222, 0), Fr("Francium", 223,
            0.7f), Ra("Radium", 226.0254f, 0.89f), Ac("Actinium", 227, 1.1f), Th(
            "Thorium", 232.0381f, 1.3f), Pa("Protactinium", 231.0359f, 1.5f), U(
            "Uranium", 238.029f, 1.38f), Np("Neptunium", 237.0482f, 1.36f), Pu(
            "Plutonium", 244, 1.28f), Am("Americium", 243, 1.3f), Cm("Curium",
            247, 1.3f), Bk("Berkelium", 247, 1.3f), Cf("Californium", 251, 1.3f), Es(
            "Einsteinium", 252, 1.3f), Fm("Fermium", 257, 1.3f), Md(
            "Mendelevium", 258, 1.3f), No("Nobelium", 259, 1.3f), Lr(
            "Lawrencium", 262, 0), Rf("Rutherfordium", 261, 0), Db("Dubnium",
            262, 0), Sg("Seaborgium", 263, 0), Bh("Bohrium", 262, 0), Hs(
            "Hassium", 265, 0), Mt("Meitnerium", 266, 0), Uun("ununnilium",
            269, 0), Uuu("unununium", 272, 0), Uub("ununbium", 277, 0);

    /**
     * Holds mappings of the Elements.
     * <p>
     * There's a bit of java kung fu going on here that deserves an explanation.
     * The map is put inside a static inner (holder) class so it gets
     * initialized before the enum instances are initialized, that way they can
     * add themselves to it. If not in the inner static class, it would not be
     * initialize, because the first thing initialized in the enum class must be
     * the instances, but static inner classes are initialized before the class
     * is initialized. (Trick from <a
     * href="http://stackoverflow.com/a/22738610/2210921">here</a>.)
     */
    static final class Holder {
        /** Mapps the atomic number to the Element */
        static final Map<Integer, Element> map_atomicNumber = new HashMap<Integer, Element>();
        /** Mapps the symbol to the Element */
        static final Map<String, Element> map_symbol = new HashMap<String, Element>();
    }

    /**
     * Creates a new Element and mapps it to 'Holder.map_atomicNumber' and
     * 'Holder.map_symbol'.
     *
     * @param fullName
     *            the full name of the element (not the symbol)
     * @param atomicMass
     *            the average atomic mass of the element in 'u'
     * @param electroNegativity
     *            the electron negativity by Pauling
     */
    private Element(String fullName, float atomicMass, float electroNegativity) {

        this.fullName = fullName;
        this.atomicMass = atomicMass;
        this.electroNegativity = electroNegativity;

        Element.Holder.map_atomicNumber.put(this.getAtomicNumber(), this);
        Element.Holder.map_symbol.put(this.name(), this);
    }

    private final String fullName;
    private final float atomicMass;
    private final float electroNegativity;

    /**
     * Get the atomic number of this Element.
     *
     * @return this Element's atomic number in 'u'
     */
    public int getAtomicNumber() {
        return this.ordinal() + 1;
    }

    /**
     * Get the symbol of this Element.
     *
     * @return this Element's symbol (1 to 3 chars long)
     */
    public String getSymbol() {
        return this.name();
    }

    /**
     * Get the full name of this Element
     *
     * @return this Element's full name (not the symbol)
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Get the atomic mass of this Element.
     *
     * @return this Element's average atomic mass in 'u'
     */
    public float getAtomicMass() {
        return this.atomicMass;
    }

    /**
     * get the electron negativity of this Element.
     *
     * @return this Element's electron negativity in Pauling
     */
    public float getElectroNegativity() {
        return this.electroNegativity;
    }

    /**
     * Get a String representation of this Element.
     * <p>
     * For example:<br>
     * <i>He[fullName="Helium", atomicMass="4.0026", electroNegativity="0"]</i>
     *
     * @return a String representation of this Element.
     */
    public String toString() {
        return name() + "[fullName=\"" + this.fullName + "\", atomicMass=\""
                + this.atomicMass + "\", electroNegativity=\""
                + this.electroNegativity + "\"]";
    }

    /**
     * Search for the Element by the given atomic number.
     *
     * @param atomicNumber
     *            ths atomic number to search by
     *
     * @return the Element with the given atomic number or null if it couldn't
     *         be found
     */
    public static Element getByAtomicNumber(int atomicNumber) {
        return Element.Holder.map_atomicNumber.get(atomicNumber);
    }

    /**
     * Search for the Element by the given symbol.
     *
     * @param symbol
     *            ths symbol to search by, must be correctly typed (case
     *            sensitive)
     *
     * @return the Element with the given symbol or null if it couldn't be found
     *
     * @see {@link #toCorrectSymbol(String symbol)}
     */
    public static Element getBySymbol(String symbol) {
        return Element.Holder.map_symbol.get(symbol);
    }

    /**
     * Get the symbol of the Elemment with the specified atomic number.
     *
     * @param atomicNumber
     *            ths atomic number to search by
     *
     * @return the element's symbol or null if the element couldn't be found
     */
    public static String getSymbolByAtomicNumber(int atomicNumber) {

        Element e = Element.Holder.map_atomicNumber.get(atomicNumber);
        if (e != null)
            return e.getSymbol();
        else
            return null;
    }

    /**
     * Get the atomic number of the Elemment with the specified symbol.
     *
     * @param atomicNumber
     *            ths symbol to search by, must be correctly typed (case
     *            sensitive)
     *
     * @return the element's atomic number or 0 if the element couldn't be found
     *
     * @see {@link #toCorrectSymbol(String symbol)}
     */
    public static int getAtomicNumberBySymbol(String symbol) {

        Element e = Element.Holder.map_atomicNumber.get(symbol);
        if (e != null)
            return e.getAtomicNumber();
        else
            return 0;
    }

    /**
     * Finds out, whether an Element with the given symbol exists.
     *
     * @param symbol
     *            the symbol to search by, must be correctly typed (case
     *            sensitive)
     *
     * @return true, if an Element with the given symbol exists.
     *
     * @see {@link #exists(int atomicNumber)}
     * @see {@link #toCorrectSymbol(String symbol)}
     */
    public static boolean exists(String symbol) {
        if (null != Element.Holder.map_symbol.get(symbol))
            return true;
        else
            return false;
    }

    /**
     * Finds out, whether an Element with the given atomic number exists.
     *
     * @param symbol
     *            the atomic number to search by
     *
     * @return true, if an Element with the given atomic number.
     *
     * @see {@link #exists(String symbol)}
     */
    public static boolean exists(int atomicNumber) {
        if (null != Element.Holder.map_symbol.get(atomicNumber))
            return true;
        else
            return false;
    }

    /**
     * Turns a, for example, lowercase Symbol ("cl") into the correctly spelled
     * symbol ("Cl"). Assumes that the symbol corresponds to a existing element
     * of the periodic table of elements.
     * <p>
     * Examples:<br>
     * "cl" -> "Cl"<br>
     * "Cl" -> "Cl"<br>
     * "cL" -> "Cl"<br>
     * "CL" -> "Cl"<br>
     *
     * @param symbol
     *            the symbol to convert (1 to 3 chars long)
     *
     * @return the correctly spelled symbol
     */
    public static String toCorrectSymbol(String symbol) {

        String correct_symbol = "";

        correct_symbol += Character.toUpperCase(symbol.charAt(0));

        if (2 == symbol.length()) {
            correct_symbol += Character.toLowerCase(symbol.charAt(1));

        } else if (3 == symbol.length()) {
            correct_symbol += Character.toLowerCase(symbol.charAt(1));
            correct_symbol += Character.toLowerCase(symbol.charAt(2));
        }

        return correct_symbol;
    }
}
