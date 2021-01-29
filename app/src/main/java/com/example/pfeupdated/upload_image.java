package com.example.pfeupdated;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class upload_image extends Fragment {

    Button chose,upload,download;
    ImageView photo;
    Uri imguri;

    private final int IMAGE_REQUEST = 1;

     FirebaseStorage mStorage;
     StorageReference mRef;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("images");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_upload_image, container, false);

        mStorage = FirebaseStorage.getInstance();
        mRef = mStorage.getReference();

        chose = root.findViewById(R.id.chose);
        upload = root.findViewById(R.id.upload);
        download = root.findViewById(R.id.download);
        photo = root.findViewById(R.id.photo);

        downloaderFromRealTime();

        chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filechoser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploader();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloaderFromRealTime();
            }
        });





        return root;
    }

    private void downloaderFromRealTime()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                ArrayList<String> urls = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    String url = data.child("url").getValue().toString();
                    urls.add(url);
                }
                Log.d("Images from realTime", "Value is: " + urls.toString() + " && "+urls.size());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Images from realTime", "Failed to read value.", error.toException());
            }
        });
    }

    private void downloaderFromStorage()
    {
        StorageReference ref = mRef.child("/images/Image 1.png");

        long maxbytes = 1024*1024;

        ref.getBytes(maxbytes).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                //convert byte to bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                photo.setImageBitmap(bitmap);

                int i = bytes.length;
                int j = bitmap.describeContents();
                Log.d("downlad",bitmap.toString()+"Length : "+i+"desc : "+j);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("downlad",e.getMessage());
            }
        });
    }


    private void uploader()
    {
        if(imguri != null)
        {
            final String uuid = UUID.randomUUID().toString();
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = mRef.child("images/"+ uuid);
            ref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoLink = uri.toString();
                                    Log.d("URI of image ",photoLink+" && "+uuid);
                                    writeImages(photoLink);
                                }
                            });

                            Toast.makeText(getContext(), "Uploaded !", Toast.LENGTH_SHORT).show();
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

    private void filechoser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select pic"),IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null)
        {
            imguri = data.getData();
            //photo.setImageURI(imguri);
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imguri);

                photo.setImageBitmap(bitmap);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void writeImages(String url)
    {
        myRef.push().child("url").setValue(url);
    }







}
