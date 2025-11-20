/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: Login page displays when un-logged User clicks on "Book ticket" tab
 */
package demo.auth;

import org.openqa.selenium.By;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;
import demo.utils.PropertiesUtils;
import demo.utils.JsonDataReader;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import demo.page.HomePage;

public class LoginPageTest_TC04 extends demo.Testbase {
    private Properties properties;
    private WebDriverWait wait;
    private JsonDataReader jsonDataReader;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        jsonDataReader = new JsonDataReader("test-data.json");
        homePage = new HomePage(driver);
    }

    @Test
    public void testBookTicketRedirectsToLoginPage() {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the "Book ticket" link on the navbar
        homePage.clickBookTicketTab();

        // Verify that the current URL is the login page URL
        String expectedLoginPageUrl = properties.getProperty("base.url") + "/Account/Login.cshtml";
        wait.until(ExpectedConditions.urlMatches(expectedLoginPageUrl + ".*"));
        assertTrue(driver.getCurrentUrl().startsWith(expectedLoginPageUrl), "User was not redirected to the login page.");

        // Optionally, verify a specific element on the login page to ensure it's the correct page
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Login Page')]")).isDisplayed(), "Login page title is not displayed.");
    }
}