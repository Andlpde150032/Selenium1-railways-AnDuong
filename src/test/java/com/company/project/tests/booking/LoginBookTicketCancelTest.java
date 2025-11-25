package com.company.project.tests.booking;

import com.company.project.base.BaseTest;
import com.company.project.models.Registration;
import com.company.project.models.Ticket;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginBookTicketCancelTest - Test case for TC16
 */
public class LoginBookTicketCancelTest extends BaseTest {

    @Test(description = "TC16: User can cancel a ticket")
    public void TC16() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Get test data from JSON
        Registration regData = Registration.getRegistrationData();
        Ticket ticketData = Ticket.getBookingData();

        // 1. Register a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = "auto_test_" + new Random().nextInt(10000) + "@gmail.com";
        registerPage.register(email, regData.getPassword(), regData.getPassword(), regData.getPid());

        // 2. Login with the new account
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, regData.getPassword());

        // 3. Navigate to Book Ticket
        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

        // 4. Book a ticket
        bookTicketPage.bookTicket(
                ticketData.getDate(),
                ticketData.getDepartFrom(),
                ticketData.getArriveAt(),
                ticketData.getSeatType(),
                ticketData.getAmount());

        // 5. Verify success
        Assert.assertEquals(bookTicketPage.getSuccessMessage(), "Ticket Booked Successfully!", "Booking failed!");

        // 6. Navigate to My Ticket
        MyTicketPage myTicketPage = homePage.goToMyTicketPage();

        // 7. Cancel the ticket
        int initialCount = myTicketPage.getTicketCount();
        myTicketPage.cancelTicket();

        // 8. Verify removal
        int finalCount = myTicketPage.getTicketCount();
        Assert.assertEquals(finalCount, initialCount - 1, "Ticket was not cancelled!");
    }
}
