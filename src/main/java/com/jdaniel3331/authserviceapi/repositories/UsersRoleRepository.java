package com.jdaniel3331.authserviceapi.repositories;

import com.jdaniel3331.authserviceapi.models.UsersRole;
import com.jdaniel3331.authserviceapi.models.UsersRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRoleRepository extends JpaRepository<UsersRole, UsersRoleId> {
}
