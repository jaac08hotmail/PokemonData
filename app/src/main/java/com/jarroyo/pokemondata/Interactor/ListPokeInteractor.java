package com.jarroyo.pokemondata.Interactor;

import android.view.WindowManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;
import com.jarroyo.pokemondata.Interfaces.iListPokeInteractor;
import com.jarroyo.pokemondata.Interfaces.iListPokePresenter;
import com.jarroyo.pokemondata.Utils.General;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class ListPokeInteractor implements iListPokeInteractor {

    private iListPokePresenter pokePresenter;

    public ListPokeInteractor(iListPokePresenter pokePresenter) {
        this.pokePresenter = pokePresenter;
    }


    @Override
    public void consultarDatos(String urlApi) {


        StringRequest postRequest = new StringRequest(Request.Method.GET, urlApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject result = new JSONObject(response);
                            PokemonModel[] pokemonModels = new Gson().fromJson(result.get("results").toString(), PokemonModel[].class);
                            if (pokemonModels != null) {
                                ArrayList<PokemonModel> arrayPokemonModel = new ArrayList<PokemonModel>(Arrays.asList(pokemonModels));
                                pokePresenter.resultadoDatos(arrayPokemonModel);
                            }
                            else
                                pokePresenter.errorConsulta("No se Encontraron Datos!!!");

                        } catch (Exception e) {
                            pokePresenter.errorConsulta(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },errorListener
        );
        try {
            //tiempo de espera de conexcion initialTimeout 4000 maxNumRetries = 0
            postRequest.setRetryPolicy(new DefaultRetryPolicy(8000,
                    0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(General.context).add(postRequest);
        }
        catch(Exception e ){
            pokePresenter.errorConsulta(e.getMessage());
            e.printStackTrace();
        }
    }

    private final Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            NetworkResponse networkResponse = error.networkResponse;
            try {

                if (networkResponse != null && networkResponse.statusCode == 400) {
                    error.printStackTrace();
                    pokePresenter.errorConsulta(error.toString());
                    return;
                }
                String msj = error.getMessage();
                error.printStackTrace();
                if (msj == null) {
                    pokePresenter.errorConsulta("Servidor No Responde");
                } else {
                    pokePresenter.errorConsulta( msj.toString());
                }
                return;
            }
            catch(WindowManager.BadTokenException e){
                pokePresenter.errorConsulta( e.getMessage());
                e.printStackTrace();
            }
            catch(Exception e){
                pokePresenter.errorConsulta( e.getMessage());
                e.printStackTrace();
            }
        }
    };

}
