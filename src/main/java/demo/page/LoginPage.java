
/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: Page Object for the Login page.
 */
package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By emailInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//input[@type='submit']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter email
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Method to enter password
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Method to click login button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}


