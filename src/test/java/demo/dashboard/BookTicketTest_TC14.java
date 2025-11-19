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
        loginPage.enterEmail(
                jsonDataReader.getTestData("login", "email")
        );
        loginPage.enterPassword(
                jsonDataReader.getTestData("login", "password")
        );
        loginPage.clickLoginButton();

        // Click on "Book ticket" tab
        driver.findElement(By.linkText("Book ticket")).click();

        // Select ticket details
        bookTicketPage = new BookTicketPage(driver);
        bookTicketPage.selectDepartDate(jsonDataReader.getTestData("bookTicketTC14", "departDate"));
        bookTicketPage.selectDepartStation(jsonDataReader.getTestData("bookTicketTC14", "departFrom"));
        bookTicketPage.selectArriveStation(jsonDataReader.getTestData("bookTicketTC14", "arriveAt"));
        bookTicketPage.selectSeatType(jsonDataReader.getTestData("bookTicketTC14", "seatType"));
        bookTicketPage.selectTicketAmount(jsonDataReader.getTestData("bookTicketTC14", "ticketAmount"));

        // Click on "Book ticket" button
        bookTicketPage.clickBookTicketButton();

        // Verify success message
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Ticket Booked Successfully!')]")).isDisplayed(), "Booking success message is not displayed.");

        // Verify ticket information (example checks, can be more detailed)
        assertTrue(driver.getPageSource().contains("Depart Date: " + jsonDataReader.getTestData("bookTicketTC14", "departDate")), "Depart Date not correct.");
        assertTrue(driver.getPageSource().contains("Depart Station: " + jsonDataReader.getTestData("bookTicketTC14", "departFrom")), "Depart Station not correct.");
        assertTrue(driver.getPageSource().contains("Arrive Station: " + jsonDataReader.getTestData("bookTicketTC14", "arriveAt")), "Arrive Station not correct.");
        assertTrue(driver.getPageSource().contains("Seat Type: " + jsonDataReader.getTestData("bookTicketTC14", "seatType")), "Seat Type not correct.");
        assertTrue(driver.getPageSource().contains("Amount: " + jsonDataReader.getTestData("bookTicketTC14", "ticketAmount")), "Amount not correct.");
    }
}