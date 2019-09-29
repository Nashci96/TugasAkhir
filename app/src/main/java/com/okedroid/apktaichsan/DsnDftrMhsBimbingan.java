package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okedroid.apktaichsan.Adapter.MahasiswaListAdapter;
import com.okedroid.apktaichsan.Model.MahasiswaListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DsnDftrMhsBimbingan extends AppCompatActivity {

    CircleImageView imgDsn;
    TextView namaDsn, nipDsn;
    RecyclerView recyclerViewMhs;
    MahasiswaListAdapter adapter;
    ArrayList<MahasiswaListModel> mahasiswaListModelArrayList;
    String GET_MHS_LIST_URL = "http://10.0.2.2/ta/dsn/A_Mhs_Bimbingan.php";
    RequestQueue requestQueue;
    String nip,getName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_dftr_mhs_bimbingan);

        requestQueue = Volley.newRequestQueue(getBaseContext());

        recyclerViewMhs = findViewById(R.id.recyclerViewMhs);
        mahasiswaListModelArrayList = new ArrayList<>();
        adapter = new MahasiswaListAdapter(mahasiswaListModelArrayList, getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewMhs.setLayoutManager(layoutManager);
        recyclerViewMhs.setAdapter(adapter);

//        nip = "198611072015041001"; // nnti nip ambil dari server, panggil fungsi get mahasiswa dalam volleynya
        nip = getSharedPreferences("user",MODE_PRIVATE).getString("user","empty");
        getName = getSharedPreferences("user",MODE_PRIVATE).getString("name","empty");

        namaDsn = findViewById(R.id.nama_dosen_dftr_mhs_act);
        nipDsn  = findViewById(R.id.nip_dosen_dftr_mhs_act);

        namaDsn.setText(getName);
        nipDsn.setText(nip);

        getMahasiswa(nip);

    }



    public void getMahasiswa(final String nip){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.DsnListMhsBimbingan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String namaMhs = jsonObject.getString("nama");
                                String noBp = jsonObject.getString("nobp");
                                String deskripsi = jsonObject.getString("deskripsi");
                                String topikTA = jsonObject.getString("topikta");

                                MahasiswaListModel mahasiswaListModel = new MahasiswaListModel(namaMhs, noBp, topikTA, deskripsi);
                                mahasiswaListModelArrayList.add(mahasiswaListModel);

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DsnDftrMhsBimbingan.this, e+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DsnDftrMhsBimbingan.this, error+"", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nip", nip);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
