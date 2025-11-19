/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: Login page displays when un-logged User clicks on "Book ticket" tab
 */
package demo.auth;

import demo.page.LoginPage;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

public class LoginPageTest_TC04 extends demo.Testbase {
    private Properties properties;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
    }

    @Test
    public void testBookTicketRedirectsToLoginPage() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the "Book ticket" link on the navbar
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "bookTicketLinkText"))).click();

        // Add a delay to observe result
        Thread.sleep(3000);

        // Verify that the current URL is the login page URL
        String expectedLoginPageUrl = properties.getProperty("base.url") + "/Account/Login.cshtml";
        assertTrue(driver.getCurrentUrl().contains(expectedLoginPageUrl), "User was not redirected to the login page.");

        // Optionally, verify a specific element on the login page to ensure it's the correct page
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Login Page')]")).isDisplayed(), "Login page title is not displayed.");
        System.out.println("TC04 Passed: Un-logged user redirected to login page when clicking 'Book ticket'.");
    }
}