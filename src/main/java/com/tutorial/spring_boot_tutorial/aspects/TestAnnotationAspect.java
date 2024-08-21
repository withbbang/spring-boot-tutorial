package com.tutorial.spring_boot_tutorial.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tutorial.spring_boot_tutorial.annotations.TestAnnotation;
import com.tutorial.spring_boot_tutorial.utils.DBCrypto;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class TestAnnotationAspect {
    @Autowired
    private DBCrypto dbCrypto;

    @Before("execution(* com.tutorial.spring_boot_tutorial.**.mapper..*(..)) && args(obj,..)")
    public void beforeMethodExecution(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(TestAnnotation.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value instanceof String) {
                    try {
                        String newValue = dbCrypto.encrypt((String) value);
                        field.set(obj, newValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
