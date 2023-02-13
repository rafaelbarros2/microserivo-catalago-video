package com.admin.catalago.exception;

import com.admin.catalago.validation.Error;

import java.util.List;

public class DomainException  extends NoStackTraceException{

    private List<Error> errors;
    public DomainException(final String eMsg, List<Error> errorList) {
        super(eMsg);
        this.errors = errorList;
    }

    public static DomainException with(List<Error> errorList) {
       return new DomainException("",errorList);
    }
    public static DomainException with(Error err) {
       return new DomainException(err.msg(),List.of(err));
    }

    public List<Error> getErrors() {
        return errors;
    }

}
