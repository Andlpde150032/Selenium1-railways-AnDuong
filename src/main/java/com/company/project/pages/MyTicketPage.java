package com.company.project.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class MyTicketPage extends BasePage {

    public void cancelTicket(String departStation, String arriveStation) {
        // XPath to find the 'Cancel' button in the row where Depart Station comes
        // before Arrive Station
        // Using following-sibling to ensure order: Depart -> Arrive
        By cancelButton = By.xpath("//tr[td[text()='" + departStation + "']/following-sibling::td[text()='"
                + arriveStation + "']]//input[@value='Cancel']");

        scroll(cancelButton);
        click(cancelButton);

        // Handle confirmation alert
        try {
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("DEBUG: No alert found or error handling alert: " + e.getMessage());
        }
    }

    public boolean isTicketDisplayed(String departStation, String arriveStation) {
        // XPath to check if the ticket row exists
        By ticketRow = By.xpath(
                "//tr[td[text()='" + departStation + "']/following-sibling::td[text()='" + arriveStation + "']]");
        return !getDriver().findElements(ticketRow).isEmpty();
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }
}
