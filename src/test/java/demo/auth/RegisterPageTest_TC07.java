/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Register
 * Description: User can create new account
 */
package demo.auth;

import demo.page.RegisterPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

public class RegisterPageTest_TC07 extends demo.Testbase {

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
    public void testCreateNewAccount() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Register link on the navbar
                driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "registerLinkText"))).click();
                                                                                                                                                                                                                  
                // Create an instance of the RegisterPage and perform registration
                        registerPage = new RegisterPage(driver);
                        registerPage.enterEmail(
                                jsonDataReader.getTestData("register", "email")
                        );
                        registerPage.enterPassword(
                                jsonDataReader.getTestData("register", "password")
                        );
                        registerPage.enterConfirmPassword(
                                jsonDataReader.getTestData("register", "password")
                        );
                        registerPage.enterPid(
                                jsonDataReader.getTestData("register", "pid")
                        );
                        registerPage.clickRegisterButton();
                
                        // Verify that the success message is displayed
                        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'" + jsonDataReader.getTestData("registerMessages", "registrationSuccessMessage") + "')]")).isDisplayed(), "Registration success message is not displayed.");
    }
}