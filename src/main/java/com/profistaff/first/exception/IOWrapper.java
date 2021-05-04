package com.profistaff.first.exception;

public class IOWrapper extends RuntimeException{
    public IOWrapper() {
    }

    public IOWrapper(String message) {
        super(message);
    }

    public IOWrapper(Throwable cause) {
        super(cause);
    }
}
