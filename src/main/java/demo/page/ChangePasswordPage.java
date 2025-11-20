package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class ChangePasswordPage {
    private WebDriver driver;

    // Locators
    private By txtCurrentPassword = By.id("currentPassword");
    private By txtNewPassword = By.id("newPassword");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By btnChangePassword = By.xpath("//input[@value='Change Password']");
    private WebDriverWait wait;

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCurrentPassword(String password) {
        driver.findElement(txtCurrentPassword).sendKeys(password);
    }

    public void enterNewPassword(String newPassword) {
        driver.findElement(txtNewPassword).sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(txtConfirmPassword).sendKeys(confirmPassword);
    }

    public void clickChangePasswordButton() {
        // Scroll the element into view before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(btnChangePassword));
        wait.until(ExpectedConditions.elementToBeClickable(btnChangePassword)).click();
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterCurrentPassword(currentPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(confirmPassword);
        clickChangePasswordButton();
    }
}