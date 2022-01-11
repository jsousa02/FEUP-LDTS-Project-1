package com.g0301.controller;

public abstract class Controller<T> {
    private T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

}
