
package com.company.project.base;

import com.company.project.config.PropertiesLoader;
import com.company.project.drivers.DriverManager;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BaseTest - Base class for all tests
 */
public class BaseTest {

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        String implicitWait = PropertiesLoader.getProperty("implicit.wait");
        String browser = PropertiesLoader.getProperty("browser");
        if (browser == null) {
            browser = "chrome"; // Default to chrome if not specified
        }
        DriverManager.initDriver(browser, implicitWait);
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
