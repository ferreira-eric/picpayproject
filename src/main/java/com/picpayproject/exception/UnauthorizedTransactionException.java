package com.picpayproject.exception;

import java.io.Serial;

public class UnauthorizedTransactionException extends Exception {

    @Serial
    private static final long serialVersionUID = 8609848611887568491L;

    public UnauthorizedTransactionException() {
        super("Unauthorized Transaction");
    }
}
