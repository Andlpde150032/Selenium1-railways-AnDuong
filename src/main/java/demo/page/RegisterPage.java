/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: Page Object for the Register page.
 */
package demo.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    private WebDriver driver;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "input#confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@id='pid']")
    private WebElement pidField;

    @FindBy(xpath = "//p[@class='form-actions']/input[@type='submit']")
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
    }

    public void enterPid(String pid) {
        pidField.sendKeys(pid);
    }

    public void clickRegisterButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", registerButton);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registerButton.click();
    }

    public void performRegistration(String email, String password, String pid) {
        System.out.println("Report: Filling registration form...");
        enterEmail(email);
        System.out.println("Report: Email field filled.");
        enterPassword(password);
        System.out.println("Report: Password field filled.");
        enterConfirmPassword(password);
        System.out.println("Report: Confirm Password field filled.");
        enterPid(pid);
        System.out.println("Report: PID/Passport Number field filled.");
        clickRegisterButton();
        System.out.println("Report: Register button clicked.");
    }
}
