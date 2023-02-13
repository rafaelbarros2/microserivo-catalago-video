package com.admin.catalago.exception;

import com.admin.catalago.validation.Error;

import java.util.List;

public class DomainException  extends RuntimeException{

    private List<Error> errors;
    public DomainException(List<Error> errorList) {
        super("",null,true,false);
        this.errors = errorList;
    }

    public static DomainException with(List<Error> errorList) {
       return new DomainException(errorList);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
