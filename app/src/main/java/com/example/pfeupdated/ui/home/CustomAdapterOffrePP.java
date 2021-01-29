package com.example.pfeupdated.ui.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import com.example.pfeupdated.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapterOffrePP extends RecyclerView.Adapter<CustomAdapterOffrePP.CustomViewHolder> {

    private Context context;
    private ArrayList<ModelOffrePP> items;

    public CustomAdapterOffrePP(Context context, ArrayList<ModelOffrePP> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomAdapterOffrePP.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomAdapterOffrePP.CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.row_plus_pop_offre, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //holder.url.set(items.get(position).getTitle());
        //holder.img.setBackground(items.get(position).getTitle());
        Picasso.get().load(items.get(position).getUrl()).into(holder.img);
        //holder.img.setImageURI();
}

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;


        public CustomViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.offrepp);

        }
    }

}
