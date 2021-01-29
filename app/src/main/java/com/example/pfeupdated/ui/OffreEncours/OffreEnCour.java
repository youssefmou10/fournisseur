package com.example.pfeupdated.ui.OffreEncours;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.pfeupdated.R;

import java.util.ArrayList;

public class OffreEnCour extends Fragment {
    ArrayList<ModelImg> items;
    AdapterEncours adapter;
    RecyclerView recyclerView;
    ProgressBar progbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_offre_en_cour, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewEncours);
        progbar = root.findViewById(R.id.prgbarEncours);

        items = new ArrayList<>();

        progbar.setVisibility(View.VISIBLE);

        items.add(new ModelImg(R.drawable.offre1));
        items.add(new ModelImg(R.drawable.offre2));
        items.add(new ModelImg(R.drawable.offre3));
        items.add(new ModelImg(R.drawable.offre4));
        items.add(new ModelImg(R.drawable.offre5));
        items.add(new ModelImg(R.drawable.n_offre1));

        adapter = new AdapterEncours(getContext(), items);
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView.setAdapter(adapter);
        progbar.setVisibility(View.GONE);


   return root;
    }


}
