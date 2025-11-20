/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: User cannot log into Railway with invalid password
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

public class LoginPageTest_TC03 extends demo.Testbase {
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
    public void testLoginWithInvalidPassword() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Create an instance of the LoginPage page object
        loginPage = new LoginPage(driver);

        // Enter valid email but invalid password
                        loginPage.enterEmail(jsonDataReader.getTestData("login", "email"));
                        loginPage.enterPassword(jsonDataReader.getTestData("loginInvalid", "password"));
                        loginPage.clickLoginButton();
                
                        // Assert error message is displayed
                        String expectedErrorMessage = jsonDataReader.getTestData("loginErrorMessages", "invalidPassword");
                        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
                        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for invalid password is not as expected.");
    }
}
