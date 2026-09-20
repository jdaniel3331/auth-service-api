package com.jdaniel3331.authserviceapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "device_sessions", schema = "auth")
public class DeviceSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_session_id", nullable = false)
    private UUID deviceSessionId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    @Size(max = 64)
    @NotNull
    @Column(name = "device_fingerprint_hash", nullable = false, length = 64)
    private String deviceFingerprintHash;

    @Size(max = 120)
    @Column(name = "device_name", length = 120)
    private String deviceName;

    @NotNull
    @Column(name = "ip_address", nullable = false)
    private InetAddress ipAddress;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_revoked", nullable = false)
    private Boolean isRevoked;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_active_at", nullable = false)
    private OffsetDateTime lastActiveAt;

    @Column(name = "revoked_at")
    private OffsetDateTime revokedAt;


}