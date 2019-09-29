package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okedroid.apktaichsan.Adapter.ResetDsnAdapter;
import com.okedroid.apktaichsan.Model.ResetDsnModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdmResDsn extends AppCompatActivity {

    RecyclerView recyclerViewResDsn;
    ResetDsnAdapter adapter;
    ArrayList<ResetDsnModel> resetDsnModelArrayList;
    String GET_LIST_DSN_URL = "http://10.0.2.2/ta/adm/listAkunDsn.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_res_dsn);

        requestQueue = Volley.newRequestQueue(getBaseContext());

        recyclerViewResDsn = findViewById(R.id.recyclerViewResDsn);
        resetDsnModelArrayList = new ArrayList<>();
        adapter = new ResetDsnAdapter(resetDsnModelArrayList,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerViewResDsn.setLayoutManager(layoutManager);
        recyclerViewResDsn.setAdapter(adapter);

        getListDsn();

    }

    private void getListDsn() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmListDsnA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String namaDsn = jsonObject.getString("namaDsn");
                                String nipDsn = jsonObject.getString("nipDsn");
                                String statusDsn = jsonObject.getString("statusDsn");

                                ResetDsnModel resetDsnModel = new ResetDsnModel(namaDsn, nipDsn, statusDsn);
                                resetDsnModelArrayList.add(resetDsnModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmResDsn.this, e + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmResDsn.this,error+"",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
