package cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 *
 */
@AllArgsConstructor
@ToString
public abstract class Locus<T extends Enum<T>> {
    /**
     *
     */
    protected final LocusType locusType;

    /**
     * @param value
     * @return
     */
    public static Class<?> getType(String value) {
        return value.contains("*") ? DNA.class : HLA.class;
    }

    /**
     * @return
     */
    public abstract Enum<T> getValue();
}
