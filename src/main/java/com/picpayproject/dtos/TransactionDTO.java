package com.picpayproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public final class TransactionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -9127054446933397245L;

    private BigDecimal value;

    private Long senderId;

    private Long receiverId;
}
