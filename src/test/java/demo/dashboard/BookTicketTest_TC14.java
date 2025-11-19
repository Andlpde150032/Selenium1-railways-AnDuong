/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Book Ticket
 * Description: User can book 1 ticket at a time
 */
package demo.dashboard;

import demo.page.BookTicketPage;
import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

public class BookTicketTest_TC14 extends demo.Testbase {
    private Properties properties;
    private LoginPage loginPage;
    private BookTicketPage bookTicketPage;
    private JsonDataReader jsonDataReader;

    @BeforeEach
    public void setUp() {
        super.setUp();
        properties = PropertiesUtils.loadProperties("src/test/resources/config.properties");
        jsonDataReader = new JsonDataReader("test-data.json");
    }

    @Test
    public void testBookOneTicket() throws InterruptedException {
        // Pre-condition: User is logged in with a valid and activated account.
        // Navigate to the main page
        driver.get(properties.getProperty("base.url"));

        // Click the Login link on the navbar
        driver.findElement(By.linkText("Login")).click();

        // Perform login
        loginPage = new LoginPage(driver);
        loginPage.performLogin(
                jsonDataReader.getTestData("login", "email"),
                jsonDataReader.getTestData("login", "password")
        );
        Thread.sleep(3000); // Wait for login to complete

        // Click on "Book ticket" tab
        driver.findElement(By.linkText("Book ticket")).click();
        Thread.sleep(3000); // Wait for page to load

        // Select ticket details
        bookTicketPage = new BookTicketPage(driver);
        bookTicketPage.selectDepartDate("1/1/2026"); // Example date, adjust as needed
        bookTicketPage.selectDepartStation("Sài Gòn");
        bookTicketPage.selectArriveStation("Nha Trang");
        bookTicketPage.selectSeatType("Soft bed with air conditioner");
        bookTicketPage.selectTicketAmount("1");

        // Click on "Book ticket" button
        bookTicketPage.clickBookTicketButton();
        Thread.sleep(5000); // Wait for booking to complete

        // Verify success message
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Ticket Booked Successfully!')]")).isDisplayed(), "Booking success message is not displayed.");

        // Verify ticket information (example checks, can be more detailed)
        assertTrue(driver.getPageSource().contains("Depart Date: 1/1/2026"), "Depart Date not correct.");
        assertTrue(driver.getPageSource().contains("Depart Station: Sài Gòn"), "Depart Station not correct.");
        assertTrue(driver.getPageSource().contains("Arrive Station: Nha Trang"), "Arrive Station not correct.");
        assertTrue(driver.getPageSource().contains("Seat Type: Soft bed with air conditioner"), "Seat Type not correct.");
        assertTrue(driver.getPageSource().contains("Amount: 1"), "Amount not correct.");

        System.out.println("TC14 Passed: User successfully booked one ticket.");
    }
}