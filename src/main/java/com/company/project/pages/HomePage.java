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

    private final By registerTab = By.xpath("//a[contains(@href, 'Register.cshtml')]");

    public void open() {
        getDriver().get(PropertiesLoader.getProperty("base.url"));
    }

    public RegisterPage goToRegisterPage() {
        click(registerTab);
        return new RegisterPage();
    }

}

    