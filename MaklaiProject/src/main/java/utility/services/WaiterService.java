package utility.services;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utility.Constants.*;
import static utility.Log.info;
import static utility.Log.warn;
import static utility.services.ReportService.assertTrue;

@Log4j
public class WaiterService {

    public static void waitForElementVisible(WebElement element, WebDriver driver) {

        try {
            WebDriverWait wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (TimeoutException e){
            assertTrue(false, "ELEMENT: \"" + element + "\" is not presents.");
        }
        catch (StaleElementReferenceException e){
            warn("ELEMENT: \"" + element + "\" is not found in the cache.");
        }

    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForJSandJQueryToLoad(final WebDriver driver) {
        ExpectedCondition<Boolean> jQueryLoad = driver1 -> {
            try {
                return ((Long) ((JavascriptExecutor) driver).executeScript("return !!window.jQuery && jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };

        ExpectedCondition<Boolean> jsLoad = driver2 -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .toString().equals("complete");

        try {
            WebDriverWait wait = new WebDriverWait(driver, PAGE_TIMEOUT);
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (TimeoutException e) {
            info("No one jQuery or Js activity");
        }
    }

}
