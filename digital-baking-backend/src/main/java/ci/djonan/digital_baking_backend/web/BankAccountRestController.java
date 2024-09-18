package ci.djonan.digital_baking_backend.web;

import ci.djonan.digital_baking_backend.dtos.AccountHistoryDTO;
import ci.djonan.digital_baking_backend.dtos.AccountOperationDTO;
import ci.djonan.digital_baking_backend.dtos.BankAccountDTO;
import ci.djonan.digital_baking_backend.entities.AccountOperation;
import ci.djonan.digital_baking_backend.exceptions.BankAccountNotFoundException;
import ci.djonan.digital_baking_backend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bankAccount")
public class BankAccountRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/all")
    List<BankAccountDTO> getBankAccountList(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/{id}")
    BankAccountDTO getBankAccount(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping("/{id}/operation")
    List<AccountOperationDTO> getAccountOperation(
            @PathVariable String id
    ){
       return bankAccountService.accountOperation(id);
    }

    @GetMapping("/{id}/history")
    AccountHistoryDTO getAccountHistory(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
            ) throws BankAccountNotFoundException {
       return bankAccountService.accountHistory(id,page,size);
    }
}
