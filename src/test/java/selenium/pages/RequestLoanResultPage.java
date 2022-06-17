package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanResultPage {

    private WebDriver driver;

    private By textlabelLoanApplicationResult = By.id("loanStatus");

    public RequestLoanResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoanApplicationResult() {
        return driver.findElement(textlabelLoanApplicationResult).getText();
    }
}
