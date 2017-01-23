package com.example.samo_.pruebanavigationstabs.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.samo_.pruebanavigationstabs.R;

/**
 * Created by samo_ on 23/01/2017.
 */
public class FragmentEcuacion extends Fragment {
    private OnFragmentInteractionListener mListener;
    private TextView textoCoeficiente1;
    private EditText coeficiente1;
    private TextView textoCoeficiente2;
    private EditText coeficiente2;
    private TextView textoCoeficiente3;
    private EditText coeficiente3;
    private Button calcular;
    private Button reiniciar;
    private TextView solucion1;
    private TextView solucion2;
    private View v;

    public FragmentEcuacion() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ecuacion, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textoCoeficiente1 = (TextView) v.findViewById(R.id.primerCoeficiente);
        coeficiente1 = (EditText) v.findViewById(R.id.coeficiente1);
        textoCoeficiente2 = (TextView) v.findViewById(R.id.segundoCoeficiente);
        coeficiente2 = (EditText) v.findViewById(R.id.coeficiente2);
        textoCoeficiente3 = (TextView) v.findViewById(R.id.tercerCoeficiente);
        coeficiente3 = (EditText) v.findViewById(R.id.coeficiente3);
        calcular = (Button) v.findViewById(R.id.Calcular);
        reiniciar = (Button) v.findViewById(R.id.reiniciar);
        solucion1 = (TextView) v.findViewById(R.id.solucion1);
        solucion2 = (TextView) v.findViewById(R.id.solucion2);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    double a = Double.parseDouble(coeficiente1.getText().toString());
                    double b = Double.parseDouble(coeficiente2.getText().toString());
                    double c = Double.parseDouble(coeficiente3.getText().toString());

                    if (a == 0 && b == 0) {
                        if (c == 0) {
                            solucion1.setText("No se ha introducido ningún valor.");
                            habilitarReiniciar();
                        } else {
                            solucion1.setText("No es una ecuación");
                            habilitarReiniciar();
                        }
                    } else {
                        if (a == 0) {
                            if (c != 0) {
                                ecuacionNoCuadratica(b, c);
                            } else {
                                solucion1.setText("Solución:  0");
                                habilitarReiniciar();
                            }
                        } else {
                            if (b == 0) {
                                if (c != 0) {
                                    ecuacionSinCoeficienteB(a, c);
                                } else {
                                    solucion1.setText("Solución:  0");
                                    habilitarReiniciar();
                                }
                            } else {
                                if (c != 0) {
                                    ecuacionDeSegundoGrado(a, b, c);
                                } else {
                                    solucion1.setText("Solución:  0");
                                    solucion2.setText(Double.toString(((b * -1) / a)));
                                    habilitarReiniciar();
                                }
                            }
                        }
                    }
                }catch(NumberFormatException e){
                    solucion1.setText("Debe introducir valores numericos");
                    habilitarReiniciar();
                }
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coeficiente1.setText("0");
                coeficiente2.setText("0");
                coeficiente3.setText("0");
                solucion1.setText("");
                solucion2.setText("");
                calcular.setVisibility(View.VISIBLE);
                reiniciar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void ecuacionDeSegundoGrado(double a, double b, double c){
        double resultado1;
        double resultado2;
        double interiorRaiz = ((b*b) - (4*a*c));

        if(interiorRaiz < 0){
            solucion1.setText("No tiene solución.");
        }else{
            resultado1 = (((b*-1)-(Math.sqrt(interiorRaiz)))/(2*a));
            resultado2 = (((b*-1)+(Math.sqrt(interiorRaiz)))/(2*a));
            solucion1.setText("Solución 1:  " + Double.toString(resultado1));
            solucion2.setText("Solución 2:  " + Double.toString(resultado2));
        }

        habilitarReiniciar();
    };

    public void ecuacionSinCoeficienteB(double a, double c){
        double resultado = ((c*-1)/a);
        solucion1.setText("Solución 1:  " + Double.toString(resultado*-1));
        solucion2.setText("Solución 2:  " + Double.toString(resultado));
        habilitarReiniciar();
    };

    public void ecuacionNoCuadratica(double b, double c){
        solucion1.setText("Solución:  " + Double.toString((c*-1)/b));
        habilitarReiniciar();
    }

    private void habilitarReiniciar() {
        calcular.setVisibility(View.INVISIBLE);
        reiniciar.setVisibility(View.VISIBLE);
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

