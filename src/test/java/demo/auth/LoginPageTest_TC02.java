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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest_TC02 extends demo.Testbase {

    private Properties properties;
    private LoginPage loginPage;
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
    public void testLoginWithBlankUsername() {
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

        // Wait for the error message to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/p")));

        // Assert error message is displayed
        String expectedErrorMessage = jsonDataReader.getTestData("loginErrorMessages", "blankUsername");
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message for blank username is not as expected.");
    }
}


