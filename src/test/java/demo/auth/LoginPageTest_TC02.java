/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: User can't login with blank "Username" textbox
 */
package demo.auth;

import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class LoginPageTest_TC02 extends demo.Testbase {

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
    public void testLoginWithBlankUsername() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        // Enter valid password but leave email blank
        loginPage.enterEmail(""); // Leave email blank
        loginPage.enterPassword(jsonDataReader.getTestData("login", "password"));
        loginPage.clickLoginButton();

        // Add a delay to observe result
        Thread.sleep(3000); // Shorter delay for error message

        // Assert error message is displayed
        String expectedErrorMessage = "There was a problem with your login and/or errors exist in your form.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for blank username is not as expected.");
        System.out.println("TC02 Passed: User cannot login with blank username and error message displayed.");
    }
}


