package com.articlefetch.app.Busniess.Hashing;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
    void toHex() throws NoSuchAlgorithmException {

        byte [] test = {1,2,3,4,5,6,7,8};
        String newtest = SHAhashing.toHex(test);

        byte [] test1 = {'a','b','c','d','e','f','g','h'};
        String newtest1 = SHAhashing.toHex(test1);

        byte [] test2 = {'a',2,'b',4,'c',6,'g',8,'f',9};
        String newtest2 = SHAhashing.toHex(test2);

        assertEquals(newtest,"0102030405060708");
        assertEquals(newtest1,"6162636465666768");
        assertEquals(newtest2,"61026204630667086609");
    }


    @Test
    void fromHex() throws NoSuchAlgorithmException {

        String test = "0102030405060708";
        byte [] newtest = SHAhashing.fromHex(test);

        String test1 = "6162636465666768";
        byte [] newtest1 = SHAhashing.fromHex(test1);

        String test2 = "61026204630667086609";
        byte [] newtest2 = SHAhashing.fromHex(test2);

        byte [] correct = {1,2,3,4,5,6,7,8};
        byte [] correct1 = {'a','b','c','d','e','f','g','h'};
        byte [] correct2 = {'a',2,'b',4,'c',6,'g',8,'f',9};

       assertEquals(Arrays.toString(newtest),Arrays.toString(correct));
       assertEquals(Arrays.toString(newtest1),Arrays.toString(correct1));
       assertEquals(Arrays.toString(newtest2),Arrays.toString(correct2));

    }
}