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

public class MhsKompre extends AppCompatActivity {

    private TextView judulta, jadwalta, pgj1, pgj2, shift;
    String getId;
    private static String URL_READ_KOMPRE = "http://10.0.2.2/ta/mhs/read_sidang.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_kompre);

        judulta = findViewById(R.id.tv_profKompre_judul_desc);
        jadwalta = findViewById(R.id.tv_profKompre_jdwl_desc);
        pgj1 = findViewById(R.id.tv_ProfKompre_pgj1_desc);
        pgj2 = findViewById(R.id.tv_ProfKompre_pgj2_desc);
        shift = findViewById(R.id.tv_profKompre_jdwl_shift_desc);

        getId = getSharedPreferences("user", MODE_PRIVATE).getString("user", "empty");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsReadKompre,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String strJudul = object.getString("judulta").trim();
                                String strJadwal = object.getString("tgl").trim();
                                String strPgj1 = object.getString("npgj1").trim();
                                String strPgj2 = object.getString("npgj2").trim();
                                String strShift = object.getString("shift").trim();

                                judulta.setText(strJudul);
                                jadwalta.setText(strJadwal);
                                pgj1.setText(strPgj1);
                                pgj2.setText(strPgj2);
                                shift.setText(strShift);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsKompre.this, "Error Reading Detail:" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MhsKompre.this, "Error Reading Detail!" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
