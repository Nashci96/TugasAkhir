package com.okedroid.apktaichsan.Model;

import com.okedroid.apktaichsan.DsnKompre;

public class DsnKompreModel {

    String nama,nobp,judul,nip_pbb_1,nip_pbb_2, nama_pbb_1, nama_pbb_2,nip_pgj_1,nip_pgj_2, nama_pgj_1, nama_pgj_2,tgl,shift;



    public DsnKompreModel(String namaMhs, String noBp, String judulTA, String nama_pbb_1, String nama_pbb_2, String nama_pgj_1,
                           String nama_pgj_2, String tgl, String shift) {
        this.nama   = namaMhs;
        this.nobp   = noBp;
        this.judul  = judulTA;
        this.nama_pbb_1 = nama_pbb_1;
        this.nama_pbb_2 = nama_pbb_2;
        this.nama_pgj_1 = nama_pgj_1;
        this.nama_pgj_2 = nama_pgj_2;
        this.tgl = tgl;
        this.shift = shift;
    }

    public String GetNama() { return  nama;}
    public String GetNobp() { return  nobp;}
    public String GetJudul() { return  judul;}
    public String GetNamaPbb1() {return nama_pbb_1;}
    public String GetNamaPbb2() {return nama_pbb_2;}
    public String GetNamaPgj1() {return nama_pgj_1;}
    public String GetNamaPgj2() {return nama_pgj_2;}
    public String GetTgl() {return  tgl;}
    public String GetShift() {return  shift;}

    public void setNama(String nama){ this.nama = nama;}
    public void setNobp(String nobp){ this.nama = nobp;}
    public void setJudul(String judul){ this.judul = judul;}
    public void setNamaPbb1(String n_pbb_1){this.nama_pbb_1 = n_pbb_1;}
    public void setNamaPbb2(String n_pbb_2){this.nama_pbb_2 = n_pbb_2;}
    public void setNamaPgj1(String n_pgj_1){this.nama_pgj_1 = n_pgj_1;}
    public void setNamaPgj2(String n_pgj_2){this.nama_pgj_2 = n_pgj_2;}
    public void setTgl(String tgl){this.tgl = tgl;}
    public void setShift(String shift){this.shift = shift;}
}
