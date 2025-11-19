/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: User can't login with an account hasn't been activated
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

public class LoginPageTest_TC08 extends demo.Testbase {
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
    public void testLoginWithUnactivatedAccount() throws InterruptedException {
        // NOTE: This test requires a pre-condition of having an account that has been
        // created but NOT activated. Automating email activation is outside the scope
        // of this test. A valid approach would be to use an existing unactivated
        // test account for this scenario or manually set up the pre-condition.

        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        // Assuming an unactivated account's credentials are available in test-data.json
        // For demonstration, using existing login credentials but this should ideally be
        // a dedicated unactivated account's credentials.
        loginPage.performLogin(
                jsonDataReader.getTestData("unactivated_login", "email"), // Placeholder for unactivated account email
                jsonDataReader.getTestData("unactivated_login", "password") // Placeholder for unactivated account password
        );

        // Add a delay to observe result
        Thread.sleep(3000);

        // Assert error message is displayed
        String expectedErrorMessage = "Invalid username or password. Please try again.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for unactivated account is not as expected.");
        System.out.println("TC08 Passed: User cannot login with unactivated account and error message displayed.");
    }
}
