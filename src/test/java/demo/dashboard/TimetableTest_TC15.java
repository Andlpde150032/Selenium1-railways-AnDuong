/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Timetable
 * Description: User can open "Book ticket" page by clicking on "Book ticket" link in "Train timetable" page
 */
package demo.dashboard;

import demo.page.BookTicketPage;
import demo.page.LoginPage;
import demo.utils.JsonDataReader;
import demo.utils.PropertiesUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Properties;

public class TimetableTest_TC15 extends demo.Testbase {
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
    public void testBookTicketLinkFromTimetable() throws InterruptedException {
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

        // Click on "Timetable" tab
        driver.findElement(By.linkText("Timetable")).click();
        Thread.sleep(3000); // Wait for page to load

        // Click on "Book ticket" link for the route from "Huế" to "Sài Gòn"
        // This XPath needs to be specific to the "Book ticket" link for this route
        // Assuming there is a unique identifier or a way to locate this specific link.
        // For example, a link within a row that contains "Huế" and "Sài Gòn".
        driver.findElement(By.xpath("//td[text()='Huế']/following-sibling::td[text()='Sài Gòn']/following-sibling::td/a[text()='Book ticket']")).click();
        Thread.sleep(5000); // Wait for Book Ticket page to load

        // Verify that the "Book ticket" page is loaded
        bookTicketPage = new BookTicketPage(driver);
        assertEquals("Sài Gòn", bookTicketPage.getSelectedArriveStation(), "Arrive Station not pre-filled correctly.");
        assertEquals("Huế", bookTicketPage.getSelectedDepartStation(), "Depart Station not pre-filled correctly.");

        System.out.println("TC15 Passed: User redirected to Book ticket page from Timetable with pre-filled stations.");
    }
}