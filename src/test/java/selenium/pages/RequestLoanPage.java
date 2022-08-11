package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import selenium.models.LoanRequest;

public class RequestLoanPage extends BasePage {

    private final By textfieldLoanAmount = By.id("amount");
    private final By textfieldDownPayment = By.id("downPayment");
    private final By dropdownFromAccountId = By.id("fromAccountId");
    private final By buttonApplyForLoan = By.xpath("//input[@value='Apply Now']");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public void applyForLoan(LoanRequest loanRequest) {
        sendKeys(textfieldLoanAmount, loanRequest.getLoanAmount());
        sendKeys(textfieldDownPayment, loanRequest.getDownPayment());
        select(dropdownFromAccountId, loanRequest.getFromAccount());
        click(buttonApplyForLoan);
    }
}
