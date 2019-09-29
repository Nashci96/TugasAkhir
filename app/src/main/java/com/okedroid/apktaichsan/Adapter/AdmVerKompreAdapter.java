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

import com.okedroid.apktaichsan.AdmVerKompre;
import com.okedroid.apktaichsan.Model.AdmVerKompreModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class AdmVerKompreAdapter extends RecyclerView.Adapter<AdmVerKompreAdapter.AdmVerKompreViewHolder> {
    ArrayList<AdmVerKompreModel> dataList;
    Context context;

    public AdmVerKompreAdapter(ArrayList<AdmVerKompreModel>dataList,Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdmVerKompreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_adm_ver_kompre,viewGroup, false);
        return new AdmVerKompreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdmVerKompreViewHolder AdmVerKompreViewHolder, final int i) {
        AdmVerKompreViewHolder.namaMhs.setText(dataList.get(i).GetNama());
        AdmVerKompreViewHolder.nobpMhs.setText(dataList.get(i).GetNobp());
        AdmVerKompreViewHolder.judulMhs.setText(dataList.get(i).GetJudul());

        AdmVerKompreViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdmVerKompre.class);
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

    public class AdmVerKompreViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs , judulMhs , nobpMhs;
        CardView mainLayout;

        public AdmVerKompreViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs = itemView.findViewById(R.id.namaMhsAdmKompre);
            judulMhs= itemView.findViewById(R.id.judulTAAdmKompre);
            nobpMhs = itemView.findViewById(R.id.nobpAdmKompre);
            mainLayout = itemView.findViewById(R.id.mainLayoutAdmKompre);
        }
    }
}
