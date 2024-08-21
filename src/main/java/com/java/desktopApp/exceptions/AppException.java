package com.java.desktopApp.exceptions;

public class AppException extends RuntimeException{


    public AppException(String message){
        super(message);
    }

    public AppException(String message, Throwable causa){
        super(message,causa);
    }

}
