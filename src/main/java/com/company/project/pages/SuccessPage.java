package com.company.project.pages;

import org.openqa.selenium.By;

public class SuccessPage extends BasePage {

    private final By successMessage = By.cssSelector("h1");
    private final By tableHeaders = By.cssSelector("table.MyTable tr.TableSmallHeader > *");

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    private String getTableCellValue(String headerName) {
        java.util.List<org.openqa.selenium.WebElement> headers = getDriver().findElements(tableHeaders);
        int columnIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equals(headerName)) {
                columnIndex = i + 1; // CSS nth-child is 1-based
                break;
            }
        }

        if (columnIndex == -1) {
            throw new RuntimeException("Header '" + headerName + "' not found in table.");
        }

        // Assuming data is in the second row (first row is header)
        By cellLocator = By.cssSelector("table.MyTable tr:nth-child(2) td:nth-child(" + columnIndex + ")");
        return getText(cellLocator);
    }

    public String getDepartStation() {
        return getTableCellValue("Depart Station");
    }

    public String getArriveStation() {
        return getTableCellValue("Arrive Station");
    }

    public String getSeatType() {
        return getTableCellValue("Seat Type");
    }

    public String getDepartDate() {
        return getTableCellValue("Depart Date");
    }

    public String getAmount() {
        return getTableCellValue("Amount");
    }
}
