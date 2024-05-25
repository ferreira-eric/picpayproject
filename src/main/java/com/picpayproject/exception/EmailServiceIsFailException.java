package com.picpayproject.exception;

import java.io.Serial;

public class EmailServiceIsFailException extends Exception{

    @Serial
    private static final long serialVersionUID = -2639133022398753948L;

    public EmailServiceIsFailException() {
        super("Email Service is Fail");
    }
}
