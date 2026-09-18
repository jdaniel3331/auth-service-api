CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE SCHEMA auth;

CREATE TABLE auth.user_accounts (
    user_account_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	username VARCHAR(32) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_locked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

CREATE TABLE auth.roles (
    role_id NUMERIC(1) PRIMARY KEY NOT NULL,
    name VARCHAR(32) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE auth.users_roles (
    user_account_id UUID NOT NULL REFERENCES auth.user_accounts ON DELETE CASCADE,
    role_id NUMERIC(1) NOT NULL REFERENCES auth.roles ON DELETE RESTRICT,
    PRIMARY KEY (user_account_id, role_id)
);

CREATE TABLE auth.device_sessions (
    device_session_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_account_id UUID NOT NULL REFERENCES auth.user_accounts ON DELETE CASCADE,
    device_fingerprint_hash VARCHAR(64) NOT NULL,
    device_name VARCHAR(120),
    ip_address INET NOT NULL,
    is_revoked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_active_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    revoked_at TIMESTAMPTZ,
    CHECK (is_revoked = (revoked_at IS NOT NULL))
);

CREATE TABLE auth.refresh_tokens (
    refresh_token_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    session_id UUID NOT NULL REFERENCES auth.device_sessions ON DELETE CASCADE,
    token_hash VARCHAR(64) NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_revoked BOOLEAN NOT NULL DEFAULT FALSE,
    revoked_at TIMESTAMPTZ,
    replaced_by_token_id UUID REFERENCES auth.refresh_tokens ON DELETE SET NULL,
    CHECK (expires_at > created_at AND is_revoked = (revoked_at IS NOT NULL))
);


CREATE INDEX idx_users_roles_role_id ON auth.users_roles(role_id);
CREATE INDEX idx_device_sessions_user_fingerprint ON auth.device_sessions(user_account_id, device_fingerprint_hash);
CREATE INDEX idx_refresh_tokens_session_id ON auth.refresh_tokens(session_id);
CREATE INDEX idx_refresh_tokens_expires_at ON auth.refresh_tokens(expires_at) WHERE is_revoked = FALSE;

INSERT INTO auth.roles (role_id, name) VALUES (1, 'ADMIN'), (2, 'USER');

-- Password 12345

INSERT INTO auth.user_accounts (username, email, password_hash) VALUES ('admin', 'admin@example.com', '$2a$12$64egJrjeiBO53.g6VKKHE.TZhCh.A.X1vYKGfK9dmIXgnMvqF/.KW');
INSERT INTO auth.users_roles ( user_account_id, role_id) VALUES ((SELECT ua.user_account_id FROM auth.user_accounts ua WHERE ua.email = 'admin@example.com'), 1);