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

import com.okedroid.apktaichsan.DsnDataBimbinganMhs;
import com.okedroid.apktaichsan.Model.MahasiswaListModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaListViewHolder> {

    ArrayList<MahasiswaListModel> dataList;
    Context context;

    public MahasiswaListAdapter(ArrayList<MahasiswaListModel> dataList, Context context){

        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public MahasiswaListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_mahasiswa_list,viewGroup, false);
        return new MahasiswaListViewHolder(view);
        //link adapter ke layout
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaListViewHolder mahasiswaListViewHolder, final int i) {

        mahasiswaListViewHolder.topikTA.setText(dataList.get(i).getTopikTA());
        mahasiswaListViewHolder.namaMhs.setText(dataList.get(i).getNama());

        mahasiswaListViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DsnDataBimbinganMhs.class);
                intent.putExtra("namaMhs", dataList.get(i).getNama());
                intent.putExtra("deskripsi", dataList.get(i).getDeskripsi());
                intent.putExtra("noBp", dataList.get(i).getNoBp());
                intent.putExtra("topikTA", dataList.get(i).getTopikTA());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataList!=null) ? dataList.size() : 0;
    }

    public class MahasiswaListViewHolder extends RecyclerView.ViewHolder {

        TextView namaMhs, topikTA;
        CardView mainLayout;

        public MahasiswaListViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMhs     =   itemView.findViewById(R.id.namaMhs);
            topikTA     =   itemView.findViewById(R.id.topikTA);
            mainLayout  =   itemView.findViewById(R.id.mainLayout);

        }
    }

}
