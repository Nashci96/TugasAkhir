package com.okedroid.apktaichsan.Model;

public class FreezeDsnModel {
    String namaDsn, nipDsn, statusDsn;

    public FreezeDsnModel(String namaDsn, String nipDsn, String statusDsn){
        this.namaDsn =   namaDsn;
        this.nipDsn =   nipDsn;
        this.statusDsn =   statusDsn;
    }

    public String getNamaDsn(){
        return namaDsn;
    }

    public String getNipDsn(){
        return nipDsn;
    }

    public String getStatusDsn(){
        return statusDsn;
    }

    public void setNamaDsn(String namaDsn){
        this.namaDsn = namaDsn;
    }

    public void setNipDsn(String nipDsn){
        this.nipDsn = nipDsn;
    }

    public void setStatusDsn(String statusDsn){
        this.statusDsn = statusDsn;
    }
}
