package com.example.pfeupdated.ui.lesAvis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.AvisOfOffre;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterOfAvis extends ArrayAdapter<AvisOfOffre> {
    public AdapterOfAvis(Context context, ArrayList<AvisOfOffre> lesavis) {
        super(context, 0,lesavis);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        AvisOfOffre avie = getItem(position);


        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_row_avis,parent,false);
        }

        TextView salName = convertView.findViewById(R.id.salNom);
        TextView salComment = convertView.findViewById(R.id.salComment);
        RatingBar salRating= convertView.findViewById(R.id.salRating);
        TextView lireSuite = convertView.findViewById(R.id.lireSuite);
        ImageView salImg = convertView.findViewById(R.id.salImg);

        salName.setText(avie.getIdSal());
        salComment.setText(avie.getComment());
        salImg.setImageResource(R.drawable.avatar);
        salRating.setRating(avie.getRating());

        lireSuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Lire la suite !!",Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
