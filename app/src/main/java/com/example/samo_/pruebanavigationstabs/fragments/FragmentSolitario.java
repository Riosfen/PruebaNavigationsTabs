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
import android.widget.TextView;

import java.util.Random;
import com.example.samo_.pruebanavigationstabs.R;

/**
 * Created by samo_ on 23/01/2017.
 */
public class FragmentSolitario extends Fragment {
    private OnFragmentInteractionListener mListener;
    private final String[] VALORES = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int primerBotonActivo;
    private int segundoBotonActivo;
    private Button[] listaBotones;
    private Button barajar;
    private TextView mensaje;
    private View v;

    public FragmentSolitario() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_solitario, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        primerBotonActivo = -1;
        segundoBotonActivo = -1;

        mensaje = (TextView) v.findViewById(R.id.mensaje);
        barajar = (Button) v.findViewById(R.id.barajar);
        listaBotones = new Button[13];
        introducirBotones();

        barajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barajarCartas();
            }
        });

        listaBotones[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 0;
                botonPulsado(posicion);
            }
        });
        listaBotones[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 1;
                botonPulsado(posicion);
            }
        });
        listaBotones[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 2;
                botonPulsado(posicion);
            }
        });
        listaBotones[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 3;
                botonPulsado(posicion);
            }
        });
        listaBotones[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 4;
                botonPulsado(posicion);
            }
        });
        listaBotones[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 5;
                botonPulsado(posicion);
            }
        });
        listaBotones[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 6;
                botonPulsado(posicion);
            }
        });
        listaBotones[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 7;
                botonPulsado(posicion);
            }
        });
        listaBotones[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 8;
                botonPulsado(posicion);
            }
        });
        listaBotones[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 9;
                botonPulsado(posicion);
            }
        });
        listaBotones[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 10;
                botonPulsado(posicion);
            }
        });
        listaBotones[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 11;
                botonPulsado(posicion);
            }
        });
        listaBotones[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = 12;
                botonPulsado(posicion);
                comprobarBarajaOrdenada();
            }
        });
    }

    private void comprobarBarajaOrdenada(){
        boolean ordenados = true;

        for(int i = 0; i < VALORES.length; i++){
            if (listaBotones[i].getText().toString().equals(VALORES[i])) {
            } else {
                ordenados = false;
            }
        }

        if(ordenados){
            mensaje.setText(R.string.gana);
            habilitarDesahiblitarBotones(false);
        }
    }

    private void habilitarDesahiblitarBotones(boolean habilitar){
        for(int i = 0; i < listaBotones.length; i++){
            listaBotones[i].setEnabled(habilitar);
        }
    }

    private void botonPulsado(int posicion){
        boolean ordenados;
        boolean dentroRando;
        String aux;

        if(primerBotonActivo == -1){
            primerBotonActivo = posicion;
        }else{
            segundoBotonActivo = posicion;
        }

        if(primerBotonActivo != -1 && segundoBotonActivo != -1){
            ordenados = ReglaAnterioresOrdenados();
            System.out.println(ordenados);
            dentroRando = ReglaRangoValor();
            System.out.println(dentroRando);
            if(ordenados && dentroRando){
                aux = listaBotones[primerBotonActivo].getText().toString();
                listaBotones[primerBotonActivo].setText(listaBotones[segundoBotonActivo].getText());
                listaBotones[segundoBotonActivo].setText(aux);
                primerBotonActivo = -1;
                segundoBotonActivo = -1;
                mensaje.setText("");
            }else{
                primerBotonActivo = -1;
                segundoBotonActivo = -1;
            }
        }
    }

    private boolean ReglaRangoValor(){
        boolean dentroRango = true;
        boolean salir = false;
        String valor1, valor2;
        int pos1 = -1, pos2 = -1;

        valor1 = listaBotones[primerBotonActivo].getText().toString();
        valor2 = listaBotones[segundoBotonActivo].getText().toString();

        for(int i = 0; i < VALORES.length && !salir; i++){
            if(valor1.compareTo(VALORES[i]) == 0){
                pos1 = i+1;
            }
            if(valor2.compareTo(VALORES[i]) == 0){
                pos2 = i+1;
            }
            if(pos1 != -1 && pos2 != -1){
                salir = true;
            }
        }

        if((pos1 - pos2) > 3 || (pos2 - pos1) > 3){
            dentroRango = false;
            mensaje.setText(R.string.mayor_3);
        }

        return dentroRango;
    }

    private boolean ReglaAnterioresOrdenados(){
        boolean ordenados = true;
        int botonPosicionMenor;
        String valor1, valor2;
        if(primerBotonActivo == 0 || segundoBotonActivo == 0) {
            ordenados = true;
        }else{
            if(primerBotonActivo != -1 || segundoBotonActivo != -1){
                if(primerBotonActivo != segundoBotonActivo) {
                    if (primerBotonActivo < segundoBotonActivo) {
                        botonPosicionMenor = primerBotonActivo;
                    } else {
                        botonPosicionMenor = segundoBotonActivo;
                    }

                    for (int i = 0; i < botonPosicionMenor && ordenados; i++) {
                        if (listaBotones[i].getText().toString().equals(VALORES[i])) {
                        } else {
                            ordenados = false;
                        }
                    }
                }
            }
        }

        if(!ordenados)
            mensaje.setText(R.string.no_ordenados);

        return ordenados;
    }

    private void barajarCartas(){
        int num;
        String[] listaNombre = new String[13];
        int[] numeros = new int[13];
        Random random = new Random();
        //Introducir numeros iniciales
        for(int i = 0; i < 13; i++){
            numeros[i] = -1;
        }

        introducirNombres(listaNombre);

        for(int i = 0; i < listaBotones.length; i++){
            do{
                num = random.nextInt(13);
            }while(encontrarNumero(numeros, num));
            listaBotones[i].setText(listaNombre[num]);
            numeros[i] = num;
        }
        habilitarDesahiblitarBotones(true);
        mensaje.setText("");
        primerBotonActivo = -1;
        segundoBotonActivo = -1;
    }

    private boolean encontrarNumero(int[] listaNumeros, int num){
        int i = 0;
        boolean encontrado = false;

        while(i < listaNumeros.length && !encontrado){

            if(listaNumeros[i] == num){
                encontrado = true;
            }
            i++;
        }

        return encontrado;
    }

    private void introducirNombres(String[] listaNombres){
        listaNombres[0] = "1";
        listaNombres[1] = "2";
        listaNombres[2] = "3";
        listaNombres[3] = "4";
        listaNombres[4] = "5";
        listaNombres[5] = "6";
        listaNombres[6] = "7";
        listaNombres[7] = "8";
        listaNombres[8] = "9";
        listaNombres[9] = "10";
        listaNombres[10] = "J";
        listaNombres[11] = "Q";
        listaNombres[12] = "K";
    }

    private void introducirBotones(){
        listaBotones[0] = (Button) v.findViewById(R.id.btnPosicion1);
        listaBotones[1] = (Button) v.findViewById(R.id.btnPosicion2);
        listaBotones[2] = (Button) v.findViewById(R.id.btnPosicion3);
        listaBotones[3] = (Button) v.findViewById(R.id.btnPosicion4);
        listaBotones[4] = (Button) v.findViewById(R.id.btnPosicion5);
        listaBotones[5] = (Button) v.findViewById(R.id.btnPosicion6);
        listaBotones[6] = (Button) v.findViewById(R.id.btnPosicion7);
        listaBotones[7] = (Button) v.findViewById(R.id.btnPosicion8);
        listaBotones[8] = (Button) v.findViewById(R.id.btnPosicion9);
        listaBotones[9] = (Button) v.findViewById(R.id.btnPosicion10);
        listaBotones[10] = (Button) v.findViewById(R.id.btnPosicion11);
        listaBotones[11] = (Button) v.findViewById(R.id.btnPosicion12);
        listaBotones[12] = (Button) v.findViewById(R.id.btnPosicion13);
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
