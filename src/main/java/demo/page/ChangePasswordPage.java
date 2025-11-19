package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPage {
    private WebDriver driver;

    // Locators
    private By txtCurrentPassword = By.id("currentPassword");
    private By txtNewPassword = By.id("newPassword");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By btnChangePassword = By.xpath("//input[@value='Change Password']");

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
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
        driver.findElement(btnChangePassword).click();
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterCurrentPassword(currentPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(confirmPassword);
        clickChangePasswordButton();
    }
}