/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: Page Object for the Register page.
 */
package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordField = By.name("password");
    private By confirmPasswordField = By.cssSelector("input#confirmPassword");
    private By pidField = By.xpath("//input[@id='pid']");
    private By registerButton = By.xpath("//p[@class='form-actions']/input[@type='submit']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void enterPid(String pid) {
        driver.findElement(pidField).sendKeys(pid);
    }

    public void clickRegisterButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(registerButton));
        driver.findElement(registerButton).click();
    }
}
