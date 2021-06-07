package com.tolgahanoktay.basketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.PostHolder> {

    private ArrayList<String> urun_resim_list;
    private ArrayList<String> urun_adi;
    private ArrayList<String> urun_br_fiyat;
    private ArrayList<String> urun_son_tarih;


    public ShowDataAdapter(ArrayList<String> urun_resim_list, ArrayList<String> urun_adi, ArrayList<String> urun_br_fiyat, ArrayList<String> urun_son_tarih) {
        this.urun_resim_list = urun_resim_list;
        this.urun_adi = urun_adi;
        this.urun_br_fiyat = urun_br_fiyat;
        this.urun_son_tarih = urun_son_tarih;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_list,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.urun_adi.setText(urun_adi.get(position));
        holder.urun_br_fiyat.setText(urun_br_fiyat.get(position));
        holder.urun_son_tarih.setText(urun_son_tarih.get(position));
        Picasso.get().load(urun_resim_list.get(position)).into(holder.urun_resim);
    }

    @Override
    public int getItemCount() {
        return urun_adi.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {

        ImageView urun_resim;
        TextView urun_adi;
        TextView urun_br_fiyat;
        TextView urun_son_tarih;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            urun_resim = itemView.findViewById(R.id.productView);
            urun_adi = itemView.findViewById(R.id.productname);
            urun_br_fiyat = itemView.findViewById(R.id.productUPrice);
            urun_son_tarih = itemView.findViewById(R.id.prd_priceTotal);


        }
    }
}
