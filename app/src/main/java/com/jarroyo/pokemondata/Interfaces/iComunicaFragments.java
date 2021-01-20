package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;

public interface iComunicaFragments {
    void infoMensaje(String dato,int tipoMensaje);
    void detallePokemon(PokemonModel pokemonModel);
}
