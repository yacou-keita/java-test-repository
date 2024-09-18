package ci.djonan.digital_baking_backend.dtos;

import ci.djonan.digital_baking_backend.entities.BankAccount;
import ci.djonan.digital_baking_backend.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private String description;
    private OperationType type;
}
