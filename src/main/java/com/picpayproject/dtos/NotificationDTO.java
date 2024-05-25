package com.picpayproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class NotificationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5793320773296274588L;

    private String email;

    private String message;
}
