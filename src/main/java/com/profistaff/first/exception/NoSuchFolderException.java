package com.profistaff.first.exception;

public class NoSuchFolderException extends RuntimeException{
    public NoSuchFolderException(Throwable cause) {
        super(cause);
    }

    public NoSuchFolderException() {
    }

    public NoSuchFolderException(String message) {
        super(message);
    }
}
