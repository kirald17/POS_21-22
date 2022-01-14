package at.kaindorf.customerdb.controller;

import at.kaindorf.customerdb.data.CustomerRepository;
import at.kaindorf.customerdb.pojos.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static org.springframework.data.rest.core.config.JsonSchemaFormat.URI;

@RestController // Wie Controller nur für REST -> kein Thymeleaf Frontend
@Slf4j
@RequestMapping(value = "/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
//        Optional<Customer> custOpt = customerRepository.findById(id);
//        if(custOpt.isPresent()){
//            return ResponseEntity.ok(custOpt.get());
//        }
//        return ResponseEntity.notFound().build();
        return ResponseEntity.of(customerRepository.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastname"));
        return ResponseEntity.of(Optional.of(customerRepository.findAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try{
            customer = customerRepository.save(customer);
            java.net.URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(customer.getCostumerId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }

}
