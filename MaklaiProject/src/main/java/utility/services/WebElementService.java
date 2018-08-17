package utility.services;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;

import static utility.Log.*;
import static utility.services.ManageUrlService.scrollDown;
import static utility.services.ManageUrlService.stopLoad;
import static utility.services.PressKeysService.pressBackSpace;
import static utility.services.ReportService.assertTrue;
import static utility.services.WaiterService.*;


public class WebElementService {

    public static void selectTextInDDByText(WebElement dropDown, String text) {
        try{
            Select optionsFromDD = new Select(dropDown);
            optionsFromDD.selectByVisibleText(text);
            utility.Log.info(text + " was selected in DropDown");
        } catch (Exception e){
            utility.Log.error("Can not work with DropDown");
            Assert.fail("Can not work with DropDown");
        }
    }

    public static boolean checkFocusOnElement(WebElement element, String elementName, WebDriver driver){
        int attempt_counter = 0;
        while (!element.equals(driver.switchTo().activeElement())){
            sleep(1);
            attempt_counter++;

            if (attempt_counter == 5){
                error("Break, element isn't focused by timeout.");
                break;
            }
        }
        //Check that field is focused.
        if(element.equals(driver.switchTo().activeElement())){
            info("\"" + elementName + "\" field is focused.");
            return true;
        }
        else {
            info("\"" + elementName + "\" field is NOT focused.");
            return false;
        }
    }
    public static boolean elementIsDisplayed(WebElement element, String elementName){

        try {

            if (element != null && element.isDisplayed()){
                //Log.info("\"" + elementName + "\" is displayed.");
                return true;
            }
            else {
                info("\"" + elementName + "\" is not displayed.");
                return false;
            }
        }
        catch (NoSuchElementException e){
            info("\"" + elementName + "\" is NOT displayed.");
            return false;
        }
        catch (ElementNotVisibleException e){
            assertTrue(false, "\"" + elementName + "\" was not visible.");
            return false;
        }
        catch (StaleElementReferenceException e){
            //ReportService.assertTrue(false, "\"" + elementName + "\" was not in the cache.");
            return false;
        }
    }

    public static void clickOnElement(WebElement element, String elementName, WebDriver driver){

        try {
            WebDriverWait wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.elementToBeClickable(element));}
        catch (TimeoutException ex){
            info("\"" + elementName + "\" is not clickable.");
            clickHack(element, elementName, driver);
            info("Click on \"" + elementName + "\".");
            return;
        }
        try {
            element.click();
            info("Click on \"" +elementName+"\".");
        }
        catch (NoSuchElementException  e){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
        catch (ElementNotVisibleException e){
            error("ElementNotVisibleException");
            clickHack(element, elementName, driver);
        }
        catch (TimeoutException e){
            stopLoad(driver);
        }
        catch (StaleElementReferenceException e){
            warn("StaleElementReferenceException.");
            info("Click on \"" +elementName+"\".");
            element.click();
        }
        catch (WebDriverException e){
            error("WebDriverException" +e);
            clickHack(element, elementName, driver);
        }
    }

    private static void clickHack(WebElement element, String elementName, WebDriver driver){
        boolean flag = true;
        int attempt = 0;

        while (flag && attempt<5){
            attempt++;
            try {
                info("\"" + elementName + "\" is hide by another element, move down.");
                scrollDown(driver,500);
                moveToCoordinate(0, 0, driver);
                element.click();
                info("Click on \"" +elementName+"\".");
                flag = false;
            }
            catch (WebDriverException ignored){}
        }
    }

    public static String getElementValue(WebElement element, String elementName){
        return getElementAttribute(element, elementName, "value");
    }

    public static String getElementText(WebElement element, String elementName) {

        try {
            String text = element.getText();
            info("\"" + elementName +"\" content on page  - \"" + ObjectService.trimer(text) + "\".");
            return text;
        }
        catch (org.openqa.selenium.NoSuchElementException | ElementNotVisibleException e){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
            throw new CustomException(e.toString());
        }
    }

    public static void sendKeysClear(WebElement element, String elementName, String inputText, WebDriver driver){

        try {
            waitForElementVisible(element, driver);
            int attempt = 0;
            element.clear();
            element.sendKeys(inputText);
            while (element.getAttribute("value").length()!=inputText.length() && attempt<5){
                attempt++;
                element.clear();
                element.sendKeys(inputText);
            }
            info("\"" + elementName + "\" input text: \"" + inputText + "\".");
        }
        catch (NoSuchElementException e ){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
        catch (ElementNotVisibleException e){
            assertTrue(false, "\"" + elementName + "\" was not visible.");
        }
        catch (InvalidElementStateException e){
            warn("Catch InvalidElementStateException.");
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(inputText);
        }
    }

    public static WebElement getElement(By locator, WebElement parent) {
        WebElement element = null;
        boolean flag = true;
        int attempt = 0;
        while (flag && attempt < 5) {
            attempt++;
            sleep(1);
            try {
                element = parent.findElement(locator);
                flag = false;
            } catch (StaleElementReferenceException e) {
                warn("StaleElementReferenceException for " + locator);
            } catch (NoSuchElementException e) {
                warn("NoSuchElementException");
            } catch (Exception e) {
                assertTrue(false, "Unknown exception.");
            }
        }
        return element;
    }

    public static void moveToCoordinate(int x, int y, WebDriver driver) {

        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).build().perform();
        info("Move to coordinate "+x+"x"+y);
    }

    public static String getElementAttribute(WebElement element, String elementName, String attribute){
        String attributeValue = "";
        try {
            //Get value.
            if (element.getAttribute(attribute) != null){
                attributeValue = element.getAttribute(attribute);
            }
            else {
                assertTrue(false,"Attribute \""+attribute+"\" not present.");
            }
            info(attribute + " \"" + elementName +"\" = \"" + attributeValue + "\".");
        }
        catch (NoSuchElementException e){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
        catch (ElementNotVisibleException e){
            assertTrue(false, "\"" + elementName + "\" was not visible.");
        }
        return attributeValue;
    }

    public static void sendKeysManualClear(WebElement element, String elementName, String inputText, WebDriver driver) {
            clearFieldManual(element, driver);
            sendKeysClear(element, elementName, inputText, driver);
    }

	/**
     * Method cleared the field (BackSpace)
     *
     * @param element input on page
     */
    public static void clearFieldManual(WebElement element, WebDriver driver) {

        int countChar = getElementValue(element, element.toString()).length();
        int i = 0;
        while (i<countChar){
            i++;
            clickOnElement(element,"element", driver);
            pressBackSpace(element);
        }
    }

    /**
     * @implSpec  Working with Reactjs component.
     */
    @Deprecated
    public static void removeImageInUplouder(List<WebElement> imageBlocks, int index, WebDriver driver) {
        Optional.ofNullable(imageBlocks)
                .filter(el -> imageBlocks.size() >= index + 1)
                .map(el -> el.get(index))
                .ifPresent(el -> clickOnElement(getElement(By.xpath(".//button[contains(@id, '-media--" + index + "remove-btn')]"), el), "removeImageItem", driver));
    }

    /**
     * @implSpec  Working with Reactjs component.
     */
    @Deprecated
    public static void addYoutubeLinkInUplouder(List<WebElement> imageBlocks, String value, int index, WebDriver driver) {
        Optional.ofNullable(imageBlocks)
                .filter(el -> imageBlocks.size() >= index + 1)
                .map(el -> el.get(index))
                .ifPresent(el -> {
                    clickOnElement(getElement(By.xpath(".//*[contains(@class, 'uploader-product-media-file-area__add-video-btn')]"), el), "youtubeBtn", driver);
                    WebElement link = getElement(By.xpath(".//*[contains(@id, '-image-video-area-" + index + "-input')]"), el);
                    waitForElementVisible(link, driver);
                    sendKeysManualClear(link, "youtubeInput", value, driver);
                    clickOnElement(getElement(By.xpath(".//*[contains(@id, '-image-video-area-" + index + "-submit')]"), el), "youtubeSubmitBtn", driver);
                });
    }
}
