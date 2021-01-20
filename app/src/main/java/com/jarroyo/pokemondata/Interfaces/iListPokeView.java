package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Pokemon;

import java.util.ArrayList;

public interface iListPokeView {
    void resultadoDatos(ArrayList<Pokemon> arrayPokemon);
    void errorConsulta(String error);
}
