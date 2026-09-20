package com.jdaniel3331.authserviceapi.repositories;

import com.jdaniel3331.authserviceapi.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}
