/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: User can't create account with "Confirm password" is not the same with "Password"
 */
package demo.auth;

import demo.page.RegisterPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Properties;

public class RegisterPageTest_TC10 extends demo.Testbase {

    private Properties properties;
    private RegisterPage registerPage;
    private JsonDataReader jsonDataReader;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
    }

    @Test
    public void testRegisterWithMismatchedPasswords() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Register link on the navbar
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "registerLinkText"))).click();

        // Create an instance of the RegisterPage and attempt registration with mismatched passwords
        registerPage = new RegisterPage(driver);
        registerPage.performRegistration(
                jsonDataReader.getTestData("register", "email"), // Use a valid email
                jsonDataReader.getTestData("registerInvalid", "password"), // Valid password
                jsonDataReader.getTestData("registerInvalid", "confirmPassword") // Mismatched confirm password
        );

        // Add a delay to observe the result
        Thread.sleep(5000);

        // Verify that the error message is displayed
        String expectedErrorMessage = jsonDataReader.getTestData("registerMessages", "mismatchedPasswordErrorMessage");
        String actualErrorMessage = driver.findElement(By.xpath("//p[@class='message error']")).getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for mismatched passwords is not displayed or not as expected.");

        System.out.println("TC10 Passed: User cannot create account with mismatched passwords and error message displayed.");
    }
}