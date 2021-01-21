package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Model.BerryModel;

import java.util.ArrayList;

public interface iListBerryPresenter {
    void consultarDatos(String urlApi);
    void resultadoDatos(ArrayList<BerryModel> arrayBerryModel);
    void actualizaFavorito(ArrayList<BerryModel> arrayBerryModel,Integer pos);
    void errorConsulta(String error);
}
