package ci.djonan.digital_baking_backend.web;

import ci.djonan.digital_baking_backend.dtos.CustomerDTO;
import ci.djonan.digital_baking_backend.entities.Customer;
import ci.djonan.digital_baking_backend.exceptions.CustomerNotFoundException;
import ci.djonan.digital_baking_backend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerResController {
    private BankAccountService bankAccountService;

    @GetMapping("all")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    List<CustomerDTO> getCustomerList(){
        return bankAccountService.getCustomerList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    CustomerDTO getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
   CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
       return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
   CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
       return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }
}
