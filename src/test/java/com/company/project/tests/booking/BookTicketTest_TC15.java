package com.company.project.tests.booking;

import com.company.project.base.BaseTest;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.TimetablePage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTicketTest_TC15 extends BaseTest {

    @Test(description = "User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page")
    public void TC15() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Pre-condition: Login with existing test account
        String email = JsonReader.getData("TC15", "loginEmail");
        String password = JsonReader.getData("TC15", "loginPassword");
        homePage.goToLoginPage().login(email, password);

        // Navigate to Timetable page
        TimetablePage timetablePage = homePage.goToTimetablePage();

        // Get expected values from test data
        String expectedDepartFrom = JsonReader.getData("TC15", "departFrom");
        String expectedArriveAt = JsonReader.getData("TC15", "arriveAt");

        // Click "book ticket" for the specified route
        timetablePage.clickBookTicket(expectedDepartFrom, expectedArriveAt);

        // Verify Book Ticket page is loaded with correct pre-filled values
        BookTicketPage bookTicketPage = new BookTicketPage();
        String actualDepartFrom = bookTicketPage.getSelectedDepartStation();
        String actualArriveAt = bookTicketPage.getSelectedArriveStation();

        Assert.assertEquals(actualDepartFrom, expectedDepartFrom, "Depart station should be pre-filled correctly");
        Assert.assertEquals(actualArriveAt, expectedArriveAt, "Arrive station should be pre-filled correctly");
    }
}
