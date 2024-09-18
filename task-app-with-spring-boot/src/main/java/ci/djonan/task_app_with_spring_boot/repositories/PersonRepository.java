package ci.djonan.task_app_with_spring_boot.repositories;

import ci.djonan.task_app_with_spring_boot.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {
}
