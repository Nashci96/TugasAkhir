package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KpdListSemhasAct extends AppCompatActivity {

    TextView namaMhs,noBp,judulTA,nama_pbb_1,nama_pbb_2,nama_pgj_1,nama_pgj_2,tgl,shift;
    String namaMhsStr,noBpStr,judulTAStr,nama_pbb_1Str,nama_pbb_2Str,nama_pgj_1Str,nama_pgj_2Str,tglStr,shiftStr;
    Button bKpdSemhasGantiPgj1,bKpdSemhasGantiPgj2;

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

        bKpdSemhasGantiPgj1 = findViewById(R.id.KpdSemhasBtnPgtPgj1);
        bKpdSemhasGantiPgj2 = findViewById(R.id.KpdSemhasBtnPgtPgj2);

        bKpdSemhasGantiPgj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SemhasPgtPgj1();
            }
        });

        bKpdSemhasGantiPgj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SemhasPgtPgj2();
            }
        });

    }

    private void SemhasPgtPgj1() {
        final String nobp   =   this.noBp.getText().toString().trim();
        final String tgl    =   this.tgl.getText().toString().trim();
        final String shift  =   this.shift.getText().toString().trim();
        final String pgj    =   "1";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjPgtSemhas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdListSemhasAct.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdListSemhasAct.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KpdListSemhasAct.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("nobp",nobp);
                params.put("tgl",tgl);
                params.put("shift",shift);
                params.put("pgj",pgj);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void SemhasPgtPgj2() {
        final String nobp   =   this.noBp.getText().toString().trim();
        final String tgl    =   this.tgl.getText().toString().trim();
        final String shift  =   this.shift.getText().toString().trim();
        final String pgj    =   "2";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjPgtSemhas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdListSemhasAct.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdListSemhasAct.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KpdListSemhasAct.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("nobp",nobp);
                params.put("tgl",tgl);
                params.put("shift",shift);
                params.put("pgj",pgj);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
