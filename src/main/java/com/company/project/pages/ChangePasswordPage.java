package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function ChangePasswordPage - Page object for Change Password Page
 */
public class ChangePasswordPage extends BasePage {

    private final By pageHeading = By.xpath("//h1[contains(text(),'Change Password')]");

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
}
