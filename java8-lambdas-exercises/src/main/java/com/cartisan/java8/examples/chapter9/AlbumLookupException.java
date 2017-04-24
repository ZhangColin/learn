package com.cartisan.java8.examples.chapter9;

public class AlbumLookupException extends RuntimeException {
    public AlbumLookupException(String message) {
        super(message);
    }

    public AlbumLookupException(Throwable cause) {
        super(cause);
    }
}
