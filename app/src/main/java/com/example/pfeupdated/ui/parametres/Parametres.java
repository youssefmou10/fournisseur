package com.example.pfeupdated.ui.parametres;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Salariee;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class Parametres extends Fragment {

    LinearLayout proLay;
    ImageView proPic;
    EditText proName,proPrenom,proGsm,proMail,proDate;
    Button proBtnMod;

    private final int IMAGE_REQUEST = 1;
    Uri profilePH;

    FirebaseStorage mStorage;
    StorageReference mRef;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_parametres, container, false);

        mStorage = FirebaseStorage.getInstance();
        mRef = mStorage.getReference();

        proLay = root.findViewById(R.id.layPic);
        proPic = root.findViewById(R.id.proPic);
        proName = root.findViewById(R.id.proName);
        proPrenom = root.findViewById(R.id.proPrenom);
        proGsm = root.findViewById(R.id.proGsm);
        proMail = root.findViewById(R.id.proMail);
        proDate = root.findViewById(R.id.proDate);
        proBtnMod = root.findViewById(R.id.proBtnMod);

        proLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        proBtnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = proName.getText().toString();
                String prenom = proPrenom.getText().toString();
                String gsm = proGsm.getText().toString();
                String email = proMail.getText().toString();
                String date = proDate.getText().toString();

                //updateProfile(nom,prenom,gsm,null,email,null,null,null,null,profilePH);
                uploader();

            }
        });



           return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            profilePH = data.getData();
            //photo.setImageURI(imguri);
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),profilePH);
                Log.d("uploadImage","URI: "+profilePH+" / bitmap: "+bitmap);
                proPic.setImageBitmap(bitmap);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    private void imageChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select pic"),IMAGE_REQUEST);
    }


    private void uploader()
    {
        if(profilePH != null)
        {
            final String uuid = UUID.randomUUID().toString();
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Updating...");
            //progressDialog.show();

            StorageReference ref = mRef.child("profilePics/"+ uuid);
            ref.putFile(profilePH)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    UserProfileChangeRequest userPro = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();
                                    user.updateProfile(userPro);

                                    Log.d("URI of image ",uri +" && "+uuid+" && "+profilePH);

                                }
                            });

                            Toast.makeText(getContext(), "Updated !", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(getContext(), "Failed"+exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


//    private void updateProfile(String nom, String prenom, String telephone, String motsDePasse, String adressmail, String active, Date dateNessance, String idSte, String id, Uri profileImg)
//    {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        String uid = "";
////        if (user != null) {
////            uid = user.getUid();
////        }
////        myRef = database.getReference("Salariee").child(uid);
////        Salariee sal = new Salariee(nom,prenom,telephone,motsDePasse,adressmail,active,dateNessance,idSte,id,profileImg);
////        myRef.setValue(sal);
//
//        //user = FirebaseAuth.getInstance().getCurrentUser();
//        UserProfileChangeRequest userPro = new UserProfileChangeRequest.Builder().setPhotoUri(profilePH).build();
//        user.updateProfile(userPro);
//
//    }




}
