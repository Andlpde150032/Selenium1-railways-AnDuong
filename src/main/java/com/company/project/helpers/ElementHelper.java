/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function ElementHelper - Helper class for WebElement actions
 */
package com.company.project.helpers;

import com.company.project.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementHelper {

    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static void scrollToElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.scrollToElement(element).perform();
    }

    public static WebElement waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void click(By locator) {
        WebElement element = waitForElementClickable(locator);
        scrollToElement(element);
        element.click();
    }

    public static void enterText(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(By locator) {
        try {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            List<WebElement> elements = getDriver().findElements(locator);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            if (elements.isEmpty()) {
                return "";
            }

            WebElement element = elements.get(0);
            scrollToElement(element);
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public static void selectDropdown(By locator, String visibleText) {
        WebElement element = waitForElementVisible(locator);
        scrollToElement(element);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }
}
