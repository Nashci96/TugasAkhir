package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DsnKendaliBimbingan extends AppCompatActivity implements View.OnClickListener {

    Button bQRScanner;
    Button bDftrMhsBimbingan;
    EditText etProgress;
    private static String URL_QRS ="http://10.0.2.2/ta/dsn/QRScan.php";
    private IntentIntegrator qrscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_kendali_bimbingan);

        bQRScanner          = findViewById(R.id.buttonQRScanner);
        bDftrMhsBimbingan   = findViewById(R.id.buttonDaftarMhsBimbingan);
        etProgress          = findViewById(R.id.et_progress_bimbingan);

        qrscan = new IntentIntegrator(this);

//        bQRScanner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(DsnKendaliBimbingan.this,DsnQRScanner.class);
////                startActivity(intent);
//            }
//        });

        bQRScanner.setOnClickListener(this);

        bDftrMhsBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DsnKendaliBimbingan.this,DsnDftrMhsBimbingan.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this,"result not found",Toast.LENGTH_LONG).show();
            } else {
                String nobp = result.getContents();
                addQR(nobp);
            }
        }
    }

    private void addQR(final String nobp) {
        final String progress =this.etProgress.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.DsnQRScan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success =jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(DsnKendaliBimbingan.this, "Scan Berhasil Disimpan", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DsnKendaliBimbingan.this, "Scan Gagal Disimpan"+e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DsnKendaliBimbingan.this,"Scan Gagal Disimpan"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String>params =new HashMap<>();
                params.put("nobo",nobp);
                params.put("progress",progress);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        qrscan.initiateScan();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
