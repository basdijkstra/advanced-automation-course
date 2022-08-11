package selenium.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Customer {

    private String name;
    private Address address;
    private String phoneNumber;
    private int accountNumber;
}
