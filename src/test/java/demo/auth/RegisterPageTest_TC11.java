/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: User can't create account while password and PID fields are empty
 */
package demo.auth;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegisterPageTest_TC11 extends demo.Testbase {

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
    public void testRegisterWithEmptyPasswordAndPid() {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Register link on the navbar
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "registerLinkText"))).click();

        // Create an instance of the RegisterPage and attempt registration with empty password and PID
        registerPage = new RegisterPage(driver);
        registerPage.enterEmail(jsonDataReader.getTestData("register_empty_fields", "email")); // Use a valid email
        registerPage.enterPassword(jsonDataReader.getTestData("registerInvalid", "emptyPassword")); // Empty password
        registerPage.enterConfirmPassword(jsonDataReader.getTestData("registerInvalid", "emptyPassword")); // Empty confirm password
        registerPage.enterPid(jsonDataReader.getTestData("registerInvalid", "emptyPid")); // Empty PID
        registerPage.clickRegisterButton();

        // Wait for the error message to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='message error']")));

        // Verify the general error message above the form
        String expectedGeneralErrorMessage = jsonDataReader.getTestData("registerMessages", "generalErrorMessage");
        String actualGeneralErrorMessage = driver.findElement(By.xpath("//p[@class='message error']")).getText().trim();
        assertEquals(expectedGeneralErrorMessage, actualGeneralErrorMessage, "General error message is not displayed or not as expected.");

        // Verify error message next to password fields
        String expectedPasswordErrorMessage = jsonDataReader.getTestData("registerMessages", "invalidPasswordLengthMessage");
        String actualPasswordErrorMessage = driver.findElement(By.xpath("//label[@for='password']/following-sibling::*[@class='validation-error']")).getText().trim();
        assertEquals(expectedPasswordErrorMessage, actualPasswordErrorMessage, "Password error message is not displayed or not as expected.");

        // Verify error message next to PID field
        String expectedPidErrorMessage = jsonDataReader.getTestData("registerMessages", "invalidPidLengthMessage");
        String actualPidErrorMessage = driver.findElement(By.xpath("//label[@for='pid']/following-sibling::*[@class='validation-error']")).getText().trim();
        assertEquals(expectedPidErrorMessage, actualPidErrorMessage, "PID error message is not displayed or not as expected.");
    }
}