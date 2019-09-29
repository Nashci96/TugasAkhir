package com.okedroid.apktaichsan.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okedroid.apktaichsan.AdmAAkunDsn;
import com.okedroid.apktaichsan.Model.ActiveDsnModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class ActiveDsnAdapter extends RecyclerView.Adapter<ActiveDsnAdapter.ActiveDsnViewHolder> {

    ArrayList<ActiveDsnModel> datalist;
    Context context;
    AdmAAkunDsn admAAkunDsn;

    public ActiveDsnAdapter(ArrayList<ActiveDsnModel>datalist,Context context,AdmAAkunDsn admAAkunDsn){
        this.datalist   = datalist;
        this.context    = context;
        this.admAAkunDsn= admAAkunDsn;
    }

    @NonNull
    @Override
    public ActiveDsnViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_a_dsn,viewGroup,false);
        return new ActiveDsnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveDsnViewHolder activeDsnViewHolder, final int i) {
        activeDsnViewHolder.namaDsn.setText(datalist.get(i).getNamaDsn());
        activeDsnViewHolder.nipDsn.setText(datalist.get(i).getNipDsn());
        activeDsnViewHolder.statusDsn.setText(datalist.get(i).getStatusDsn());

        activeDsnViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admAAkunDsn.showDialog(datalist.get(i).getNipDsn());
            }
        });

    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class ActiveDsnViewHolder extends RecyclerView.ViewHolder {
        TextView namaDsn,nipDsn,statusDsn;
        CardView mainLayout;

        public ActiveDsnViewHolder(@NonNull View itemView) {
            super(itemView);
            namaDsn     =   itemView.findViewById(R.id.namaDsn_i_a_dsn);
            nipDsn      =   itemView.findViewById(R.id.nipDsn_i_a_dsn);
            statusDsn   =   itemView.findViewById(R.id.statusDsn_i_a_dsn);

            mainLayout  =   itemView.findViewById(R.id.mainLayout_a_dsn);

        }
    }
}
