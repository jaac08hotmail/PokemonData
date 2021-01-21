package com.jarroyo.pokemondata.Presenters;

import com.jarroyo.pokemondata.Interactor.ListBerryInteractor;
import com.jarroyo.pokemondata.Interactor.Model.BerryModel;
import com.jarroyo.pokemondata.Interfaces.iListBerryInteractor;
import com.jarroyo.pokemondata.Interfaces.iListBerryPresenter;
import com.jarroyo.pokemondata.Interfaces.iListBerryView;

import java.util.ArrayList;

public class ListBerryPresenter implements iListBerryPresenter {

   private iListBerryView berryView;
   private iListBerryInteractor berryInteractor;

    public ListBerryPresenter(iListBerryView berryView) {
        this.berryView = berryView;
        this.berryInteractor = new ListBerryInteractor(this);
    }


    @Override
    public void consultarDatos(String urlApi) {
        if (berryInteractor!=null)
            berryInteractor.consultarDatos(urlApi);
    }

    @Override
    public void resultadoDatos(ArrayList<BerryModel> arrayBerryModel) {
        if(berryView!=null)
            berryView.resultadoDatos(arrayBerryModel);

    }

    @Override
    public void actualizaFavorito(ArrayList<BerryModel> arrayBerryModel,Integer pos) {
        berryInteractor.actualizaFavorito(arrayBerryModel,pos);
    }

    @Override
    public void errorConsulta(String error) {
        if(berryView!=null)
            berryView.errorConsulta(error);
    }
}
