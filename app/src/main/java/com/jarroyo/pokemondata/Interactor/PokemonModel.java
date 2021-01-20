package com.jarroyo.pokemondata.Interactor;

import java.io.Serializable;

public class PokemonModel implements Serializable {

    private String name;
    private String url;

    public PokemonModel() {
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
}
