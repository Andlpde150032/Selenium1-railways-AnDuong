/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: User can't create account while password and PID fields are empty
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

public class RegisterPageTest_TC11 extends demo.Testbase {

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
    public void testRegisterWithEmptyPasswordAndPid() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Register link on the navbar
        driver.findElement(By.linkText("Register")).click();

        // Create an instance of the RegisterPage and attempt registration with empty password and PID
        registerPage = new RegisterPage(driver);
        registerPage.performRegistration(
                jsonDataReader.getTestData("register_empty_fields", "email"), // Use a valid email
                "", // Empty password
                ""  // Empty PID
        );

        // Add a delay to observe the result
        Thread.sleep(5000);

        // Verify the general error message above the form
        String expectedGeneralErrorMessage = "There're errors in the form. Please correct the errors and try again.";
        String actualGeneralErrorMessage = driver.findElement(By.xpath("//p[@class='message error']")).getText().trim();
        assertEquals(expectedGeneralErrorMessage, actualGeneralErrorMessage, "General error message is not displayed or not as expected.");

        // Verify error message next to password fields
        String expectedPasswordErrorMessage = "Invalid password length";
        String actualPasswordErrorMessage = driver.findElement(By.xpath("//label[@for='password']/following-sibling::*[@class='validation-error']")).getText().trim();
        assertEquals(expectedPasswordErrorMessage, actualPasswordErrorMessage, "Password error message is not displayed or not as expected.");

        // Verify error message next to PID field
        String expectedPidErrorMessage = "Invalid ID length";
        String actualPidErrorMessage = driver.findElement(By.xpath("//label[@for='pid']/following-sibling::*[@class='validation-error']")).getText().trim();
        assertEquals(expectedPidErrorMessage, actualPidErrorMessage, "PID error message is not displayed or not as expected.");

        System.out.println("TC11 Passed: User cannot create account with empty password and PID and error messages displayed.");
    }
}