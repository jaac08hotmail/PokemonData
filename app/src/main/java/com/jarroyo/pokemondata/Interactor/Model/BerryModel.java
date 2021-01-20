package com.jarroyo.pokemondata.Interactor.Model;

import java.io.Serializable;

public class BerryModel implements Serializable {

    private String name;
    private String url;
    private boolean favorite;

    public BerryModel() {
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

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
