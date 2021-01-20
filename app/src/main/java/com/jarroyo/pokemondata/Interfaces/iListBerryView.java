package com.jarroyo.pokemondata.Interfaces;

import com.jarroyo.pokemondata.Interactor.Model.BerryModel;

import java.util.ArrayList;

public interface iListBerryView {
    void resultadoDatos(ArrayList<BerryModel> arrayBerryModel);
    void errorConsulta(String error);
}
