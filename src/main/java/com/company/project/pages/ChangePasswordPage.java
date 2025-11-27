package com.company.project.pages;

import org.openqa.selenium.By;

import com.company.project.config.PropertiesLoader;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function ChangePasswordPage - Page object for Change Password Page
 */
public class ChangePasswordPage extends BasePage {

    public static final String PAGE_URL = PropertiesLoader.getProperty("base.url") + "Account/ChangePassword.cshtml";

    private final By currentPasswordInput = By.id("currentPassword");
    private final By newPasswordInput = By.id("newPassword");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By changePasswordButton = By.cssSelector("input[title='Change password']");
    private final By successMessage = By.xpath("//p[@class='message success']");

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterText(currentPasswordInput, currentPassword);
        enterText(newPasswordInput, newPassword);
        enterText(confirmPasswordInput, confirmPassword);
        click(changePasswordButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }
}
