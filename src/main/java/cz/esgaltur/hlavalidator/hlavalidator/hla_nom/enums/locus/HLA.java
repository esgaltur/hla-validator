package cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus;

import lombok.ToString;

/**
 *
 */
@ToString
public enum HLA {
    A("A"), B("B"), Bw("Bw"), Cw("Cw"), DPw("DPw"), DR("DR"), DQ("DQ"),
    Dw("Dw");
    private final String nameHLA;

    HLA(String nameHLA) {
        this.nameHLA = nameHLA;
    }

    public String getNameHLA() {
        return this.nameHLA;
    }
}
