package com.admin.catalago;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {

    protected final ID id;

    public Entity(final ID id) {
        Objects.requireNonNull(id,"Id não pode ser nulo");
        this.id = id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
