package az.orient.bankboot.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespAccount {
    @JsonProperty(value = "customerId")
    private Long accountId;
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private RespCustomer respCustomer;
}
