package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okedroid.apktaichsan.Adapter.TopikListAdapter;
import com.okedroid.apktaichsan.Model.TopikListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class KpdAlokasiPbb extends AppCompatActivity {
    // info kpd
    CircleImageView imgKpd;
    TextView namaKpd,idKpd;
    String getId,getName;
    // RV
    RecyclerView recyclerViewTopik;
    TopikListAdapter adapter;
    ArrayList<TopikListModel> topikListModelsArrayList;
    String GET_TOPIK_LIST_URL = "http://10.0.2.2/ta/kpd/listTopikTa.php";
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_alokasi_pbb);

        requestQueue = Volley.newRequestQueue(getBaseContext());

        recyclerViewTopik = findViewById(R.id.recyclerViewTopik);
        topikListModelsArrayList = new ArrayList<>();
        adapter = new TopikListAdapter(topikListModelsArrayList,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerViewTopik.setLayoutManager(layoutManager);
        recyclerViewTopik.setAdapter(adapter);

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user","empty");
        getName = getSharedPreferences("user",MODE_PRIVATE).getString("name","empty");

        namaKpd = findViewById(R.id.nama_kpd_alokasi_pbb);
        idKpd   = findViewById(R.id.id_kpd_alokasi_pbb);

        namaKpd.setText(getName);
        idKpd.setText(getId);

        getTopik();

    }

    public void getTopik() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.KpdListTopik,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String namaMhs = jsonObject.getString("nama");
                                String noBp = jsonObject.getString("nobp");
                                String deskripsi = jsonObject.getString("deskripsi");
                                String topikTA = jsonObject.getString("topikta");

                                TopikListModel topikListModel = new TopikListModel(namaMhs, noBp, topikTA,deskripsi);
                                topikListModelsArrayList.add(topikListModel);

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KpdAlokasiPbb.this, e + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KpdAlokasiPbb.this,error+"",Toast.LENGTH_LONG).show();
            }
        })
//        {
//            @Override
//            protected Map<String,String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id", id);
//                return params;
//            }
//        }
        ;
        requestQueue.add(stringRequest);
    }
}
