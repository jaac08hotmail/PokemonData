package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.PokemonModel;

import java.util.ArrayList;

public interface iListPokePresenter {
    void consultarDatos(String urlApi);
    void resultadoDatos(ArrayList<PokemonModel> arrayPokemonModel);
    void errorConsulta(String error);
}
