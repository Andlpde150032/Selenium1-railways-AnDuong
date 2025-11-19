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
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

public class ChangePasswordPageTest_TC09 extends demo.Testbase {
    private Properties properties;
    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;
    private JsonDataReader jsonDataReader;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
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
        
                // Click on "Change Password" tab
                driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "changePasswordLinkText"))).click();
        
                // Perform password change
                changePasswordPage = new ChangePasswordPage(driver);
                changePasswordPage.changePassword(
                        jsonDataReader.getTestData("login", "password"), // Old password
                        jsonDataReader.getTestData("new_password", "password"), // New password
                        jsonDataReader.getTestData("new_password", "password")  // Confirm new password
                );
        
                // Verify success message
                assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'" + jsonDataReader.getTestData("changePasswordMessages", "passwordChangeSuccessMessage") + "')]")).isDisplayed(), "Password change s
        uccess message is not displayed.");
    }
}