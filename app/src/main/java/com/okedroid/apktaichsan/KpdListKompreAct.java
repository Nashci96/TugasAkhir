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

public class KpdListKompreAct extends AppCompatActivity {

    TextView namaMhs,noBp,judulTA,nama_pbb_1,nama_pbb_2,nama_pgj_1,nama_pgj_2,tgl,shift;
    String namaMhsStr,noBpStr,judulTAStr,nama_pbb_1Str,nama_pbb_2Str,nama_pgj_1Str,nama_pgj_2Str,tglStr,shiftStr;
    Button bKpdKompreGantiPgj1,bKpdKompreGantiPgj2 ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_list_kompre2);

        namaMhsStr = getIntent().getStringExtra("namaMhs");
        noBpStr = getIntent().getStringExtra("noBp");
        judulTAStr = getIntent().getStringExtra("judulTA");
        nama_pbb_1Str = getIntent().getStringExtra("nama_pbb_1");
        nama_pbb_2Str = getIntent().getStringExtra("nama_pbb_2");
        nama_pgj_1Str = getIntent().getStringExtra("nama_pgj_1");
        nama_pgj_2Str = getIntent().getStringExtra("nama_pgj_2");
        tglStr = getIntent().getStringExtra("tgl");
        shiftStr = getIntent().getStringExtra("shift");

        namaMhs = findViewById(R.id.tv_KpdKompreLayout_Nama);
        noBp = findViewById(R.id.tv_KpdKompreLayout_BP);
        judulTA = findViewById(R.id.tv_KpdKompre_judul_desc);
        nama_pgj_1 = findViewById(R.id.tv_KpdKompre_pgj1_desc);
        nama_pgj_2 = findViewById(R.id.tv_KpdKompre_pgj2_desc);
        nama_pbb_1 = findViewById(R.id.tv_KpdKompre_pbb1_desc);
        nama_pbb_2 = findViewById(R.id.tv_KpdKompre_pbb2_desc);
        tgl = findViewById(R.id.tv_KpdKompre_tgl_desc);
        shift = findViewById(R.id.tv_KpdKompre_shift_desc);

        namaMhs.setText(namaMhsStr);
        noBp.setText(noBpStr);
        judulTA.setText(judulTAStr);
        nama_pgj_1.setText(nama_pgj_1Str);
        nama_pgj_2.setText(nama_pgj_2Str);
        nama_pbb_1.setText(nama_pbb_1Str);
        nama_pbb_2.setText(nama_pbb_2Str);
        tgl.setText(tglStr);
        shift.setText(shiftStr);

        bKpdKompreGantiPgj1 = findViewById(R.id.KpdKompreBtnPgtPgj1);
        bKpdKompreGantiPgj2 = findViewById(R.id.KpdKompreBtnPgtPgj2);

        bKpdKompreGantiPgj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KomprePgtPgj1();
            }
        });

        bKpdKompreGantiPgj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KomprePgtPgj2();
            }
        });
        
    }

    private void KomprePgtPgj1() {
        final String nobp   =   this.noBp.getText().toString().trim();
        final String tgl    =   this.tgl.getText().toString().trim();
        final String shift  =   this.shift.getText().toString().trim();
        final String pgj    =   "1";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjPgtKompre,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdListKompreAct.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdListKompreAct.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(KpdListKompreAct.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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

    private void KomprePgtPgj2() {
        final String nobp   =   this.noBp.getText().toString().trim();
        final String tgl    =   this.tgl.getText().toString().trim();
        final String shift  =   this.shift.getText().toString().trim();
        final String pgj    =   "2";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjPgtKompre,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdListKompreAct.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdListKompreAct.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KpdListKompreAct.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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
