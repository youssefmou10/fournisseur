package com.example.pfeupdated.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pfeupdated.R;
import com.example.pfeupdated.ui.newOffre.ListOffreFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;

public class CustomAdapterCatOffre extends RecyclerView.Adapter<CustomAdapterCatOffre.CustomViewHolder> {

    private Context context;
    private ArrayList<ModelCat> items;

    public CustomAdapterCatOffre(Context context, ArrayList<ModelCat> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.row_cat_offre, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        holder.catOffre.setText(items.get(position).getTitle());
        holder.catOffre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, holder.catOffre.getText()+"clicked", Toast.LENGTH_SHORT).show();
                ListOffreFragment listOffreFragment = new ListOffreFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                bundle.putString("nomCat",holder.catOffre.getText().toString());
                listOffreFragment.setArguments(bundle);
                ft.replace(R.id.nav_host_fragment, listOffreFragment);
                ft.commit();
                Log.d("AdapterCat",holder.catOffre.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private Button catOffre;

        public CustomViewHolder(View view) {
            super(view);
            catOffre = view.findViewById(R.id.btnCatOffre);

        }
    }




}
