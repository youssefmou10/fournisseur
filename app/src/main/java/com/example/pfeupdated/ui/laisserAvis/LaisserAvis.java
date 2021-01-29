package com.example.pfeupdated.ui.laisserAvis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.AvisOfOffre;
import com.example.pfeupdated.ui.lesAvis.ListAvis;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LaisserAvis extends Fragment {

    RatingBar ratngBar;
    EditText laisserComn;
    ImageButton saveComn;

    private FirebaseUser user;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_laisser_avis, container, false);

        ratngBar = root.findViewById(R.id.ratingBarComn);
        laisserComn = root.findViewById(R.id.laisserComn);
        saveComn = root.findViewById(R.id.saveComn);

        Bundle bundle = this.getArguments();
        String idOffre = null;
        //get idOffre from DetailleOffre
        if(bundle != null ) {
            idOffre = getArguments().getString("idOffre");
            Log.d("LaisserAvie","idOffre is "+idOffre);
        }
        final String finalIdOffre = idOffre;

        //getUserInfo();
        saveComn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    //userInfosAuth
                    String idSal = user.getDisplayName();
                    float rating = ratngBar.getRating();
                    String comn = laisserComn.getText().toString();

                    AvisOfOffre avie = new AvisOfOffre(idSal, finalIdOffre, comn, rating,"0");

                    myRef.child("Avis").push().setValue(avie);
                    //Toast.makeText(getContext(), "Rating : " + avie.getRating() + "=> Comment : " + avie.getComment() +" name : "+avie.getIdSal()+" PUSH: "+myRef.push(), Toast.LENGTH_LONG).show();
                    Log.d("PUSH","Push"+myRef.push());

                    ListAvis listAvis = new ListAvis();
                    Bundle bundle = new Bundle();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    bundle.putString("idOffre", finalIdOffre);
                    listAvis.setArguments(bundle);
                    fr.replace(R.id.nav_host_fragment, listAvis);
                    fr.addToBackStack(null);
                    fr.commit();


                }

            }
        });



        return root;
    }


    private void getUserInfo()
    {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            //userInfosAuth
            final String uidUser = user.getUid();
            final String nameUser = user.getDisplayName();
            final String emailUser = user.getEmail();
            final Uri photoUrlUser = user.getPhotoUrl();
            final boolean emailVerifiedUser = user.isEmailVerified();

            Log.d("LaisserAvis",uidUser);

        }
    }

}
