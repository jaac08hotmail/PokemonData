package com.jarroyo.pokemondata.Interactor.Model;

public class StatsModel {

    private int base_stat;
    private int effort;
    private StatModel stat;

    public StatsModel() {
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public StatModel getStat() {
        return stat;
    }

    public void setStat(StatModel stat) {
        this.stat = stat;
    }

}
