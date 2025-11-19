/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Timetable
 * Description: Page Object for the Timetable page.
 */
package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

public class TimetablePage {

    private final WebDriver driver;

    public TimetablePage(WebDriver driver) {
        this.driver = driver;
    }

    private int getColumnIndex(String columnName) {
        List<WebElement> headerCells = driver.findElements(By.xpath("//table//tr/th"));
        for (int i = 0; i < headerCells.size(); i++) {
            if (headerCells.get(i).getText().equals(columnName)) {
                return i + 1;
            }
        }
        throw new RuntimeException("Could not find column: " + columnName);
    }

    public void bookTicket(String departStation, String arriveStation) {
        int departStationIndex = getColumnIndex("Depart Station");
        int arriveStationIndex = getColumnIndex("Arrive Station");
        int bookTicketIndex = getColumnIndex("Book ticket");

        String xpath = String.format("//tr[td[%d] = '%s' and td[%d] = '%s']/td[%d]/a",
                departStationIndex, departStation, arriveStationIndex, arriveStation, bookTicketIndex);
        WebElement bookTicketLink = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookTicketLink);
        bookTicketLink.click();
    }
}
