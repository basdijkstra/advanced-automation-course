package selenium;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.models.Account;
import selenium.models.Address;
import selenium.models.Customer;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredRequestLoanTest {

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpec() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://parabank.parasoft.com/parabank/services/bank/")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();

        initializeDatabase();
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 100, 12456, Approved",
            "100000, 100, 12345, Denied"
    })
    public void applyForLoan_shouldYieldExpectedResult_usingCsvSource_withRestAssured(
            String loanAmount, String downPayment, String fromAcccountId, String expectedLoanApplicationResult
    ) {
        // Get customer ID
        int customerId = given()
                .spec(requestSpec)
                .pathParam("username", "john")
                .pathParam("password", "demo")
                .when()
                .get("/login/{username}/{password}")
                .then().assertThat().statusCode(200).and().extract().path("id");

        // Send loan application
        given()
                .spec(requestSpec)
                .queryParam("customerId", customerId)
                .queryParam("amount", loanAmount)
                .queryParam("downPayment", downPayment)
                .queryParam("fromAccountId", fromAcccountId)
                .when().post("/requestLoan")
                .then()
                .log().body().and()
                .assertThat()
                .statusCode(200)
                .and()
                .body("approved", equalTo(expectedLoanApplicationResult.equals("Approved")));

    }

    private void initializeDatabase() {

        given()
                .spec(requestSpec)
                .when()
                .post("/initializeDB")
                .then()
                .statusCode(204);
    }

    @Test
    public void demonstrateDeserialization() {

        Account account = given()
                .spec(requestSpec)
                .when()
                .get("/accounts/12345")
                .as(Account.class);

        Assertions.assertEquals(12212, account.getCustomerId());
    }

    @Test
    public void demonstrateSerialization() {

        Address address = new Address("My street", "My city", "CA", "90210");

        Customer cust = Customer
                .builder()
                .name("John")
                .build();

        Customer customer = new Customer("John Smith", address, "310-447-4121", 13344);

        given().log().all()
                .spec(requestSpec)
                .queryParam("accountId", 12345)
                .queryParam("amount", "100")
                .body(customer)
                .when().post("/billpay")
                .then().log().all();
    }
}
