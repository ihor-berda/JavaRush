package com.javarush.test.level27.lesson06.home01;

import java.util.HashSet;
import java.util.Set;

public class RealEstate {

    private final Set<Apartment> allApartments;
    private final Set<Apartment> activeApartments;

    public RealEstate() {
        allApartments = new HashSet();
        activeApartments = new HashSet();

        //add some data
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
    }

    public Set<Apartment> getAllApartments() {
        return allApartments;
    }

    public void up(Apartment apartment) {
        activeApartments.add(apartment);
    }

    public void revalidate() {
        Set<Apartment> copy;
        synchronized (this) {
            copy = new HashSet<>(allApartments);
        }
        activeApartments.clear();
        for (Apartment apartment : copy) {
            boolean randomValue = Math.random() * 2 % 2 == 0;
            apartment.revalidate(randomValue);
        }
    }
}
