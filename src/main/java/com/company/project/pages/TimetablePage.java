package com.company.project.pages;

import org.openqa.selenium.By;

public class TimetablePage extends BasePage {

    public void clickBookTicket(String departStation, String arriveStation) {
        // Dynamic XPath to find the 'book ticket' link for the specific route
        // Assuming the table structure: Depart Station is in one cell, Arrive Station
        // in the next, and 'book ticket' link in the last cell of the row
        // Adjust XPath based on actual table structure if needed.
        // Based on typical table structure:
        // //td[text()='Depart']/following-sibling::td[text()='Arrive']/..//a[text()='book
        // ticket']
        String xpath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/..//a[text()='book ticket']",
                departStation, arriveStation);
        click(By.xpath(xpath));
    }
}
