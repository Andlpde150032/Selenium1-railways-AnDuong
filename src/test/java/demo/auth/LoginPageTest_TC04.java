/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Login
 * Description: Login page displays when un-logged User clicks on "Book ticket" tab
 */
package demo.auth;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPageTest_TC04 extends demo.Testbase {
    private Properties properties;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testBookTicketRedirectsToLoginPage() {
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the "Book ticket" link on the navbar
        driver.findElement(By.linkText(jsonDataReader.getTestData("uiElements", "bookTicketLinkText"))).click();

        // Verify that the current URL is the login page URL
        String expectedLoginPageUrl = properties.getProperty("base.url") + "/Account/Login.cshtml";
        wait.until(ExpectedConditions.urlContains(expectedLoginPageUrl));
        assertTrue(driver.getCurrentUrl().contains(expectedLoginPageUrl), "User was not redirected to the login page.");

        // Optionally, verify a specific element on the login page to ensure it's the correct page
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Login Page')]")).isDisplayed(), "Login page title is not displayed.");
    }
}