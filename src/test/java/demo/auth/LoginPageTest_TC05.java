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
                String invalidPassword = jsonDataReader.getTestData("loginInvalid", "invalidPasswordMultiAttempt"); // Use a consistently wrong password
                                                                                                                                                                                                                  
                        for (int i = 0; i < 4; i++) { // Repeat 4 times to trigger the message after 4 attempts
                                                                                                                                                                                                                  
                            loginPage.enterEmail(validEmail);
                                                                                                                                                                                                                  
                            loginPage.enterPassword(invalidPassword);
                                                                                                                                                                                                                  
                            loginPage.clickLoginButton();
                                                                                                                                                                                                                  
                        }
                                                                                                                                                                                                                  
                
                                                                                                                                                                                                                  
                        // Assert error message is displayed
                                                                                                                                                                                                                  
                        String expectedErrorMessage = jsonDataReader.getTestData("loginErrorMessages", "multiAttemptErrorMessage");
                                                                                                                                                                                                                  
                        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
                                                                                                                                                                                                                  
                        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for multiple wrong passwords is not as expected.");
    }
}
