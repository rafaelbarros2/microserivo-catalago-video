package com.admin.catalago.category;

import com.admin.catalago.validation.Error;
import com.admin.catalago.validation.ValidateHandler;
import com.admin.catalago.validation.Validator;

public class CategoryValidator extends Validator{

    public static final int NAME_MAX_LENGHT = 355;
    public static final int NAME_MIN_LENGHT = 3;
    private final Category category;

    public CategoryValidator(final Category accategory, ValidateHandler handler) {
        super(handler);
        this.category = accategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();

    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if(name == null ){
            this.validateHandler().append(new Error("name should not be null"));
            return;
        }
        if( name.isBlank()){
            this.validateHandler().append(new Error("name should not be empty"));
            return;
        }

        final var lenght =  name.trim().length();
        if(lenght > NAME_MAX_LENGHT || lenght < NAME_MIN_LENGHT){
            this.validateHandler().append(new Error("'name' must between 3 and 255 characters"));

        }
    }
}
