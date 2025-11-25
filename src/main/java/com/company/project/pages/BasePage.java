package com.company.project.pages;

import com.company.project.drivers.DriverManager;
import com.company.project.helpers.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        WebElement element = getDriver().findElement(locator);
        ElementHelper.scrollToElement(element);
    }
}
