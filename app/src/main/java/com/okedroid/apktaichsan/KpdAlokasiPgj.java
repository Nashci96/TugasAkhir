package com.okedroid.apktaichsan;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class KpdAlokasiPgj extends AppCompatActivity {

    private Button bAlocSempro , bAlocSemhas , bAlocKompre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_alokasi_pgj);
        
        bAlocSempro = findViewById(R.id.bAlocSempro);
        bAlocSemhas = findViewById(R.id.bAlocSemhas);
        bAlocKompre = findViewById(R.id.bAlocKompre);
        
        bAlocSempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alocSempro();
                Intent intent = new Intent(KpdAlokasiPgj.this,KpdListSempro.class);
                startActivity(intent);
            }
        });
        
        bAlocSemhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alocSemhas();
                Intent intent = new Intent(KpdAlokasiPgj.this,KpdListSemhas.class);
                startActivity(intent);
            }
        });
        
        bAlocKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alocKompre();
                Intent intent = new Intent(KpdAlokasiPgj.this,KpdListKompre.class);
                startActivity(intent);
            }
        });
    }

    private void alocKompre() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjKompre,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdAlokasiPgj.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdAlokasiPgj.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KpdAlokasiPgj.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void alocSemhas() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjSemhas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdAlokasiPgj.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdAlokasiPgj.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KpdAlokasiPgj.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void alocSempro() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AlokasiPgjSempro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdAlokasiPgj.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdAlokasiPgj.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KpdAlokasiPgj.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
