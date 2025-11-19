/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Book Ticket
 * Description: Page Object for the Book Ticket page.
 */
package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BookTicketPage {

    private WebDriver driver;

    private By departStationDropdown = By.name("DepartStation");
    private By arriveStationDropdown = By.name("ArriveStation");
    private By departDateDropdown = By.name("Date");
    private By seatTypeDropdown = By.name("SeatType");
    private By ticketAmountDropdown = By.name("TicketAmount");
    private By bookTicketButton = By.xpath("//input[@value='Book ticket']");

    public BookTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSelectedDepartStation() {
        Select select = new Select(driver.findElement(departStationDropdown));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedArriveStation() {
        Select select = new Select(driver.findElement(arriveStationDropdown));
        return select.getFirstSelectedOption().getText();
    }

    public void selectDepartStation(String station) {
        Select select = new Select(driver.findElement(departStationDropdown));
        select.selectByVisibleText(station);
    }

    public void selectArriveStation(String station) {
        Select select = new Select(driver.findElement(arriveStationDropdown));
        select.selectByVisibleText(station);
    }

    public void selectDepartDate(String date) {
        Select select = new Select(driver.findElement(departDateDropdown));
        select.selectByVisibleText(date);
    }

    public void selectSeatType(String seatType) {
        Select select = new Select(driver.findElement(seatTypeDropdown));
        select.selectByVisibleText(seatType);
    }

    public void selectTicketAmount(String amount) {
        Select select = new Select(driver.findElement(ticketAmountDropdown));
        select.selectByVisibleText(amount);
    }

    public void clickBookTicketButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(bookTicketButton));
        driver.findElement(bookTicketButton).click();
    }
}
