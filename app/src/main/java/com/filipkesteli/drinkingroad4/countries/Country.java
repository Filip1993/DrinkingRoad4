package com.filipkesteli.drinkingroad4.countries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 30.5.2016..
 */
public abstract class Country {

    //Privatne varijable za stvaranje poslovne logike:
    private int minSips;
    private int maxSips;
    private List<Integer> possibleSips = new ArrayList<>();

    //Otvaramo se Constructor-om prema svim drzavama i ostalim klasama unutar projekta
    public Country(int minSips, int maxSips) {
        this.minSips = minSips;
        this.maxSips = maxSips;
        for (int i = minSips; i <= maxSips; i++) {
            this.possibleSips.add(i);
        }
    }

    //Otvaramo se getterom MainActivityju i ostalim Activityjima kako bismo mogli radit logiku
    public List<Integer> getPossibleSips() {
        return possibleSips;
    }
}
