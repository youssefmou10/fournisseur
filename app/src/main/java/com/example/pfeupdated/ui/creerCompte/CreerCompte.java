package com.example.pfeupdated.ui.creerCompte;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.javaClasses.Salariee;
import com.example.pfeupdated.ui.seConnecter.SeConnecterFragment;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import static com.example.pfeupdated.ui.activerCompte.ActiverCompte.TAG;


public class CreerCompte extends Fragment {

    EditText nomSal,prenomSal,gsmSal,emailSal,mdpSal,mdpConfSal;
    ImageButton enregistrer;
    public static String TAG = "CreerCompte";
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_send, container, false);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        nomSal = root.findViewById(R.id.nomSal);
        prenomSal = root.findViewById(R.id.prenomSal);
        gsmSal = root.findViewById(R.id.gsmSal);
        emailSal = root.findViewById(R.id.emailSal);
        mdpSal = root.findViewById(R.id.mdpSal);
        mdpConfSal = root.findViewById(R.id.mdpConfSal);
        enregistrer = root.findViewById(R.id.enregistrerCompteSal);

       /* if (mAuth.getCurrentUser() != null)
        {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.nav_host_fragment,new SeConnecterFragment()).addToBackStack("tag");
            fr.commit();
        }*/

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = nomSal.getText().toString();
                String prenom = prenomSal.getText().toString();
                String gsm = gsmSal.getText().toString();
                String email = emailSal.getText().toString();
                String mdp = mdpSal.getText().toString();
                String mdpcnf = mdpConfSal.getText().toString();

              /*  if (nom.isEmpty())
                {
                    nomSal.setError("Entrer votre nom !");
                    return;
                }
                if (prenom.isEmpty())
                {
                    prenomSal.setError("Entrer votre prenom !");
                    return;
                }
                if (gsm.isEmpty())
                {
                    gsmSal.setError("Entrer votre numero de telephone !");
                    return;
                }*/
                if (email.isEmpty())
                {
                    emailSal.setError("Entrer votre email *");
                    return;
                }
                if (mdp.isEmpty())
                {
                    mdpSal.setError("Entrer un mot de passe *");
                    return;
                }
                if (mdp.length() < 6)
                {
                    mdpSal.setError("Mot de passe doit > 6 charactere*");
                    return;
                }
                if (mdpcnf.isEmpty())
                {
                    mdpConfSal.setError("Confirmer le mot de passe *");
                    return;
                }
                if (!mdp.equals(mdpcnf))
                {
                    mdpConfSal.setError("mot de passe est different !");
                    return;
                }

                // Sign Up new User
                mAuth.createUserWithEmailAndPassword(email,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getContext(), "User created successfuly !", Toast.LENGTH_SHORT).show();
                            FragmentTransaction fr = getFragmentManager().beginTransaction();
                            fr.replace(R.id.nav_host_fragment,new SeConnecterFragment()).addToBackStack("tag");
                            fr.commit();
                        }else {
                            Toast.makeText(getContext(), "Error !"+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG,task.getException().toString());
                        }
                    }
                });

                Toast.makeText(getContext(), "Bien ajouter", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }
}
