package demo.auth;
import demo.page.HomePage;
import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginPageTest_TC01 extends demo.Testbase {
private Properties properties;
private LoginPage loginPage;
private HomePage homePage;
private JsonDataReader jsonDataReader;

@BeforeEach
public void setUp() {
super.setUp();
properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
jsonDataReader = new JsonDataReader("test-data.json");
}

@Test
public void testLogin() throws InterruptedException {
// Navigate to the main page
driver.get(properties.getProperty("base.url"));

// Click the LoginPage link on the navbar
driver.findElement(By.linkText("Login")).click();

// Create an instance of the LoginPage page object
loginPage = new LoginPage(driver);

// Perform login
loginPage.enterEmail(jsonDataReader.getTestData("login", "email"));
loginPage.enterPassword(jsonDataReader.getTestData("login", "password"));
loginPage.clickLoginButton();

Thread.sleep(5000);

// Verify redirection to homepage
String expectedUrl = properties.getProperty("base.url") + HomePage.HOMEPAGE_PATH;
assertEquals(expectedUrl, driver.getCurrentUrl(), "User was not redirected to the homepage.");

homePage = new HomePage(driver);
assertNotNull(homePage.getTitle(), "HomePage title should not be null");

String expectedWelcomeMessage = "Welcome " + jsonDataReader.getTestData("login", "email");
String actualWelcomeMessage = driver.findElement(By.xpath("//div[@class='account']/strong")).getText().trim();
assertEquals(expectedWelcomeMessage, actualWelcomeMessage, "Welcome message is not as expected.");

}
}