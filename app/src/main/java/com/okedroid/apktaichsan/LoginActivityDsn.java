package com.okedroid.apktaichsan;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityDsn extends AppCompatActivity {

    String id;
    String pass;

    private EditText etNIP,etPassDsn;
    private Button bLoginDsn,bLoginOptDsn;

//    private static String URL_LOGIN_DSN = "192.168.1.114/ta/dsn/login.php";
    private static String URL_LOGIN_DSN = "http://10.0.2.2/ta/dsn/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dsn);

        etNIP       = findViewById(R.id.et_nip);
        etPassDsn   = findViewById(R.id.et_passdsn);

        bLoginDsn   = findViewById(R.id.buttonLoginDsn);
        bLoginOptDsn= findViewById(R.id.buttonLogInOptDsn);

        bLoginDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id     = etNIP.getText().toString().trim();
                pass = etPassDsn.getText().toString().trim();

                if (!id.isEmpty() || !pass.isEmpty()){
                    Login(id,pass);
                } else {
                    etNIP.setError("Please insert NIP");
                    etPassDsn.setError("please insert password");
                }
            }
        });

        bLoginOptDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivityDsn.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(final String id, final String pass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.LoginDsn,
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
                                    editor.putString("level",level);
                                    editor.putString("name",name);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivityDsn.this, DsnMainMenu.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("id", id);
                                    intent.putExtra("level",level);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivityDsn.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivityDsn.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("nip",id);
                params.put("pass",pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
