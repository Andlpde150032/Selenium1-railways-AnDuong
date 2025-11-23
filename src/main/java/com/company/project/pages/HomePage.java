package com.company.project.pages;

import com.company.project.config.PropertiesLoader;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By loginTab = By.xpath("//span[contains(text(),'Login')]");

    public void open() {
        getDriver().get(PropertiesLoader.getProperty("base.url"));
    }

    public LoginPage goToLoginPage() {
        click(loginTab);
        return new LoginPage();
    }
}
