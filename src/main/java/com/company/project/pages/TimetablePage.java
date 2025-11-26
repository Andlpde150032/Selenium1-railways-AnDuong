package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-26
 * @project Selenium1-railways-AnDuong
 * @function TimetablePage - Page object for Timetable Page
 */
public class TimetablePage extends BasePage {

    public void clickBookTicket(String departStation, String arriveStation) {
        // XPath to find the 'book ticket' link in the row where Depart Station and
        // Arrive Station match
        // Assuming the table structure: <tr><td>Depart</td><td>Arrive</td>...<td><a
        // href='...'>book ticket</a></td></tr>
        // We need to be careful about the exact text and column order, but finding by
        // cell text in the same row is robust.
        // XPath to find the 'book ticket' link in the row where Depart Station comes
        // before Arrive Station
        // Using following-sibling to ensure order: Depart -> Arrive
        By bookTicketLink = By
                .xpath("//tr[td[text()='" + departStation + "']/following-sibling::td[text()='" + arriveStation
                        + "']]//a[contains(@href, 'BookTicketPage.cshtml')]");

        // Scroll to element might be needed if the table is long
        scrollToElement(bookTicketLink);
        click(bookTicketLink);
    }

    private void scrollToElement(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                getDriver().findElement(locator));
    }
}
