package knickerbockerHotelTests;

import org.testng.annotations.Test;
import utility.BaseTest;
import utility.Constants;

import static utility.services.ManageUrlService.getDirectlyURL;
import static utility.services.ManageUrlService.switchToWindow;
import static utility.services.ReportService.assertEquals;
import static utility.services.ReportService.assertTrue;
import static utility.services.WebElementService.elementIsDisplayed;
import static utility.services.WebElementService.getElementText;

/**
 * Created by Anya on 17.08.2018.
 */

public class KnickerbockerHotel_001_KnickerbockerHotelTest extends BaseTest{

    @Test
    public void test_001() {

        //Go to landing page.
        getDirectlyURL(Constants.URL,driver);

        //Verify that page has all necessary components.
        assertTrue(elementIsDisplayed(knickerbockerHotelPage.overviewLink,"Overview Link"),
                "Overview Link is NOT displayed");
        assertTrue(elementIsDisplayed(knickerbockerHotelPage.facilitiesLink,"Facilities Link"),
                "Facilities Link is NOT displayed");
        assertTrue(elementIsDisplayed(knickerbockerHotelPage.roomsLink,"Rooms Link"),
                "Rooms Link is NOT displayed");
        assertTrue(elementIsDisplayed(knickerbockerHotelPage.locationLink,"Location Link"),
                "Location Link is NOT displayed");
        assertTrue(elementIsDisplayed(knickerbockerHotelPage.reviewsLink,"Reviews Link"),
                "Reviews Link is NOT displayed");

        knickerbockerHotelPage.clickOnOverviewLink();
        knickerbockerHotelPage.clickOnFacilitiesLink();
        knickerbockerHotelPage.clickOnRoomsLink();
        knickerbockerHotelPage.clickOnLocationLink();
        knickerbockerHotelPage.clickOnReviewsLink();

        knickerbockerHotelPage.clickOnCheckInDate();
        knickerbockerHotelPage.clickOnNextMonthButton();
        knickerbockerHotelPage.clickOnSelectCheckInDate();
        knickerbockerHotelPage.clickOnSelectCheckOutDate();
        knickerbockerHotelPage.clickOnRoomsAndGuests();

        knickerbockerHotelPage.clickOnAddAdultButton();
        knickerbockerHotelPage.clickOnAddChildrenButton();
        knickerbockerHotelPage.selectChildrenAge1InDD("2");
        knickerbockerHotelPage.selectChildrenAge2InDD("10");

        assertEquals("5Гости / 1Номер", getElementText(knickerbockerHotelPage.roomsAndGuests, "Rooms And Guests"), "Incorrect rooms and guests Error message");

        knickerbockerHotelPage.clickOnShowPriceButton();

        switchToWindow(driver);

        knickerbockerHotelPage.clickOnBookNowButton();

        assertEquals("2019-01-24", getElementText(knickerbockerHotelPage.selectedCheckInDate, "Selected Check In Date"), "Incorrect selected check in date Error message");
        assertEquals("2019-01-25", getElementText(knickerbockerHotelPage.selectedCheckOutDate, "Selected Check Out Date"), "Incorrect selected check out date Error message");
        assertEquals("3 Взрослых,\n" +
                "2 Детей", getElementText(knickerbockerHotelPage.selectedPeopleNumber, "Selected People Number"), "Incorrect selected people number Error message");
    }
}

