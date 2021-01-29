package com.example.pfeupdated.ui.seConnecter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.pfeupdated.R;
import com.example.pfeupdated.ui.activerCompte.ActiverCompte;

public class SeConnecterFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    EditText email,motDePasse;
    Button jeMeConn;
    TextView activerCompte;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
       /*final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });*/

        email = root.findViewById(R.id.emailConnecter);
        motDePasse = root.findViewById(R.id.motdepasse);
        activerCompte = root.findViewById(R.id.comptepasactiver);
        jeMeConn = root.findViewById(R.id.jemeconnecte);
        jeMeConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Good !!",Toast.LENGTH_SHORT).show();
            }
        });

        activerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new ActiverCompte()).addToBackStack("tag");
                fr.commit();
            }
        });

        return root;
    }
}