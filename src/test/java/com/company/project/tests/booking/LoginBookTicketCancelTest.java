package com.company.project.tests.booking;

import com.company.project.base.BaseTest;
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

    @Test
    public void TC16() {
        HomePage homePage = new HomePage();
        homePage.open();

        // 1. Register a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = "auto_test_" + new Random().nextInt(10000) + "@gmail.com";
        String password = "Password123";
        String pid = "123456789";
        registerPage.register(email, password, password, pid);

        // 2. Login with the new account
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        // 3. Navigate to Book Ticket
        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

        // 4. Book a ticket
        String date = "12/5/2025"; // Ensure this date is valid in the dropdown
        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Soft bed with air conditioner";
        String amount = "1";
        bookTicketPage.bookTicket(date, departFrom, arriveAt, seatType, amount);

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
