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

import com.okedroid.apktaichsan.KpdListSemproAct;
import com.okedroid.apktaichsan.Model.KpdSemproModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class KpdSemproAdapter extends RecyclerView.Adapter<KpdSemproAdapter.KpdSemproViewHolder> {
    ArrayList<KpdSemproModel> dataList;
    Context context;

    public KpdSemproAdapter(ArrayList<KpdSemproModel>dataList,Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public KpdSemproViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_kpd_sempro,viewGroup, false);
        return new KpdSemproViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KpdSemproViewHolder kpdSemproViewHolder, final int i) {
        kpdSemproViewHolder.namaMhs.setText(dataList.get(i).GetNama());
        kpdSemproViewHolder.nobpMhs.setText(dataList.get(i).GetNobp());
        kpdSemproViewHolder.judulMhs.setText(dataList.get(i).GetJudul());

        kpdSemproViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KpdListSemproAct.class);
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

    public class KpdSemproViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs , judulMhs , nobpMhs;
        CardView mainLayout;

        public KpdSemproViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs = itemView.findViewById(R.id.namaMhsKpdSempro);
            judulMhs= itemView.findViewById(R.id.judulTAKpdSempro);
            nobpMhs = itemView.findViewById(R.id.nobpKpdSempro);
            mainLayout = itemView.findViewById(R.id.mainLayoutKpdSempro);
        }
    }
}
