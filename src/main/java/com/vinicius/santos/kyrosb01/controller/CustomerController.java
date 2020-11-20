package com.vinicius.santos.kyrosb01.controller;

import com.vinicius.santos.kyrosb01.entity.CustomerEntity;
import com.vinicius.santos.kyrosb01.repository.CustomerRepository;
import com.vinicius.santos.kyrosb01.response.ErrorResponse;
import com.vinicius.santos.kyrosb01.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addNewCostumer(@RequestBody CustomerEntity customerEntity) {
        if (customerEntity != null && customerEntity.getCustomerBirthDate() != null && customerEntity.getCustomerCpf() != null
                && customerEntity.getCustomerEmail() != null && customerEntity.getCustomerName() != null && customerEntity.getCustomerPhone() != null) {
            customerRepository.save(customerEntity);
            List<Object> customerAdded = new ArrayList<>();
            customerAdded.add(customerEntity);
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setMessage("Cliente cadastrado.");
            successResponse.setRecords(customerAdded);
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Não foi possivel cadastrar este cliente. Por favor complete as informações corretamente.");
            errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }

    @GetMapping(path = "/getAll")
    public @ResponseBody
    ResponseEntity getAllCustomers() {
        Iterable<CustomerEntity> customers = customerRepository.findAll();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setRecords(customers);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @GetMapping(path = "/getByCpf")
    public @ResponseBody
    ResponseEntity getCustomerByCpf(@RequestParam String customerCpf) {
        CustomerEntity customer = customerRepository.findByCustomerCpf(customerCpf);
        if (customer != null && customer.getCustomerId() != 0) {
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setRecords(customer);
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Não foi possivel buscar este cliente. Por favor insira as informações corretamente.");
            errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    ResponseEntity deleteCustomer(@RequestParam String customerCpf) {
        CustomerEntity customer = customerRepository.findByCustomerCpf(customerCpf);
        if (customer != null && customer.getCustomerId() != 0) {
            customerRepository.deleteById(customer.getCustomerId());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Não foi possivel deletar este cliente. Por favor insira as informações corretamente.");
            errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping(path = "update")
    public @ResponseBody
    ResponseEntity updateCUstomer(@RequestParam String customerCpf, @RequestBody CustomerEntity customerEntity) {
        if (customerEntity != null && customerEntity.getCustomerBirthDate() != null && customerEntity.getCustomerCpf() != null
                && customerEntity.getCustomerEmail() != null && customerEntity.getCustomerName() != null && customerEntity.getCustomerPhone() != null) {
            CustomerEntity customer = customerRepository.findByCustomerCpf(customerCpf);
            if (customer != null && customer.getCustomerId() != 0) {
                customer.setCustomerBirthDate(customerEntity.getCustomerBirthDate());
                customer.setCustomerCpf(customerEntity.getCustomerCpf());
                customer.setCustomerEmail(customerEntity.getCustomerEmail());
                customer.setCustomerName(customerEntity.getCustomerName());
                customer.setCustomerPhone(customerEntity.getCustomerPhone());
                customerRepository.save(customer);
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setMessage("Cliente atualizado.");
                successResponse.setRecords(customer);
                return ResponseEntity.status(HttpStatus.OK).body(successResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Não foi possivel atualizar este cliente. Por favor insira as informações corretamente.");
                errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Não foi possivel atualizar este cliente. Por favor complete as informações corretamente.");
            errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        }
    }

}
