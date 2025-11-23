package com.company.project.pages;

import org.openqa.selenium.By;

public class SuccessPage extends BasePage {

    private final By successMessage = By.cssSelector("h1");
    // Using CSS selectors for table cells (Horizontal table structure)
    // Row 1 is header, Row 2 is data
    private final By departStation = By.cssSelector("table.MyTable tr:nth-child(2) td:nth-child(1)");
    private final By arriveStation = By.cssSelector("table.MyTable tr:nth-child(2) td:nth-child(2)");
    private final By seatType = By.cssSelector("table.MyTable tr:nth-child(2) td:nth-child(3)");
    private final By departDate = By.cssSelector("table.MyTable tr:nth-child(2) td:nth-child(4)");
    private final By amount = By.cssSelector("table.MyTable tr:nth-child(2) td:nth-child(7)");

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getDepartStation() {
        return getText(departStation);
    }

    public String getArriveStation() {
        return getText(arriveStation);
    }

    public String getSeatType() {
        return getText(seatType);
    }

    public String getDepartDate() {
        return getText(departDate);
    }

    public String getAmount() {
        return getText(amount);
    }
}
