package com.jarroyo.pokemondata.Interactor.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jarroyo.pokemondata.Utils.EstructuraBD;

import java.io.Serializable;

public class BerryModel implements Serializable {

    private Integer id = 0;
    private String name;
    private String url;
    private Integer favorite = 0;

    public BerryModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void insertarBerry(SQLiteDatabase sqLiteDatabase ){
        ContentValues valores = new ContentValues();

        valores.put("name",this.name);
        valores.put("url",this.url);
        valores.put("favorite",this.favorite);

        sqLiteDatabase.insertOrThrow(EstructuraBD.TABLA_BERRY,null,valores);
    }

    public void actualizarBerry(SQLiteDatabase sqLiteDatabase ){
        ContentValues valores = new ContentValues();

        valores.put("favorite",this.favorite);

        sqLiteDatabase.update(EstructuraBD.TABLA_BERRY,valores,"id=" + this.id,null);
    }

}
