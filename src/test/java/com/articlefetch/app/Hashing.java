package com.articlefetch.app;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import static org.junit.Assert.*;
import org.junit.Test;
import static com.articlefetch.app.Busniess.Hashing.SHAhashing.generateHashPassword;

public class Hashing {

    public void testHashing() throws InvalidKeySpecException, NoSuchAlgorithmException {

        String Password = "password";
        String newPasswordHash = generateHashPassword(Password);

        assertEquals(generateHashPassword(Password), "1000:5b4240333032306164:f38d165fce8ce42f59d366139ef5d9e1" +
                "ca1247f0e06e503ee1a611dd9ec40876bb5edb8409f5abe5504aab6628e70cfb3d3a18e99d70357d295002c3d0a308a0");

    }


}
