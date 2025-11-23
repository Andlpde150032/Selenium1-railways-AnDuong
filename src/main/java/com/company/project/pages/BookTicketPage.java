package com.company.project.pages;

import org.openqa.selenium.By;

public class BookTicketPage extends BasePage {

    private final By departDateSelect = By.name("Date");
    private final By departFromSelect = By.name("DepartStation");
    private final By arriveAtSelect = By.name("ArriveStation");
    private final By seatTypeSelect = By.name("SeatType");
    private final By ticketAmountSelect = By.name("TicketAmount");
    private final By bookTicketButton = By.cssSelector("input[value='Book ticket']");

    public void selectDepartDate(String date) {
        selectDropdown(departDateSelect, date);
    }

    public void selectDepartFrom(String station) {
        selectDropdown(departFromSelect, station);
    }

    public void selectArriveAt(String station) {
        selectDropdown(arriveAtSelect, station);
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

    public void bookTicket(String date, String departFrom, String arriveAt, String seatType, String amount) {
        selectDepartDate(date);
        selectDepartFrom(departFrom);
        selectArriveAt(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(amount);
        scroll(bookTicketButton);
        clickBookTicket();
    }
}
