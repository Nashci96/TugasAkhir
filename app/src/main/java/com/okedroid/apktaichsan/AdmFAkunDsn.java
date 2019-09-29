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
import com.okedroid.apktaichsan.Adapter.FreezeDsnAdapter;
import com.okedroid.apktaichsan.Model.FreezeDsnModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdmFAkunDsn extends AppCompatActivity {

    RecyclerView recyclerView;
    FreezeDsnAdapter adapter;
    ArrayList<FreezeDsnModel> freezeDsnModelArrayList;
    String GET_LIST_DSN_F_URL ="http://10.0.2.2/ta/adm/listAkunDsn.php";
    String FRZ_DSN_ACCOUNT_URL = "http://10.0.2.2/ta/adm/FreezeDsn.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_fakun_dsn);

        requestQueue    = Volley.newRequestQueue(getBaseContext());
        recyclerView    = findViewById(R.id.recyclerViewFDsn);
        freezeDsnModelArrayList = new ArrayList<>();
        adapter = new FreezeDsnAdapter(freezeDsnModelArrayList,getBaseContext(),AdmFAkunDsn.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getListDsnF();
    }

    private void getListDsnF() {
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

                                FreezeDsnModel freezeDsnModel = new FreezeDsnModel(namaDsn, nipDsn,statusDsn);
                                freezeDsnModelArrayList.add(freezeDsnModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmFAkunDsn.this, e + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmFAkunDsn.this, error + "", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    public void showDialog(final String nipDsn) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Bekukan Akun?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk Bekukan \n Dosen dengan no NIP : "+nipDsn+"!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        FrzDsn(nipDsn);
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

    private void FrzDsn(final String nipDsn) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmFDsnAccount,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(AdmFAkunDsn.this, "Success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(AdmFAkunDsn.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmFAkunDsn.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nip",nipDsn);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
