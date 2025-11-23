package com.company.project.tests.booking;

import com.company.project.base.BaseTest;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import org.testng.annotations.Test;

public class DebugSuccessPageTest extends BaseTest {

    @Test(description = "Debug - Print Success Page HTML")
    public void debugSuccessPage() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Login
        homePage.goToLoginPage().login("AdminAn@gmail.com", "Admin12345");

        // Book a ticket
        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();
        bookTicketPage.bookTicket("12/1/2025", "Sài Gòn", "Nha Trang", "Soft bed with air conditioner", "1");

        // Print page source
        System.out.println("=== PAGE SOURCE START ===");
        System.out.println(getDriver().getPageSource());
        System.out.println("=== PAGE SOURCE END ===");
    }
}
