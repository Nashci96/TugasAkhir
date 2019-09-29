package com.okedroid.apktaichsan;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
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

public class AdmMainMenu extends AppCompatActivity {

    private Button bProfileAdm,bRegAkun,bRegSidang,bJdwlAntrianAdm,bPengaturanAkun,bLogOutAdm;
    private static final String TAG = AdmMainMenu.class.getSimpleName();
    private TextView namaAdm,idAdm;
    SessionManager sessionManager;
    String getId,getLevel;
    private static String URL_READ_ADM_MM = "http://10.0.2.2/ta/adm/read_detail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_main_menu);

        bProfileAdm         = findViewById(R.id.buttonProfileAdm);
        bRegAkun            = findViewById(R.id.buttonRegAkun);
        bRegSidang          = findViewById(R.id.buttonRegSidang);
        bJdwlAntrianAdm     = findViewById(R.id.buttonJdwlAntrianAdm);
        bPengaturanAkun     = findViewById(R.id.buttonPengaturanAkun);
        bLogOutAdm          = findViewById(R.id.buttonLogOutAdm);

        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();

        namaAdm     = findViewById(R.id.textViewNamaAdmHome);
        idAdm       = findViewById(R.id.textViewIDAdmHome);

//        HashMap<String,String> user = sessionManager.getUserDetail();
//        getId = user.get(sessionManager.ID);
//        getLevel = user.get(sessionManager.LEVEL);

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");
        getLevel = getSharedPreferences("user",MODE_PRIVATE).getString("level", "empty");

        bProfileAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmMainMenu.this,AdmProfile.class);
                startActivity(intent);
            }
        });

        bRegAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmMainMenu.this,AdmRegAkun.class);
                startActivity(intent);
            }
        });

        bRegSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdmMainMenu.this,AdmRegSidang.class);
                startActivity(intent);
            }
        });

        bJdwlAntrianAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmMainMenu.this,AdmJdwlAntrian.class);
                startActivity(intent);
            }
        });

        bPengaturanAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmMainMenu.this,AdmPengaturanAkun.class);
                startActivity(intent);
            }
        });

        bLogOutAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sessionManager.logout();
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("level", "empty");
                editor.putString("user", "empty");
                editor.putString("name", "empty");
                editor.apply();
                Intent intent = new Intent(AdmMainMenu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmReadMM,
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

                                    namaAdm.setText(strName);
                                    idAdm.setText(strId);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(AdmMainMenu.this, "Error Reading Detail" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AdmMainMenu.this,"Error Reading Detail"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("id",getId);
//                params.put("level",getLevel);
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
