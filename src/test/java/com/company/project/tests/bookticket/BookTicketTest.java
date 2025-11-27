package com.company.project.tests.bookticket;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.*;
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
    public void TC14_UserCanBookOneTicket() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Register a new account to ensure clean state
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");
        registerPage.register(email, password, password, pid);

        // Login
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        // Go to Book Ticket page
        BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

        // Book ticket
        String departStation = JsonReader.getData("bookTicket", "departStation");
        String arriveStation = JsonReader.getData("bookTicket", "arriveStation");
        String seatType = JsonReader.getData("bookTicket", "seatType");
        String ticketAmount = JsonReader.getData("bookTicket", "ticketAmount");

        bookTicketPage.bookTicket(departStation, arriveStation, seatType, ticketAmount);

        // Verify success message
        SuccessPage successPage = new SuccessPage();
        String actualMessage = successPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, "Ticket Booked Successfully!", "Success message does not match");
    }
}
