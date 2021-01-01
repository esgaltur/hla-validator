package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class LocusHLA extends Locus<HLA> {

    private final Enum<HLA> hla;

    LocusHLA(Enum<HLA> hla) {
        super(LocusType.HLA);
        this.hla = hla;
    }

    @Override
    public Enum<HLA> getValue() {
        return hla;
    }
}
