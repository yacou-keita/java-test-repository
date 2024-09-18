package ci.djonan.digital_baking_backend;

import ci.djonan.digital_baking_backend.dtos.CustomerDTO;
import ci.djonan.digital_baking_backend.entities.*;
import ci.djonan.digital_baking_backend.enums.AccountStatus;
import ci.djonan.digital_baking_backend.enums.OperationType;
import ci.djonan.digital_baking_backend.exceptions.BalanceNotSufficent;
import ci.djonan.digital_baking_backend.exceptions.BankAccountNotFoundException;
import ci.djonan.digital_baking_backend.exceptions.CustomerNotFoundException;
import ci.djonan.digital_baking_backend.repositories.IAccountOperationRepository;
import ci.djonan.digital_baking_backend.repositories.IBankAccountRepository;
import ci.djonan.digital_baking_backend.repositories.ICustomerRepository;
import ci.djonan.digital_baking_backend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBakingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBakingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			BankAccountService bankAccountService,
			IBankAccountRepository bankAccountRepository
	){
		return args -> {
			Stream.of("Yacou","Momo","Chaca").forEach(name ->{
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setName(name);
				customerDTO.setEmail(name + "@gmail.com");
				bankAccountService.saveCustomer(customerDTO);
			});
			List<CustomerDTO> customerList = bankAccountService.getCustomerList();

			customerList.forEach(customer -> {

                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000,9000,customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random() * 120000,5.5, customer.getId());
					List<BankAccount> bankAccountList = bankAccountRepository.findAll();

					for (BankAccount bankAccount:bankAccountList){
						for (int i = 0; i < 10 ; i++) {
							bankAccountService.credit(bankAccount.getId(),10000+Math.random()*120000,"Credit");
//							bankAccountService.debit(bankAccount.getId(),1000+Math.random()*9000,"Debit");
						}
					}
                } catch (CustomerNotFoundException | BankAccountNotFoundException e) {
                    e.printStackTrace();
                }
            });
		};
	}


}
