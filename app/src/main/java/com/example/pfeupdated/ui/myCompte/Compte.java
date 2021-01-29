package com.example.pfeupdated.ui.myCompte;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pfeupdated.R;
import com.example.pfeupdated.ui.parametres.Parametres;
import com.example.pfeupdated.ui.seConnecter.SeConnecterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class Compte extends Fragment{

    LinearLayout proLay;
    TextView tvnom,tvemail,tvgsm;
    ImageView img;
    ImageButton logout,update;
    ProgressBar prgbarcompte;

    private final int IMAGE_REQUEST = 1;

    Uri profilePH;
    String downloadImageUrl;

    private FirebaseUser user;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_compte, container, false);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();

        //testing
        tvnom = root.findViewById(R.id.tvnom1);
        tvemail = root.findViewById(R.id.tvemail1);
        tvgsm = root.findViewById(R.id.tvgsm1);
        prgbarcompte = root.findViewById(R.id.prgbarcompte);
        img = root.findViewById(R.id.img);
        proLay = root.findViewById(R.id.profileLay);
        logout = root.findViewById(R.id.btnLogOut);
        update = root.findViewById(R.id.btnUpdate);

        //chosePhoto
        proLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Parametres());
                fr.commit();
            }
        });


        //sign out
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        //loadUserInformations();
        prgbarcompte.setVisibility(View.VISIBLE);
        getUserInfo();




        return root;
    }

    private void saveUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null && downloadImageUrl != null)
        {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setPhotoUri(Uri.parse(downloadImageUrl)).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {

                        Toast.makeText(getContext(), "Profile updated", Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            profilePH = data.getData();
            //img.setImageURI(profilePH);

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),profilePH);
                img.setImageBitmap(bitmap);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    private void uploadImageToFirebase() {
//        mStorageRef = FirebaseStorage.getInstance().getReference("profilePics/"+System.currentTimeMillis()+".jpg");
//        if (uriImage != null)
//        {
//
//            mStorageRef.putFile(uriImage)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            // Get a URL to the uploaded content
//                            downloadImageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Handle unsuccessful uploads
//                            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//        }
    }

    private void imageChooser()
    {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"select pic"),IMAGE_REQUEST);
    }

    private void loadUserInformations(){
//        FirebaseUser user = mAuth.getCurrentUser();
//
//        if (user != null) {
//            if (user.getPhotoUrl() != null) {
//                Glide.with(this).load(user.getPhotoUrl().toString()).into(usericon);
//            }
//        }
     }

     private void signout()
     {
         mAuth.signOut();
         getActivity().finish();
         startActivity(new Intent(getContext(), SeConnecterActivity.class));
     }

     private void getUserInfo()
     {
         user = FirebaseAuth.getInstance().getCurrentUser();
         if (user != null)
         {
             //userInfosAuth
             final String uidUser = user.getUid();
             //final String nameUser = user.getDisplayName();
             final String emailUser = user.getEmail();
             final Uri photoUrlUser = user.getPhotoUrl();
             final boolean emailVerifiedUser = user.isEmailVerified();


             mRef = mData.getReference("Salariee").child(uidUser);
             mRef.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     //userInfosRealTime
                     String nom = dataSnapshot.child("nom").getValue().toString();
                     String prenom = dataSnapshot.child("prenom").getValue().toString();
                     String gsm = dataSnapshot.child("telephone").getValue().toString();

                     //Log.d("USer info RealTime",nom+"/"+prenom);
                     //updateUserInfos
                     UserProfileChangeRequest userPro = new UserProfileChangeRequest.Builder().setDisplayName(nom+" "+prenom).build();
                     user.updateProfile(userPro);
                     String nameUser = userPro.getDisplayName();
                     //
                     prgbarcompte.setVisibility(View.GONE);
                     tvnom.setText(nameUser);
                     tvemail.setText(emailUser);
                     tvgsm.setText(gsm);
                     img.setImageURI(photoUrlUser);

                     Log.d("MainActivity","RealTime nom is :"+nameUser+"/"+emailUser+"/"+photoUrlUser+"/"+emailVerifiedUser+"/"+uidUser+"/"+gsm);
                 }
                 @Override
                 public void onCancelled(DatabaseError error) {
                     // Failed to read value
                     Log.w("MainActivity", "Failed to read value.", error.toException());
                 }
             });


             //Log.d("compteshow",nameUser+"/"+emailUser+"/"+uidUser);
         }
     }

     private void updateProfile()
     {
         user = FirebaseAuth.getInstance().getCurrentUser();
         UserProfileChangeRequest userPro = new UserProfileChangeRequest.Builder().setPhotoUri(profilePH).build();
         user.updateProfile(userPro);

         Log.d("My Compte",user.getPhotoUrl()+"");
     }

}
