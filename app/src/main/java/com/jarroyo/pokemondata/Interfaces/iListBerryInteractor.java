package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Model.BerryModel;

import java.util.ArrayList;

public interface iListBerryInteractor {
    void consultarDatos(String urlApi);
    void actualizaFavorito(ArrayList<BerryModel> arrayBerryModel,Integer pos);
}
