package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.*;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class RequestLoanTest {

    private WebDriver driver;

    @BeforeEach
    public void executeTestSetup() {

        initializeDatabase();
        startBrowser();
    }

    @Test
    public void applyForLoan_withinBoundaries_shouldBeAccepted() throws InterruptedException {

        new LoginPage(this.driver)
                .loginAs("john", "demo");

        Thread.sleep(1000);

        new AccountOverviewPage(this.driver)
                .selectMenuItem("Request Loan");

        Thread.sleep(1000);

        new RequestLoanPage(this.driver)
                .applyForLoan("1000", "100", "12345");

        Thread.sleep(1000);

        String result = new RequestLoanResultPage(this.driver)
                .getLoanApplicationResult();

        Assertions.assertEquals("Approved", result);
    }

    @Test
    public void applyForLoan_outsideBoundaries_shouldBeDenied() throws InterruptedException {

        new LoginPage(this.driver)
                .loginAs("john", "demo");

        Thread.sleep(1000);

        new AccountOverviewPage(this.driver)
                .selectMenuItem("Request Loan");

        Thread.sleep(1000);

        new RequestLoanPage(this.driver)
                .applyForLoan("100000", "100", "12345");

        Thread.sleep(1000);

        String result = new RequestLoanResultPage(this.driver)
                .getLoanApplicationResult();

        Assertions.assertEquals("Denied", result);
    }

    @AfterEach
    public void executeTestTeardown() {

        driver.quit();
    }

    private void initializeDatabase() {

        given()
                .when()
                .post("http://localhost:8080/parabank/services/bank/initializeDB")
                .then()
                .statusCode(204);
    }

    private void startBrowser() {

        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }
}
