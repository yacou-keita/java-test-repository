package ci.djonan.digital_baking_backend.repositories;

import ci.djonan.digital_baking_backend.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccountId(String id);
    Page<AccountOperation> findByBankAccountId(String id, Pageable pageable);
}
