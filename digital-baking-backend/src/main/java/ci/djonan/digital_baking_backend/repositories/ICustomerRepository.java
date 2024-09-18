package ci.djonan.digital_baking_backend.repositories;

import ci.djonan.digital_baking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
