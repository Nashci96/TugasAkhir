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

import com.okedroid.apktaichsan.AdmResMhsSet;
import com.okedroid.apktaichsan.Model.ResetMhsModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class ResetMhsAdapter extends RecyclerView.Adapter<ResetMhsAdapter.ResetMhsViewHolder> {

    ArrayList<ResetMhsModel> datalist;
    Context context;

    public ResetMhsAdapter(ArrayList<ResetMhsModel> datalist, Context context){
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public ResetMhsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_res_mhs,viewGroup,false);
        return new ResetMhsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResetMhsViewHolder resetMhsViewHolder, final int i) {
        resetMhsViewHolder.namaMhs.setText(datalist.get(i).getNamaMhs());
        resetMhsViewHolder.nobpMhs.setText(datalist.get(i).getNobpMhs());
        resetMhsViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   =   new Intent(context, AdmResMhsSet.class);
                intent.putExtra("namaMhs",datalist.get(i).getNamaMhs());
                intent.putExtra("nobpMhs",datalist.get(i).getNobpMhs());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class ResetMhsViewHolder extends RecyclerView.ViewHolder {
        TextView namaMhs,nobpMhs;
        CardView mainLayout;

        public ResetMhsViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs     =   itemView.findViewById(R.id.namaMhs_i_res_mhs);
            nobpMhs     =   itemView.findViewById(R.id.nobpMhs_i_res_mhs);

            mainLayout  =   itemView.findViewById(R.id.mainLayout_res_mhs);
        }
    }
}
