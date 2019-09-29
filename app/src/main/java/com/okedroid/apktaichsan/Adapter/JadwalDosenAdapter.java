package com.okedroid.apktaichsan.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okedroid.apktaichsan.Model.JadwalDosenModel;
import com.okedroid.apktaichsan.R;

import java.util.ArrayList;

public class JadwalDosenAdapter extends RecyclerView.Adapter<JadwalDosenAdapter.JadwalDosenViewHolder> {

    ArrayList<JadwalDosenModel> dataList;
    Context context;

    public JadwalDosenAdapter(ArrayList<JadwalDosenModel> dataList, Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public JadwalDosenViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.jadwal_dsn_item,viewGroup, false);
        return new JadwalDosenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalDosenViewHolder jadwalDosenViewHolder, int i) {

        jadwalDosenViewHolder.shift.setText(dataList.get(i).getShift());
        jadwalDosenViewHolder.tanggal.setText(dataList.get(i).getTanggal());

    }

    @Override
    public int getItemCount() {
        return (dataList != null)? dataList.size() : 0; //if datalist != null return size else return 0
    }

    public class JadwalDosenViewHolder extends RecyclerView.ViewHolder {

        TextView tanggal, shift;

        public JadwalDosenViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tanggalTV);
            shift = itemView.findViewById(R.id.shiftTV);
        }
    }

}
