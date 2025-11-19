/**
 * @author      AnDLP
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Book Ticket
 * Description: Page Object for the Book Ticket page.
 */
package demo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class BookTicketPage {

    private WebDriver driver;

    @FindBy(name = "DepartStation")
    private WebElement departStationDropdown;

    @FindBy(name = "ArriveStation")
    private WebElement arriveStationDropdown;

    @FindBy(name = "Date")
    private WebElement departDateDropdown;

    @FindBy(name = "SeatType")
    private WebElement seatTypeDropdown;

    @FindBy(name = "TicketAmount")
    private WebElement ticketAmountDropdown;

    @FindBy(xpath = "//input[@value='Book ticket']")
    private WebElement bookTicketButton;

    public BookTicketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSelectedDepartStation() {
        Select select = new Select(departStationDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedArriveStation() {
        Select select = new Select(arriveStationDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void selectDepartStation(String station) {
        Select select = new Select(departStationDropdown);
        select.selectByVisibleText(station);
        System.out.println("Report: Selected Depart Station: " + station);
    }

    public void selectArriveStation(String station) {
        Select select = new Select(arriveStationDropdown);
        select.selectByVisibleText(station);
        System.out.println("Report: Selected Arrive Station: " + station);
    }

    public void selectDepartDate(String date) {
        Select select = new Select(departDateDropdown);
        select.selectByVisibleText(date);
        System.out.println("Report: Selected Depart Date: " + date);
    }

    public void selectSeatType(String seatType) {
        Select select = new Select(seatTypeDropdown);
        select.selectByVisibleText(seatType);
        System.out.println("Report: Selected Seat Type: " + seatType);
    }

    public void selectTicketAmount(String amount) {
        Select select = new Select(ticketAmountDropdown);
        select.selectByVisibleText(amount);
        System.out.println("Report: Selected Ticket Amount: " + amount);
    }

    public void clickBookTicketButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookTicketButton);
        bookTicketButton.click();
    }
}
