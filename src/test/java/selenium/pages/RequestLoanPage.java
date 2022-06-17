package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RequestLoanPage {

    private WebDriver driver;

    private By textfieldLoanAmount = By.id("amount");
    private By textfieldDownPayment = By.id("downPayment");
    private By dropdownFromAccountId = By.id("fromAccountId");
    private By buttonApplyForLoan = By.xpath("//input[@value='Apply Now']");

    public RequestLoanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void applyForLoan(String loanAmount, String downPayment, String fromAccount) {
        driver.findElement(textfieldLoanAmount).sendKeys(loanAmount);
        driver.findElement(textfieldDownPayment).sendKeys(downPayment);
        new Select(driver.findElement(dropdownFromAccountId)).selectByVisibleText(fromAccount);
        driver.findElement(buttonApplyForLoan).click();
    }
}
