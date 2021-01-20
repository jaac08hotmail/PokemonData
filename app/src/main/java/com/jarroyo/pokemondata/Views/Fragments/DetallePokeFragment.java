package com.jarroyo.pokemondata.Views.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jarroyo.pokemondata.Interactor.DetaPokeInteractor;
import com.jarroyo.pokemondata.Interactor.PokemonModel;
import com.jarroyo.pokemondata.R;


public class DetallePokeFragment extends Fragment {

    DetaPokeInteractor detallePokeInteractor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_poke, container, false);

        Bundle objetoPersona = getArguments();
        PokemonModel pokemonModel = null;

        if(objetoPersona !=null){
            pokemonModel = (PokemonModel) objetoPersona.getSerializable("pokemonModel");
            detallePokeInteractor = new DetaPokeInteractor();
            detallePokeInteractor.consultaDetalle("https://pokeapi.co/api/v2/pokemon/1/");
            //imagen.setImageResource(persona.getImagenid());
            //nombre.setText(persona.getNombre());
        }

        return view;
    }
}