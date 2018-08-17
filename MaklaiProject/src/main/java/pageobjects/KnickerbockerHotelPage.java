package pageobjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;

import java.io.File;
import static utility.services.WebElementService.clickOnElement;
import static utility.services.WebElementService.selectTextInDDByText;

/**
 * Created by Anya on 17.08.2018.
 */
public class KnickerbockerHotelPage {

    @FindBy(xpath = "//a[@href='#overview']")
    public WebElement overviewLink;

    @FindBy(xpath = "//a[@href='#facilities']")
    public WebElement facilitiesLink;

    @FindBy(xpath = "//a[@href='#rooms']")
    public WebElement roomsLink;

    @FindBy(xpath = "//a[@href='#location']")
    public WebElement locationLink;

    @FindBy(xpath = "//a[@href='#reviews']")
    public WebElement reviewsLink;

    @FindBy(xpath = "//span[contains(text(), 'Дата заезда')]//following-sibling::div/span")
    public WebElement checkInDate;

    @FindBy(xpath = "//span[contains(text(), 'Дата выезда')]//following-sibling::div/span")
    public WebElement checkOutDate;

    @FindBy(xpath = "//span[contains(text(), 'Номера и Гости')]//following-sibling::div")
    public WebElement roomsAndGuests;

    @FindBy(xpath = "//table[@class='month2']/tbody/tr[4]/td[5]/div")
    public WebElement selectCheckInDate;

    @FindBy(xpath = "//table[@class='month2']/tbody/tr[4]/td[6]/div")
    public WebElement selectCheckOutDate;

    @FindBy(xpath = "//*[@class='svg-icon svg-icon-calendar-arrow arrow-next']")
    public WebElement nextMonthButton;

    @FindBy(xpath = "//button[contains(text(), 'Посмотреть цены')]")
    public WebElement showPriceButton;

    @FindBy(xpath = "//div[@class='adults-wrapper']//following-sibling::div[@class='js-add btn-add']")
    public WebElement addAdultButton;

    @FindBy(xpath="//div[@class='children-wrapper']//following-sibling::div[@class='js-add btn-add']")
    public WebElement addChildButton;

    @FindBy(xpath="//div[@class='js-child-age-container child-age-wrapper']//descendant::select[@data-child-age='1']")
    public WebElement selectChildrenAge1;

    @FindBy(xpath="//div[@class='js-child-age-container child-age-wrapper']//descendant::select[@data-child-age='2']")
    public WebElement selectChildrenAge2;

    @FindBy(xpath="//div[@class='book-now-btn_wrapper']/button")
    public WebElement bookNowButton;

    @FindBy(xpath="//div[@class='book-info__room-info']//descendant::span[contains(text(), '2019-01-24')]")
    public WebElement selectedCheckInDate;

    @FindBy(xpath="//div[@class='book-info__room-info']//descendant::span[contains(text(), '2019-01-25')]")
    public WebElement selectedCheckOutDate;

    @FindBy(xpath="//div[@class='book-info__blank room-blank-main-mobile']//descendant::span[@class='book-info-people-number']")
    public WebElement selectedPeopleNumber;

    protected WebDriver driver;
    public String name = "KnickerbockerHotel_001_KnickerbockerHotelTest";

    public KnickerbockerHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnOverviewLink() {
        clickOnElement(overviewLink, "Overview Link", driver);
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + name + System.currentTimeMillis() + ".png"));
            Log.info("After click " + overviewLink + " screenshot is taken");
        }
        catch (Exception e) {
            Log.info("Exception while taking ScreenShot " + e.getMessage());
        }
    }

    public void clickOnFacilitiesLink(){

        clickOnElement(facilitiesLink, "Facilities Link", driver);
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + name + System.currentTimeMillis() + ".png"));
            Log.info("After click " + facilitiesLink + " screenshot is taken");
        }
        catch (Exception e) {
            Log.info("Exception while taking ScreenShot " + e.getMessage());
        }
    }

    public void clickOnRoomsLink() {
        clickOnElement(roomsLink, "Rooms Link", driver);
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + name + System.currentTimeMillis() + ".png"));
            Log.info("After click " + roomsAndGuests + " screenshot is taken");
        }
        catch (Exception e) {
            Log.info("Exception while taking ScreenShot " + e.getMessage());
        }
    }

    public void clickOnLocationLink() {
        clickOnElement(locationLink, "Location Link", driver);
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + name + System.currentTimeMillis() + ".png"));
            Log.info("After click " + locationLink + " screenshot is taken");
        }
        catch (Exception e) {
            Log.info("Exception while taking ScreenShot " + e.getMessage());
        }
    }

    public void clickOnReviewsLink() {
        clickOnElement(reviewsLink, "Reviews Link", driver);
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + name + System.currentTimeMillis() + ".png"));
            Log.info("After click " + reviewsLink + " screenshot is taken");
        }
        catch (Exception e) {
            Log.info("Exception while taking ScreenShot " + e.getMessage());
        }
    }

    public void clickOnCheckInDate() {
        clickOnElement(checkInDate, "Check In Date", driver);
    }

    public void clickOnSelectCheckInDate() {
        clickOnElement(selectCheckInDate, "Select Check In Date", driver);
    }

    public void clickOnSelectCheckOutDate() {
        clickOnElement(selectCheckOutDate, "Select Check Out Date", driver);
    }

    public void clickOnShowPriceButton() {
        clickOnElement(showPriceButton, "Show Price Button", driver);
    }

    public void clickOnRoomsAndGuests() {
        clickOnElement(roomsAndGuests, "Rooms And Guests", driver);
    }

    public void clickOnAddAdultButton() {
        clickOnElement(addAdultButton, "Add Adult Button", driver);
    }

    public void clickOnAddChildrenButton() {
        for(int i=0; i<=1; i++) {
            clickOnElement(addChildButton, "Add Children Button", driver);
        }
    }

    public void clickOnNextMonthButton() {
        for(int i=0; i<=3; i++) {
            clickOnElement(nextMonthButton, "Next Month Button", driver);
        }
    }

    public void selectChildrenAge1InDD(String text){
        selectTextInDDByText(selectChildrenAge1, text);
    }

    public void selectChildrenAge2InDD(String text){
        selectTextInDDByText(selectChildrenAge2, text);
    }

    public void clickOnBookNowButton() {
        clickOnElement(bookNowButton, "Book Now Button", driver);
    }
}
