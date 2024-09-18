package ci.djonan.task_app_with_spring_boot;

import ci.djonan.task_app_with_spring_boot.entities.Person;
import ci.djonan.task_app_with_spring_boot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskAppWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskAppWithSpringBootApplication.class, args);
	}

}
