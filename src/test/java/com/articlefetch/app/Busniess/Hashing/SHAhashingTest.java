package com.articlefetch.app.Busniess.Hashing;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.articlefetch.app.Busniess.Hashing.SHAhashing.generateHashPassword;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SHAhashingTest {


    @Test
    void generateHashPassword() throws InvalidKeySpecException, NoSuchAlgorithmException {

        String Password = "password";
        String newPasswordHash = SHAhashing.generateHashPassword(Password);

        assertEquals(SHAhashing.generateHashPassword(Password), "1000:50bcca48b8f8f5d43cb3d8dfa52e32f9:5596527bb4" +
                "2f5f8dc3882febd389202084c104686ce12dcec9d2cfea95d0efe633876a13a2a39b818e8acee09378074e4deca19e5599eae13bc5c3159e117c7e\n");
    }

    @Test
    void checkPassword() {
    }

    @Test
    void toHex() {
    }

    @Test
    void fromHex() {
    }
}