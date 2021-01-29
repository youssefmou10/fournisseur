package com.example.pfeupdated.ui.activerCompte;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pfeupdated.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActiverCompte extends Fragment {

    private GalleryViewModel galleryViewModel;
    public static String TAG = "ActiverCompte";
    EditText codeSte,matricule;
    Button btnContinue;

    FirebaseDatabase myDB = FirebaseDatabase.getInstance();
    DatabaseReference myRef = myDB.getReference("CompteSte");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bar_char, container, false);

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        codeSte = root.findViewById(R.id.codeSte);
        matricule = root.findViewById(R.id.matricule);
        btnContinue = root.findViewById(R.id.btnContinue);
     /*   btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        ArrayList<String> values = new ArrayList<>();


                        for (DataSnapshot data : dataSnapshot.getChildren())
                        {
                            values.add(data.child("codeSte").getValue().toString());
                        }

                        for(int i=0; i<values.size(); i++)
                        {
                            String val = codeSte.getText().toString();
                            if(values.get(i).equals(val))
                            {
                                Toast.makeText(getContext(),"codeSte Valide !!",Toast.LENGTH_LONG).show();
                            }else
                            {
                                Toast.makeText(getContext(),"codeSte invalide !!",Toast.LENGTH_SHORT).show();
                            }

                        }

                        //Log.d(TAG, "Value is: " + values.get(0));
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }
        });*/

        return root;
    }
}