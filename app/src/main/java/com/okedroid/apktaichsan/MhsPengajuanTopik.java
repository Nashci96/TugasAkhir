package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MhsPengajuanTopik extends AppCompatActivity {

    private EditText judul,deskripsi;
    private Button btn_topik;
    private static String URL_TOPIK = "http://10.0.2.2/ta/mhs/topik.php";
    private String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_pengajuan_topik);

        judul       =   findViewById(R.id.et_judultopik);
        deskripsi   =   findViewById(R.id.et_desctopik);

        btn_topik   =   findViewById(R.id.bTopik);

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");

        btn_topik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Topik();
            }
        });
    }

    private void Topik() {

        final String judul      =   this.judul.getText().toString().trim();
        final String deskripsi  =   this.deskripsi.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsTopikTA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(MhsPengajuanTopik.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsPengajuanTopik.this, "Register Error" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsPengajuanTopik.this,"Register Error" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("topik",judul);
                params.put("desc",deskripsi);
                params.put("id",getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
