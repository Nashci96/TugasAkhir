package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class MhsProfileTa extends AppCompatActivity {

    private TextView judulta,topikta,pbb1,pbb2;
    String getId;
    private static final String TAG = MhsProfileTa.class.getSimpleName();
    private static String URL_READ_TA = "http://10.0.2.2/ta/mhs/profileta.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_profile_ta);

        judulta     = findViewById(R.id.tv_profTA_judulta_desc);
        topikta     = findViewById(R.id.tv_profTA_topikta_desc);
        pbb1        = findViewById(R.id.tv_profTA_pbb1_desc);
        pbb2        = findViewById(R.id.tv_profTA_pbb2_desc);

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");

        StringRequest stringRequest =   new StringRequest(Request.Method.POST, URL.MhsReadTA,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.i(TAG, response.toString());
                        JSONArray jsonArray = null;
                        try {
//                            JSONObject jsonObject = new JSONObject(response);
////                            String success = jsonObject.getString("success");
//                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            jsonArray = new JSONArray(response);
//                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String strTopikta = object.getString("topikta").trim();
                                    String strJudulta = object.getString("judulta").trim();
                                    String strPbb1 = object.getString("npbb1").trim();
                                    String strPbb2 = object.getString("npbb2").trim();

                                    topikta.setText(strTopikta);
                                    judulta.setText(strJudulta);
                                    pbb1.setText(strPbb1);
                                    pbb2.setText(strPbb2);
                                }
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsProfileTa.this, "Error Reading Detail:" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsProfileTa.this,"Error Reading Detail!"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("id",getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
