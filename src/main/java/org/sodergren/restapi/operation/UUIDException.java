package org.sodergren.restapi.operation;

public class UUIDException extends Exception {
    public UUIDException(String message, IllegalArgumentException cause) {
        super(message, cause);
    }
}
