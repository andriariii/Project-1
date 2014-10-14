package com.dreamblend.isolusindo.batasalesmanagment.app;

/**
 * Created by andriaripratama on 2/7/14.
 */
public class Store {
    private int id;
    private String name;

    public Store(){}

    public Store(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
