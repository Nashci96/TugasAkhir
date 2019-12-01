package com.okedroid.apktaichsan;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
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

public class MhsMainMenu extends AppCompatActivity {

    private Button bProfileMhs,bPengajuanTopik,bKendaliBimbinganMhs,bTugasAkhirMhs,bSidangMhs,bLogOutMhs;
    private static final String TAG = MhsMainMenu.class.getSimpleName();
    private TextView namaMhs,bpMhs;
    SessionManager sessionManager;
    String getId,getLevel;
    private static String URL_READ_MHS_MM = "http://10.0.2.2/ta/mhs/read_detail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_main_menu);

        bProfileMhs             = findViewById(R.id.buttonMhsProfile);
        bPengajuanTopik         = findViewById(R.id.buttonPengajuanTopik);
        bKendaliBimbinganMhs    = findViewById(R.id.buttonKendaliBimbinganMhs);
        bTugasAkhirMhs          = findViewById(R.id.buttonTugasAkhirMhs);
        bSidangMhs              = findViewById(R.id.buttonSidangMhs);
        bLogOutMhs              = findViewById(R.id.buttonMhsLogOut);

        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();

//        final Context context = this;
        namaMhs     = findViewById(R.id.textviewNameMhsMenu);
        bpMhs       = findViewById(R.id.textViewNoBPMhsMenu);

        /*HashMap<String,String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        getLevel = user.get(sessionManager.LEVEL);*/

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");
        getLevel = getSharedPreferences("user",MODE_PRIVATE).getString("level", "empty");


        bProfileMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsMainMenu.this,MhsProfile.class);
                startActivity(intent);
            }
        });

        bPengajuanTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsMainMenu.this,MhsPengajuanTopik.class);
                startActivity(intent);
            }
        });

        bKendaliBimbinganMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsMainMenu.this,MhsMenuKendaliBimbingan.class);
                startActivity(intent);
            }
        });

        bTugasAkhirMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsMainMenu.this,MhsTugasAkhir.class);
                startActivity(intent);
            }
        });

        bSidangMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsMainMenu.this,MhsSidang.class);
                startActivity(intent);
            }
        });

        bLogOutMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                logout();
                editor.putString("level", "empty");
                editor.putString("user" , "empty");
                editor.putString("name" , "empty");
                editor.putString("token", "empty");
                editor.apply();

                Intent intent = new Intent(MhsMainMenu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void logout() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsLogout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(MhsMainMenu.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsMainMenu.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsMainMenu.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsReadMM,
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
                                    String strBP = object.getString("id").trim();

                                    namaMhs.setText(strName);
                                    bpMhs.setText(strBP);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(MhsMainMenu.this, "Error Reading Detail" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MhsMainMenu.this,"Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id",getId);
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
