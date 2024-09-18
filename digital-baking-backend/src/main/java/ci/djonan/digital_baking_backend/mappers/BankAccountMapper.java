package ci.djonan.digital_baking_backend.mappers;

import ci.djonan.digital_baking_backend.dtos.AccountOperationDTO;
import ci.djonan.digital_baking_backend.dtos.CurrentBankAccountDTO;
import ci.djonan.digital_baking_backend.dtos.CustomerDTO;
import ci.djonan.digital_baking_backend.dtos.SavingBankAccountDTO;
import ci.djonan.digital_baking_backend.entities.AccountOperation;
import ci.djonan.digital_baking_backend.entities.CurrentAccount;
import ci.djonan.digital_baking_backend.entities.Customer;
import ci.djonan.digital_baking_backend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {

  public  CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

  public   Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
      CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
      BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
      currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
      currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
      return  currentBankAccountDTO;
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
      CurrentAccount currentAccount = new CurrentAccount();
      BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
      currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
      return currentAccount;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
      SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
      BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
      savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
      savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
      return  savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
      SavingAccount savingAccount = new SavingAccount();
      BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
      savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
      return savingAccount;
    }

    public AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO){
      AccountOperation accountOperation = new AccountOperation();
      BeanUtils.copyProperties(accountOperationDTO,accountOperation);
      return accountOperation;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
      AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
      BeanUtils.copyProperties(accountOperation,accountOperationDTO);
      return accountOperationDTO;
    }
}
