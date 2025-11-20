package demo.auth;

import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPageTest_TC05 extends demo.Testbase {
    private Properties properties;
    private JsonDataReader jsonDataReader;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
    }

    private void performLoginAttempt(String email, String password) {
        LoginPage loginPage = new LoginPage(driver); // Re-instantiate LoginPage to get fresh elements
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Test
    public void testLoginWithMultipleWrongPasswords() throws InterruptedException {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        String validEmail = jsonDataReader.getTestData("login", "email");
        String invalidPassword = jsonDataReader.getTestData("loginInvalid", "password");

        // Perform 5 login attempts
        performLoginAttempt(validEmail, invalidPassword);
        performLoginAttempt(validEmail, invalidPassword);
        performLoginAttempt(validEmail, invalidPassword);
        performLoginAttempt(validEmail, invalidPassword);
        performLoginAttempt(validEmail, invalidPassword);

        // Assert error message is displayed for multiple attempts
        String expectedErrorMessage = jsonDataReader.getTestData("loginErrorMessages", "multiAttemptErrorMessage");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for a generic error message element to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/p")));
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText().trim();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message for multiple wrong passwords is not as expected. Actual: " + actualErrorMessage + ", Expected to contain: " + expectedErrorMessage);
    }
}
