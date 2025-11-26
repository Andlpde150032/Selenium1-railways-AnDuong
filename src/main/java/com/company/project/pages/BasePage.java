
package com.company.project.pages;

import com.company.project.drivers.DriverManager;
import com.company.project.helpers.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BasePage - Base class for all page objects
 */
public class BasePage {

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected void click(By locator) {
        ElementHelper.click(locator);
    }

    protected void enterText(By locator, String text) {
        ElementHelper.enterText(locator, text);
    }

    protected String getText(By locator) {
        return ElementHelper.getText(locator);
    }

    protected void selectDropdown(By locator, String visibleText) {
        ElementHelper.selectDropdown(locator, visibleText);
    }

    protected void scroll(By locator) {
        ElementHelper.scrollToElement(locator);
    }

    public void openUrl(String url) {
        getDriver().get(url);
    }
}
