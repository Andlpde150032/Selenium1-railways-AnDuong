/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function DriverManager - Manage WebDriver instances
 */
package com.company.project.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser, String implicitWait) {
        if (driver.get() == null) {
            WebDriver webDriver;
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--start-maximized");
                    webDriver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    webDriver = new org.openqa.selenium.firefox.FirefoxDriver();
                    break;
                case "edge":
                    webDriver = new org.openqa.selenium.edge.EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported.");
            }

            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));
            webDriver.manage().window().maximize();
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
