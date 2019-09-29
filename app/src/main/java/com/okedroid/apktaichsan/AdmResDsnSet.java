package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class AdmResDsnSet extends AppCompatActivity {
    TextView namaDsn,nipDsn,statusDsn;
    String namaDsnStr,nipDsnStr,statusDsnStr,passBaruStr;
    EditText passbaru;
    Button bUpdate;
    private  static String URL_RES_DSN = "http://10.0.2.2/ta/adm/resetDsn.php" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_res_dsn_set);

        namaDsnStr = getIntent().getStringExtra("namaDsn");
        nipDsnStr = getIntent().getStringExtra("nipDsn");
        statusDsnStr = getIntent().getStringExtra("statusDsn");
        passBaruStr = getIntent().getStringExtra("passDsn");

        namaDsn = findViewById(R.id.namaDsn_res_dsn_set);
        nipDsn = findViewById(R.id.nipDsn_res_dsn_set);
        statusDsn = findViewById(R.id.statusDsn_res_dsn_set);
        passbaru = findViewById(R.id.et_pass_dsn_res_set);
        bUpdate = findViewById(R.id.b_update_res_dsn);

        namaDsn.setText(namaDsnStr);
        nipDsn.setText(nipDsnStr);
        statusDsn.setText(statusDsnStr);
        passbaru.setText(passBaruStr);

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetDsn(passBaruStr);
            }
        });

    }

    private void ResetDsn(String passBaruStr) {
        final String nipDsn = this.nipDsn.getText().toString().trim();
        final String passDsn = this.passbaru.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmResetAkunDsn,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(AdmResDsnSet.this, "success", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmResDsnSet.this, "1.Register Error" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmResDsnSet.this, "1.Register Error" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("nip",nipDsn);
                params.put("pass",passDsn);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
