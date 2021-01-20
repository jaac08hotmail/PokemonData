package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Pokemon;

import java.util.ArrayList;

public interface iListPokePresenter {
    void consultarDatos(String urlApi);
    void resultadoDatos(ArrayList<Pokemon> arrayPokemon);
    void errorConsulta(String error);
}
