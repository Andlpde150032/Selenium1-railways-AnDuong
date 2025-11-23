package com.company.project.pages;

import org.openqa.selenium.By;

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
