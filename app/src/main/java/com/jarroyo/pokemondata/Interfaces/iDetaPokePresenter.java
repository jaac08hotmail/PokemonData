package com.jarroyo.pokemondata.Interfaces;

import android.graphics.Bitmap;

import com.jarroyo.pokemondata.Interactor.Model.DetallePokemonModel;

public interface iDetaPokePresenter {
    void consultarDetalle(String urlApi);
    void errorConsulta(String error);
    void resultadoDetalle(DetallePokemonModel detallePokemon);
}
