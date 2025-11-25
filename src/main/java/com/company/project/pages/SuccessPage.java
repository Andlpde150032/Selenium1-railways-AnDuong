package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function SuccessPage - Page object for Success Page (e.g. after booking)
 */
public class SuccessPage extends BasePage {

    private final By successMessage = By.cssSelector("h1");

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
