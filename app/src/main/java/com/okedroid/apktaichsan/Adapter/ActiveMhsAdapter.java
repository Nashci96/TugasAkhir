package com.okedroid.apktaichsan.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okedroid.apktaichsan.AdmAAkunMhs;
import com.okedroid.apktaichsan.Model.ActiveMhsModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class ActiveMhsAdapter extends RecyclerView.Adapter<ActiveMhsAdapter.ActiveMhsViewHolder> {

    ArrayList<ActiveMhsModel> datalist;
    Context context;
//    String ACT_MHS_ACCOUNT_URL = "http://10.0.2.2/ta/adm/unFMhs.php";
    AdmAAkunMhs admAAkunMhs;

    public ActiveMhsAdapter(ArrayList<ActiveMhsModel>datalist,Context context, AdmAAkunMhs admAAkunMhs){
        this.datalist = datalist;
        this.context = context;
        this.admAAkunMhs = admAAkunMhs;
    }

    @NonNull
    @Override
    public ActiveMhsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_a_mhs,viewGroup,false);
        return new ActiveMhsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveMhsViewHolder activeMhsViewHolder, final int i) {
        activeMhsViewHolder.namaMhs.setText(datalist.get(i).getNamaMhs());
        activeMhsViewHolder.nobpMhs.setText(datalist.get(i).getNobpMhs());
        activeMhsViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admAAkunMhs.showDialog(datalist.get(i).getNobpMhs());
            }
        });

    }

//    private void showDialog(final int i) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//        // set title dialog
//        alertDialogBuilder.setTitle("Aktifkan Akun?");
//
//        // set pesan dari dialog
//        alertDialogBuilder
//                .setMessage("Klik Ya untuk Aktifkan!")
//                .setIcon(R.mipmap.ic_launcher)
//                .setCancelable(false)
//                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // jika tombol diklik, maka akan menutup activity ini
//                        ActMhs(i);
//                    }
//                })
//                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // jika tombol ini diklik, akan menutup dialog
//                        // dan tidak terjadi apa2
//                        dialog.cancel();
//                    }
//                });
//
//        // membuat alert dialog dari builder
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // menampilkan alert dialog
//        alertDialog.show();
//
//    }

//    private void ActMhs(int i) {
//        final String nobpMhs = this.admAAkunMhs.
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, ACT_MHS_ACCOUNT_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String success = jsonObject.getString("success");
//                            if (success.equals("1")) {
//                                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("nobp",nobpMhs);
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        requestQueue.add(stringRequest);
//
//    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class ActiveMhsViewHolder extends RecyclerView.ViewHolder {
        TextView namaMhs,nobpMhs;
        CardView mainLayout;

        public ActiveMhsViewHolder(@NonNull View itemView) {
            super(itemView);
            namaMhs     =   itemView.findViewById(R.id.namaMhs_i_a_mhs);
            nobpMhs     =   itemView.findViewById(R.id.nobpMhs_i_a_mhs);
            mainLayout  =   itemView.findViewById(R.id.mainLayout_a_mhs);
        }
    }
}
