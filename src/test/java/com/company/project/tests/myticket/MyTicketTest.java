package com.company.project.tests.myticket;

import com.company.project.base.BaseTest;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function MyTicketTest - Test class for My Ticket functionality (TC16)
 */
public class MyTicketTest extends BaseTest {

    @Test(description = "TC16 - User can cancel a ticket")
    public void TC16_UserCanCancelTicket() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String email = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");

        registerPage.register(email, password, password, pid);

        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

        String departStation = JsonReader.getData("tc16", "departStation");
        String arriveStation = JsonReader.getData("tc16", "arriveStation");
        String seatType = JsonReader.getData("tc16", "seatType");
        String ticketAmount = JsonReader.getData("tc16", "ticketAmount");

        bookTicketPage.bookTicket(departStation, arriveStation, seatType, ticketAmount);

        MyTicketPage myTicketPage = homePage.goToMyTicketPage();

        // Verify ticket exists before cancelling
        Assert.assertTrue(myTicketPage.isTicketDisplayed(departStation, arriveStation),
                "Ticket not found before cancellation");

        myTicketPage.cancelTicket(departStation, arriveStation);

        // Verify ticket is removed
        Assert.assertFalse(myTicketPage.isTicketDisplayed(departStation, arriveStation),
                "Ticket still displayed after cancellation");
    }

}