package selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        }
        catch (TimeoutException te) {
            Assertions.fail(String.format("Exception in click(): %s", te.getMessage()));
        }
    }

    protected void sendKeys(By locator, String text) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).sendKeys(text);
        }
        catch (TimeoutException te) {
            Assertions.fail(String.format("Exception in sendKeys(): %s", te.getMessage()));
        }
    }

    protected void select(By locator, String valueToSelect) {

        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.elementToBeClickable(locator),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//option[@value='%s']", valueToSelect)))
            ));
           new Select(driver.findElement(locator)).selectByVisibleText(valueToSelect);
        }
        catch (TimeoutException te) {
            Assertions.fail(String.format("Exception in select(): %s", te.getMessage()));
        }
    }

    protected String getElementText(By locator) {

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).getText();
        }
        catch (TimeoutException te) {
            Assertions.fail(String.format("Exception in getElementText(): %s", te.getMessage()));
        }
        return "";
    }
}
