package org.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    @NotEmpty
    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
    private String username;
    @NotEmpty
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    private String password;
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;

    public User(@NotEmpty @Size(min = 2, max = 30) String username, @NotEmpty @Size(min = 6, max = 30) String password, @Min(18) int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
