package com.jarroyo.pokemondata.Interfaces;

import android.graphics.Bitmap;

import com.jarroyo.pokemondata.Interactor.Model.DetallePokemonModel;

public interface iDetaPokeView {
    void resultadoDetalle(DetallePokemonModel detallePokemon);
    void errorConsulta(String error);
}
