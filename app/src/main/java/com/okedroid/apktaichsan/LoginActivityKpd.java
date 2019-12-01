package com.okedroid.apktaichsan;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityKpd extends AppCompatActivity {

    private String id,pass;
    private EditText etUseridkpd,etPasskpd;
    private Button bLoginKpd,bLoginOptKpd;
    private static String URL_LOGIN_KPD ="http://10.0.2.2/ta/kpd/login.php";
//    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_kpd);

        etUseridkpd = findViewById(R.id.et_useridkpd);
        etPasskpd   = findViewById(R.id.et_passkpd);
        bLoginKpd   = findViewById(R.id.buttonLoginKpd);
        bLoginOptKpd= findViewById(R.id.buttonLoginOptKpd);

        bLoginKpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id  = etUseridkpd.getText().toString().trim();
                pass     = etPasskpd.getText().toString().trim();

                if (!id.isEmpty() || !pass.isEmpty()){
                    Login(id,pass);
                } else {
                    etUseridkpd.setError("Please insert ID");
                    etPasskpd.setError("Please insert password");
                }
            }
        });

        bLoginOptKpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivityKpd.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(final String id, final String pass) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.LoginKpd,
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
                                    final String id = object.getString("id").trim();
                                    String level = object.getString("level").trim();
                                    final String tokens = object.getString("token").trim();

                                    SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("user",id);
                                    editor.putString("level", level);
                                    editor.putString("name",name);
                                    editor.apply();
                                    //
                                    Log.e("NEW_INACTIVITY_TOKEN",tokens);
                                    FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginActivityKpd.this,
                                            new OnSuccessListener<InstanceIdResult>() {
                                                @Override
                                                public void onSuccess(InstanceIdResult instanceIdResult) {
                                                    String newToken = instanceIdResult.getToken();
                                                    Log.e("newToken",newToken);
                                                    SharedPreferences.Editor editor = getSharedPreferences("prefs",MODE_PRIVATE).edit();
                                                    if (newToken!=null){
                                                        editor.putString("token",newToken);
                                                        editor.apply();
                                                        updateToken(id,newToken);
                                                    }
                                                }
                                            });
                                    //
                                    Intent intent = new Intent(LoginActivityKpd.this, KpdMainMenu.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("id", id);
                                    intent.putExtra("level", level);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivityKpd.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivityKpd.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();
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

    private void updateToken(final String id, final String newToken) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.KpdToken,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(LoginActivityKpd.this, "Success!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivityKpd.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivityKpd.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("id",id);
                params.put("token",newToken);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
