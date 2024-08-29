package com.tutorial.spring_boot_tutorial.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tutorial.spring_boot_tutorial.annotations.DatabaseCryptoFieldAnnotation;
import com.tutorial.spring_boot_tutorial.utils.DatabaseCrypto;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;

/**
 * 데이터베이스 암복호화 Aspect
 */
@Aspect
@Component
@Slf4j
public class DatabaseCryptAspect {
    @Autowired
    private DatabaseCrypto databaseCrypto;

    /**
     * Mapper 전 후로 DTO/VO 의 특정 필드를 암호화
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.tutorial.spring_boot_tutorial..mapper.*.*(..))")
    public Object databaseCryptoAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        // Mapper로 전달되는 DTO의 특정 필드를 암호화
        for (Object arg : args) {
            encryptData(arg);
        }

        // Mapper 호출
        Object result;
        try {
            result = joinPoint.proceed(args);
        } catch (Exception e) {
            log.error("DatabaseCryptAspect error: ", e);
            throw e;
        }

        // Mapper에서 반환된 VO의 특정 필드를 복호화
        decryptData(result);

        return result;
    }

    /**
     * Database 암호화
     * 
     * @param arg
     * @throws Exception
     */
    private void encryptData(Object arg) throws Exception {
        if (arg == null)
            return;

        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DatabaseCryptoFieldAnnotation.class)) {
                field.setAccessible(true);
                String originalValue = (String) field.get(arg);
                if (originalValue != null) {
                    try {
                        String newValue = databaseCrypto.encrypt((String) originalValue);
                        field.set(arg, newValue);
                    } catch (Exception e) {
                        log.error("Encrypt Data Error: ", e);
                        throw e;
                    }
                }
            }
        }
    }

    /**
     * Database 복호화
     * 
     * @param arg
     * @throws Exception
     */
    private void decryptData(Object arg) throws Exception {
        if (arg == null)
            return;

        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DatabaseCryptoFieldAnnotation.class)) {
                field.setAccessible(true);
                String originalValue = (String) field.get(arg);
                if (originalValue != null) {
                    try {
                        String newValue = databaseCrypto.decrypt((String) originalValue);
                        field.set(arg, newValue);
                    } catch (Exception e) {
                        log.error("Decrypt Data Error: ", e);
                        throw e;
                    }
                }
            }
        }
    }
}
