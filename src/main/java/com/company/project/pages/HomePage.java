package com.company.project.pages;

import com.company.project.config.PropertiesLoader;
import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function HomePage - Page object for Home Page
 */
public class HomePage extends BasePage {

    private final By loginTab = By.xpath("//a[contains(@href, 'Login.cshtml')]");

    public void open() {
        getDriver().get(PropertiesLoader.getProperty("base.url"));
    }

    public LoginPage goToLoginPage() {
        click(loginTab);
        return new LoginPage();
    }
}