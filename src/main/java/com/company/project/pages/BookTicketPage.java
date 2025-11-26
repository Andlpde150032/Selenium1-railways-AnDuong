package com.company.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BookTicketPage - Page object for Book Ticket Page
 */
public class BookTicketPage extends BasePage {

    private final By departDateSelect = By.name("Date");
    private final By departStationSelect = By.name("DepartStation");
    private final By arriveStationSelect = By.name("ArriveStation");
    private final By seatTypeSelect = By.name("SeatType");
    private final By ticketAmountSelect = By.name("TicketAmount");
    private final By bookTicketButton = By.xpath("//input[@value='Book ticket']");

    public void bookTicket(String departStation, String arriveStation, String seatType, String amount) {
        selectRandomDepartDate();
        selectDropdown(departStationSelect, departStation);
        selectDropdown(arriveStationSelect, arriveStation);
        selectDropdown(seatTypeSelect, seatType);
        selectDropdown(ticketAmountSelect, amount);
        scroll(bookTicketButton);
        click(bookTicketButton);
    }

    public void selectRandomDepartDate() {
        WebElement dateDropdown = getDriver().findElement(departDateSelect);
        Select select = new Select(dateDropdown);
        List<WebElement> options = select.getOptions();

        // Skip the first option if it's a placeholder (e.g. "Date" or "Depart Date")
        int startIndex = 0;
        if (!options.isEmpty()
                && (options.get(0).getText().contains("Date") || options.get(0).getText().trim().isEmpty())) {
            startIndex = 1;
        }

        if (options.size() <= startIndex) {
            throw new RuntimeException("No valid dates available in dropdown");
        }

        int randomIndex = new Random().nextInt(options.size() - startIndex) + startIndex;
        select.selectByIndex(randomIndex);
        System.out.println("Selected Date: " + options.get(randomIndex).getText());
    }

    public String getSelectedDepartStation() {
        return new Select(getDriver().findElement(departStationSelect)).getFirstSelectedOption().getText();
    }

    public String getSelectedArriveStation() {
        return new Select(getDriver().findElement(arriveStationSelect)).getFirstSelectedOption().getText();
    }
}
