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

import com.okedroid.apktaichsan.AdmVerSemhas;
import com.okedroid.apktaichsan.Model.AdmVerSemhasModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class AdmVerSemhasAdapter extends RecyclerView.Adapter<AdmVerSemhasAdapter.AdmVerSemhasViewHolder> {
    ArrayList<AdmVerSemhasModel> dataList;
    Context context;

    public AdmVerSemhasAdapter(ArrayList<AdmVerSemhasModel>dataList,Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdmVerSemhasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_adm_ver_semhas,viewGroup, false);
        return new AdmVerSemhasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdmVerSemhasViewHolder AdmVerSemhasViewHolder, final int i) {
        AdmVerSemhasViewHolder.namaMhs.setText(dataList.get(i).GetNama());
        AdmVerSemhasViewHolder.nobpMhs.setText(dataList.get(i).GetNobp());
        AdmVerSemhasViewHolder.judulMhs.setText(dataList.get(i).GetJudul());

        AdmVerSemhasViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdmVerSemhas.class);
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

    public class AdmVerSemhasViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs , judulMhs , nobpMhs;
        CardView mainLayout;

        public AdmVerSemhasViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs = itemView.findViewById(R.id.namaMhsAdmSemhas);
            judulMhs= itemView.findViewById(R.id.judulTAAdmSemhas);
            nobpMhs = itemView.findViewById(R.id.nobpAdmSemhas);
            mainLayout = itemView.findViewById(R.id.mainLayoutAdmSemhas);
        }
    }
}
