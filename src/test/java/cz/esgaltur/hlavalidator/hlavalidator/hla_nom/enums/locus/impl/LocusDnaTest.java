package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LocusDnaTest {

    @Test
    void locusDna_basic() {
        Locus<DNA> dna = LocusDna.builder().dna(DNA.A).build();
        Assertions.assertEquals(LocusType.DNA.ordinal(), dna.locusType.ordinal());
        Assertions.assertEquals(DNA.A.ordinal(), dna.getValue().ordinal());
        Assertions.assertEquals("A", dna.getValue().name());
    }

    @Test
    void locusDna_basic_DNA() {
        Locus<DNA> dna = LocusDna.builder().dna(DNA.A).build();
        Assertions.assertEquals(LocusType.DNA.ordinal(), dna.locusType.ordinal());
        Assertions.assertEquals(DNA.A.ordinal(), dna.getValue().ordinal());
        Assertions.assertEquals("A", dna.getValue().name());
    }

    @Test
    void locusDna_basic_HLA_getValue() {
        Locus<DNA> dna = LocusDna.builder().dna(DNA.A).build();
        Assertions.assertEquals(LocusType.DNA.ordinal(), dna.getValue().ordinal());

    }


}
