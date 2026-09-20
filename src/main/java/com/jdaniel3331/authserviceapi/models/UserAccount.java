package com.jdaniel3331.authserviceapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_accounts", schema = "auth")
public class UserAccount {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "user_account_id", nullable = false)
    private UUID userAccountId;

    @Size(max = 32)
    @NotNull
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "userAccount")
    private Set<DeviceSession> deviceSessions = new LinkedHashSet<>();

    @ManyToMany
    private Set<Role> roles = new LinkedHashSet<>();


}