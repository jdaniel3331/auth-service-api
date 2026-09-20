package com.jdaniel3331.authserviceapi.repositories;

import com.jdaniel3331.authserviceapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface RoleRepository extends JpaRepository<Role, BigDecimal> {
}
