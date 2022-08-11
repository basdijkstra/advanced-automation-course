package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.models.Credentials;
import selenium.models.LoanApplicationResult;
import selenium.models.LoanRequest;
import selenium.pages.*;

import java.time.Duration;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class RequestLoanTest {

    private WebDriver driver;
    private Credentials credentials;

    @BeforeEach
    public void executeTestSetup() {

        this.credentials = new Credentials("john", "demo");

        initializeDatabase();
        startBrowser();
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 100, 13011, Approved",
            "100000, 100, 12345, Denied"
    })
    public void applyForLoan_shouldYieldExpectedResult_usingCsvSource(
            String loanAmount, String downPayment, String fromAcccountId, String expectedLoanApplicationResult
    ) {

        LoanRequest loanRequest = new LoanRequest(
                loanAmount, downPayment, fromAcccountId
        );

        new LoginPage(this.driver)
                .loginAs(credentials.getUsername(), credentials.getPassword());

        new AccountOverviewPage(this.driver)
                .selectMenuItem("Request Loan");

        new RequestLoanPage(this.driver)
                .applyForLoan(loanRequest);

        String actualLoanApplicationResult = new RequestLoanResultPage(this.driver)
                .getLoanApplicationResult();

        Assertions.assertEquals(expectedLoanApplicationResult, actualLoanApplicationResult);
    }

    @ParameterizedTest
    @MethodSource("selenium.TestDataProvider#loanApplicationTestData")
    public void applyForLoan_shouldYieldExpectedResult_usingMethodSource(
            LoanRequest loanRequest, String expectedLoanApplicationResult
    ) {
        new LoginPage(this.driver)
                .loginAs(credentials.getUsername(), credentials.getPassword());

        new AccountOverviewPage(this.driver)
                .selectMenuItem("Request Loan");

        new RequestLoanPage(this.driver)
                .applyForLoan(loanRequest);

        String actualLoanApplicationResult = new RequestLoanResultPage(this.driver)
                .getLoanApplicationResult();

        Assertions.assertEquals(expectedLoanApplicationResult, actualLoanApplicationResult);
    }



    @AfterEach
    public void executeTestTeardown() {

        driver.quit();
    }

    private void initializeDatabase() {

        given()
                .when()
                .post("https://parabank.parasoft.com/parabank/services/bank/initializeDB")
                .then()
                .statusCode(204);
    }

    private void startBrowser() {

        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }
}
