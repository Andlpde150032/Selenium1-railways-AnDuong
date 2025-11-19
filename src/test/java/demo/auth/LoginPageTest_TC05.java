/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: System shows message when user enters wrong password several times
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

public class LoginPageTest_TC05 extends demo.Testbase {
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
    public void testLoginWithMultipleWrongPasswords() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        String validEmail = jsonDataReader.getTestData("login", "email");
        String invalidPassword = "wrong_password"; // Use a consistently wrong password

        for (int i = 0; i < 4; i++) { // Repeat 4 times to trigger the message after 4 attempts
            loginPage.performLogin(validEmail, invalidPassword);
            Thread.sleep(2000); // Wait for potential error message
        }

        // Add a final delay to observe result
        Thread.sleep(3000);

        // Assert error message is displayed
        String expectedErrorMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for multiple wrong passwords is not as expected.");
        System.out.println("TC05 Passed: System displays message after multiple wrong password attempts.");
    }
}
