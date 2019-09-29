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

public class AdmRegAkunDsn extends AppCompatActivity {

    private EditText name,id,password,status;
    private Button btn_regist;
    private static String URL_REGIST_DSN = "http://10.0.2.2/ta/adm/regDsn.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_reg_akun_dsn);

        name        =   findViewById(R.id.nama_dsn_reg);
        id          =   findViewById(R.id.nip_reg);
        password    =   findViewById(R.id.pass_dsn_reg);
        status      =   findViewById(R.id.status_dsn_reg);
        btn_regist  =   findViewById(R.id.b_reg_dsn);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist(){

        final String name       = this.name.getText().toString().trim();
        final String id         = this.id.getText().toString().trim();
        final String password   = this.password.getText().toString().trim();
        final String status     = this.status.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmRegistAkunDsn,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(AdmRegAkunDsn.this,"Register Success!",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(AdmRegAkunDsn.this,"Register Error! " + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmRegAkunDsn.this,"Register Error !" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("name",name);
                params.put("id",id);
                params.put("pass",password);
                params.put("class",status);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
