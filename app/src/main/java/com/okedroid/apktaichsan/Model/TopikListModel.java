package com.okedroid.apktaichsan.Model;

public class TopikListModel {

    String nama, noBp,topikTA,deskripsi;

    public TopikListModel(String nama,String noBp,String topikTA, String deskripsi){
        this.nama       =   nama;
        this.noBp       =   noBp;
        this.topikTA    =   topikTA;
        this.deskripsi  =   deskripsi;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNoBp(){
        return noBp;
    }

    public void setNoBp(String noBp){
        this.noBp = noBp;
    }

    public String getTopikTA(){
        return topikTA;
    }

    public void setTopikTA(String topikTA){
        this.topikTA = topikTA;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

}
