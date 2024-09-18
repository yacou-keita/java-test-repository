package ci.djonan.digital_baking_backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
