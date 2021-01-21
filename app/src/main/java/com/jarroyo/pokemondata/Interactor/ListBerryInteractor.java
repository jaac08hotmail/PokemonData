package com.jarroyo.pokemondata.Interactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.WindowManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jarroyo.pokemondata.Interactor.Model.BerryModel;
import com.jarroyo.pokemondata.Interfaces.iListBerryInteractor;
import com.jarroyo.pokemondata.Interfaces.iListBerryPresenter;
import com.jarroyo.pokemondata.Utils.ConexionSQliteHelper;
import com.jarroyo.pokemondata.Utils.EstructuraBD;
import com.jarroyo.pokemondata.Utils.General;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class ListBerryInteractor implements iListBerryInteractor {

    private iListBerryPresenter berryPresenter;
    private ConexionSQliteHelper conn;

    public ListBerryInteractor(iListBerryPresenter berryPresenter) {
        this.berryPresenter = berryPresenter;
        this.conn = new ConexionSQliteHelper(General.context,"db_pokedata",null,1);
    }

    public void consultarFavoritos(BerryModel[] berryModels){

        SQLiteDatabase db = conn.getReadableDatabase();
        for (int ite =0; ite<berryModels.length;ite++) {
            Cursor cursor = db.rawQuery("SELECT id,favorite FROM " + EstructuraBD.TABLA_BERRY  + " WHERE name =  '" + berryModels[ite].getName() + "'" ,null);
            cursor.moveToFirst();

            if (cursor.getCount() != 0 )
            {
                berryModels[ite].setId(cursor.getInt(0));
                berryModels[ite].setFavorite(cursor.getInt(1));
                cursor.moveToNext();
            } else {
                berryModels[ite].setId(0);
                berryModels[ite].setFavorite(0);
            }
        }

        ArrayList<BerryModel> arrayBerryModel = new ArrayList<>(Arrays.asList(berryModels));
        berryPresenter.resultadoDatos(arrayBerryModel);

    }
    @Override
    public void consultarDatos(String urlApi) {


        StringRequest postRequest = new StringRequest(Request.Method.GET, urlApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject result = new JSONObject(response);
                            BerryModel[] berryModels = new Gson().fromJson(result.get("results").toString(), BerryModel[].class);
                            if (berryModels != null) {
                                consultarFavoritos(berryModels);
                            }
                            else
                                berryPresenter.errorConsulta("No se Encontraron Datos!!!");

                        } catch (Exception e) {
                            berryPresenter.errorConsulta(e.getMessage());
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
            berryPresenter.errorConsulta(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizaFavorito(ArrayList<BerryModel> arrayBerryModel,Integer pos) {
        SQLiteDatabase db = conn.getWritableDatabase();
        if(arrayBerryModel.get(pos).getId()==0) {
            arrayBerryModel.get(pos).insertarBerry(db);
            Cursor cursor = db.rawQuery("Select id from "+EstructuraBD.TABLA_BERRY +" Where name = '" + arrayBerryModel.get(pos).getName() +"'",null);
            cursor.moveToFirst();

            if (cursor.getCount() != 0 ) {
                arrayBerryModel.get(pos).setId(cursor.getInt(0));
            }
        }
        else
            arrayBerryModel.get(pos).actualizarBerry(db);

        berryPresenter.resultadoDatos(arrayBerryModel);
    }


    private final Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            NetworkResponse networkResponse = error.networkResponse;
            try {

                if (networkResponse != null && networkResponse.statusCode == 400) {
                    error.printStackTrace();
                    berryPresenter.errorConsulta(error.toString());
                    return;
                }
                String msj = error.getMessage();
                error.printStackTrace();
                if (msj == null) {
                    berryPresenter.errorConsulta("Servidor No Responde");
                } else {
                    berryPresenter.errorConsulta( msj.toString());
                }
                return;
            }
            catch(WindowManager.BadTokenException e){
                berryPresenter.errorConsulta( e.getMessage());
                e.printStackTrace();
            }
            catch(Exception e){
                berryPresenter.errorConsulta( e.getMessage());
                e.printStackTrace();
            }
        }
    };

}
