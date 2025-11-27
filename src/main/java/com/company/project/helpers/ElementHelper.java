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

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function ElementHelper - Helper class for WebElement actions
 */
public class ElementHelper {

    private static final int TIMEOUT_IN_SECONDS = 2;

    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static void scrollToElement(WebElement element) {
        org.openqa.selenium.interactions.WheelInput.ScrollOrigin scrollOrigin = org.openqa.selenium.interactions.WheelInput.ScrollOrigin
                .fromElement(element);
        new Actions(getDriver()).scrollFromOrigin(scrollOrigin, 0, 200).perform();
    }

    public static void scrollToElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        scrollToElement(element);
    }

    public static WebElement waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void click(By locator) {
        waitForElementClickable(locator).click();
    }

    public static void scrollAndClick(By locator) {
        WebElement element = waitForElementVisible(locator);
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
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_IN_SECONDS));

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

    public static boolean isElementDisplayed(By locator) {
        try {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            List<WebElement> elements = getDriver().findElements(locator);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_IN_SECONDS));
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void selectDropdown(By locator, String visibleText) {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = waitForElementVisible(locator);
                scrollToElement(element);
                Select select = new Select(element);
                select.selectByVisibleText(visibleText);
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                // Retry
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Failed to select dropdown option after retries: " + visibleText);
    }
}
