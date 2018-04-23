package com.ase2018.fmfm;

import java.util.ArrayList;
import java.util.List;

public class Substitute {
    public ArrayList<Ingredient> sub;
    public double distance;

    public Substitute(Ingredient sub, double distance){
        this.sub = new ArrayList<>(1);
        this.sub.add(sub);
        this.distance = distance;
    }

    public Substitute(List<Ingredient> sub, double distance){
        this.sub = new ArrayList<>(sub);
        this.distance = distance;
    }


    @Override
    public String toString() {
        return "-> "+sub+"["+distance+"]";
    }

    public void addIngredient(Ingredient ingredient) {
        sub.add(ingredient);
    }
}
