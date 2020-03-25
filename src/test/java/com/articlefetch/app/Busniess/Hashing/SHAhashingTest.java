package com.articlefetch.app.Busniess.Hashing;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.articlefetch.app.Busniess.Hashing.SHAhashing.generateHashPassword;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SHAhashingTest {

    @Test
    void checkPassword() throws InvalidKeySpecException, NoSuchAlgorithmException{

        String Password1 = "password";
        String Password2 = "passwordddd";
        String newPasswordHash = SHAhashing.generateHashPassword(Password1);

        boolean correct = SHAhashing.checkPassword(Password1, newPasswordHash);
        boolean wrong = SHAhashing.checkPassword(Password2, newPasswordHash);

        assertEquals(correct, true);
        assertEquals(wrong, false);










    }

    @Test
    void toHex() {
    }

    @Test
    void fromHex() {
    }
}