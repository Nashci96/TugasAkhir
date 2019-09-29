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
import com.okedroid.apktaichsan.Adapter.ActiveDsnAdapter;
import com.okedroid.apktaichsan.Model.ActiveDsnModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdmAAkunDsn extends AppCompatActivity {

    RecyclerView recyclerView;
    ActiveDsnAdapter adapter;
    ArrayList<ActiveDsnModel> activeDsnModelArrayList;
    String GET_LIST_DSN_A_URL ="http://10.0.2.2/ta/adm/listAkunDsnF.php";
    String ACT_DSN_ACCOUNT_URL = "http://10.0.2.2/ta/adm/unFDsn.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aakun_dsn);

        requestQueue    = Volley.newRequestQueue(getBaseContext());
        recyclerView    = findViewById(R.id.recyclerViewADsn);
        activeDsnModelArrayList = new ArrayList<>();
        adapter = new ActiveDsnAdapter(activeDsnModelArrayList,getBaseContext(),AdmAAkunDsn.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getListDsnA();
    }

    private void getListDsnA() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmListDsnF,
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

                                ActiveDsnModel activeDsnModel = new ActiveDsnModel(namaDsn, nipDsn,statusDsn);
                                activeDsnModelArrayList.add(activeDsnModel);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdmAAkunDsn.this, e + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmAAkunDsn.this, error + "", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    public void showDialog(final String nipDsn) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Aktifkan Akun?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk Aktifkan \n Dosen dengan no NIP : "+nipDsn+"!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        ActDsn(nipDsn);
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

    private void ActDsn(final String nipDsn) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.AdmActDsnAccount,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(AdmAAkunDsn.this, "Success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(AdmAAkunDsn.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdmAAkunDsn.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
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
