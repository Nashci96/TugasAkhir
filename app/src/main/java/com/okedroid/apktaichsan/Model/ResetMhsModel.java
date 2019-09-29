package com.okedroid.apktaichsan.Model;

public class ResetMhsModel {

    String namaMhs,nobpMhs;

    public ResetMhsModel(String namaMhs,String nobpMhs){
        this.namaMhs = namaMhs;
        this.nobpMhs = nobpMhs;
    }

    public String getNamaMhs() { return namaMhs;}
    public String getNobpMhs() { return nobpMhs;}

    public void setNamaMhs(String namaMhs){ this.namaMhs = namaMhs;}
    public void setNobpMhs(String nobpMhs){ this.nobpMhs = nobpMhs;}
}
