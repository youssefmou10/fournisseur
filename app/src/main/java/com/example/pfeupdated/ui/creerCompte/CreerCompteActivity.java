package com.example.pfeupdated.ui.creerCompte;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.MainActivity;
import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Salariee;
import com.example.pfeupdated.javaClasses.Societe;
import com.example.pfeupdated.ui.activerCompte.ActiverCompte;
import com.example.pfeupdated.ui.activerCompte.ActiverCompteActivity;
import com.example.pfeupdated.ui.seConnecter.SeConnecterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreerCompteActivity extends AppCompatActivity {

    EditText nomSal,prenomSal,gsmSal,emailSal,mdpSal,mdpConfSal;
    Button enregistrer;
    TextView connecter;


    private FirebaseAuth mAuth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_creer_compte);

        nomSal = findViewById(R.id.nomSal);
        prenomSal = findViewById(R.id.prenomSal);
        gsmSal = findViewById(R.id.gsmSal);
        emailSal = findViewById(R.id.emailSal);
        mdpSal = findViewById(R.id.mdpSal);
        mdpConfSal = findViewById(R.id.mdpConfSal);
        connecter = findViewById(R.id.goConnecter);
        enregistrer = findViewById(R.id.enregistrerCompteSal);

        // Go to Login Activity
        connecter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
                startActivity(new Intent(CreerCompteActivity.this, SeConnecterActivity.class));
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Button Creer Compte
        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = nomSal.getText().toString();
                String prenom = prenomSal.getText().toString();
                String gsm = gsmSal.getText().toString();
                String email = emailSal.getText().toString();
                String mdp = mdpSal.getText().toString();
                String mdpcnf = mdpConfSal.getText().toString();
                String codeste = getIntent().getStringExtra("codeste");
                String matricule = getIntent().getStringExtra("matricule");

                if (email.isEmpty()) {
                    emailSal.setError("Entrer votre email *");
                    emailSal.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailSal.setError("Entrer un valide email ");
                }
                if (mdp.isEmpty()) {
                    mdpSal.setError("Entrer un mot de passe *");
                    return;
                }
                if (mdp.length() < 6) {
                    mdpSal.setError("Mot de passe doit > 6 charactere");
                    return;
                }
                if (mdpcnf.isEmpty()) {
                    mdpConfSal.setError("Confirmer le mot de passe *");
                    return;
                }
                if (!mdp.equals(mdpcnf)) {
                    mdpConfSal.setError("mot de passe est different !");
                    return;
                }

                creerCompte(nom,prenom,gsm,mdp,email,"0",null,codeste,matricule,null);

                //Simple sign up
                /*mAuth.createUserWithEmailAndPassword(email,mdp).addOnCompleteListener(CreerCompteActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(CreerCompteActivity.this, "Creer avec succes !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreerCompteActivity.this, SeConnecterActivity.class));
                        }else
                            {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                {
                                    Toast.makeText(CreerCompteActivity.this, "Vous avez deja inscrivez !!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(CreerCompteActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    }
                });*/
            }
        });
    }

    private void creerCompte(final String nom, final String prenom, final String gsm, final String mdp , final String email, final String active, final Date dateNss , final String codeste, final String matricule, final Uri profileImg)
    {
        //Sign up using Auth and RealTime

        /*final String nom = nomSal.getText().toString();
        final String prenom = prenomSal.getText().toString();
        final String gsm = gsmSal.getText().toString();
        final String email = emailSal.getText().toString();
        final String mdp = mdpSal.getText().toString();
        final String mdpcnf = mdpConfSal.getText().toString();
        final String codeste = getIntent().getStringExtra("codeste");
        final String matricule = getIntent().getStringExtra("matricule");*/


        //Log.d("This Activity","Values are "+nom+"/"+prenom+"/"+gsm+"/"+email+"/"+mdp+"/"+codeste);


        mAuth.createUserWithEmailAndPassword(email,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //we will create salarie
                    Salariee sal = new Salariee(nom,prenom,gsm,mdp,email,active,dateNss,codeste,matricule,profileImg);
                    FirebaseDatabase.getInstance().getReference("Salariee")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(sal).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CreerCompteActivity.this, "Creer avec succes !", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(CreerCompteActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(CreerCompteActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreerCompteActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}






