
package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginPage - Page object for Login Page
 */
public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("input[title='Login']");
    private final By welcomeMessage = By.xpath("//div[@class='account']/strong");

    public void login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
        click(loginButton);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }
}
