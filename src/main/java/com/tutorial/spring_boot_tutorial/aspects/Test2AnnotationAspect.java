package com.tutorial.spring_boot_tutorial.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tutorial.spring_boot_tutorial.annotations.Test2Annotation;
import com.tutorial.spring_boot_tutorial.utils.DBCrypto;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class Test2AnnotationAspect {
    @Autowired
    private DBCrypto dbCrypto;

    @After("execution(* com.tutorial.spring_boot_tutorial.**.mapper..*(..)) && args(obj,..)")
    public void afterMethodExecution(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Test2Annotation.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value instanceof String) {
                    try {
                        String newValue = dbCrypto.decrypt((String) value);
                        field.set(obj, newValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
