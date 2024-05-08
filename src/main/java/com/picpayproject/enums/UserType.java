package com.picpayproject.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    COMMON("COMMON"),
    MERCHANT("MERCHANT");

    private final String name;
}
