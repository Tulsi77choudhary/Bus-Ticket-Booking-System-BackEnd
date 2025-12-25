package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.CustomerDTO;
import com.example.OnlineTicket.Service.CustomerService;
import com.example.OnlineTicket.Service.UserService;
import com.example.OnlineTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;


    @GetMapping("/with-user/{id}")
    public ResponseEntity<?> getCustomerWithUser(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer == null) return ResponseEntity.notFound().build();
        Map<String, Object> result = new HashMap<>();
        result.put("customer", customer);


        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updated = customerService.updateCustomer(id, customerDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

