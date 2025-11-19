
/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: Page Object for the Login page.
 */
package demo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    // Using @FindBy for locating elements
    @FindBy(id = "username")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to enter email
    public void enterEmail(String email) {
        emailInput.sendKeys(email);
        System.out.println("Report: Email field filled.");
    }

    // Method to enter password
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        System.out.println("Report: Password field filled.");
    }

    // Method to click login button
    public void clickLoginButton() {
        loginButton.click();
        System.out.println("Report: LoginPage button clicked.");
    }

    // Method to perform login
    public void performLogin(String email, String password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickLoginButton();
    }
}


