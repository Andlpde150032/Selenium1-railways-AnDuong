package com.company.project.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By pidInput = By.id("pid");
    private final By registerButton = By.cssSelector("input[title='Register']");
    // Using a robust locator for the content area as the message is a text node
    // within it
    private final By successMessage = By.xpath("//div[@id='content']");
    private final By generalErrorMessage = By.cssSelector("p.message.error");

    public void register(String email, String password, String confirmPassword, String pid) {
        enterText(emailInput, email);
        enterText(passwordInput, password);
        enterText(confirmPasswordInput, confirmPassword);
        enterText(pidInput, pid);
        click(registerButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getGeneralErrorMessage() {
        return getText(generalErrorMessage);
    }
}
