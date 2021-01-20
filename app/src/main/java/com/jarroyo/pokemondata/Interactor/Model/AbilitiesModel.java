package com.jarroyo.pokemondata.Interactor.Model;

public class AbilitiesModel {
    private AbilityModel ability;
    private boolean is_hidden;
    private int slot;

    public AbilitiesModel() {
    }

    public AbilityModel getAbility() {
        return ability;
    }

    public void setAbility(AbilityModel ability) {
        this.ability = ability;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
