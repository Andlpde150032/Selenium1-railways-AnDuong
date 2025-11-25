package com.company.project.models;

import com.company.project.utils.JsonReader;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function Registration - Model for registration data
 */
public class Registration {
    @JsonProperty("password")
    private String password;

    @JsonProperty("pid")
    private String pid;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public static Registration getRegistrationData() {
        return JsonReader.getTestData("registration", Registration.class);
    }
}
