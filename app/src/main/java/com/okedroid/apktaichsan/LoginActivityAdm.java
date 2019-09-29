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

public class LoginActivityAdm extends AppCompatActivity {

    private String id,pass;
    private EditText etUserIdAdm,etPassAdm;
    private Button bLoginAdm,bLoginOptAdm;
    private static String URL_LOGIN_ADM = "http://10.0.2.2/ta/adm/login.php";
//    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_adm);

        etUserIdAdm = findViewById(R.id.et_useridadm);
        etPassAdm   = findViewById(R.id.et_passadm);
        bLoginAdm   = findViewById(R.id.buttonLoginAdm);
        bLoginOptAdm= findViewById(R.id.buttonLoginOptAdm);

        bLoginAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id   = etUserIdAdm.getText().toString().trim();
                pass     = etPassAdm.getText().toString().trim();

                if (!id.isEmpty() || !pass.isEmpty()){
                    Login(id,pass);
                } else {
                    etPassAdm.setError("please insert pass");
                    etUserIdAdm.setError("please insert id");
                }
            }
        });

        bLoginOptAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityAdm.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(final String id, final String pass) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.LoginAdm,
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

//                                    sessionManager.createSession(name, id, level);

                                    SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("user",id);
                                    editor.putString("level", level);
                                    editor.putString("name",name);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivityAdm.this, AdmMainMenu.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("id", id);
                                    intent.putExtra("level",level);

                                    startActivity(intent);
                                    finish();

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivityAdm.this, "error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivityAdm.this,"error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("pass",pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
