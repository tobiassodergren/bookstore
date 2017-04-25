package org.sodergren.restapi.operation;

import org.sodergren.restapi.json.JSON;

import javax.ws.rs.core.Response;
import java.util.UUID;

abstract class OperationBase {
    public abstract Response execute();

    String generateErrorBody(String errorMessage, Throwable e) {
        return JSON.makeObject()
                .put("errorMessage", errorMessage)
                .put("reason", e.getLocalizedMessage())
                .build()
                .toJson();
    }

    UUID convertToUuid(String uuid, String field) throws UUIDException {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new UUIDException("Error converting " + field, e);
        }
    }

}
