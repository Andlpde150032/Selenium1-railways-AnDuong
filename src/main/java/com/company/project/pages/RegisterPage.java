package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function RegisterPage - Page object for Register Page
 */
public class RegisterPage extends BasePage {

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By pidInput = By.id("pid");
    private final By registerButton = By.cssSelector("input[title='Register']");
    private final By successMessage = By.xpath("//div[@id='content']/p");
    private final By mainErrorMessage = By.xpath("//*[contains(text(), \"There're errors in the form\")]");
    private final By passwordErrorMessage = By
            .xpath("//input[@id='password']/following-sibling::label[@class='validation-error']");
    private final By confirmPasswordErrorMessage = By.cssSelector("label.validation-error");
    private final By pidErrorMessage = By
            .xpath("//input[@id='pid']/following-sibling::label[@class='validation-error']");

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

    public String getMainErrorMessage() {
        return getText(mainErrorMessage);
    }

    public String getPasswordErrorMessage() {
        return getText(passwordErrorMessage);
    }

    public String getConfirmPasswordErrorMessage() {
        return getText(confirmPasswordErrorMessage);
    }

    public String getPidErrorMessage() {
        return getText(pidErrorMessage);
    }
}
