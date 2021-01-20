package com.jarroyo.pokemondata.Presenters;


import android.graphics.Bitmap;

import com.jarroyo.pokemondata.Interactor.DetaPokeInteractor;
import com.jarroyo.pokemondata.Interactor.Model.DetallePokemonModel;
import com.jarroyo.pokemondata.Interfaces.iDetaPokeInteractor;
import com.jarroyo.pokemondata.Interfaces.iDetaPokePresenter;
import com.jarroyo.pokemondata.Interfaces.iDetaPokeView;

public class DetaPokePresenter implements iDetaPokePresenter {

    iDetaPokeView detaPokeView;
    iDetaPokeInteractor detapokeInteractor;

    public DetaPokePresenter(iDetaPokeView detaPokeView) {
        this.detaPokeView = detaPokeView;
        this.detapokeInteractor = new DetaPokeInteractor(this);
    }

    @Override
    public void consultarDetalle(String urlApi) {
        detapokeInteractor.consultarDetalle(urlApi);
    }


    @Override
    public void errorConsulta(String error) {
        detaPokeView.errorConsulta(error);
    }

    @Override
    public void resultadoDetalle(DetallePokemonModel detallePokemon) {
        detaPokeView.resultadoDetalle(detallePokemon);
    }

}
