package com.example.pfeupdated.ui.newOffre;


import android.app.FragmentContainer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Offre;
import com.example.pfeupdated.ui.laisserAvis.LaisserAvis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class AdapterOfOffre extends ArrayAdapter<Offre> {

    private Context mContext;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;


    public AdapterOfOffre(Context context, ArrayList<Offre> mesOffres) {
        super(context, 0, mesOffres);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Offre offre = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_row_new_offre,parent,false);
        }

        TextView title = convertView.findViewById(R.id.titleOfOffre);
        TextView desc = convertView.findViewById(R.id.descOfOffre);
        ImageView img = convertView.findViewById(R.id.imgOffre);
        Button detaille = convertView.findViewById(R.id.btnApprouver);

        title.setText(offre.getNomOffre());
        desc.setText(offre.getDiscription());
        Picasso.get().load(offre.getImageOffre()).into(img);



        detaille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Offre off = getItem(position);

                DetailleOffre detailleOffre = new DetailleOffre();
                Bundle bundle = new Bundle();
                FragmentTransaction ft = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                bundle.putString("idOffre",off.getIdOffre());
                detailleOffre.setArguments(bundle);
                ft.replace(R.id.nav_host_fragment, detailleOffre);
                ft.commit();

            }
        });

        return convertView;
    }

}
