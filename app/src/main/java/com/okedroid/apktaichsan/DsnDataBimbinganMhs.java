package com.okedroid.apktaichsan;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DsnDataBimbinganMhs extends AppCompatActivity {

    TextView namaMhs, noBp, topikTa, deskripsi;
    String namaMhsStr, noBpStr, topikTaStr, deskripsiStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_data_bimbingan_mhs);

        namaMhsStr = getIntent().getStringExtra("nama");
        noBpStr = getIntent().getStringExtra("noBp");
        topikTaStr = getIntent().getStringExtra("topikTA");
        deskripsiStr = getIntent().getStringExtra("deskripsi");

        namaMhs = findViewById(R.id.namaMhs);
        noBp = findViewById(R.id.noBpMhs);
        topikTa = findViewById(R.id.topikTA);
        deskripsi = findViewById(R.id.deskripsi);

        namaMhs.setText(namaMhsStr);
        noBp.setText(noBpStr);
        topikTa.setText(topikTaStr);
        deskripsi.setText(deskripsiStr);

    }
}
