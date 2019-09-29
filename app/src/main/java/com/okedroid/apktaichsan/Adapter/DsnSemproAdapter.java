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


import com.okedroid.apktaichsan.DsnSemproAct;
import com.okedroid.apktaichsan.Model.DsnSemproModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class DsnSemproAdapter extends RecyclerView.Adapter<DsnSemproAdapter.DsnSemproViewHolder> {
    ArrayList<DsnSemproModel> dataList;
    Context context;

    public DsnSemproAdapter(ArrayList<DsnSemproModel>dataList,Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public DsnSemproViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_dsn_sempro,viewGroup, false);
        return new DsnSemproViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsnSemproViewHolder DsnSemproViewHolder, final int i) {
        DsnSemproViewHolder.namaMhs.setText(dataList.get(i).GetNama());
        DsnSemproViewHolder.nobpMhs.setText(dataList.get(i).GetNobp());
        DsnSemproViewHolder.judulMhs.setText(dataList.get(i).GetJudul());

        DsnSemproViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DsnSemproAct.class);
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

    public class DsnSemproViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs , judulMhs , nobpMhs;
        CardView mainLayout;

        public DsnSemproViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs = itemView.findViewById(R.id.namaMhsDsnSempro);
            judulMhs= itemView.findViewById(R.id.judulTADsnSempro);
            nobpMhs = itemView.findViewById(R.id.nobpDsnSempro);
            mainLayout = itemView.findViewById(R.id.mainLayoutDsnSempro);
        }
    }
}
