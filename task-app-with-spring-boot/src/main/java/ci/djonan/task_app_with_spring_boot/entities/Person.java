package ci.djonan.task_app_with_spring_boot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Data @NoArgsConstructor @AllArgsConstructor
public class Person {
    @Id
    private String id;
    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;

}
