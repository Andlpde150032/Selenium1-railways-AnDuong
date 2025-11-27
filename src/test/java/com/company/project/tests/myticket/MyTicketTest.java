package com.company.project.tests.myticket;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
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

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

        String departStation = "Sài Gòn";
        String arriveStation = "Nha Trang";
        String seatType = "Soft seat with air conditioner";
        String ticketAmount = "1";

        bookTicketPage.bookTicket(departStation, arriveStation, seatType, ticketAmount);

        MyTicketPage myTicketPage = homePage.goToMyTicketPage();
        

        Assert.assertTrue(myTicketPage.isTicketDisplayed(departStation, arriveStation), "Ticket not found before cancellation");

                
                
        myTicketPage.cancelTicket(departStation, arriveStation);

        // Verify ticket is removed
        Assert.assertFalse(myTicketPage.isTicketDisplayed(departStation, arriveStation), "Ticket still displayed after cancellation");
    }
                
                
}
