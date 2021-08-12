package com.eteration.bank.helpers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateAccountNumber {
    public static String getSecureRandom() {
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("NativePRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Integer.toString(Math.abs(secureRandom.nextInt()));
    }
}
