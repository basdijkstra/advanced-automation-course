package selenium;

import org.junit.jupiter.params.provider.Arguments;
import selenium.models.LoanApplicationResult;
import selenium.models.LoanRequest;

import java.util.stream.Stream;

public class TestDataProvider {

    public static Stream<Arguments> loanApplicationTestData() {
        return Stream.of(
                Arguments.of(
                        new LoanRequest("1000", "100", "13011"),
                        LoanApplicationResult.APPROVED
                ),
                Arguments.of(
                        new LoanRequest("100000", "100", "12345"),
                        LoanApplicationResult.DENIED
                )
        );
    }
}
