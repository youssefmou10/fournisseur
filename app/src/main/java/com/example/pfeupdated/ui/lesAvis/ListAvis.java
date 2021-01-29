package com.example.pfeupdated.ui.lesAvis;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.AvisOfOffre;
import com.example.pfeupdated.javaClasses.Salariee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListAvis extends Fragment {
    ListView listAvis;
    ArrayList<AvisOfOffre> avis ;
    ProgressBar prgbaravie;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Avis");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_avis, container, false);

        listAvis = root.findViewById(R.id.listOfAvis);
        prgbaravie = root.findViewById(R.id.prgbaravie);

        //get idOffre from ListOffreFragment
        Bundle bundle = this.getArguments();
        String idOffre = null;

        if(bundle != null ) {
            idOffre = getArguments().getString("idOffre");
            Log.d("ListAvis","idOffre is "+idOffre);
        }
        final String finalIdOffre = idOffre;

        avis = new ArrayList<>();
/*
        AvisOfOffre avie1 = new AvisOfOffre("idSal","idOffre","comn1",1.5+"");
        AvisOfOffre avie2 = new AvisOfOffre("idSa2","idOffr2","comn2",3.5+"");
        AvisOfOffre avie3 = new AvisOfOffre("idSa3","idOffr3","comn3",4.5+"");

        avis.add(avie1);
        avis.add(avie2);
        avis.add(avie3);

        Log.d("ListAvis2",avis.size()+"");
*/
        prgbaravie.setVisibility(View.VISIBLE);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                AvisOfOffre avie ;

                for (DataSnapshot val : dataSnapshot.getChildren())
                {
                    String idOffre,idSal,comn,rating,active;
                    float ratingNew;

                    idOffre = val.child("idOffre").getValue().toString();
                    idSal = val.child("idSal").getValue().toString();
                    comn = val.child("comment").getValue().toString();
                    active = val.child("active").getValue().toString();
                    rating = val.child("rating").getValue().toString();
                    ratingNew = Float.parseFloat(rating);

                        if(val.child("idOffre").getValue().equals(finalIdOffre))
                        {
                            avie = new AvisOfOffre(idSal,finalIdOffre,comn,ratingNew,active);
                            Log.d("ListAvis",avie.toString());
                            avis.add(avie);
                        }else
                        {
                            Log.d("Val att","Not Founded");
                        }




                   // avie = new AvisOfOffre(idSal,idOffre,comn,ratingNew,active);
                    //Log.d("ListAvis",avie.toString());
                    //avis.add(avie);
                    //Log.d("ListAvis",idOffre+"/"+idSal+"/"+comn+"/"+rating);
                }

                Log.d("ListAvis2",avis.size()+"");
                prgbaravie.setVisibility(View.GONE);
                AdapterOfAvis adapter = new AdapterOfAvis(getContext(),avis);
                listAvis.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ListAvis", "Failed to read value.", error.toException());
            }
        });








        return root;
    }

}
