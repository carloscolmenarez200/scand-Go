package com.cencosud.scan_commons.store.domain.model;

/**
 * Created by joseamaro on 27-06-18.
 */

public class Store implements Comparable{

    public String name;
    public String address;
    public String phoneNumber;
    public String mail;
    public String image;
    public String local;
    public String latitude;
    public String longitude;
    public String annexed;
    public boolean available;


    public float distance;

    @Override
    public int compareTo(Object another) {
        int value = 0;
        Store other = (Store) another;
        if (distance > other.distance) {
            value = 1;
        } else if (distance < other.distance) {
            value = -1;
        } else {
            value = 0;
        }
        return value;
    }
}
