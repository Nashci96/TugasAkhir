package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MhsProgressBimbingan extends AppCompatActivity {

    TextView judulta,tgl,progress,nama,nobp;
    String judultaStr,tglStr,progressStr,namaStr,nobpStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_progress_bimbingan);

        judultaStr  = getIntent().getStringExtra("judulta");
        tglStr      = getIntent().getStringExtra("tgl");
        progressStr = getIntent().getStringExtra("progress");
        namaStr     = getIntent().getStringExtra("nama");
        nobpStr     = getIntent().getStringExtra("nobp");


        judulta     = findViewById(R.id.progress_judulta);
        tgl         = findViewById(R.id.progress_tanggal);
        progress    = findViewById(R.id.progress_progress);

        judulta.setText(judultaStr);
        tgl.setText(tglStr);
        progress.setText(progressStr);
//        nama.setText(namaStr);
//        nobp.setText(nobpStr);

    }
}
