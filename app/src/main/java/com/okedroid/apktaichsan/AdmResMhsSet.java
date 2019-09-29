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

public class AdmResMhsSet extends AppCompatActivity {
    TextView namaMhs,nobpMhs;
    String namaMhsStr,nobpMhsStr,passBaruStr;
    EditText passbaru;
    Button bUpdate;
    private static String URL_RES_MHS = "http://10.0.2.2/ta/adm/resetMhs.php" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_res_mhs_set);

        namaMhsStr  =   getIntent().getStringExtra("namaMhs");
        nobpMhsStr  =   getIntent().getStringExtra("nobpMhs");
        passBaruStr =   getIntent().getStringExtra("passMhs");

        namaMhs     = findViewById(R.id.namaMhs_res_mhs_set);
        nobpMhs     = findViewById(R.id.nobpMhs_res_mhs_set);
        passbaru    = findViewById(R.id.et_pass_mhs_res_set);
        bUpdate     = findViewById(R.id.b_update_res_mhs);

        namaMhs.setText(namaMhsStr);
        nobpMhs.setText(nobpMhsStr);
        passbaru.setText(passBaruStr);

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetMhs();
            }
        });

    }

    private void ResetMhs() {
        final String nobpMhs    =   this.nobpMhs.getText().toString().trim();
        final String passMhs    =   this.passbaru.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmResetAkunMhs,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(AdmResMhsSet.this, "success", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmResMhsSet.this, "1.Register Error" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmResMhsSet.this, "1.Register Error" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("nobp",nobpMhs);
                params.put("pass",passMhs);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
