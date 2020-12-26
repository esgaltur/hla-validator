package cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus;

import lombok.ToString;

/**
 *
 */
@ToString
public enum DNA {
    A("A*"),
    B("B*"),
    C("C*"),
    Cw("Cw*"),
    DMA("DMA*"),
    DMB("DMB*"),
    DOA("DOA*"),
    DOB("DOB*"),
    DPA1("DPA1*"),
    DPA2("DPA2*"),
    DPB1("DPB1*"),
    DPB2("DPB2*"),
    DQA1("DQA1*"),
    DQA2("DQA2*"),
    DQB1("DQB1*"),
    DQB2("DQB2*"),
    DRA("DRA*"),
    DRB1("DRB1*"),
    DRB2("DRB2*"),
    DRB3("DRB3*"),
    DRB4("DRB4*"),
    DRB5("DRB5*"),
    DRB6("DRB6*"),
    DRB7("DRB7*"),
    DRB8("DRB8*"),
    DRB9("DRB9*"),
    E("E*"), F("F*"),
    G("G*"), H("H*"),
    HFE("HFE*"), J("J*"),
    K("K*"), L("L*"),
    MICA("MICA*"), MICB("MICB*"),
    N("N*"), P("P*"),
    S("S*"), T("T*"),
    TAP1("TAP1*"), TAP2("TAP2*"),
    U("U*"), V("V*"),
    W("W*"), Y("Y*");

    private final String nameDNA;

    DNA(String nameDNA) {
        this.nameDNA = nameDNA;
    }


    public String getNameDNA() {
        return this.nameDNA;
    }


}
