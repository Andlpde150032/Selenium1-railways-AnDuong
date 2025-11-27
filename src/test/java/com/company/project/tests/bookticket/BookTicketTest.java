package com.company.project.tests.bookticket;

import com.company.project.base.BaseTest;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.RegisterPage;
import com.company.project.pages.SuccessPage;
import com.company.project.pages.TimetablePage;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BookTicketTest - Test class for Book Ticket functionality (TC14,TC15)
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

    @Test(description = "TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page")
    public void TC15_UserCanOpenBookTicketFromTimetable() {
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

        // Go to Timetable page
        TimetablePage timetablePage = homePage.goToTimetablePage();

        // Click "book ticket" for "Huế" to "Sài Gòn"
        String departStation = JsonReader.getData("tc15", "departStation");
        String arriveStation = JsonReader.getData("tc15", "arriveStation");
        timetablePage.clickBookTicket(departStation, arriveStation);

        // Verify "Book ticket" page is loaded (implicitly by checking elements on it)
        BookTicketPage bookTicketPage = new BookTicketPage();

        // Verify "Depart Station" is "Huế"
        String actualDepartStation = bookTicketPage.getSelectedDepartStation();
        Assert.assertEquals(actualDepartStation, departStation, "Depart Station is not pre-filled correctly");

        // Verify "Arrive Station" is "Sài Gòn"
        String actualArriveStation = bookTicketPage.getSelectedArriveStation();
        Assert.assertEquals(actualArriveStation, arriveStation, "Arrive Station is not pre-filled correctly");
    }
}
