package com.jarroyo.pokemondata.Interactor.Model;

import java.util.ArrayList;

public class DetallePokemonModel {
    private ArrayList<AbilitiesModel> abilities;
    private int base_experience;
    private int height;
    private String name;
    private int order;
    private ArrayList<StatsModel> stats;


    public DetallePokemonModel() {
    }

    public ArrayList<AbilitiesModel> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<AbilitiesModel> abilities) {
        this.abilities = abilities;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ArrayList<StatsModel> getStats() {
        return stats;
    }

    public void setStats(ArrayList<StatsModel> stats) {
        this.stats = stats;
    }
}

