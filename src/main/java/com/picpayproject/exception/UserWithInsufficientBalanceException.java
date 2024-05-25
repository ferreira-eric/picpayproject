package com.picpayproject.exception;

public class UserWithInsufficientBalanceException extends Exception{

    private static final long serialVersionUID = 2468156418267479977L;

    public UserWithInsufficientBalanceException() {
        super("User With Insufficient Balance");
    }

}
