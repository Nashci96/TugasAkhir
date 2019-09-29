package com.okedroid.apktaichsan;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

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


public class LoginActivityMhs extends AppCompatActivity {

    private EditText etNoBp,etPassMhs;
    private Button bLoginMhs,bLoginOpt;
    private TextView tvRespon;
    private ProgressBar loadingmhs;
//    private static String URL_LOGIN_MHS = "192.168.1.114/ta/mhs/login.php";
    private static String URL_LOGIN_MHS = "http://10.0.2.2/ta/mhs/login.php";
//    SessionManager sessionManager;

    String id;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mhs);

//        sessionManager = new SessionManager(this);

        etNoBp      = findViewById(R.id.et_nobp);
        etPassMhs   = findViewById(R.id.et_passmhs);
        bLoginMhs   = findViewById(R.id.buttonLoginMhs);
        bLoginOpt   = findViewById(R.id.buttonLoginOptMhs);
        tvRespon    = findViewById(R.id.tvrespon);
        loadingmhs    = findViewById(R.id.loadingmhs);
//        tvLoginMhs  = findViewById(R.id.tvLoginMhs);

        bLoginMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id    = etNoBp.getText().toString().trim();
                pass = etPassMhs.getText().toString().trim();

                if (!id.isEmpty() || !pass.isEmpty()){
                    Login(id,pass);
                } else {
                    etNoBp.setError("Please Insert Email");
                    etPassMhs.setError("Please insert password");
                }
            }
        });

        bLoginOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityMhs.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(final String id,final String pass) {
        loadingmhs.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.LoginMhs,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String id = object.getString("id").trim();
                                    String level = object.getString("level").trim();

                                    SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("user",id);
                                    editor.putString("level", level);
                                    editor.putString("name",name);
                                    editor.apply();
                                    //
                                    Intent intent = new Intent(LoginActivityMhs.this, MhsMainMenu.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("id", id);
                                    intent.putExtra("level", level);
                                    startActivity(intent);
                                    finish();

                                    loadingmhs.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loadingmhs.setVisibility(View.GONE);
                            Toast.makeText(LoginActivityMhs.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingmhs.setVisibility(View.GONE);
                        Toast.makeText(LoginActivityMhs.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("nobp",id);
                params.put("pass",pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}


