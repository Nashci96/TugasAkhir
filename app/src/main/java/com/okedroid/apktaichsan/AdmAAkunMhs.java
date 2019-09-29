package com.okedroid.apktaichsan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okedroid.apktaichsan.Adapter.ActiveMhsAdapter;
import com.okedroid.apktaichsan.Model.ActiveMhsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdmAAkunMhs extends AppCompatActivity {

    RecyclerView recyclerView;
    ActiveMhsAdapter adapter;
    ArrayList<ActiveMhsModel> activeMhsModelArrayList;
    String GET_LIST_MHS_A_URL = "http://10.0.2.2/ta/adm/listAkunMhsF.php";
    String ACT_MHS_ACCOUNT_URL = "http://10.0.2.2/ta/adm/unFMhs.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aakun_mhs);
        
        requestQueue = Volley.newRequestQueue(getBaseContext());
        recyclerView = findViewById(R.id.recyclerViewAMhs);
        activeMhsModelArrayList = new ArrayList<>();
        adapter = new ActiveMhsAdapter(activeMhsModelArrayList,getBaseContext(), AdmAAkunMhs.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        
        getListMhsA();
    }

    private void getListMhsA() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmListMhsF,
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

                                ActiveMhsModel activeMhsModel = new ActiveMhsModel(namaMhs, nobpMhs);
                                activeMhsModelArrayList.add(activeMhsModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmAAkunMhs.this, e + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmAAkunMhs.this, error + "", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    public void showDialog(final String nomorBp) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Aktifkan Akun?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk Aktifkan \nMahasiswa dengan no BP : "+nomorBp+"!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        ActMhs(nomorBp);
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }

    private void ActMhs(final String nobpMhs) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmActMhsAccount,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(AdmAAkunMhs.this, "Success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(AdmAAkunMhs.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmAAkunMhs.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nobp",nobpMhs);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
