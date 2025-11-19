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
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "loginLinkText"))).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        // Perform login with valid credentials
        loginPage.performLogin(jsonDataReader.getTestData("login", "email"), jsonDataReader.getTestData("login", "password"));

        // Add a delay to observe result
        Thread.sleep(5000);

        // Verify the presence of "My ticket", "Change password" and "Logout" tabs
        assertTrue(driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "myTicketLinkText"))).isDisplayed(), "My ticket tab is not displayed.");
        assertTrue(driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "changePasswordLinkText"))).isDisplayed(), "Change password tab is not displayed.");
        assertTrue(driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "logoutLinkText"))).isDisplayed(), "Logout tab is not displayed.");

        // Verify redirection for "My ticket"
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "myTicketLinkText"))).click();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains(jsonDataReader.getTestData("urls", "myTicketPageUrlPartial")), "Not redirected to My ticket page.");
        driver.navigate().back(); // Go back to home page

        // Verify redirection for "Change password"
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "changePasswordLinkText"))).click();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains(jsonDataReader.getTestData("urls", "changePasswordPageUrlPartial")), "Not redirected to Change password page.");

        System.out.println("TC06 Passed: Additional pages displayed and redirects correctly after login.");
    }
}