/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Change Password
 * Description: User can change password
 */
package demo.auth;

import demo.page.LoginPage;
import demo.page.ChangePasswordPage;
import demo.page.HomePage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Properties;

public class ChangePasswordPageTest_TC09 extends demo.Testbase {
    private Properties properties;
    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;
    private JsonDataReader jsonDataReader;
    private WebDriverWait wait;
    private HomePage homePage;


    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testChangePassword() throws InterruptedException {
        // Pre-condition: User is logged in with a valid and activated account.
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "loginLinkText"))).click();

        // Perform login
                loginPage = new LoginPage(driver);
                loginPage.enterEmail(
                        jsonDataReader.getTestData("login", "email")
                );
                loginPage.enterPassword(
                        jsonDataReader.getTestData("login", "password")
                );
                loginPage.clickLoginButton();

                // Verify redirection to homepage
                // String expectedUrl = properties.getProperty("base.url") + HomePage.HOMEPAGE_PATH;
                // assertEquals(expectedUrl, driver.getCurrentUrl(), "User was not redirected to the homepage after login.");


                homePage = new HomePage(driver);
                                assertNotNull(homePage.getTitle(), "HomePage title should not be null after login.");
                
                                // Navigate directly to the Change Password page
                                driver.get(properties.getProperty("base.url") + "/Account/ChangePassword.cshtml");
                        
                                // Perform password change
                                changePasswordPage = new ChangePasswordPage(driver);
                changePasswordPage.changePassword(
                        jsonDataReader.getTestData("login", "password"), // Old password
                        jsonDataReader.getTestData("new_password", "password"), // New password
                        jsonDataReader.getTestData("new_password", "password")  // Confirm new password
                );
        
                // Verify success message
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + jsonDataReader.getTestData("changePasswordMessages", "passwordChangeSuccessMessage") + "')]")));
                assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + jsonDataReader.getTestData("changePasswordMessages", "passwordChangeSuccessMessage") + "')]")).isDisplayed(), "Password change success message is not displayed.");
    }
}
