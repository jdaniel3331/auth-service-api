package com.jdaniel3331.authserviceapi.repositories;

import com.jdaniel3331.authserviceapi.models.DeviceSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceSessionRepository extends JpaRepository<DeviceSession, UUID> {
}
