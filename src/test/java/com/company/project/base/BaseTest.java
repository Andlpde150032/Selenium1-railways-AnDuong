package com.company.project.base;

import com.company.project.config.PropertiesLoader;
import com.company.project.drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        String implicitWait = PropertiesLoader.getProperty("implicit.wait");
        DriverManager.initDriver(implicitWait);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
