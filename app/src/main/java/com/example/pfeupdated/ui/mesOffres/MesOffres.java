package com.example.pfeupdated.ui.mesOffres;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.pfeupdated.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MesOffres.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MesOffres#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MesOffres extends Fragment {

    ListView mesOffres;
    ProgressBar bar;
    ArrayList<MyOffre> myOffres;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MesOffres() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MesOffres.
     */
    // TODO: Rename and change types and number of parameters
    public static MesOffres newInstance(String param1, String param2) {
        MesOffres fragment = new MesOffres();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mes_offres, container, false);
        mesOffres = root.findViewById(R.id.listmesoffres);
        bar = root.findViewById(R.id.prgbarmesoffres);

        myOffres = new ArrayList<>();

        MyOffre offre1 = new MyOffre(R.drawable.qrcode,R.drawable.offre1,R.drawable.qrcode,R.drawable.offre4,"-30%","-50%");
        MyOffre offre2 = new MyOffre(R.drawable.qrcode,R.drawable.offre2,R.drawable.qrcode,R.drawable.offre5,"-80%","-70%");
        MyOffre offre3 = new MyOffre(R.drawable.qrcode,R.drawable.offre3,R.drawable.qrcode,R.drawable.offre2,"-40%","-25%");


        myOffres.add(offre1);
        myOffres.add(offre2);
        myOffres.add(offre3);

        AdapterMesOffres adapter = new AdapterMesOffres(getContext(),myOffres);
        mesOffres.setAdapter(adapter);


        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
