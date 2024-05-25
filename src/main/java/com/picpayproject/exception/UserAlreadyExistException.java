package com.picpayproject.exception;

public class UserAlreadyExistException extends Exception {

    private static final long serialVersionUID = -832646779570180355L;

    public UserAlreadyExistException() {
        super("User Already Exist");
    }
}
