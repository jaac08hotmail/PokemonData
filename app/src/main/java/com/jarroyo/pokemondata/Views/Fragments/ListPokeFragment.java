package com.jarroyo.pokemondata.Views.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;
import com.jarroyo.pokemondata.Interfaces.iComunicaFragments;
import com.jarroyo.pokemondata.Interfaces.iListPokePresenter;
import com.jarroyo.pokemondata.Interfaces.iListPokeView;
import com.jarroyo.pokemondata.Presenters.ListPokePresenter;
import com.jarroyo.pokemondata.R;
import com.jarroyo.pokemondata.Utils.General;
import com.jarroyo.pokemondata.Views.Adapter.AdapterListPoke;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ListPokeFragment extends Fragment implements iListPokeView {

    AdapterListPoke adapterPokemon;
    RecyclerView recyclerPokemon;
    LinearLayoutManager linearLayoutManager;
    iListPokePresenter listPokePresenter;
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_poke,container,false);
        recyclerPokemon = view.findViewById(R.id.RecyclerPokemon);
        listPokePresenter = new ListPokePresenter(this);
        linearLayoutManager = new LinearLayoutManager(getContext());

        consultarDatos();

        return view;
    }

    public void consultarDatos(){

        interfaceComunicaFragments.infoMensaje("Consultando Datos",2);
        listPokePresenter.consultarDatos(General.g_urlApi+"/pokemon");
    }

    @Override
    public void resultadoDatos(final ArrayList<PokemonModel> arrayPokemonModel) {
        interfaceComunicaFragments.infoMensaje("",3);
        recyclerPokemon.setLayoutManager(linearLayoutManager);
        adapterPokemon = new AdapterListPoke(getContext(), arrayPokemonModel);
        recyclerPokemon.setAdapter(adapterPokemon);

        adapterPokemon.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicaFragments.detallePokemon(arrayPokemonModel.get(recyclerPokemon.getChildAdapterPosition(view)));
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

}