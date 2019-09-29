package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class KpdListSemhasAct extends AppCompatActivity {

    TextView namaMhs,noBp,judulTA,nama_pbb_1,nama_pbb_2,nama_pgj_1,nama_pgj_2,tgl,shift;
    String namaMhsStr,noBpStr,judulTAStr,nama_pbb_1Str,nama_pbb_2Str,nama_pgj_1Str,nama_pgj_2Str,tglStr,shiftStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_list_semhas2);

        namaMhsStr = getIntent().getStringExtra("namaMhs");
        noBpStr = getIntent().getStringExtra("noBp");
        judulTAStr = getIntent().getStringExtra("judulTA");
        nama_pbb_1Str = getIntent().getStringExtra("nama_pbb_1");
        nama_pbb_2Str = getIntent().getStringExtra("nama_pbb_2");
        nama_pgj_1Str = getIntent().getStringExtra("nama_pgj_1");
        nama_pgj_2Str = getIntent().getStringExtra("nama_pgj_2");
        tglStr = getIntent().getStringExtra("tgl");
        shiftStr = getIntent().getStringExtra("shift");

        namaMhs = findViewById(R.id.tv_KpdSemhasLayout_Nama);
        noBp = findViewById(R.id.tv_KpdSemhasLayout_BP);
        judulTA = findViewById(R.id.tv_KpdSemhas_judul_desc);
        nama_pgj_1 = findViewById(R.id.tv_KpdSemhas_pgj1_desc);
        nama_pgj_2 = findViewById(R.id.tv_KpdSemhas_pgj2_desc);
        nama_pbb_1 = findViewById(R.id.tv_KpdSemhas_pbb1_desc);
        nama_pbb_2 = findViewById(R.id.tv_KpdSemhas_pbb2_desc);
        tgl = findViewById(R.id.tv_KpdSemhas_tgl_desc);
        shift = findViewById(R.id.tv_KpdSemhas_shift_desc);

        namaMhs.setText(namaMhsStr);
        noBp.setText(noBpStr);
        judulTA.setText(judulTAStr);
        nama_pgj_1.setText(nama_pgj_1Str);
        nama_pgj_2.setText(nama_pgj_2Str);
        nama_pbb_1.setText(nama_pbb_1Str);
        nama_pbb_2.setText(nama_pbb_2Str);
        tgl.setText(tglStr);
        shift.setText(shiftStr);
    }
}
