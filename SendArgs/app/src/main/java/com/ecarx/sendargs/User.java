package com.ecarx.sendargs;

import java.io.Serializable;

/**
 * Created by Lenovo on 2018/8/20.
 */
public class User implements Serializable{
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }
}
