package com.vinicius.santos.kyrosb01.controller;

import com.vinicius.santos.kyrosb01.entity.CustomerEntity;
import com.vinicius.santos.kyrosb01.response.RequestResponse;
import com.vinicius.santos.kyrosb01.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private RequestResponse requestResponse;

    @PostMapping(path = "/addCustomer")
    public @ResponseBody
    ResponseEntity addNewCostumer(@RequestBody CustomerEntity customerEntity) {
        this.requestResponse = this.customerService.newCustomer(customerEntity);
        return ResponseEntity.status(this.requestResponse.getHttpStatus()).body(this.requestResponse.getBody());
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody
    ResponseEntity getAllCustomers() {
        this.requestResponse = this.customerService.getAllCustomers();
        return ResponseEntity.status(this.requestResponse.getHttpStatus()).body(this.requestResponse.getBody());
    }

    @GetMapping(path = "/getByCpf")
    public @ResponseBody
    ResponseEntity getCustomerByCpf(@RequestParam String customerCpf) {
        this.requestResponse = this.customerService.getCustomerByCpf(customerCpf);
        return ResponseEntity.status(this.requestResponse.getHttpStatus()).body(this.requestResponse.getBody());
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    ResponseEntity deleteCustomer(@RequestParam String customerCpf) {
        this.requestResponse = this.customerService.deleteCustomer(customerCpf);
        return ResponseEntity.status(this.requestResponse.getHttpStatus()).body(this.requestResponse.getBody());
    }

    @PutMapping(path = "/update")
    public @ResponseBody
    ResponseEntity updateCustomer(@RequestParam String customerCpf, @RequestBody CustomerEntity customerEntity) {
        this.requestResponse = this.customerService.updateCustomer(customerCpf, customerEntity);
        return ResponseEntity.status(this.requestResponse.getHttpStatus()).body(this.requestResponse.getBody());
    }

}
