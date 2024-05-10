package com.picpayproject.dtos;

import com.picpayproject.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public final class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4394542370240109360L;

    private final String firstName;

    private final String lastName;

    private final String document;

    private final String email;

    private final String password;

    private final BigDecimal balance;

    private final UserType userType;
}
