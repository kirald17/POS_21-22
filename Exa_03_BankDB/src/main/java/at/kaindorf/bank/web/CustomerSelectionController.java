package at.kaindorf.bank.web;


import at.kaindorf.bank.database.CustomerRepository;
import at.kaindorf.bank.database.GiroAccountRepository;
import at.kaindorf.bank.database.SavingsAccountRepository;
import at.kaindorf.bank.pojos.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/bank")
public class CustomerSelectionController {

    @Autowired
    private CustomerRepository customerRepository;

    @ModelAttribute(name = "lastname")
    public String lastname(){
        return new String();
    }

    @GetMapping
    public String showInitialData(){
        return "customerSelectionView";
    }

    @GetMapping("/getCustomer")
    public String getCustomer(@RequestParam("lastname") String lastname){
        System.out.println(lastname);
        //List<Customer> customers =
        return "customerSelectionView";
    }
}
