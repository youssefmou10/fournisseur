package com.example.pfeupdated;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.pfeupdated.ui.QRcode.ScanQRcode;
import com.example.pfeupdated.ui.mesOffres.MesOffres;
import com.example.pfeupdated.ui.myCompte.Compte;
import com.example.pfeupdated.ui.notifications.notifications;
import com.example.pfeupdated.ui.parametres.Parametres;
import com.example.pfeupdated.ui.rechercher.Rechercher;
import com.example.pfeupdated.ui.seConnecter.SeConnecterActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
//import com.google.firebase.firestore.FirebaseFirestore;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  MesOffres.OnFragmentInteractionListener,BarChar.OnFragmentInteractionListener,ScanQRcode.OnFragmentInteractionListener/*BlankFragment2.OnFragmentInteractionListener,*/, notifications.OnFragmentInteractionListener,  Rechercher.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;
    private FirebaseUser user;

    View view;

    TextView userName;
    TextView steName;
    ImageView userIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialisation firebase stuff
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_tools, R.id.nav_share, R.id.nav_send,R.id.f_test,
                R.id.f_test2,R.id.f_test3,R.id.f_notifications,/*R.id.f_creerCompteSal,*/R.id.f_listOfOffres,R.id.f_listOfOfAvis,R.id.f_parametres,
                R.id.f_laisserAvis,R.id.f_rechercher,R.id.f_scanQRcode,R.id.f_pieChar,R.id.nav_compte,R.id.f_mesOffres,R.id.f_uploadImage,R.id.f_offreEncours,R.id.nav_exit)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        view = navigationView.getHeaderView(0);
        userName = view.findViewById(R.id.username);
        steName = view.findViewById(R.id.userstename);
        userIcon = view.findViewById(R.id.usericone);

        //Get current user infos
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //userInfosAuth
            final String uidUser = user.getUid();
            final String nameUser = user.getDisplayName();
            final Uri photoUrlUser = user.getPhotoUrl();

            mRef = mData.getReference("Salariee").child(uidUser);
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //userInfosRealTime
                    final String idSte = dataSnapshot.child("idSte").getValue().toString();
                    mRef = mData.getReference("CompteSte");
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot val : dataSnapshot.getChildren())
                            {
                                String codeSte= val.child("codeSte").getValue().toString();
                                if (codeSte.equals(idSte))
                                {
                                    if(photoUrlUser != null)
                                    {
                                        String nomSte = val.child("nomSte").getValue().toString();
                                        steName.setText(nomSte);
                                        userName.setText(nameUser);
                                        Picasso.get().load(photoUrlUser).into(userIcon);
                                        Log.d("Main","nomSte: "+nomSte+" userName: "+nameUser+" photo: "+photoUrlUser);
                                     }else
                                        {
                                            String nomSte = val.child("nomSte").getValue().toString();
                                            steName.setText(nomSte);
                                            userName.setText(nameUser);
                                            userIcon.setImageResource(R.drawable.avatar);
                                            Log.d("Main","nomSte: "+nomSte+" userName: "+nameUser+" photo: "+photoUrlUser);
                                        }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Main","Eroor : "+databaseError.getMessage());
                        }
                    });
                    //Log.d("MainActivity","RealTime nom is :"+nameUser+"/"+emailUser+"/"+photoUrlUser+"/"+emailVerifiedUser+"/"+uidUser);
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("MainActivity", "Failed to read value.", error.toException());
                }
            });
            //Log.d("Main","User Infos :"+uidUser+"/"+emailUser+"/"+photoUrlUser+"/"+emailVerifiedUser+"/"+nameUser);
        }

        //userIcon click
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment,new Parametres()).addToBackStack(null);
                ft.commit();
            }
        });

        //Item Exit clicked
        Menu nav_exit = navigationView.getMenu();
        nav_exit.findItem(R.id.nav_exit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                signout();
                return true;
            }
        });
        //userInfos();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(MainActivity.this, SeConnecterActivity.class));
        }
    }

    private void getUserInfos()
    {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //userInfosAuth
            final String uidUser = user.getUid();
            final String emailUser = user.getEmail();
            final Uri photoUrlUser = user.getPhotoUrl();
            final boolean emailVerifiedUser = user.isEmailVerified();

            Log.d("Main","User Infos :"+uidUser+"/"+emailUser+"/"+photoUrlUser+"/"+emailVerifiedUser+"/"+user.getDisplayName());

            mRef = mData.getReference("Salariee").child(uidUser);
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //userInfosRealTime
                    String nom = dataSnapshot.child("nom").getValue().toString();
                    String prenom = dataSnapshot.child("prenom").getValue().toString();

                    Log.d("USer info RealTime",nom+"/"+prenom);
                    //updateUserInfos
                    UserProfileChangeRequest userPro = new UserProfileChangeRequest.Builder().setDisplayName(nom+" "+prenom).build();
                    user.updateProfile(userPro);

                    final String nameUser = userPro.getDisplayName();
                    Log.d("UserProName",nameUser+"/"+userPro.getDisplayName());

                    //send infos from here to compte Fragment
                    Intent intentVal = new Intent(MainActivity.this, Compte.class);
                    intentVal.putExtra("nomcomplet",nameUser);
                    intentVal.putExtra("email",emailUser);

                    Log.d("MainActivity","RealTime nom is :"+nameUser+"/"+emailUser+"/"+photoUrlUser+"/"+emailVerifiedUser+"/"+uidUser);
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("MainActivity", "Failed to read value.", error.toException());
                }
            });
        }
    }

    private void signout()
    {
        mAuth.signOut();
        MainActivity.this.finish();
        startActivity(new Intent(getApplicationContext(), SeConnecterActivity.class));
    }
}
