package com.okedroid.apktaichsan;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MhsSidang extends AppCompatActivity {

    private Button bReqSemPro,bReqSemHas,bReqKompre;
    private static String URL_REQ_SEMPRO = "http://10.0.2.2/ta/mhs/request_sempro.php";
    private static String URL_REQ_SEMHAS = "http://10.0.2.2/ta/mhs/request_semhas.php";
    private static String URL_REQ_SIDANG = "http://10.0.2.2/ta/mhs/request_sidang.php";

    String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_sidang);

        bReqSemPro = findViewById(R.id.bReqSempro);
        bReqSemHas = findViewById(R.id.bReqSemhas);
        bReqKompre = findViewById(R.id.bRegKompre);


        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");

        bReqSemPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog();
                reqSempro();
            }
        });

        bReqSemHas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqSemhas();
            }
        });

        bReqKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqKompre();
            }
        });

    }

    private void reqKompre() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsReqKompre,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(MhsSidang.this, "Success!", Toast.LENGTH_LONG).show();
                                showDialogAlert(message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsSidang.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsSidang.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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

    private void reqSempro() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsReqSempro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(MhsSidang.this, "Success!", Toast.LENGTH_LONG).show();
                                showDialogAlert(message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsSidang.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsSidang.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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

    private void reqSemhas() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsReqSemhas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message").trim();
                            if (success.equals("1")) {
                                Toast.makeText(MhsSidang.this, "Success!", Toast.LENGTH_LONG).show();
                                showDialogAlert(message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsSidang.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsSidang.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
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

    private void showDialogAlert(String message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Request Message : ");

        alertDialogBuilder
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
