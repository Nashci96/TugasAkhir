package com.okedroid.apktaichsan.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okedroid.apktaichsan.KpdListKompreAct;
import com.okedroid.apktaichsan.Model.KpdKompreModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class KpdKompreAdapter extends RecyclerView.Adapter<KpdKompreAdapter.KpdKompreViewHolder> {
    ArrayList<KpdKompreModel> dataList;
    Context context;

    public KpdKompreAdapter(ArrayList<KpdKompreModel>dataList,Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public KpdKompreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_kpd_kompre,viewGroup, false);
        return new KpdKompreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KpdKompreAdapter.KpdKompreViewHolder kpdKompreViewHolder, final int i) {
        kpdKompreViewHolder.namaMhs.setText(dataList.get(i).GetNama());
        kpdKompreViewHolder.nobpMhs.setText(dataList.get(i).GetNobp());
        kpdKompreViewHolder.judulMhs.setText(dataList.get(i).GetJudul());

        kpdKompreViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KpdListKompreAct.class);
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

    public class KpdKompreViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs , judulMhs , nobpMhs;
        CardView mainLayout;

        public KpdKompreViewHolder(@NonNull View itemView) {
            super(itemView);
            namaMhs = itemView.findViewById(R.id.namaMhsKpdKompre);
            judulMhs= itemView.findViewById(R.id.judulTAKpdKompre);
            nobpMhs = itemView.findViewById(R.id.nobpKpdKompre);
            mainLayout= itemView.findViewById(R.id.mainLayoutKpdKompre);
        }
    }
}
