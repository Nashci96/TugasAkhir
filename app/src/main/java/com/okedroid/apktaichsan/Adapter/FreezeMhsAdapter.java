package com.okedroid.apktaichsan.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okedroid.apktaichsan.AdmFAkunMhs;
import com.okedroid.apktaichsan.Model.FreezeMhsModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class FreezeMhsAdapter extends RecyclerView.Adapter<FreezeMhsAdapter.FreezeMhsViewHolder> {
    ArrayList<FreezeMhsModel> datalist;
    Context context;
    AdmFAkunMhs admFAkunMhs;

    public FreezeMhsAdapter(ArrayList<FreezeMhsModel>datalist,Context context,AdmFAkunMhs admFAkunMhs){
        this.datalist = datalist;
        this.context = context;
        this.admFAkunMhs = admFAkunMhs;
    }

    @NonNull
    @Override
    public FreezeMhsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_f_mhs,viewGroup,false);
        return new FreezeMhsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreezeMhsViewHolder freezeMhsViewHolder, final int i) {
        freezeMhsViewHolder.namaMhs.setText(datalist.get(i).getNamaMhs());
        freezeMhsViewHolder.nobpMhs.setText(datalist.get(i).getNobpMhs());
        freezeMhsViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admFAkunMhs.showDialog(datalist.get(i).getNobpMhs());
            }
        });
    }


    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class FreezeMhsViewHolder extends RecyclerView.ViewHolder {
        TextView namaMhs,nobpMhs;
        CardView mainLayout;

        public FreezeMhsViewHolder(@NonNull View itemView) {
            super(itemView);
            namaMhs     =   itemView.findViewById(R.id.namaMhs_i_f_mhs);
            nobpMhs     =   itemView.findViewById(R.id.nobpMhs_i_f_mhs);
            mainLayout  =   itemView.findViewById(R.id.mainLayout_f_mhs);
        }
    }
}
