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

public class AdmVerSempro extends AppCompatActivity {

    TextView namaMhs,noBp,judulTA,nama_pbb_1,nama_pbb_2,nama_pgj_1,nama_pgj_2,tgl,shift;
    String namaMhsStr,noBpStr,judulTAStr,nama_pbb_1Str,nama_pbb_2Str,nama_pgj_1Str,nama_pgj_2Str,tglStr,shiftStr;
    Button bVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_ver_sempro);

        namaMhsStr = getIntent().getStringExtra("namaMhs");
        noBpStr = getIntent().getStringExtra("noBp");
        judulTAStr = getIntent().getStringExtra("judulTA");
        nama_pbb_1Str = getIntent().getStringExtra("nama_pbb_1");
        nama_pbb_2Str = getIntent().getStringExtra("nama_pbb_2");
        nama_pgj_1Str = getIntent().getStringExtra("nama_pgj_1");
        nama_pgj_2Str = getIntent().getStringExtra("nama_pgj_2");
        tglStr = getIntent().getStringExtra("tgl");
        shiftStr = getIntent().getStringExtra("shift");

        namaMhs = findViewById(R.id.tv_AdmVerSemproLayout_Nama);
        noBp = findViewById(R.id.tv_AdmVerSemproLayout_BP);
        judulTA = findViewById(R.id.tv_AdmVerSempro_judul_desc);
        nama_pgj_1 = findViewById(R.id.tv_AdmVerSempro_pgj1_desc);
        nama_pgj_2 = findViewById(R.id.tv_AdmVerSempro_pgj2_desc);
        nama_pbb_1 = findViewById(R.id.tv_AdmVerSempro_pbb1_desc);
        nama_pbb_2 = findViewById(R.id.tv_AdmVerSempro_pbb2_desc);
        tgl = findViewById(R.id.tv_AdmVerSempro_tgl_desc);
        shift = findViewById(R.id.tv_AdmVerSempro_shift_desc);
        bVer = findViewById(R.id.bAdmVerSempro);

        namaMhs.setText(namaMhsStr);
        noBp.setText(noBpStr);
        judulTA.setText(judulTAStr);
        nama_pgj_1.setText(nama_pgj_1Str);
        nama_pgj_2.setText(nama_pgj_2Str);
        nama_pbb_1.setText(nama_pbb_1Str);
        nama_pbb_2.setText(nama_pbb_2Str);
        tgl.setText(tglStr);
        shift.setText(shiftStr);
        
        bVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verSempro();
            }
        });
    }

    private void verSempro() {

        final String nobp   =   this.noBp.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.VerRegSempro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(AdmVerSempro.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmVerSempro.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmVerSempro.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("nobp",nobp);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
