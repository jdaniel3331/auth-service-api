package com.jdaniel3331.authserviceapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "refresh_tokens", schema = "auth")
public class RefreshToken {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "refresh_token_id", nullable = false)
    private UUID refreshTokenId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "session_id", nullable = false)
    private DeviceSession sessionId;

    @Size(max = 64)
    @NotNull
    @Column(name = "token_hash", nullable = false, length = 64)
    private String tokenHash;

    @NotNull
    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_revoked", nullable = false)
    private Boolean isRevoked;

    @Column(name = "revoked_at")
    private OffsetDateTime revokedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "replaced_by_token_id")
    private RefreshToken replacedByToken;

    @OneToMany(mappedBy = "replacedByToken")
    private Set<RefreshToken> refreshTokens = new LinkedHashSet<>();


}