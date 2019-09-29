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

import com.okedroid.apktaichsan.KpdDataTopikMhs;
import com.okedroid.apktaichsan.Model.TopikListModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class TopikListAdapter extends RecyclerView.Adapter<TopikListAdapter.TopikListViewHolder> {

    ArrayList<TopikListModel> datalist;
    Context context;

    public TopikListAdapter(ArrayList<TopikListModel> datalist,Context context){

        this.datalist = datalist;
        this.context  = context;

    }

    @NonNull
    @Override
    public TopikListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_topik_list,viewGroup,false);
        return new TopikListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopikListViewHolder topikListViewHolder, final int i) {

        topikListViewHolder.topikTa.setText(datalist.get(i).getTopikTA());
        topikListViewHolder.namaMhs.setText(datalist.get(i).getNama());

        topikListViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KpdDataTopikMhs.class);
                intent.putExtra("namaMhs",datalist.get(i).getNama());
                intent.putExtra("noBp",datalist.get(i).getNoBp());
                intent.putExtra("deskripsi",datalist.get(i).getDeskripsi());
                intent.putExtra("topikTA",datalist.get(i).getTopikTA());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (datalist!=null) ? datalist.size():0 ;
    }

    public class TopikListViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs,topikTa;
        CardView mainLayout;

        public TopikListViewHolder(@NonNull View itemView) {
            super(itemView);
//
            namaMhs     =   itemView.findViewById(R.id.namaMhs_topik);
            topikTa     =   itemView.findViewById(R.id.topikTA_topik);
            mainLayout  =   itemView.findViewById(R.id.mainLayout_topik);
        }
    }
}
