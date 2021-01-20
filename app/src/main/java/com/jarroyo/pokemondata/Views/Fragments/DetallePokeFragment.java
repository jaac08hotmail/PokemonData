package com.jarroyo.pokemondata.Views.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jarroyo.pokemondata.Interactor.Model.DetallePokemonModel;
import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;
import com.jarroyo.pokemondata.Interactor.Model.StatsModel;
import com.jarroyo.pokemondata.Interfaces.iComunicaFragments;
import com.jarroyo.pokemondata.Interfaces.iDetaPokePresenter;
import com.jarroyo.pokemondata.Interfaces.iDetaPokeView;
import com.jarroyo.pokemondata.Presenters.DetaPokePresenter;
import com.jarroyo.pokemondata.R;
import com.jarroyo.pokemondata.Utils.General;
import com.jarroyo.pokemondata.Views.Adapter.AdapterListPoke;
import com.jarroyo.pokemondata.Views.Adapter.AdapterListStas;

import java.util.ArrayList;
import java.util.Arrays;


public class DetallePokeFragment extends Fragment implements iDetaPokeView {

    iDetaPokePresenter detaPokePresenter;
    iComunicaFragments interfaceComunicaFragments;
    TextView txtVNombre,txtVTamano,txtVExperiencia;
    ImageView imgPokemon;
    Spinner spnHabilidades;
    Button btnBuscar;
    ArrayList<String> listaHabilidades;
    RecyclerView recyclerEstadisticas;
    Activity actividad;
    AdapterListStas adapterListStas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_poke, container, false);

        txtVNombre = view.findViewById(R.id.txtVNombre);
        txtVTamano = view.findViewById(R.id.txtVTamano);
        txtVExperiencia = view.findViewById(R.id.txtVExperiencia);
        spnHabilidades = view.findViewById(R.id.spnHabilidades);
        imgPokemon = view.findViewById(R.id.imgPokemon);
        btnBuscar = view.findViewById(R.id.btnBuscar);
        recyclerEstadisticas = view.findViewById(R.id.reecyclerEstadisticas);


        detaPokePresenter = new DetaPokePresenter(this);
        Bundle objetoPersona = getArguments();
        PokemonModel pokemonModel = null;

        if(objetoPersona !=null){

            pokemonModel = (PokemonModel) objetoPersona.getSerializable("pokemonModel");
            String[] splitUrl =  pokemonModel.getUrl().split("/");
            interfaceComunicaFragments.infoMensaje("Consultando Detalles del Pokemon...",2);
            detaPokePresenter.consultarDetalle(pokemonModel.getUrl());
            Glide.with(this).load(General.g_urlimage + "/" + splitUrl[splitUrl.length-1] + ".png").into(imgPokemon);
        }


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarInternet();
            }
        });
        return view;
    }


    public void buscarInternet(){
        String url = "https://www.google.com/search?q={" + txtVNombre.getText() + "}";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void resultadoDetalle(DetallePokemonModel detallePokemon) {

        if (detallePokemon != null){
            txtVNombre.setText(detallePokemon.getName());
            txtVExperiencia.setText("" + detallePokemon.getBase_experience());
            txtVTamano.setText("" + detallePokemon.getHeight());

            listaHabilidades = new ArrayList<>();
            for(int iter=0; iter < detallePokemon.getAbilities().size(); iter++){
                listaHabilidades.add(detallePokemon.getAbilities().get(iter).getAbility().getName());
            }


            ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(actividad,android.R.layout.simple_spinner_item,listaHabilidades);
            spnHabilidades.setAdapter(adaptador);

            ArrayList<StatsModel> statsModels = detallePokemon.getStats();

            recyclerEstadisticas.setLayoutManager(new LinearLayoutManager(getContext()));
            adapterListStas = new AdapterListStas(getContext(), statsModels);
            recyclerEstadisticas.setAdapter(adapterListStas);
            interfaceComunicaFragments.infoMensaje("",3);
        }
        else
            interfaceComunicaFragments.infoMensaje("No se encontro detalles para el Pokemon!!!" ,1);
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