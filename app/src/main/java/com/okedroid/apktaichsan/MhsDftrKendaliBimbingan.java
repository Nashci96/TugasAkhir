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
import com.okedroid.apktaichsan.Adapter.BimbinganMhsListAdapter;
import com.okedroid.apktaichsan.Model.BimbinganMhsListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MhsDftrKendaliBimbingan extends AppCompatActivity {

    CircleImageView imgMhs;
    TextView namaMhs,bpMhs;
    RecyclerView recyclerViewMhs;
    BimbinganMhsListAdapter adapter;
    ArrayList<BimbinganMhsListModel> bimbinganMhsListModelArrayList;
    String GET_LIST_BIMBINGAN_URL = "http://10.0.2.2/ta/mhs/ListBimbingan.php";
    RequestQueue requestQueue;
    String getBp,getName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_dftr_kendali_bimbingan);

        requestQueue    = Volley.newRequestQueue(getBaseContext());
        recyclerViewMhs = findViewById(R.id.recyclerViewMhs_kendali_bimbingan);
        bimbinganMhsListModelArrayList =  new ArrayList<>();
        adapter =   new BimbinganMhsListAdapter(bimbinganMhsListModelArrayList,getBaseContext());
        RecyclerView.LayoutManager layoutManager    =   new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerViewMhs.setLayoutManager(layoutManager);
        recyclerViewMhs.setAdapter(adapter);

        getBp = getSharedPreferences("user",MODE_PRIVATE).getString("user","empty");
        getName = getSharedPreferences("user",MODE_PRIVATE).getString("name","empty");

        namaMhs = findViewById(R.id.nama_mhs_bimbingan_list);
        bpMhs   = findViewById(R.id.bp_mhs_bimbingan_list);

        namaMhs.setText(getName);
        bpMhs.setText(getBp);

        getBimbingan(getBp);
    }

    private void getBimbingan(final String getBp) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.MhsListBimbingan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nama = jsonObject.getString("nama");
                                String nobp = jsonObject.getString("nobp");
                                String progress = jsonObject.getString("progress");
                                String judulta = jsonObject.getString("judulta");
                                String tgl      = jsonObject.getString("date");

                                BimbinganMhsListModel bimbinganMhsListModel = new BimbinganMhsListModel(nama, nobp, progress, judulta,tgl);
                                bimbinganMhsListModelArrayList.add(bimbinganMhsListModel);

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MhsDftrKendaliBimbingan.this, e+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MhsDftrKendaliBimbingan.this, error+"", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getBp);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
