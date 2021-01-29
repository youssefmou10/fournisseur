package com.example.pfeupdated.ui.newOffre;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.ui.laisserAvis.LaisserAvis;
import com.example.pfeupdated.ui.lesAvis.ListAvis;


public class DetailleOffre extends Fragment {


    ImageView offreImg;
    TextView nomOffre,descOffre,voirAvie,laisserAvie;
    Button profite;




    public DetailleOffre() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_offre_detaille, container, false);

        offreImg = root.findViewById(R.id.offreimg);
        nomOffre = root.findViewById(R.id.nomoffre);
        descOffre = root.findViewById(R.id.descoffre);
        voirAvie = root.findViewById(R.id.voiravie);
        laisserAvie = root.findViewById(R.id.laisseravie);
        profite = root.findViewById(R.id.jenprofite);

        Bundle bundle = this.getArguments();
        String idOffre = null;

        //get idOffre from ListOffreFragment
        if(bundle != null ) {
            idOffre = getArguments().getString("idOffre");
            Log.d("DetailleOffre","idOffre is "+idOffre);
        }
        final String finalIdOffre = idOffre;

        //Go to list of avis Fragment
        voirAvie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListAvis listAvis = new ListAvis();
                Bundle bundle = new Bundle();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                bundle.putString("idOffre", finalIdOffre);
                listAvis.setArguments(bundle);
                ft.replace(R.id.nav_host_fragment, listAvis);
                ft.addToBackStack(null);
                ft.commit();

                //FragmentTransaction fr = getFragmentManager().beginTransaction();
                //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragment_list_offre)).commit();
                //fr.commit();
            }
        });

        //Go to laisser un commentaire Fragment
        laisserAvie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("laisserAvieClick","idOffre is "+finalIdOffre);

                LaisserAvis laisserAvis = new LaisserAvis();
                Bundle bundle = new Bundle();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                bundle.putString("idOffre", finalIdOffre);
                laisserAvis.setArguments(bundle);
                ft.replace(R.id.nav_host_fragment, laisserAvis);
                ft.addToBackStack(null);
                ft.commit();
            }
        });




        profite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Toast from btn profite", Toast.LENGTH_SHORT).show();
                //Bundle bundle = DetailleOffre.this.getArguments();

                if(getArguments() != null){
                    // handle your code here.
                    Toast.makeText(getContext(), "Toast from IF", Toast.LENGTH_SHORT).show();
                    //Log.d("DetailleOffre","Data from ListOffre : "+bundle.getString("key"));
                }
            }
        });





        return root;
    }


}
