package com.tutorial.spring_boot_tutorial.main.domain.res;

public class MainResponse {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MainResponse{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
