package cz.esgaltur.hlavalidator.hlavalidator.hla_nom;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.RowPositionsHlaNomCsv;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.Locus;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusDna;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusHLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusUnknown;
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
     * Locus from the concrete ENUM HLA/DNA/UNKNOWN
     */
    private Locus<?> locus;
    /**
     * value -
     * Example for the HLA format: 1
     * Example for the DNA format: 01:01:01:01
     */
    private String value;
    /**
     * Date of the Assignment
     */
    private Date dateAssigned;
    /**
     * Date of the Deletion
     */
    private Date dateDeleted;
    /**
     * Deleted HLA/DNA, reason for the deletion is, identical to
     */
    private String identical;
    /**
     * Deletion Reason
     */
    private String reason;

    /**
     * @param attributes one line of the hla_nom file
     */
    public HlaNomRecord(String[] attributes) {
        init();
        setUpValues(attributes);

    }

    /**
     * Public constructor without params
     */
    public HlaNomRecord() {
        init();
    }

    /**
     * Initialization method
     */
    private void init() {
        locus = LocusUnknown.builder().build();
        value = "";
        dateAssigned = Date.from(Instant.ofEpochSecond(0));
        dateDeleted = Date.from(Instant.ofEpochSecond(0));
        identical = "";
        reason = "";

    }

    /**
     * @param attributes splitted into the String array row in format
     *                   <code>LOCUS;Value;DATEASSIGNED;DATEDELETED;IDENTICALTO;REASON</code>
     * @see RowPositionsHlaNomCsv
     */
    public void setUpValues(String[] attributes) {
        Class<?> aClazz = Locus.getType(attributes[RowPositionsHlaNomCsv.LOCUS.ordinal()]);
        LocusType locusType = aClazz.isAssignableFrom(DNA.class) ? LocusType.DNA : LocusType.HLA;
        if (locusType == LocusType.HLA) {
            locus = LocusHLA.builder().hla(HLA.valueOf(attributes[RowPositionsHlaNomCsv.LOCUS.ordinal()])).build();
        } else {
            locus = LocusDna.builder().dna(DNA.valueOf(attributes[RowPositionsHlaNomCsv.LOCUS.ordinal()].replace("*", ""))).build();
        }
        value = attributes[RowPositionsHlaNomCsv.VALUE.ordinal()];
        try {
            dateAssigned = new SimpleDateFormat("yyyyMMdd").parse(attributes[RowPositionsHlaNomCsv.DATE_ASSIGNED.ordinal()]);
            if (attributes.length > 3) {
                dateDeleted = new SimpleDateFormat("yyyyMMdd").parse(attributes[RowPositionsHlaNomCsv.DATE_DELETED.ordinal()]);
                identical = attributes[RowPositionsHlaNomCsv.IDENTICAL.ordinal()];
                reason = attributes[RowPositionsHlaNomCsv.REASON.ordinal()];
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return boolean true  in case, that Date of the Deletion is filled up in the line;
     */
    public boolean isDeleted() {
        return this.getDateDeleted().toInstant().getEpochSecond() != 0;
    }


}


