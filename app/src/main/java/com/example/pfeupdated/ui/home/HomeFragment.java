package com.example.pfeupdated.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeupdated.R;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.example.pfeupdated.javaClasses.Offre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    ArrayList<ModelCat> items1;
    ArrayList<ModelOffrePP> items2;
    ArrayList<ModelNewOffre> items3;
    ArrayList<Offre> offres = new ArrayList<>();

    CustomAdapterCatOffre adapter1;
    CustomAdapterOffrePP adapter2;
    CustomAdapterNewOffre adapter3;

    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    ViewFlipper slider;
    ProgressBar progbar1,progbar2,progbar3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Date dt = new Date();
        Log.d("Home","Date: "+dt.getDate());

        slider =  root.findViewById(R.id.viewFlipperSlider);
        recyclerView1 = root.findViewById(R.id.recyclerViewHori1);
        recyclerView2 = root.findViewById(R.id.recyclerViewHori2);
        recyclerView3 = root.findViewById(R.id.recyclerViewHori3);
        progbar1 = root.findViewById(R.id.prgbar1);
        progbar2 = root.findViewById(R.id.prgbar2);
        progbar3 = root.findViewById(R.id.prgbar3);

        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items3 = new ArrayList<>();

        adapter1 = new CustomAdapterCatOffre(getContext(), items1);
        adapter2 = new CustomAdapterOffrePP(getContext(), items2);
//        adapter3 = new CustomAdapterNewOffre(getContext(), items3);
        adapter3 = new CustomAdapterNewOffre(getContext(), offres);

        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView1.setAdapter(adapter1);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);

        //Methode of slider
        int images [] = {R.drawable.slide1,R.drawable.slider2};
        for (int image:images)
        {
            slideFunction(image);
            //Log.i("loop","in loop images");
        }
        /*end main slider*/

        //ReadData
        progbar1.setVisibility(View.VISIBLE);
        readCatData();
        progbar2.setVisibility(View.VISIBLE);
        readOffrePPData();
        progbar3.setVisibility(View.VISIBLE);
        //readNewOffreData();

        getOffres();





        return root;
    }


    public void slideFunction(int image)
    {
        ImageView imgImageView = new ImageView(getContext());
        imgImageView.setBackgroundResource(image);
        slider.addView(imgImageView);
        slider.setFlipInterval(2500);
        slider.setAutoStart(true);

        slider.setInAnimation(getContext(),android.R.anim.slide_in_left);
        slider.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }

    public void readCatData(){
        myRef = database.getReference("CategorieOffre");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<String> values = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    values.add(data.child("nom").getValue().toString());
                }

                for (String data : values) {
                    items1.add(new ModelCat( data));
                    adapter1.notifyDataSetChanged();
                    progbar1.setVisibility(View.GONE);
                }
                //Log.d("HomeFragment", "Value is: " + values.get(0));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("HomeFragment", "Failed to read value.", error.toException());
            }
        });
    }

    private void readOffrePPData()
    {
        myRef = database.getReference("images");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<ModelOffrePP> values = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    String title,urlS;
                    Uri url;
                    title = data.child("img").getValue().toString();
                    urlS = data.child("url").getValue().toString();
                    url = Uri.parse(urlS);

                   values.add(new ModelOffrePP(url,title));
                }

                for (ModelOffrePP data : values) {
                    items2.add(data);
                    adapter2.notifyDataSetChanged();
                    progbar2.setVisibility(View.GONE);
                }


                Log.d("HomeFragment OffrePP", "Value is: " + values.toString()+" && "+values.size());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("HomeFragment OffrePP", "Failed to read value.", error.toException());
            }
        });

    }

    private void readNewOffreData()
    {
        myRef = database.getReference("imagesNewOffre");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<ModelNewOffre> values = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    String title,urlS;
                    Uri url;
                    title = data.child("img").getValue().toString();
                    urlS = data.child("url").getValue().toString();
                    url = Uri.parse(urlS);

                    values.add(new ModelNewOffre(title,url));
                }

                for (ModelNewOffre data : values) {
                    items3.add(data);
                    adapter3.notifyDataSetChanged();
                    progbar3.setVisibility(View.GONE);
                }


                Log.d("HomeFragment New Offre", "Value is: " + values.toString()+" && "+values.size());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("HomeFragment New Offre", "Failed to read value.", error.toException());
            }
        });
    }

    private void getOffres()
    {
        myRef = database.getReference("Offres");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    //Current day
                    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                    int day = calendar.get(Calendar.DATE);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int year = calendar.get(Calendar.YEAR);
                    String cMonth = String.valueOf(month);
                    String cYear = String.valueOf(year);

                    String idoffre,adress,desc,idforn,idtype,nomoffre,valider,dateD,dateF;
                    Uri uri;
                    String dateDbDay = data.child("dateDebut").child("date").getValue().toString();
                    String dateDbMonth = data.child("dateDebut").child("month").getValue().toString();
                    String dateDbYear = data.child("dateDebut").child("year").getValue().toString();
                    int dDay = Integer.parseInt(dateDbDay);

                    //Log.d("home","dateDB: "+year+"/"+dateDbYear);
                    if(dateDbYear.equals(cYear)) {
                        if (dateDbMonth.equals(cMonth)) {
                            if (day - dDay <= 10) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");

                                idoffre = data.getKey();
                                adress = data.child("adress").getValue().toString();
                                desc = data.child("discription").getValue().toString();
                                idforn = data.child("idFornisseur").getValue().toString();
                                idtype = data.child("idType").getValue().toString();
                                nomoffre = data.child("nomOffre").getValue().toString();
                                valider = data.child("valider").getValue().toString();
                                uri = Uri.parse(data.child("url").getValue().toString());
                                dateD = dateDbDay+"/"+dateDbMonth+"/"+dateDbYear;
    //                            try {
    //                                Date dd = sdf.parse(dateD);
    //                                Log.d("HomeF","DD: "+dd);
    //                            } catch (ParseException e) {
    //
    //                                e.printStackTrace();
    //                            }
                                dateF = data.child("dateFin").getValue().toString();


                                offres.add(new Offre(nomoffre, desc, uri, null, valider, null, dateD, null, idforn, idtype, idoffre, adress));
                                adapter3.notifyDataSetChanged();
                                progbar3.setVisibility(View.GONE);
                                //Log.d("HomeFragment","offreDate: "+dateDbDay+"/"+dateDbMonth);
                            } else {
                                Log.d("HomeFragment", "Day > than 10 !!");
                            }
                        } else {
                            Log.d("HomeFragment", "Not the same Month !!");
                        }
                    }else {
                        Log.d("HomeFragment", "Not the same Year !!");
                    }

                }
                Log.d("HomeF","size= "+offres.size()+" offres: "+offres);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}