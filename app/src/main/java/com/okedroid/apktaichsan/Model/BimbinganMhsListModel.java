package com.okedroid.apktaichsan.Model;

public class BimbinganMhsListModel {

    String nama,nobp,judulta,progress,tgl;

    public BimbinganMhsListModel(String nama, String nobp, String judulta, String progress, String tgl){
        this.nama       =   nama;
        this.nobp       =   nobp;
        this.judulta    =   judulta;
        this.progress   =   progress;
        this.tgl        =   tgl;
    }

    public String GetNama(){
        return nama;
    }

    public String GetNobp(){
        return nobp;
    }

    public String GetJudulta(){
        return judulta;
    }

    public String GetProgress(){
        return progress;
    }

    public String GetTgl() {
        return tgl;
    }

    public void setNama(String nama){
        this.nama   =   nama;
    }

    public void setNobp(String nobp){
        this.nobp   =   nobp;
    }

    public void setJudulta(String judulta){
        this.judulta   =   judulta;
    }

    public void setProgress(String progress){
        this.progress   =   progress;
    }

    public void setTgl(String tgl){
        this.tgl    =   tgl;
    }

}
