package ci.djonan.many_to_many_usecase;

import ci.djonan.many_to_many_usecase.entities.Role;
import ci.djonan.many_to_many_usecase.entities.User;
import ci.djonan.many_to_many_usecase.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ManyToManyUsecaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyUsecaseApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			User user = new User();
			user.setName("Yacou");
			user.setPassword("1234");
			userService.addNewUser(user);

			User user1 = new User();
			user1.setName("seydou");
			user1.setPassword("1234");
			userService.addNewUser(user1);

			Stream.of("ETUDENT","ADMIN","USER").forEach(name -> {
				Role role = new Role();
				role.setName(name);
				userService.addNewRole(role);
			});


			userService.addRoleToUser("Yacou","ETUDENT");
			userService.addRoleToUser("Yacou","USER");
			userService.addRoleToUser("seydou","ADMIN");
			userService.addRoleToUser("seydou","USER");

		User findUserConnected = userService.authenticate("Yacou","1234");

		try{
			System.out.println("User connected " + findUserConnected );
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

		};
	}

}
