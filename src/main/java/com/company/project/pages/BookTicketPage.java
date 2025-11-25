package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BookTicketPage - Page object for Booking Tickets
 */
public class BookTicketPage extends BasePage {

    private final By departDateSelect = By.name("Date");
    private final By departFromSelect = By.name("DepartStation");
    private final By arriveAtSelect = By.name("ArriveStation");
    private final By seatTypeSelect = By.name("SeatType");
    private final By ticketAmountSelect = By.name("TicketAmount");
    private final By bookTicketButton = By.cssSelector("input[value='Book ticket']");
    private final By successMessage = By.xpath("//div[@id='content']/h1");

    public void bookTicket(String date, String departFrom, String arriveAt, String seatType, String amount) {
        selectDepartDate(date);
        selectDepartFrom(departFrom);
        selectArriveAt(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(amount);
        scrollToElement(bookTicketButton);
        clickBookTicket();
    }

    public void selectDepartDate(String date) {
        selectDropdown(departDateSelect, date);
    }

    public void selectDepartFrom(String departFrom) {
        selectDropdown(departFromSelect, departFrom);
    }

    public void selectArriveAt(String arriveAt) {
        selectDropdown(arriveAtSelect, arriveAt);
    }

    public void selectSeatType(String seatType) {
        selectDropdown(seatTypeSelect, seatType);
    }

    public void selectTicketAmount(String amount) {
        selectDropdown(ticketAmountSelect, amount);
    }

    public void clickBookTicket() {
        click(bookTicketButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
