package ci.djonan.many_to_many_usecase.repositories;

import ci.djonan.many_to_many_usecase.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
