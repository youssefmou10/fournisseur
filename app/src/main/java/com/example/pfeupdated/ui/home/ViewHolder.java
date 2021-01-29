package com.example.pfeupdated.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.pfeupdated.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setDetaille(Context context,String url,String title)
    {
        ImageView img = view.findViewById(R.id.offrepp);

        Picasso.get().load(url).into(img);
    }
}
