package com.example.pfeupdated.ui.seConnecter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfeupdated.MainActivity;
import com.example.pfeupdated.R;
import com.example.pfeupdated.ui.activerCompte.ActiverCompteActivity;
import com.example.pfeupdated.ui.creerCompte.CreerCompteActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SeConnecterActivity extends AppCompatActivity {

    EditText email,motDePasse;
    Button jeMeConn;
    TextView activerCompte,creerCompte;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_seconnecter);

        email = findViewById(R.id.emailConnecter);
        motDePasse = findViewById(R.id.motdepasse);
        activerCompte = findViewById(R.id.comptepasactiver);
        creerCompte = findViewById(R.id.inscrivezvous);
        jeMeConn = findViewById(R.id.jemeconnecte);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        activerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), ActiverCompteActivity.class));
            }
        });
        creerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), CreerCompteActivity.class));
            }
        });
        jeMeConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Mdp = motDePasse.getText().toString();

                if (Email.isEmpty())
                {
                    email.setError("Entrer votre email *");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    email.setError("Entrer un valide email ");
                }
                if (Mdp.isEmpty())
                {
                    motDePasse.setError("Entrer un mot de passe *");
                    return;
                }


                mAuth.signInWithEmailAndPassword(Email,Mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            finish();
                            Intent intent = new Intent(SeConnecterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SeConnecterActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

//        if (mAuth.getCurrentUser() != null){
//            //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            //startActivity(intent);
//            //finish();
//            Log.d("SeConnecter","user != null");
//        }else{
//            Log.d("SeConnecter","user == null");
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(SeConnecterActivity.this, MainActivity.class));
        }
    }
}

