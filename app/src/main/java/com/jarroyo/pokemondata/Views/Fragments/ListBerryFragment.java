package com.jarroyo.pokemondata.Views.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jarroyo.pokemondata.Interactor.Model.BerryModel;
import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;
import com.jarroyo.pokemondata.Interfaces.iComunicaFragments;
import com.jarroyo.pokemondata.Interfaces.iListBerryPresenter;
import com.jarroyo.pokemondata.Interfaces.iListBerryView;
import com.jarroyo.pokemondata.Interfaces.iListPokePresenter;
import com.jarroyo.pokemondata.Presenters.ListBerryPresenter;
import com.jarroyo.pokemondata.Presenters.ListPokePresenter;
import com.jarroyo.pokemondata.R;
import com.jarroyo.pokemondata.Utils.General;
import com.jarroyo.pokemondata.Views.Adapter.AdapterListBerry;
import com.jarroyo.pokemondata.Views.Adapter.AdapterListPoke;

import java.util.ArrayList;


public class ListBerryFragment extends Fragment implements iListBerryView {

    AdapterListBerry adapterBerry;
    RecyclerView recyclerBerry;
    private iListBerryPresenter listBerryPresenter;
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_berry, container, false);
        recyclerBerry = view.findViewById(R.id.RecyclerBerry);
        listBerryPresenter = new ListBerryPresenter(this);

        consultarDatos();

        return view;
    }

    public void consultarDatos(){

        interfaceComunicaFragments.infoMensaje("Consultando Datos",2);
        listBerryPresenter.consultarDatos(General.g_urlApi+"/berry");
    }

    @Override
    public void resultadoDatos(final ArrayList<BerryModel> arrayBerryModel) {
        interfaceComunicaFragments.infoMensaje("",3);
        recyclerBerry.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterBerry = new AdapterListBerry(getContext(), arrayBerryModel);
        recyclerBerry.setAdapter(adapterBerry);

        adapterBerry.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = arrayBerryModel.get(recyclerBerry.getChildAdapterPosition(view)).getName();
                if (arrayBerryModel.get(recyclerBerry.getChildAdapterPosition(view)).getFavorite()==true)
                    arrayBerryModel.get(recyclerBerry.getChildAdapterPosition(view)).setFavorite(false);
                else
                    arrayBerryModel.get(recyclerBerry.getChildAdapterPosition(view)).setFavorite(true);



            }
        });

    }

    @Override
    public void errorConsulta(String error) {
        interfaceComunicaFragments.infoMensaje("Invovenientes al Consultar Datos: " + error,1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            this.actividad= (Activity) context;
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
        }


    }
}