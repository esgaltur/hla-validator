package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.UNKNOWN;
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
     * @param value String value of the Locus
     * @return Class<?>
     */
    public static Class<?> getType(String value) {
        if (value == null)
            return UNKNOWN.class;
        if (value.isEmpty() || value.isBlank())
            return UNKNOWN.class;
        return value.contains("*") ? DNA.class : HLA.class;
    }


    /**
     * @return Enum<T>
     */
    public abstract Enum<T> getValue();
}
