/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: User can log into Railway with valid username and password
 */
package demo.auth;

import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Properties;

public class LoginPageTest_TC01 extends demo.Testbase {
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
    public void testLogin() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the LoginPage link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        // Perform login
        loginPage.performLogin(jsonDataReader.getTestData("login", "email"), jsonDataReader.getTestData("login", "password"));

        // Add a delay to observe result
        Thread.sleep(5000);

        // Verify redirection to homepage
        String expectedUrl = properties.getProperty("base.url") + "/Page/HomePage.cshtml";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "User was not redirected to the homepage.");

        String expectedWelcomeMessage = "Welcome " + jsonDataReader.getTestData("login", "email");
        String actualWelcomeMessage = driver.findElement(By.xpath("//div[@class='account']/strong")).getText().trim();
        assertEquals(expectedWelcomeMessage, actualWelcomeMessage, "Welcome message is not as expected.");
        System.out.println("TC01 Passed: User successfully logged in and welcome message displayed.");

    }
}

