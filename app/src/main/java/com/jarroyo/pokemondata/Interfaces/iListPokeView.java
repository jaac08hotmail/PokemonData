package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;

import java.util.ArrayList;

public interface iListPokeView {
    void resultadoDatos(ArrayList<PokemonModel> arrayPokemonModel);
    void errorConsulta(String error);
}
