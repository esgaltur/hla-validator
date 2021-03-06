package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LocusHLATest {
    @Test
    void locusDna_basic_HLA() {
        Locus<HLA> dna = LocusHLA.builder().hla(HLA.A).build();
        Assertions.assertEquals(LocusType.HLA.ordinal(), dna.locusType.ordinal());
        Assertions.assertEquals(HLA.A.ordinal(), dna.getValue().ordinal());
        Assertions.assertEquals("A", dna.getValue().name());
    }

    @Test
    void locusDna_basic_HLA_getValue() {
        Locus<HLA> dna = LocusHLA.builder().hla(HLA.A).build();
        Assertions.assertEquals(LocusType.HLA.ordinal(), dna.getValue().ordinal());

    }
}
