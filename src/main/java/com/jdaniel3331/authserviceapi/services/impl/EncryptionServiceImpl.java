package com.jdaniel3331.authserviceapi.services.impl;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements PasswordEncoder {

    private final int saltRounds = 12;

    @Override
    public String encode(@Nullable CharSequence rawPassword) {
        return BCrypt.hashpw(
                rawPassword.toString(),
                BCrypt.gensalt(saltRounds)
        );
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
