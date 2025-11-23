package com.company.project.tests.booking;

import com.company.project.base.BaseTest;
import com.company.project.pages.BookTicketPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.SuccessPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTicketTest_TC14 extends BaseTest {

        @Test(description = "User can book 1 ticket at a time")
        public void TC14() {
                HomePage homePage = new HomePage();
                homePage.open();

                // Pre-condition: Login with existing test account
                String email = JsonReader.getData("login", "email");
                String password = JsonReader.getData("login", "password");
                homePage.goToLoginPage().login(email, password);

                // Navigate to Book Ticket page
                BookTicketPage bookTicketPage = homePage.goToBookTicketPage();

                // Book a ticket
                String departDate = JsonReader.getData("TC14", "departDate");
                String departFrom = JsonReader.getData("TC14", "departFrom");
                String arriveAt = JsonReader.getData("TC14", "arriveAt");
                String seatType = JsonReader.getData("TC14", "seatType");
                String ticketAmount = JsonReader.getData("TC14", "ticketAmount");

                bookTicketPage.bookTicket(departDate, departFrom, arriveAt, seatType, ticketAmount);

                // Verify success message
                SuccessPage successPage = new SuccessPage();
                String successMessage = successPage.getSuccessMessage();
                Assert.assertTrue(successMessage.contains("Ticket Booked Successfully"),
                                "Success message should contain 'Ticket Booked Successfully'");

                // Verify ticket details
                Assert.assertEquals(successPage.getDepartStation(), departFrom, "Depart station should match");
                Assert.assertEquals(successPage.getArriveStation(), arriveAt, "Arrive station should match");
                Assert.assertEquals(successPage.getSeatType(), seatType, "Seat type should match");
                Assert.assertEquals(successPage.getDepartDate(), departDate, "Depart date should match");
                Assert.assertEquals(successPage.getAmount(), ticketAmount, "Ticket amount should match");
        }
}
