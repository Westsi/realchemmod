package com.github.westsi.realchem.formula;

import java.util.HashMap;

public class Formula {
    private final HashMap<Element, Integer> molecularFormula = new HashMap<>();

    public Formula(Element... elements) {
        for (Element e : elements) {
            this.molecularFormula.put(e, this.molecularFormula.getOrDefault(e, 0) + 1);
        }
    }

    public Formula(String formula) {

    }

    public HashMap<Element, Integer> getMolecularFormula() {
        return molecularFormula;
    }
}
