/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: User can't create account with "Confirm password" is not the same with "Password"
 */
package demo.auth;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegisterPageTest_TC10 extends demo.Testbase {

    private Properties properties;
    private RegisterPage registerPage;
    private JsonDataReader jsonDataReader;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testRegisterWithMismatchedPasswords() {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Register link on the navbar
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "registerLinkText"))).click();

        // Create an instance of the RegisterPage and attempt registration with mismatched passwords
        registerPage = new RegisterPage(driver);
        registerPage.enterEmail(jsonDataReader.getTestData("register", "email")); // Use a valid email
        registerPage.enterPassword(jsonDataReader.getTestData("registerInvalid", "password")); // Valid password
        registerPage.enterConfirmPassword(jsonDataReader.getTestData("registerInvalid", "confirmPassword")); // Mismatched confirm password
        registerPage.enterPid(jsonDataReader.getTestData("register", "pid")); // Add PID
        registerPage.clickRegisterButton();

        // Wait for the error message to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='message error']")));

        // Verify that the error message is displayed
        String expectedErrorMessage = jsonDataReader.getTestData("registerMessages", "mismatchedPasswordErrorMessage");
        String actualErrorMessage = driver.findElement(By.xpath("//p[@class='message error']")).getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for mismatched passwords is not displayed or not as expected.");
    }
}