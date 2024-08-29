package com.tutorial.spring_boot_tutorial.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.tutorial.spring_boot_tutorial.common.CodeMessage;
import com.tutorial.spring_boot_tutorial.common.CustomException;
import lombok.extern.slf4j.Slf4j;

/**
 * AES 암복호화 클래스
 */
@Slf4j
@Component
public class AesCrypto {
    @Value("${key.database.aes-key}")
    private String databaseKey;

    @Value("${key.section.aes-key}")
    private String sectionKey;

    /**
     * 암호화
     * 
     * @param plainText
     * @return
     * @throws CustomException
     */
    public String encrypt(String plainText, String type) throws CustomException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String key = type.equals("database") ? databaseKey : sectionKey;

            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec IV = new IvParameterSpec(key.substring(0, 16).getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);

            byte[] encryptedText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(CodeMessage.ER0001);
        }
    }

    /**
     * 복호화
     * 
     * @param encryptedText
     * @return
     * @throws CustomException
     */
    public String decrypt(String encryptedText, String type) throws CustomException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String key = type.equals("database") ? databaseKey : sectionKey;

            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec IV = new IvParameterSpec(key.substring(0, 16).getBytes());

            cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);

            byte[] decryptedByte = Base64.getDecoder().decode(encryptedText);

            return new String(cipher.doFinal(decryptedByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(CodeMessage.ER0001);
        }
    }
}
