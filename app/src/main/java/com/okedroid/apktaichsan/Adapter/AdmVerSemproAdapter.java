package com.okedroid.apktaichsan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.okedroid.apktaichsan.AdmVerSempro;
import com.okedroid.apktaichsan.Model.AdmVerSemproModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class AdmVerSemproAdapter extends RecyclerView.Adapter<AdmVerSemproAdapter.AdmVerSemproViewHolder> {
    ArrayList<AdmVerSemproModel> dataList;
    Context context;

    public AdmVerSemproAdapter(ArrayList<AdmVerSemproModel>dataList,Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdmVerSemproViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_adm_ver_sempro,viewGroup, false);
        return new AdmVerSemproViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdmVerSemproViewHolder AdmVerSemproViewHolder, final int i) {
        AdmVerSemproViewHolder.namaMhs.setText(dataList.get(i).GetNama());
        AdmVerSemproViewHolder.nobpMhs.setText(dataList.get(i).GetNobp());
        AdmVerSemproViewHolder.judulMhs.setText(dataList.get(i).GetJudul());

        AdmVerSemproViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdmVerSempro.class);
                intent.putExtra("namaMhs",dataList.get(i).GetNama());
                intent.putExtra("noBp",dataList.get(i).GetNobp());
                intent.putExtra("judulTA",dataList.get(i).GetJudul());
                intent.putExtra("nama_pbb_1",dataList.get(i).GetNamaPbb1());
                intent.putExtra("nama_pbb_2",dataList.get(i).GetNamaPbb2());
                intent.putExtra("nama_pgj_1",dataList.get(i).GetNamaPgj1());
                intent.putExtra("nama_pgj_2",dataList.get(i).GetNamaPgj2());
                intent.putExtra("tgl",dataList.get(i).GetTgl());
                intent.putExtra("shift",dataList.get(i).GetShift());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList!=null) ? dataList.size() : 0;
    }

    public class AdmVerSemproViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs , judulMhs , nobpMhs;
        CardView mainLayout;

        public AdmVerSemproViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs = itemView.findViewById(R.id.namaMhsAdmSempro);
            judulMhs= itemView.findViewById(R.id.judulTAAdmSempro);
            nobpMhs = itemView.findViewById(R.id.nobpAdmSempro);
            mainLayout = itemView.findViewById(R.id.mainLayoutAdmSempro);
        }
    }
}
