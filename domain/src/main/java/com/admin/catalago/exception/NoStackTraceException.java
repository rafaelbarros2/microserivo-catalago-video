package com.admin.catalago.exception;

public class NoStackTraceException extends RuntimeException{

    public NoStackTraceException(String msg){
        this(msg,null);
    }

    public NoStackTraceException(String msg, final Throwable cause){
        super(msg,cause,true,false);
    }
}
