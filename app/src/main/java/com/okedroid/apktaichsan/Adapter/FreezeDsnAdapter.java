package com.okedroid.apktaichsan.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okedroid.apktaichsan.AdmFAkunDsn;
import com.okedroid.apktaichsan.Model.FreezeDsnModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class FreezeDsnAdapter extends RecyclerView.Adapter<FreezeDsnAdapter.FreezeDsnViewHolder> {

    ArrayList<FreezeDsnModel> datalist;
    Context context;
    AdmFAkunDsn admFAkunDsn;

    public FreezeDsnAdapter(ArrayList<FreezeDsnModel>datalist,Context context,AdmFAkunDsn admFAkunDsn){
        this.datalist = datalist;
        this.context  = context;
        this.admFAkunDsn = admFAkunDsn;
    }

    @NonNull
    @Override
    public FreezeDsnViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_f_dsn,viewGroup,false);
        return  new FreezeDsnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreezeDsnViewHolder freezeDsnViewHolder, final int i) {
        freezeDsnViewHolder.namaDsn.setText(datalist.get(i).getNamaDsn());
        freezeDsnViewHolder.nipDsn.setText(datalist.get(i).getNipDsn());
        freezeDsnViewHolder.statusDsn.setText(datalist.get(i).getStatusDsn());

        freezeDsnViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admFAkunDsn.showDialog(datalist.get(i).getNipDsn());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class FreezeDsnViewHolder extends RecyclerView.ViewHolder {
        TextView namaDsn,nipDsn,statusDsn;
        CardView mainLayout;

        public FreezeDsnViewHolder(@NonNull View itemView) {
            super(itemView);
            namaDsn     =   itemView.findViewById(R.id.namaDsn_i_f_dsn);
            nipDsn      =   itemView.findViewById(R.id.nipDsn_i_f_dsn);
            statusDsn   =   itemView.findViewById(R.id.statusDsn_i_f_dsn);

            mainLayout  =   itemView.findViewById(R.id.mainLayout_f_dsn);
        }
    }
}
