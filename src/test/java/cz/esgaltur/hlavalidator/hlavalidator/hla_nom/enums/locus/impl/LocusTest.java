package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.UNKNOWN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class LocusTest {

    @Test
    void resolveType_basic_DNA() {
        Class<?> aClazz = Locus.getType("A*");
        Assertions.assertEquals(aClazz.getName(), DNA.class.getName());
    }

    @Test
    void resolveType_basic_HLA() {
        Class<?> aClazz = Locus.getType("A");
        Assertions.assertEquals(aClazz.getName(), HLA.class.getName());
    }

    @Test
    void resolveType_basic_UNKNOWN() {
        Class<?> aClazz = Locus.getType("");
        Assertions.assertEquals(aClazz.getName(), UNKNOWN.class.getName());
    }

    @Test
    void resolveType_basic_UNKNOWN_null() {
        Class<?> aClazz = Locus.getType(null);
        Assertions.assertEquals(aClazz.getName(), UNKNOWN.class.getName());
    }
}
