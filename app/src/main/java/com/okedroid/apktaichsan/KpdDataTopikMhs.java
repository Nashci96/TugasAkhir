package com.okedroid.apktaichsan;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.Toast;

public class KpdDataTopikMhs extends AppCompatActivity {

    TextView namaMhs, noBp, topikTA, deskripsi;
    String namaMhsStr, noBpStr, topikTaStr, deskripsiStr;
    List<String> namaDsn = new ArrayList<String>();
    List<String> nipDsn = new ArrayList<String>();
    RequestQueue requestQueue;
    SearchableSpinner spinnerPembimbingI, spinnerPembimbingII;
    String namaPbb1, nipPbb1, namaPbb2, nipPbb2;
    Button confirmButton;
    String GET_ALLOC_PBB_URL ="http://10.0.2.2/ta/kpd/alokasipbb.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_data_topik_mhs);

        namaMhsStr = getIntent().getStringExtra("namaMhs");
        noBpStr = getIntent().getStringExtra("noBp");
        topikTaStr = getIntent().getStringExtra("topikTA");
        deskripsiStr = getIntent().getStringExtra("deskripsi");

        namaMhs = findViewById(R.id.namaMhs_data_topik);
        noBp = findViewById(R.id.noBpMhs_data_topik);
        topikTA = findViewById(R.id.topikTA_data_topik);
        deskripsi = findViewById(R.id.deskripsi_data_topik);
        spinnerPembimbingI = findViewById(R.id.spinnerPembimbingI);
        spinnerPembimbingII = findViewById(R.id.spinnerPembimbingII);
        confirmButton = findViewById(R.id.confirmButton);

        requestQueue = Volley.newRequestQueue(getBaseContext());

        namaMhs.setText(namaMhsStr);
        noBp.setText(noBpStr);
        topikTA.setText(topikTaStr);
        deskripsi.setText(deskripsiStr);

        getDosen();

        ArrayAdapter<String> spimnnerAdapter = new ArrayAdapter<>(KpdDataTopikMhs.this,
               R.layout.spinner_item, namaDsn );

        spinnerPembimbingI.setAdapter(spimnnerAdapter);
        spinnerPembimbingII.setAdapter(spimnnerAdapter);

        spinnerPembimbingI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namaPbb1 = namaDsn.get(position);
                nipPbb1 = nipDsn.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPembimbingII.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namaPbb2 = namaDsn.get(position);
                nipPbb2 = nipDsn.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePembimbing();
            }
        });

    }

    public void getDosen(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/ta/get_dosen.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("nama");
                                String nip = jsonObject.getString("nip");

                                namaDsn.add(name);
                                nipDsn.add(nip);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    public void updatePembimbing(){
        Toast.makeText(this, namaPbb1+nipPbb1+namaPbb2+nipPbb2+"", Toast.LENGTH_SHORT).show();

        final String nobp   =   this.noBp.getText().toString().trim();
        final String npbb1  =   this.nipPbb1;
        final String npbb2  =   this.nipPbb2;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.KpdAlokasiPbb,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(KpdDataTopikMhs.this, "success", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdDataTopikMhs.this, "1.Error" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KpdDataTopikMhs.this, "2.Error" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String>params = new HashMap<>();
                params.put("id",nobp);
                params.put("pbb1",npbb1);
                params.put("pbb2",npbb2);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}


