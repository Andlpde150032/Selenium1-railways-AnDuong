
package com.company.project.pages;

import com.company.project.drivers.DriverManager;
import com.company.project.helpers.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void click(By locator) {
<<<<<<< HEAD
        WebElement element = waitForElementClickable(locator);
        scrollToElement(element);
        element.click();
    }

    protected void enterText(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            scrollToElement(element);
            return element.getText();
        } catch (Exception e) {
            return "";
        }
=======
        ElementHelper.click(locator);
    }

    protected void enterText(By locator, String text) {
        ElementHelper.enterText(locator, text);
    }

    protected String getText(By locator) {
        return ElementHelper.getText(locator);
>>>>>>> TC01-Login
    }

    protected void selectDropdown(By locator, String visibleText) {
        ElementHelper.selectDropdown(locator, visibleText);
    }

    protected void scroll(By locator) {
        WebElement element = getDriver().findElement(locator);
        ElementHelper.scrollToElement(element);
    }
}
