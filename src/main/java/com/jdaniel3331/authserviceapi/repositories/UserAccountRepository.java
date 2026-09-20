package com.jdaniel3331.authserviceapi.repositories;

import com.jdaniel3331.authserviceapi.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
}
