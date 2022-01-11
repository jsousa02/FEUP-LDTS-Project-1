package com.g0301.controller;

public abstract class Controller<T> {
    private T model;

    public Controller(T model) {
        this.model = model;
    }

    /**
     * @return the model
     */
    public T getModel() {
        return model;
    }

    /**
     * @brief Sets a new model
     * @param model The new model to be set
     */
    public void setModel(T model) {
        this.model = model;
    }
}
