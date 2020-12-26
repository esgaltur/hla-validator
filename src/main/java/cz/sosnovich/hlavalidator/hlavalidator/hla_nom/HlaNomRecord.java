package cz.sosnovich.hlavalidator.hlavalidator.hla_nom;

import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.RowPositions;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.Locus;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusDna;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusHLA;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusUnknown;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 *
 */
@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@CommonsLog
public class HlaNomRecord {
    /**
     *
     */
    private Locus<?> locus;
    /**
     *
     */
    private String value;
    /**
     *
     */
    private Date dateAssigned;
    /**
     *
     */
    private Date dateDeleted;
    /**
     *
     */
    private String identical;
    /**
     *
     */
    private String reason;

    /**
     * @param attributes
     */
    public HlaNomRecord(String[] attributes) {
        initValues();
        Class<?> aClazz = Locus.getType(attributes[RowPositions.LOCUS.ordinal()]);
        LocusType locusType = aClazz.isAssignableFrom(DNA.class) ? LocusType.DNA : LocusType.HLA;
        if (locusType == LocusType.HLA) {
            locus = LocusHLA.builder().hla(HLA.valueOf(attributes[RowPositions.LOCUS.ordinal()])).build();
        } else {
            locus = LocusDna.builder().dna(DNA.valueOf(attributes[RowPositions.LOCUS.ordinal()].replace("*", ""))).build();
        }
        value = attributes[RowPositions.VALUE.ordinal()];
        try {
            dateAssigned = new SimpleDateFormat("yyyyMMdd").parse(attributes[RowPositions.DATE_ASSIGNED.ordinal()]);
            if (attributes.length > 3) {
                dateDeleted = new SimpleDateFormat("yyyyMMdd").parse(attributes[RowPositions.DATE_DELETED.ordinal()]);
                identical = attributes[RowPositions.IDENTICAL.ordinal()];
                reason = attributes[RowPositions.REASON.ordinal()];
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    private void initValues() {
        locus = LocusUnknown.builder().build();
        value = "";
        dateAssigned = Date.from(Instant.ofEpochSecond(0));
        dateDeleted = Date.from(Instant.ofEpochSecond(0));
        identical = "";
        reason = "";

    }


}


