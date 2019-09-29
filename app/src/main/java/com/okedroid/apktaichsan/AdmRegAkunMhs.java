package com.okedroid.apktaichsan;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdmRegAkunMhs extends AppCompatActivity {

    private EditText nama,id,pass;
    private Button btn_regist;
    private static String URL_REGIST = "http://10.0.2.2/ta/adm/regMhs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_reg_akun_mhs);

        id        = findViewById(R.id.nobp_reg);
        nama      = findViewById(R.id.nama_mhs_reg);
        pass      = findViewById(R.id.pass_mhs_reg);
        btn_regist= findViewById(R.id.bRegAkunMhs);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist() {
        final String id     = this.id.getText().toString().trim();
        final String nama   = this.nama.getText().toString().trim();
        final String pass   = this.pass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmRegistAkunMhs,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(AdmRegAkunMhs.this, "success", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmRegAkunMhs.this, "1.Register Error" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AdmRegAkunMhs.this, "1.Register Error" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String>params = new HashMap<>();
                params.put("name",nama);
                params.put("id",id);
                params.put("pass",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
