package com.tutorial.spring_boot_tutorial.main.domain.res;

public class MainResponse {
    private String c;
    private String d;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "MainResponse{" + "c='" + c + '\'' + ", d='" + d + '\'' + '}';
    }
}
