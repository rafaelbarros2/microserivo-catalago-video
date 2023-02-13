package com.admin.catalago.validation;

import java.util.List;

public interface ValidateHandler {

    ValidateHandler append(Error error);

    ValidateHandler append(ValidateHandler anHandler);

    ValidateHandler append(Validation validation);

    List<Error> getErrors();
    public interface Validation{
        void validate();
    }

    default boolean hasError(){
        return getErrors() != null && !getErrors().isEmpty();
    }
}
