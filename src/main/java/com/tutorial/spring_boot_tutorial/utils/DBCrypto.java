package com.tutorial.spring_boot_tutorial.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DBCrypto {
    @Value("${key.database.aes-key}")
    private String key;

    public String encrypt(String plainText) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec IV = new IvParameterSpec(key.substring(0, 16).getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);

            byte[] encryptedText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e);
        }
    }

    public String decrypt(String encryptedText) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec IV = new IvParameterSpec(key.substring(0, 16).getBytes());

            cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);

            byte[] decryptedByte = Base64.getDecoder().decode(encryptedText);

            return new String(cipher.doFinal(decryptedByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e);
        }
    }
}
