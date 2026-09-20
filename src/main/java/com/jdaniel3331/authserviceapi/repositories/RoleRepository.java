package com.jdaniel3331.authserviceapi.repositories;

import com.jdaniel3331.authserviceapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RoleRepository extends JpaRepository<Role, BigDecimal> {
}
