package com.tasky.app.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncrypterTest {
    @Test
    public void testPasswordIsHashed() {
        String raw = "mySecretPassword";
        String hashed = PasswordEncrypter.hashPassword(raw);

        assertNotEquals(raw, hashed);
        assertNotNull(hashed);
        assertFalse(hashed.isEmpty());
    }

    @Test
    public void testHashConsistency() {
        String raw = "samePassword";
        String hash1 = PasswordEncrypter.hashPassword(raw);
        String hash2 = PasswordEncrypter.hashPassword(raw);

        assertEquals(hash1, hash2);
    }

    @Test
    public void testHashEmptyString() {
        String hash = PasswordEncrypter.hashPassword("");
        assertNotNull(hash);
        assertFalse(hash.isEmpty(), "Hash of empty string should not be empty");
    }

    @Test
    public void testHashNullPassword() {
        assertThrows(NullPointerException.class, () -> {
            PasswordEncrypter.hashPassword(null);
        });
    }
}
