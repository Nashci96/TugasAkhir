package com.okedroid.apktaichsan;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KpdMainMenu extends AppCompatActivity {

    private Button bProfileKpd,bAlokasiPbb,bAlokasiPgj,bJdwlAntrianSidang,bLogoutKpd;
    private static final String TAG = KpdMainMenu.class.getSimpleName();
    private TextView namaKpd,idKpd;
    SessionManager sessionManager;
    String getId,getLevel;
    private static String URL_READ_KPD_MM = "http://10.0.2.2/ta/kpd/read_detail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_main_menu);

        bProfileKpd         = findViewById(R.id.buttonProfileKpd);
        bAlokasiPbb         = findViewById(R.id.buttonAlokasiPbbTA);
        bAlokasiPgj         = findViewById(R.id.buttonAlokasiPgjTA);
        bJdwlAntrianSidang  = findViewById(R.id.buttonJdwlAntrianSeminar);
        bLogoutKpd          = findViewById(R.id.buttonLogOutKpd);

        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();

        namaKpd     = findViewById(R.id.textviewNamaKpdMenu);
        idKpd       = findViewById(R.id.textviewUserIdKaprodiMenu);

//        HashMap<String,String> user = sessionManager.getUserDetail();
//        getId = user.get(sessionManager.ID);
//        getLevel = user.get(sessionManager.LEVEL);

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");
        getLevel = getSharedPreferences("user",MODE_PRIVATE).getString("level", "empty");

        bProfileKpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KpdMainMenu.this,KpdProfile.class);
                startActivity(intent);
            }
        });

        bAlokasiPbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( KpdMainMenu.this, KpdAlokasiPbb.class);
                startActivity(intent);
            }
        });

        bAlokasiPgj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( KpdMainMenu.this, KpdAlokasiPgj.class);
                startActivity(intent);
            }
        });

        bJdwlAntrianSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( KpdMainMenu.this,KpdJdwlAntrianSidang.class);
                startActivity(intent);
            }
        });

        bLogoutKpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sessionManager.logout();
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                logout();
                editor.putString("level", "empty");
                editor.putString("user", "empty");
                editor.putString("name", "empty");
                editor.putString("token", "empty");
                editor.apply();
                Intent intent = new Intent(KpdMainMenu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void logout() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.KpdLogout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(KpdMainMenu.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdMainMenu.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KpdMainMenu.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("id",getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.KpdReadMM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String strName = object.getString("name").trim();
                                    String strId = object.getString("id").trim();
//                                    String strLevel = object.getString("level").trim();

                                    namaKpd.setText(strName);
                                    idKpd.setText(strId);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(KpdMainMenu.this, "Error Reading Detail" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(KpdMainMenu.this,"Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id",getId);
                params.put("level",getLevel);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume(){
        super.onResume();
        getUserDetail();
    }

}
