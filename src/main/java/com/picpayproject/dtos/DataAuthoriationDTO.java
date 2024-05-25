package com.picpayproject.dtos;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class DataAuthoriationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2304250381872436382L;

    public boolean authorization;
}
