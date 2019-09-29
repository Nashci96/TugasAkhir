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

import com.okedroid.apktaichsan.AdmResDsnSet;
import com.okedroid.apktaichsan.Model.ResetDsnModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class ResetDsnAdapter extends RecyclerView.Adapter<ResetDsnAdapter.ResetDsnViewHolder> {

    ArrayList<ResetDsnModel> datalist;
    Context context;

    public  ResetDsnAdapter(ArrayList<ResetDsnModel> datalist,Context context){

        this.datalist = datalist;
        this.context  = context;

    }

    @NonNull
    @Override
    public ResetDsnViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_res_dsn,viewGroup,false);
        return new ResetDsnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResetDsnViewHolder resetDsnViewHolder, final int i) {
        resetDsnViewHolder.namaDsn.setText(datalist.get(i).getNamaDsn());
        resetDsnViewHolder.nipDsn.setText(datalist.get(i).getNipDsn());
        resetDsnViewHolder.statusDsn.setText(datalist.get(i).getStatusDsn());

        resetDsnViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdmResDsnSet.class);
                intent.putExtra("namaDsn",datalist.get(i).getNamaDsn());
                intent.putExtra("nipDsn",datalist.get(i).getNipDsn());
                intent.putExtra("statusDsn",datalist.get(i).getStatusDsn());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class ResetDsnViewHolder extends RecyclerView.ViewHolder {
        TextView namaDsn,nipDsn,statusDsn;
        CardView mainLayout;

        public ResetDsnViewHolder(@NonNull View itemView) {
            super(itemView);

            namaDsn     =   itemView.findViewById(R.id.namaDsn_i_res_dsn);
            nipDsn      =   itemView.findViewById(R.id.nipDsn_i_res_dsn);
            statusDsn   =   itemView.findViewById(R.id.statusDsn_i_res_dsn);

            mainLayout  =   itemView.findViewById(R.id.mainLayout_res_dsn);
        }
    }
}
