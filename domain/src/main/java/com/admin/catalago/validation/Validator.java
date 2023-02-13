package com.admin.catalago.validation;

public abstract class Validator {

    private final ValidateHandler handler;


    protected Validator(final ValidateHandler handler) {
        this.handler = handler;
    }

    protected ValidateHandler validateHandler(){
        return this.handler;
    }

    public abstract void validate();
}
