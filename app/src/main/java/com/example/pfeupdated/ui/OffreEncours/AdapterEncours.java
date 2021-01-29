package com.example.pfeupdated.ui.OffreEncours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pfeupdated.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterEncours extends RecyclerView.Adapter<AdapterEncours.CustomViewHolder>{

    private Context context;
    private ArrayList<ModelImg> items;

    public AdapterEncours(Context context, ArrayList<ModelImg> items) {
        this.context = context;
        this.items = items;
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterEncours.CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.row_offre_encours, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.img.setImageResource(items.get(position).getImgOffre());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;


        public CustomViewHolder(View view) {
            super(view);

            img = view.findViewById(R.id.offreEncours);
        }
    }
}
