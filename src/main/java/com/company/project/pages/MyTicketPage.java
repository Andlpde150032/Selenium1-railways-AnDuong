package com.company.project.pages;

import com.company.project.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function MyTicketPage - Page object for My Tickets
 */
public class MyTicketPage extends BasePage {

    private final By cancelButton = By.xpath("//input[@value='Cancel']");
    private final By ticketTableRows = By.xpath("//table[@class='MyTable']//tr[td]");

    public void cancelTicket() {
        scrollToElement(cancelButton);
        click(cancelButton);
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.accept();
    }

    public int getTicketCount() {
        return DriverManager.getDriver().findElements(ticketTableRows).size();
    }

    public boolean isCancelButtonDisplayed() {
        try {
            return DriverManager.getDriver().findElement(cancelButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
