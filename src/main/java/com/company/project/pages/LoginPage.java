package com.company.project.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("input[title='Login']");
    private final By welcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By errorMessage = By.xpath("//p[contains(@class, 'message') and contains(@class, 'error')]");

    public void login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
        click(loginButton);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
