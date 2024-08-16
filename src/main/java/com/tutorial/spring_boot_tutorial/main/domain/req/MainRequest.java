package com.tutorial.spring_boot_tutorial.main.domain.req;

public class MainRequest {
    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "MainRequest{" + "a='" + a + '\'' + ", b='" + b + '\'' + '}';
    }
}
