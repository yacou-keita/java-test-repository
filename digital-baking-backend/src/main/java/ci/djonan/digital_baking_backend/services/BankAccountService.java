package ci.djonan.digital_baking_backend.services;

import ci.djonan.digital_baking_backend.dtos.*;
import ci.djonan.digital_baking_backend.entities.*;
import ci.djonan.digital_baking_backend.enums.OperationType;
import ci.djonan.digital_baking_backend.exceptions.BalanceNotSufficent;
import ci.djonan.digital_baking_backend.exceptions.BankAccountNotFoundException;
import ci.djonan.digital_baking_backend.exceptions.CustomerNotFoundException;
import ci.djonan.digital_baking_backend.mappers.BankAccountMapper;
import ci.djonan.digital_baking_backend.repositories.IAccountOperationRepository;
import ci.djonan.digital_baking_backend.repositories.IBankAccountRepository;
import ci.djonan.digital_baking_backend.repositories.ICustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountService implements IBankAccountService {

    private IBankAccountRepository bankAccountRepository;
    private ICustomerRepository customerRepository;
    private IAccountOperationRepository accountOperationRepository;
    private BankAccountMapper bankAccountMapper;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = bankAccountMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Saving new Customer");
        return bankAccountMapper.fromCustomer(savedCustomer);
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = bankAccountMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Saving new Customer");
        return bankAccountMapper.fromCustomer(savedCustomer);
    }


    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreateAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverdraft(overDraft);
        CurrentAccount savedCurrentAccount = bankAccountRepository.save(currentAccount);
        return bankAccountMapper.fromCurrentBankAccount(savedCurrentAccount);
    }

    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreateAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        SavingAccount savedSavingAccount = bankAccountRepository.save(savingAccount);
        return bankAccountMapper.fromSavingBankAccount(savedSavingAccount);
    }


    public List<CustomerDTO> getCustomerList() {
        List<Customer> customerList = customerRepository.findAll();
       List<CustomerDTO> customerDTOList = customerList.stream().map(customer -> bankAccountMapper.fromCustomer(customer)).toList();
        return customerDTOList;
    }

public CustomerDTO getCustomer(Long id) throws CustomerNotFoundException{
        Customer customer = customerRepository.findById(id).orElseThrow(() ->new CustomerNotFoundException("Customer not find"));
        CustomerDTO customerDTO  = new BankAccountMapper().fromCustomer(customer);
        return  customerDTO;
    }

    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bankaccount not found"));
        if(bankAccount instanceof  CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return bankAccountMapper.fromCurrentBankAccount(currentAccount);
        }
        SavingAccount savingAccount = (SavingAccount) bankAccount;
        return bankAccountMapper.fromSavingBankAccount(savingAccount);
    }

    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficent {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bankaccount not found"));
        if(bankAccount.getBalance() > amount) throw new BalanceNotSufficent("Balance not sufficient");
        AccountOperation accountOperation = AccountOperation.builder()
                .type(OperationType.DEBIT)
                .amount(amount)
                .description(description)
                .operationDate(new Date())
                .bankAccount(bankAccount)
                .build();
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bankaccount not found"));
        AccountOperation accountOperation = AccountOperation.builder()
                .type(OperationType.CREDIT)
                .amount(amount)
                .description(description)
                .operationDate(new Date())
                .bankAccount(bankAccount)
                .build();
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficent {
       debit(accountIdSource,amount,"Transfer to " + accountIdDestination);
       credit(accountIdDestination,amount,"Transfer from " + accountIdSource);
    }


    public List<BankAccountDTO> bankAccountList(){
        List<BankAccount> bankAccountList =  bankAccountRepository.findAll();
        return bankAccountList.stream().map(bankAccount -> {
            if(bankAccount instanceof  CurrentAccount){
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankAccountMapper.fromCurrentBankAccount(currentAccount);
            }
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankAccountMapper.fromSavingBankAccount(savingAccount);
        }).toList();
    }


    public List<AccountOperationDTO> accountOperation(String accountId){
        List<AccountOperation> accountOperationList = accountOperationRepository.findByBankAccountId(accountId);
        return accountOperationList.stream().map(accountOperation -> bankAccountMapper.fromAccountOperation(accountOperation)).toList();
    }


    public AccountHistoryDTO accountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank account not found"));
        Page<AccountOperation> accountOperationList = accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page,size));
        List<AccountOperationDTO> accountOperationDTOList = accountOperationList.stream().map(accountOperation -> bankAccountMapper.fromAccountOperation(accountOperation)).toList();
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setOperationHistory(accountOperationDTOList);
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperationList.getTotalPages());
        return accountHistoryDTO;
    }


}
