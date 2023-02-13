package com.admin.catalago.category;

import com.admin.catalago.validation.Error;
import com.admin.catalago.validation.ValidateHandler;
import com.admin.catalago.validation.Validator;

public class CategoryValidator extends Validator{

    private final Category category;

    public CategoryValidator(final Category accategory, ValidateHandler handler) {
        super(handler);
        this.category = accategory;
    }

    @Override
    public void validate() {
        if(this.category.getName() == null ){
            this.validateHandler().append(new Error("name should not be null"));
        }
    }
}
