package ci.djonan.digital_baking_backend.repositories;

import ci.djonan.digital_baking_backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankAccountRepository extends JpaRepository<BankAccount,String> {
}
