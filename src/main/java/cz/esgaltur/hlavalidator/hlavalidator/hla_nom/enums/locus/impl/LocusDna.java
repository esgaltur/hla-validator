package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class LocusDna extends Locus<DNA> {

    private final Enum<DNA> dna;

    public LocusDna(Enum<DNA> dna) {
        super(LocusType.DNA);
        this.dna = dna;
    }

    @Override
    public Enum<DNA> getValue() {
        return dna;
    }
}
