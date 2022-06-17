package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanResultPage extends BasePage {

    private final By textlabelLoanApplicationResult = By.id("loanStatus");

    public RequestLoanResultPage(WebDriver driver) {
        super(driver);
    }

    public String getLoanApplicationResult() {
        return getElementText(textlabelLoanApplicationResult);
    }
}
