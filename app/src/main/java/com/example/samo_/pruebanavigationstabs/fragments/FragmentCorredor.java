package com.example.samo_.pruebanavigationstabs.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.samo_.pruebanavigationstabs.R;

/**
 * Created by samo_ on 23/01/2017.
 */
public class FragmentCorredor extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ImageButton corredor;
    private Button reiniciar;
    private int cont;
    private View v;

    public FragmentCorredor() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_corredor, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cont = 0;
        corredor = (ImageButton) v.findViewById(R.id.corredor);
        reiniciar = (Button) v.findViewById(R.id.reiniciar);
        reiniciar.setEnabled(false);
        reiniciar.setVisibility(View.INVISIBLE);

        corredor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont == 10){
                    corredor.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.corredorcansado, null));
                }
                if(cont == 15){
                    corredor.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.corredordolorido, null));
                }
                if(cont == 20){
                    corredor.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.meta, null));
                    reiniciar.setEnabled(true);
                    reiniciar.setVisibility(View.VISIBLE);
                }
                if(cont <= 20){
                    cont++;
                }
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont = 0;
                reiniciar.setEnabled(false);
                reiniciar.setVisibility(View.INVISIBLE);
                corredor.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.corredor, null));
            }
        });
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

