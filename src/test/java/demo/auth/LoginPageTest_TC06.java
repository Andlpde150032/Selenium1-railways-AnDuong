/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: Additional pages display once user logged in
 */
package demo.auth;

import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

public class LoginPageTest_TC06 extends demo.Testbase {
    private Properties properties;
    private LoginPage loginPage;
    private JsonDataReader jsonDataReader;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
    }

    @Test
    public void testAdditionalPagesDisplayAfterLogin() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        // Perform login with valid credentials
        loginPage.performLogin(jsonDataReader.getTestData("login", "email"), jsonDataReader.getTestData("login", "password"));

        // Add a delay to observe result
        Thread.sleep(5000);

        // Verify the presence of "My ticket", "Change password" and "Logout" tabs
        assertTrue(driver.findElement(By.linkText("My ticket")).isDisplayed(), "My ticket tab is not displayed.");
        assertTrue(driver.findElement(By.linkText("Change password")).isDisplayed(), "Change password tab is not displayed.");
        assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "Logout tab is not displayed.");

        // Verify redirection for "My ticket"
        driver.findElement(By.linkText("My ticket")).click();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains("/Ticket/Manage.cshtml"), "Not redirected to My ticket page.");
        driver.navigate().back(); // Go back to home page

        // Verify redirection for "Change password"
        driver.findElement(By.linkText("Change password")).click();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains("/Account/ChangePassword.cshtml"), "Not redirected to Change password page.");

        System.out.println("TC06 Passed: Additional pages displayed and redirects correctly after login.");
    }
}