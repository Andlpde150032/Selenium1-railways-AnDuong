package com.company.project.tests.bookticket;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BookTicketTest - Test class for Book Ticket functionality (TC14)
 */
public class BookTicketTest extends BaseTest {

    @Test(description = "TC14 - User can book 1 ticket at a time")
    public void TC14_UserCanBook1Ticket() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

        String departDate = TestUtils.getFutureDate(5);
        String departStation = "Sài Gòn";
        String arriveStation = "Nha Trang";
        String seatType = "Soft seat with air conditioner";
        String ticketAmount = "1";

        bookTicketPage.bookTicket(departDate, departStation, arriveStation, seatType, ticketAmount);

        String successMessage = bookTicketPage.getSuccessMessage();
        Assert.assertEquals(successMessage, "Ticket booked successfully!",
                "Success message is not displayed correctly");
    }
}
