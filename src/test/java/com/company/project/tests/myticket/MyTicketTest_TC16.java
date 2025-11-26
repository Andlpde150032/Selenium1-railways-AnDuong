package com.company.project.tests.myticket;

import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import com.company.project.base.BaseTest;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyTicketTest_TC16 extends BaseTest {

    @Test(description = "User can cancel a ticket")
    public void TC16() {
        String email = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");
        String departStation = JsonReader.getData("tc16", "departStation");
        String arriveStation = JsonReader.getData("tc16", "arriveStation");
        String seatType = JsonReader.getData("tc16", "seatType");
        String ticketAmount = JsonReader.getData("tc16", "ticketAmount");

        HomePage homePage = new HomePage();
        homePage.open();

        // Register and Login
        homePage.goToRegisterPage()
                .register(email, password, password, pid);

        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        // Book a ticket
        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();
        bookTicketPage.bookTicket(departStation, arriveStation, seatType, ticketAmount);

        // Go to My Ticket page
        MyTicketPage myTicketPage = homePage.goToMyTicketPage();

        // Verify ticket is displayed before cancelling
        Assert.assertTrue(myTicketPage.isTicketDisplayed(departStation, arriveStation),
                "Ticket should be displayed in My Ticket page before cancellation");

        // Cancel the ticket
        myTicketPage.cancelTicket(departStation, arriveStation);

        // Verify ticket is removed
        Assert.assertFalse(myTicketPage.isTicketDisplayed(departStation, arriveStation),
                "Ticket should be removed from My Ticket page after cancellation");
    }
}
