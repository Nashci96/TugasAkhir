package com.okedroid.apktaichsan.Model;

public class JadwalDosenModel {

    String tanggal, shift;

    public JadwalDosenModel(String tanggal, String shift){

        this.tanggal = tanggal;
        this.shift = shift;

    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
