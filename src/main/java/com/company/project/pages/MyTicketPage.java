package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function MyTicketPage - Page object for My Ticket Page
 */
public class MyTicketPage extends BasePage {

    private final By pageHeading = By.xpath("//h1[contains(text(), 'Manage Tickets')]");

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean isPageLoaded() {
        try {
            return getText(pageHeading).contains("Manage Tickets");
        } catch (Exception e) {
            return false;
        }
    }
}
