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

import com.okedroid.apktaichsan.MhsProgressBimbingan;
import com.okedroid.apktaichsan.Model.BimbinganMhsListModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class BimbinganMhsListAdapter extends RecyclerView.Adapter<BimbinganMhsListAdapter.BimbinganMhsListViewHolder> {

    ArrayList<BimbinganMhsListModel> datalist;
    Context context;

    public BimbinganMhsListAdapter(ArrayList<BimbinganMhsListModel> datalist, Context context){
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public BimbinganMhsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_bimbingan_mhs,viewGroup,false);
        return new BimbinganMhsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BimbinganMhsListViewHolder bimbinganMhsViewHolder, final int i) {
        bimbinganMhsViewHolder.tgl.setText(datalist.get(i).GetTgl());
        bimbinganMhsViewHolder.judulta.setText(datalist.get(i).GetJudulta());

        bimbinganMhsViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MhsProgressBimbingan.class);
                intent.putExtra("nama",datalist.get(i).GetNama());
                intent.putExtra("nobp",datalist.get(i).GetNobp());
                intent.putExtra("progress",datalist.get(i).GetProgress());
                intent.putExtra("judulta",datalist.get(i).GetJudulta());
                intent.putExtra("tgl",datalist.get(i).GetTgl());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0;
    }

    public class BimbinganMhsListViewHolder extends RecyclerView.ViewHolder {
        TextView tgl,judulta;
        CardView mainLayout;

        public BimbinganMhsListViewHolder(@NonNull View itemView) {
            super(itemView);

            tgl     =   itemView.findViewById(R.id.tgl_bimbingan_mhs);
            judulta =   itemView.findViewById(R.id.jdl_bimbingan_mhs);
            mainLayout  =   itemView.findViewById(R.id.mainLayout_bimbingan_mhs);

        }
    }
}
