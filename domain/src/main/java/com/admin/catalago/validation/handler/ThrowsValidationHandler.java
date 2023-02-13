package com.admin.catalago.validation.handler;

import com.admin.catalago.exception.DomainException;
import com.admin.catalago.validation.Error;
import com.admin.catalago.validation.ValidateHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidateHandler {
    @Override
    public ValidateHandler append(Error error) {
        throw DomainException.with(List.of(error));
    }

    @Override
    public ValidateHandler append(ValidateHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidateHandler append(Validation validation) {
        try{
            validation.validate();
        }catch (final Exception ex){
            throw DomainException.with(List.of(new Error(ex.getMessage())));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
