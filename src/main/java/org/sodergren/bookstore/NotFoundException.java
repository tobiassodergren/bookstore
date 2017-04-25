package org.sodergren.bookstore;

import java.util.UUID;

public class NotFoundException extends Exception {
    private final String entityType;
    private final UUID uuid;

    public NotFoundException(String entityType, UUID uuid) {
        this.entityType = entityType;
        this.uuid = uuid;
    }

    @Override
    public String getMessage() {
        return "Entity " + entityType + " with id " + uuid + " not found";
    }
}
