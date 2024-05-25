package com.picpayproject.dtos;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class AuthorizationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8229646097370468732L;

    public String status;

    public DataAuthoriationDTO data;
}
