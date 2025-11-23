package com.company.project.pages;

import com.company.project.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void click(By locator) {
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
        WebElement element = waitForElementVisible(locator);
        scrollToElement(element);
        return element.getText();
    }

    protected WebElement waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
