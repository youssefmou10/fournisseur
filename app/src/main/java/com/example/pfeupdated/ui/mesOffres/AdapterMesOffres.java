package com.example.pfeupdated.ui.mesOffres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pfeupdated.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterMesOffres extends ArrayAdapter<MyOffre> {


    public AdapterMesOffres( Context context, ArrayList<MyOffre> mesoffres) {
        super(context, 0,mesoffres);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyOffre offre = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_of_mes_offres,parent,false);
        }

        ImageView qrcode1 = convertView.findViewById(R.id.qrcode1);
        ImageView logo1 = convertView.findViewById(R.id.offre1);
        TextView per1 = convertView.findViewById(R.id.per1);
        ImageView qrcode2 = convertView.findViewById(R.id.qrcode2);
        ImageView logo2 = convertView.findViewById(R.id.offre2);
        TextView per2 = convertView.findViewById(R.id.per2);

        qrcode1.setImageResource(offre.getQrcode1());
        logo1.setImageResource(offre.getLogooffre1());
        per1.setText(offre.getPer1());

        qrcode2.setImageResource(offre.getQrcode2());
        logo2.setImageResource(offre.getLogooffre2());
        per2.setText(offre.getPer2());

        return convertView;
    }
}
