package com.tutorial.spring_boot_tutorial.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.tutorial.spring_boot_tutorial.annotations.TestAnnotation;
import java.lang.reflect.Field;

@Aspect
@Component
public class TestAnnotationAspect {
    @Before("execution(* com.tutorial.spring_boot_tutorial.**.service..*(..)) && args(obj,..)")
    public void beforeMethodExecution(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(TestAnnotation.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value instanceof String) {
                    String newValue = ((String) value).toUpperCase();
                    field.set(obj, newValue);
                }
            }
        }
    }
}
