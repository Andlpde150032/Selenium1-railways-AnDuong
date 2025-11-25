/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function User - User data model
 */
package com.company.project.models;

import com.company.project.utils.JsonReader;

public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getValidUser() {
        String email = JsonReader.getData("login", "email");
        String password = JsonReader.getData("login", "password");
        return new User(email, password);
    }
}
