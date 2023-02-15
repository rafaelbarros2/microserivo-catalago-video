package com.admin.catalago.category;

import com.admin.catalago.AggregateRoot;
import com.admin.catalago.validation.ValidateHandler;

import java.time.Instant;


public class Category extends AggregateRoot<CategoryId> {


    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(final CategoryId id, final String name, final String description, final boolean active
            , final Instant createdAt, final Instant updatedAt, final Instant deletedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean isActive){
        final var id = CategoryId.unique();
        final var now = Instant.now();
        final var deleted = isActive ? null : now;
        return new Category(id, aName, aDescription, isActive, now , now,deleted);
    }

    @Override
    public void validate(final ValidateHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category deactivate(){
        if(getDeletedAt() == null){
            this.deletedAt = Instant.now();
        }
        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category activated(){
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }


    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }


}