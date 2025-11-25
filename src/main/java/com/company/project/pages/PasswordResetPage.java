package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function PasswordResetPage - Page object for Password Reset Page
 */
public class PasswordResetPage extends BasePage {

    private final By newPasswordInput = By.id("newPassword");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By resetTokenInput = By.id("resetToken");
    private final By resetPasswordButton = By.cssSelector("input[title='Reset password']");
    private final By mainErrorMessage = By.cssSelector(".message.error");
    // The token error message is in a label with class validation-error next to the
    // input
    private final By tokenErrorMessage = By
            .xpath("//input[@id='resetToken']/following-sibling::label[@class='validation-error']");

    public void resetPassword(String newPassword, String confirmPassword, String token) {
        enterText(newPasswordInput, newPassword);
        enterText(confirmPasswordInput, confirmPassword);
        enterText(resetTokenInput, token);
        click(resetPasswordButton);
    }

    public String getMainErrorMessage() {
        return getText(mainErrorMessage);
    }

    public String getTokenErrorMessage() {
        return getText(tokenErrorMessage);
    }
}
