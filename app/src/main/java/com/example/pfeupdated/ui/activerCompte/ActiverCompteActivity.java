package com.example.pfeupdated.ui.activerCompte;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.MainActivity;
import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Societe;
import com.example.pfeupdated.ui.creerCompte.CreerCompteActivity;
import com.example.pfeupdated.ui.seConnecter.SeConnecterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActiverCompteActivity extends AppCompatActivity {

    EditText codeSte,matricule;
    Button btnContinue;
    TextView goconnecter;

    //Declarations
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activer_compte);
        //Initialisation
        database = FirebaseDatabase.getInstance();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        codeSte = findViewById(R.id.codeSte);
        matricule = findViewById(R.id.matricule);
        goconnecter = findViewById(R.id.goConnecter);
        btnContinue = findViewById(R.id.btnContinue);

        //TextView SeConnecter go to login Activity
        goconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ActiverCompteActivity.this, SeConnecterActivity.class));
            }
        });

        //Button activer
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codesteVAl = codeSte.getText().toString();
                String matriculeVAL = matricule.getText().toString();

                if (codesteVAl.isEmpty())
                {
                    codeSte.setError("Entrer le code de societe*");
                    return;
                }
                if (matriculeVAL.isEmpty())
                {
                    matricule.setError("Entrer votre matricule*");
                    return;
                }
                readData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(ActiverCompteActivity.this, MainActivity.class));
        }
    }

    public void readData(){
        myRef = database.getReference("CompteSte");
        final String codesteVal = codeSte.getText().toString();
        final String matriculeVal = matricule.getText().toString();

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

                for(int i=0;i<values.size();i++)
                {
                    if(values.get(i).equals(codesteVal) )
                    {

                        Toast.makeText(ActiverCompteActivity.this, "CodeSte funded !!", Toast.LENGTH_SHORT).show();
                        Intent intentVal = new Intent(ActiverCompteActivity.this, CreerCompteActivity.class);
                        intentVal.putExtra("codeste",codesteVal);
                        intentVal.putExtra("matricule",matriculeVal);
                        finish();
                        startActivity(intentVal);
                    }
                    //Toast.makeText(ActiverCompteActivity.this, "CodeSte did not find !!", Toast.LENGTH_SHORT).show();

                }
                Log.d("ActiverComte", "Values is: " + values);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ActiverComte", "Failed to read values.", error.toException());
            }
        });
    }
}
