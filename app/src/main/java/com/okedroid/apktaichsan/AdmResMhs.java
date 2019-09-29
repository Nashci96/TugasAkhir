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
import com.okedroid.apktaichsan.Adapter.ResetMhsAdapter;
import com.okedroid.apktaichsan.Model.ResetMhsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdmResMhs extends AppCompatActivity {

    RecyclerView recyclerViewResMhs;
    ResetMhsAdapter adapter;
    ArrayList<ResetMhsModel> resetMhsModelArrayList;
    String GET_LIST_MHS_URL = "http://10.0.2.2/ta/adm/listAkunMhs.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_res_mhs);

        requestQueue = Volley.newRequestQueue(getBaseContext());
        recyclerViewResMhs  = findViewById(R.id.recyclerViewResMhs);
        resetMhsModelArrayList = new ArrayList<>();
        adapter = new ResetMhsAdapter(resetMhsModelArrayList,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerViewResMhs.setLayoutManager(layoutManager);
        recyclerViewResMhs.setAdapter(adapter);

        getListMhs();
    }

    private void getListMhs() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmListMhsA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String namaMhs = jsonObject.getString("namaMhs");
                                String nobpMhs = jsonObject.getString("nobpMhs");

                                ResetMhsModel resetMhsModel = new ResetMhsModel(namaMhs, nobpMhs);
                                resetMhsModelArrayList.add(resetMhsModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmResMhs.this, e + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmResMhs.this, error + "", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
