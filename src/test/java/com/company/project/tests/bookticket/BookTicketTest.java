package com.company.project.tests.bookticket;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.TimetablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function BookTicketTest - Test class for Book Ticket functionality (TC15)
 */
public class BookTicketTest extends BaseTest {

    @Test(description = "TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Timetable' page")
    public void TC15_UserCanOpenBookTicketPageFromTimetable() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        TimetablePage timetablePage = homePage.goToTimetablePage();
        BookTicketPage bookTicketPage = timetablePage.clickBookTicket("Sài Gòn", "Đà Nẵng");

        String actualDepartStation = bookTicketPage.getSelectedDepartStation();
        Assert.assertEquals(actualDepartStation, "Sài Gòn", "Depart station is not selected correctly");

        String actualArriveStation = bookTicketPage.getSelectedArriveStation();
        Assert.assertEquals(actualArriveStation, "Đà Nẵng", "Arrive station is not selected correctly");
    }}

    
    

    

    
    
    
            