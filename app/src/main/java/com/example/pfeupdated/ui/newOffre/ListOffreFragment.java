package com.example.pfeupdated.ui.newOffre;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Offre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ListOffreFragment extends Fragment {
    ListView listOffres;
    ArrayList<Offre> offres = new ArrayList<>();
    ProgressBar progressBar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;

    String idCat=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_offre, container, false);

        listOffres = root.findViewById(R.id.listOfOffres);
        progressBar = root.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        Bundle bundle = this.getArguments();
        final String nomCat ;


        //get idCat from buttons
        if(bundle != null ) {
            nomCat = getArguments().getString("nomCat");
            myRef = database.getReference("CategorieOffre");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data : dataSnapshot.getChildren())
                    {
                        String nomCategorie = data.child("nom").getValue().toString();
                        if (nomCategorie.equals(nomCat))
                        {
                            idCat = data.getKey();
                            getOffresData2();
                            Log.d("listOffre","idCat: "+idCat);
                        }else
                        {
                            progressBar.setVisibility(View.GONE);
                            //Toast.makeText(getContext(), "No data to show", Toast.LENGTH_SHORT).show();
                            Log.d("listOffre","nomCat did not matches");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("listOffre","ERROR: "+databaseError.getMessage());
                }
            });
            //Log.d("ListOffre","idCat is "+idCat);

        }else{
            getOffresData();
        }


        return root;
    }

    private void getOffresData()
    {
        myRef = database.getReference("Offres");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //ArrayList<String> dates = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    String idoffre,adress,desc,idforn,idtype,nomoffre,valider,dateD,dateF;
                    Uri uri;

                    idoffre = data.getKey();
                    adress = data.child("adress").getValue().toString();
                    desc = data.child("discription").getValue().toString();
                    idforn = data.child("idFornisseur").getValue().toString();
                    nomoffre = data.child("nomOffre").getValue().toString();
                    valider = data.child("valider").getValue().toString();
                    dateD = data.child("dateDebut").getValue().toString();
                    dateF = data.child("dateFin").getValue().toString();
                    uri = Uri.parse(data.child("url").getValue().toString());
                    idtype = data.child("idType").getValue().toString();
                    offres.add(new Offre(nomoffre,desc,uri,null,valider,null,null,null,idforn,idtype,idoffre,adress));
                    AdapterOfOffre adapter = new AdapterOfOffre(getContext(),offres);
                    listOffres.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
                Log.d("ListOffreFragment", "Daata : "+ offres);
                //Log.d("ListOffreFragment", "urls : "+ dates);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ListOffreFragment", "Failed to read value.", error.toException());
            }
        });
    }

    private void getOffresData2()
    {
        myRef = database.getReference("Offres");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //ArrayList<String> dates = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    String idoffre,adress,desc,idforn,idtype,nomoffre,valider,dateD,dateF;
                    Uri uri;

                    idoffre = data.getKey();
                    adress = data.child("adress").getValue().toString();
                    desc = data.child("discription").getValue().toString();
                    idforn = data.child("idFornisseur").getValue().toString();
                    idtype = data.child("idType").getValue().toString();
                    nomoffre = data.child("nomOffre").getValue().toString();
                    valider = data.child("valider").getValue().toString();
                    dateD = data.child("dateDebut").getValue().toString();
                    dateF = data.child("dateFin").getValue().toString();
                    uri = Uri.parse(data.child("url").getValue().toString());
                    if (idtype.equals(idCat))
                    {
                        offres.add(new Offre(nomoffre,desc,uri,null,valider,null,null,null,idforn,idCat,idoffre,adress));
                        AdapterOfOffre adapter = new AdapterOfOffre(getContext(),offres);
                        listOffres.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                    }else
                    {
                        progressBar.setVisibility(View.GONE);
                        //Toast.makeText(getContext(), "No data to show", Toast.LENGTH_LONG).show();
                        Log.d("listOffre","idCat did not matches");
                    }

                }
                Log.d("ListOffreFragment", "Daata : "+ offres);
                //Log.d("ListOffreFragment", "urls : "+ dates);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ListOffreFragment", "Failed to read value.", error.toException());
            }
        });
    }


}
