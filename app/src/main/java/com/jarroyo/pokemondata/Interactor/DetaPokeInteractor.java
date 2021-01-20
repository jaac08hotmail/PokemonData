package com.jarroyo.pokemondata.Interactor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.WindowManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jarroyo.pokemondata.Interactor.Model.DetallePokemonModel;
import com.jarroyo.pokemondata.Interfaces.iDetaPokeInteractor;
import com.jarroyo.pokemondata.Interfaces.iDetaPokePresenter;
import com.jarroyo.pokemondata.Utils.General;

import org.json.JSONObject;

import java.net.URL;

public class DetaPokeInteractor implements iDetaPokeInteractor {

    iDetaPokePresenter detaPokePresenter;

    public DetaPokeInteractor(iDetaPokePresenter detaPokePresenter) {
        this.detaPokePresenter = detaPokePresenter;
    }


    @Override
    public void consultarDetalle(String urlApi) {
        StringRequest postRequest = new StringRequest(Request.Method.GET, urlApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject result = new JSONObject(response);
                            DetallePokemonModel detallePokemonModel = new Gson().fromJson(result.toString(), DetallePokemonModel.class);
                            if (detallePokemonModel != null) {
                                detaPokePresenter.resultadoDetalle(detallePokemonModel);
                            }
                            else
                                detaPokePresenter.errorConsulta("No se Encontro Detalle!!!");

                        } catch (Exception e) {
                            detaPokePresenter.errorConsulta(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },errorListener
        );
        try {
            postRequest.setRetryPolicy(new DefaultRetryPolicy(8000,
                    0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(General.context).add(postRequest);
        }
        catch(Exception e ){
            detaPokePresenter.errorConsulta(e.getMessage());
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
                    detaPokePresenter.errorConsulta(error.toString());
                    return;
                }
                String msj = error.getMessage();
                error.printStackTrace();
                if (msj == null) {
                    detaPokePresenter.errorConsulta("Servidor No Responde");
                } else {
                    detaPokePresenter.errorConsulta( msj.toString());
                }
                return;
            }
            catch(WindowManager.BadTokenException e){
                detaPokePresenter.errorConsulta( e.getMessage());
                e.printStackTrace();
            }
            catch(Exception e){
                detaPokePresenter.errorConsulta( e.getMessage());
                e.printStackTrace();
            }
        }
    };



}
