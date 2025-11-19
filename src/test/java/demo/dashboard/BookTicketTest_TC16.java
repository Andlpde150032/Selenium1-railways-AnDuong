/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Book Ticket
 * Description: User can cancel a ticket
 */
package demo.dashboard;

import demo.page.BookTicketPage;
import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Properties;

public class BookTicketTest_TC16 extends demo.Testbase {
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
    public void testCancelTicket() throws InterruptedException {
        // Pre-condition: User is logged in and has booked a ticket.
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

        // Book a ticket (reusing logic from TC14 for setup)
        driver.findElement(By.linkText("Book ticket")).click();
        Thread.sleep(3000); // Wait for page to load

        bookTicketPage = new BookTicketPage(driver);
        bookTicketPage.selectDepartDate("1/1/2026"); // Example date, adjust as needed
        bookTicketPage.selectDepartStation("Sài Gòn");
        bookTicketPage.selectArriveStation("Nha Trang");
        bookTicketPage.selectSeatType("Soft bed with air conditioner");
        bookTicketPage.selectTicketAmount("1");
        bookTicketPage.clickBookTicketButton();
        Thread.sleep(5000); // Wait for booking to complete
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Ticket Booked Successfully!')]")).isDisplayed(), "Booking success message is not displayed.");

        // Click on "My ticket" tab
        driver.findElement(By.linkText("My ticket")).click();
        Thread.sleep(3000); // Wait for page to load

        // Assuming there's a unique way to identify the booked ticket, e.g., by a "Cancel" button next to it.
        // This XPath needs to be adjusted based on the actual HTML structure of "My ticket" page.
        // For demonstration, clicking the first "Cancel" button found.
        String ticketRowXPath = "//table[@class='MyTable']//tr[td[text()='1/1/2026'] and td[text()='Sài Gòn'] and td[text()='Nha Trang']]";
        driver.findElement(By.xpath(ticketRowXPath + "//input[@value='Cancel']")).click();
        Thread.sleep(1000);

        // Accept the confirmation alert
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(5000); // Wait for cancellation to process and page to refresh

        // Verify the canceled ticket is no longer displayed
        assertFalse(driver.findElements(By.xpath(ticketRowXPath)).size() > 0, "Canceled ticket is still displayed.");

        System.out.println("TC16 Passed: User successfully canceled a ticket.");
    }
}