package com.jarroyo.pokemondata.Presenters;

import com.jarroyo.pokemondata.Interactor.ListPokeInteractor;
import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;
import com.jarroyo.pokemondata.Interfaces.iListPokeInteractor;
import com.jarroyo.pokemondata.Interfaces.iListPokePresenter;
import com.jarroyo.pokemondata.Interfaces.iListPokeView;

import java.util.ArrayList;

public class ListPokePresenter implements iListPokePresenter {

   private iListPokeView pokeView;
   private iListPokeInteractor pokeInteractor;

    public ListPokePresenter(iListPokeView pokeView) {
        this.pokeView = pokeView;
        this.pokeInteractor = new ListPokeInteractor(this);
    }


    @Override
    public void consultarDatos(String urlApi) {
        if (pokeInteractor!=null)
            pokeInteractor.consultarDatos(urlApi);
    }

    @Override
    public void resultadoDatos(ArrayList<PokemonModel> arrayPokemonModel) {
        if(pokeView!=null)
            pokeView.resultadoDatos(arrayPokemonModel);

    }

    @Override
    public void errorConsulta(String error) {
        if(pokeView!=null)
            pokeView.errorConsulta(error);
    }
}
