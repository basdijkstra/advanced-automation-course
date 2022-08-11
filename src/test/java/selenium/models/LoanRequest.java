package selenium.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanRequest {

    private String loanAmount;
    private String downPayment;
    private String fromAccount;
}
