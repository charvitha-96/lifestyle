package com.lifestyle.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HexFormat;

public final class HashUtil {
    private static final SecureRandom RNG = new SecureRandom();
    private HashUtil(){}

    public static String generateSaltHex(int bytes) {
        byte[] salt = new byte[bytes];
        RNG.nextBytes(salt);
        return HexFormat.of().formatHex(salt);
    }

    public static String sha256Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] out = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(out);
        } catch (Exception e) {
            throw new IllegalStateException("Hashing failed", e);
        }
    }

    public static String saltedHash(String rawPassword, String saltHex) {
        return sha256Hex(saltHex + ":" + rawPassword);
    }
}