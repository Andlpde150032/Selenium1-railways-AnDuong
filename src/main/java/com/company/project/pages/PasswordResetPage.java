package com.company.project.pages;

import org.openqa.selenium.By;

public class PasswordResetPage extends BasePage {

    private final By newPasswordInput = By.id("newPassword");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By resetTokenInput = By.id("resetToken");
    private final By resetPasswordButton = By.cssSelector("input[title='Reset password']");
    private final By formErrorMessage = By.cssSelector("p.message.error");
    private final By tokenErrorMessage = By.xpath("//li[@class='reset-token']/label[@class='validation-error']");
    private final By confirmPasswordErrorMessage = By
            .xpath("//li[@class='confirm-password']/label[@class='validation-error']");

    public void resetPassword(String newPassword, String confirmPassword) {
        enterText(newPasswordInput, newPassword);
        enterText(confirmPasswordInput, confirmPassword);
        // Clear token if it has any value (though we expect it to be empty for this
        // test)
        getDriver().findElement(resetTokenInput).clear();
        click(resetPasswordButton);
    }

    public String getFormErrorMessage() {
        return getText(formErrorMessage);
    }

    public String getTokenErrorMessage() {
        return getText(tokenErrorMessage);
    }

    public String getConfirmPasswordErrorMessage() {
        return getText(confirmPasswordErrorMessage);
    }
}
