package az.orient.bankboot.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RespCustomer {
    @JsonProperty(value = "customerId")
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String dob;
    private String cif;
    private String pin;
    private String seria;
}
