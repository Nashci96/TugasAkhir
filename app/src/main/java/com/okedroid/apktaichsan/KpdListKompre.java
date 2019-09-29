package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okedroid.apktaichsan.Adapter.KpdKompreAdapter;
import com.okedroid.apktaichsan.Model.KpdKompreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KpdListKompre extends AppCompatActivity {

    RecyclerView recyclerView;
    KpdKompreAdapter adapter;
    ArrayList<KpdKompreModel> kpdKompreModelArrayList;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_list_kompre);

        requestQueue = Volley.newRequestQueue(getBaseContext());

        recyclerView = findViewById(R.id.recyclerViewKpdKompre);
        kpdKompreModelArrayList = new ArrayList<>();
        adapter = new KpdKompreAdapter(kpdKompreModelArrayList,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        getDftrKompre();
    }

    private void getDftrKompre() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ListKompreBerlangsung,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String namaMhs = jsonObject.getString("nama_mhs");
                                String noBp = jsonObject.getString("nobp");
                                String judulTA = jsonObject.getString("judulta");
                                String nama_pbb_1 = jsonObject.getString("nama_pbb_1");
                                String nama_pbb_2 = jsonObject.getString("nama_pbb_2");
                                String nama_pgj_1 = jsonObject.getString("nama_pgj_1");
                                String nama_pgj_2 = jsonObject.getString("nama_pgj_2");
                                String tgl = jsonObject.getString("tgl");
                                String shift = jsonObject.getString("shift");

                                KpdKompreModel kpdKompreModel = new KpdKompreModel(namaMhs,noBp,judulTA,nama_pbb_1,nama_pbb_2,nama_pgj_1,nama_pgj_2,tgl,shift);
                                kpdKompreModelArrayList.add(kpdKompreModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdListKompre.this, e + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KpdListKompre.this, error+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
