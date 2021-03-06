package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.UNKNOWN;
import lombok.Builder;

import static cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.UNKNOWN.UNK;


@Builder
public class LocusUnknown extends Locus<UNKNOWN> {


    LocusUnknown() {
        super(LocusType.UNKNOWN);
    }

    @Override
    public Enum<UNKNOWN> getValue() {
        return UNK;
    }
}
