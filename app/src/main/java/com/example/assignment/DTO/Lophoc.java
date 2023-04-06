package com.example.assignment.DTO;

public class Lophoc {
    int idlop;
    String malop;
    String tenlop;

    public int getIdlop() {
        return idlop;
    }

    public void setIdlop(int idlop) {
        this.idlop = idlop;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }
    public static final String TB_NAME = "tb_lop";
    public static final String CLM_ID = "idlop";
    public static final String CLM_MALOP = "malop";
    public static final String CLM_TENLOP = "tenlop";
}
