package com.okedroid.apktaichsan;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
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

public class DsnMainMenu extends AppCompatActivity {

    private Button bProfileDsn,bBimbinganDsn,bJadwalDsn,bLogoutDsn;
    private static final String TAG = DsnMainMenu.class.getSimpleName();
    private TextView namaDsn,nipDsn;
    SessionManager sessionManager;
    String getId,getLevel,getName;
    private static String URL_READ_DSN_MM = "http://10.0.2.2/ta/dsn/read_detail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_main_menu);

        bProfileDsn     =findViewById(R.id.buttonProfileDsn);
        bBimbinganDsn   =findViewById(R.id.buttonBimbinganDsn);
        bJadwalDsn      =findViewById(R.id.buttonJadwalDsn);
        bLogoutDsn      =findViewById(R.id.buttonLogoutDsn);

        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();

        namaDsn     = findViewById(R.id.textviewNamaDosenHome);
        nipDsn      = findViewById(R.id.textviewNIPDosenHome);

//        HashMap<String,String> user = sessionManager.getUserDetail();
//        getId = user.get(sessionManager.ID);
//        getLevel = user.get(sessionManager.LEVEL);

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user","empty");
        getLevel = getSharedPreferences("user",MODE_PRIVATE).getString("level","empty");

        bProfileDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DsnMainMenu.this,DsnProfile.class);
                startActivity(intent);
            }
        });

        bBimbinganDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsnMainMenu.this,DsnKendaliBimbingan.class);
                startActivity(intent);
            }
        });

        bJadwalDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsnMainMenu.this,DsnJadwal.class);
                startActivity(intent);
            }
        });

        bLogoutDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sessionManager.logout();
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                logout();
                editor.putString("level","empty");
                editor.putString("user","empty");
                editor.putString("name", "empty");
                editor.putString("token", "empty");
                editor.apply();
                Intent intent = new Intent( DsnMainMenu.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void logout() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.DsnLogout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(DsnMainMenu.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DsnMainMenu.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DsnMainMenu.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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
        progressDialog.setMessage("loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.DsnReadMM,
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
                                    String strNip = object.getString("nip").trim();

                                    namaDsn.setText(strName);
                                    nipDsn.setText(strNip);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(DsnMainMenu.this, "Error Reading Detail" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(DsnMainMenu.this,"error reading detail" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("nip",getId);
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
