package ci.djonan.many_to_many_usecase.repositories;

import ci.djonan.many_to_many_usecase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    User findByName(String name);
    @Query("SELECT u FROM User u where u.name = :name AND u.password = :password")
    User authentificate(@Param("name") String name,@Param("password") String password);
}
