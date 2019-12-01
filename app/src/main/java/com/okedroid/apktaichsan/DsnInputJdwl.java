package com.okedroid.apktaichsan;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okedroid.apktaichsan.Adapter.JadwalDosenAdapter;
import com.okedroid.apktaichsan.Model.JadwalDosenModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DsnInputJdwl extends AppCompatActivity {

    LinearLayout blurLayout;
    CardView inputLayout;
    Button confirmButton;
    CircleImageView dsnImg, addButton;
    TextView namaDsn, nikDsn, noData;
    EditText tanggalET;
    Spinner shiftSpinner;
    RecyclerView inputListRV;
    ImageView closeInputLayoutButton;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Calendar calendar;
    int shiftId;
    JadwalDosenAdapter adapter;
    ArrayList<JadwalDosenModel> jadwalDosenModelArrayList;
    String getId,getName;
    String INPUT_JDWL_DSN_URL = "http://10.0.2.2/ta/dsn/inputJadwal.php";
    String GET_JDWL_DSN_URL = "http://10.0.2.2/ta/dsn/ListJadwal.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_input_jdwl);

        blurLayout = findViewById(R.id.blurLayout);
        confirmButton = findViewById(R.id.confirmButton);
        inputLayout = findViewById(R.id.inputLayout);
        dsnImg = findViewById(R.id.imgDsn);
        addButton = findViewById(R.id.addJadwalButton);
        namaDsn = findViewById(R.id.namaDsnInputJdwl);
        nikDsn = findViewById(R.id.nipDsnInputJadwal);
        noData = findViewById(R.id.noDataTV);
        tanggalET = findViewById(R.id.inputTanggalET);
        shiftSpinner = findViewById(R.id.spinnerShift);
        inputListRV = findViewById(R.id.inputJadwalDsnRV);
        closeInputLayoutButton = findViewById(R.id.closeInputLayoutButton);
        calendar = Calendar.getInstance(); //tanggal sekarang
        requestQueue = Volley.newRequestQueue(getBaseContext());

        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");
        getName = getSharedPreferences("user",MODE_PRIVATE).getString("name","empty");
        namaDsn.setText(getName);
        nikDsn.setText(getId);

        jadwalDosenModelArrayList = new ArrayList<>();
        adapter = new JadwalDosenAdapter(jadwalDosenModelArrayList, getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        inputListRV.setLayoutManager(layoutManager);
        inputListRV.setAdapter(adapter);

        blurLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurLayout.setVisibility(View.GONE);
                inputLayout.setVisibility(View.GONE);
            }
        });

        closeInputLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurLayout.setVisibility(View.GONE);
                inputLayout.setVisibility(View.GONE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = tanggalET.getText().toString();
                addItem(tanggal, shiftId);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurLayout.setVisibility(View.VISIBLE);
                inputLayout.setVisibility(View.VISIBLE);
            }
        });

        tanggalET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DsnInputJdwl.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String day = String.format("%02d",dayOfMonth);
                String monthView = String.format("%02d",month);
                tanggalET.setText(day+"/"+monthView+"/"+String.valueOf(year));
            }
        };

        shiftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shiftId = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getJadwalDosen(getId); //anjeng

    }

    public void addItem(final String tanggal, final int shiftId){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.DsnInputJadwal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if (jsonObject.getString("success").equals("1")){
                                String shift = "Shift "+shiftId;
                                Toast.makeText(getBaseContext(), tanggal+shift+"", Toast.LENGTH_SHORT).show();
                                JadwalDosenModel jadwalDosenModel = new JadwalDosenModel(tanggal, shift);
                                jadwalDosenModelArrayList.add(jadwalDosenModel);
                                adapter.notifyDataSetChanged();
                                blurLayout.setVisibility(View.GONE);
                                inputLayout.setVisibility(View.GONE);
                            }else {
                                Toast.makeText(DsnInputJdwl.this, "Input jadwal gagal", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                params.put("tgl", tanggal);
                params.put("shift",String.valueOf(shiftId));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getJadwalDosen(final String nip){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.DsnListJdwl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String tanggal = jsonObject.getString("tgl");
                                String shift = "Shift"+jsonObject.getString("shift");
                                JadwalDosenModel jadwalDosenModel = new JadwalDosenModel(tanggal, shift);
                                jadwalDosenModelArrayList.add(jadwalDosenModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DsnInputJdwl.this, e+"", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DsnInputJdwl.this,error+ "", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",nip);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

}
