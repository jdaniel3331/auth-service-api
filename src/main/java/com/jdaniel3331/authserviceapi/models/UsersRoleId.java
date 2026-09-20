package com.jdaniel3331.authserviceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class UsersRoleId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6604735307327686861L;

    @NotNull
    @Column(name = "user_account_id", nullable = false)
    private UUID userAccountId;

    @NotNull
    @Column(name = "role_id", nullable = false, precision = 1)
    private BigDecimal roleId;


}