package com.example.pfeupdated.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Offre;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapterNewOffre extends RecyclerView.Adapter<CustomAdapterNewOffre.CustomViewHolder>{

    private Context context;
//
private ArrayList<Offre> items;

    public CustomAdapterNewOffre(Context context, ArrayList<Offre> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.row_new_offre, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
//        Picasso.get().load(items.get(position).getUrl()).into(holder.img);
        Picasso.get().load(items.get(position).getImageOffre()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Offre offre = items.get(position);
                Log.d("adapter!!",offre+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;


        public CustomViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.newoffre);

        }
    }
}
