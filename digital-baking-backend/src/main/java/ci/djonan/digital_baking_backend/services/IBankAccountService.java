package ci.djonan.digital_baking_backend.services;

import ci.djonan.digital_baking_backend.dtos.*;
import ci.djonan.digital_baking_backend.entities.BankAccount;
import ci.djonan.digital_baking_backend.entities.CurrentAccount;
import ci.djonan.digital_baking_backend.entities.SavingAccount;
import ci.djonan.digital_baking_backend.exceptions.BalanceNotSufficent;
import ci.djonan.digital_baking_backend.exceptions.BankAccountNotFoundException;
import ci.djonan.digital_baking_backend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IBankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> getCustomerList();

    CustomerDTO getCustomer(Long id) throws CustomerNotFoundException;

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficent;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficent;

    List<BankAccountDTO> bankAccountList();

    List<AccountOperationDTO> accountOperation(String accountId);

    AccountHistoryDTO accountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
