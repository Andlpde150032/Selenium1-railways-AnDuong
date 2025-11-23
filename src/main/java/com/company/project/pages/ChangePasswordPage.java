package com.company.project.pages;

import org.openqa.selenium.By;

public class ChangePasswordPage extends BasePage {

    private final By pageHeading = By.xpath("//h1[contains(text(),'Change Password')]");

    private final By currentPasswordInput = By.id("currentPassword");
    private final By newPasswordInput = By.id("newPassword");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By changePasswordButton = By.cssSelector("input[title='Change password']");
    private final By successMessage = By.xpath("//p[@class='message success']");

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean isPageLoaded() {
        try {
            return getText(pageHeading).contains("Change Password");
        } catch (Exception e) {
            return false;
        }
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterText(currentPasswordInput, currentPassword);
        enterText(newPasswordInput, newPassword);
        enterText(confirmPasswordInput, confirmPassword);
        click(changePasswordButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
