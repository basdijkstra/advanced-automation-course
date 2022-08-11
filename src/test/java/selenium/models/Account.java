package selenium.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Account {

    private int id;
    private int customerId;
    private String type;
    private String balance;
}
