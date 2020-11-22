package com.vinicius.santos.kyrosb01.service;

import com.vinicius.santos.kyrosb01.entity.CustomerEntity;
import com.vinicius.santos.kyrosb01.repository.CustomerRepository;
import com.vinicius.santos.kyrosb01.response.ErrorResponse;
import com.vinicius.santos.kyrosb01.response.RequestResponse;
import com.vinicius.santos.kyrosb01.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public RequestResponse newCustomer(CustomerEntity customerEntity) {
        try {
            if (customerEntity != null && customerEntity.getCustomerBirthDate() != null && customerEntity.getCustomerCpf() != null
                    && customerEntity.getCustomerEmail() != null && customerEntity.getCustomerName() != null && customerEntity.getCustomerPhone() != null) {
                customerRepository.save(customerEntity);
                List<Object> customerAdded = new ArrayList<>();
                customerAdded.add(customerEntity);
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setMessage("Cliente cadastrado.");
                successResponse.setSuccess(true);
                successResponse.setRecords(customerAdded);
                return new RequestResponse(successResponse, HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Não foi possivel cadastrar este cliente. Por favor complete as informações corretamente.");
                errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                errorResponse.setSuccess(false);
                return new RequestResponse(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new RequestResponse(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public RequestResponse getAllCustomers() {
        try {
            Iterable<CustomerEntity> customers = customerRepository.findAll();
            List<CustomerEntity> customerEntityList = new ArrayList<>();
            customers.forEach(customerEntityList::add);
            if(customerEntityList.size() != 0) {
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setRecords(customerEntityList);
                successResponse.setSuccess(true);
                return new RequestResponse(successResponse, HttpStatus.OK);
            }else{
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setStatusCode(404);
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Não há clientes para serem buscados.");
                return new RequestResponse(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new RequestResponse(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public RequestResponse getCustomerByCpf(String customerCpf) {
        try {
            CustomerEntity customer = this.customerRepository.findByCustomerCpf(customerCpf);
            if (customer != null && customer.getCustomerId() != 0) {
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setRecords(customer);
                successResponse.setSuccess(true);
                return new RequestResponse(successResponse, HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Não foi possivel buscar este cliente. Por favor insira as informações corretamente.");
                errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                errorResponse.setSuccess(false);
                return new RequestResponse(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new RequestResponse(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public RequestResponse deleteCustomer(String customerCpf) {
        try {
            CustomerEntity customer = this.customerRepository.findByCustomerCpf(customerCpf);
            if (customer != null && customer.getCustomerId() != 0) {
                this.customerRepository.deleteById(customer.getCustomerId());
                return new RequestResponse(null, HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Não foi possivel deletar este cliente. Por favor insira as informações corretamente.");
                errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                errorResponse.setSuccess(false);
                return new RequestResponse(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new RequestResponse(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public RequestResponse updateCustomer(String customerCpf, CustomerEntity customerEntity) {
        try {
            if (customerEntity != null && customerEntity.getCustomerBirthDate() != null && customerEntity.getCustomerCpf() != null
                    && customerEntity.getCustomerEmail() != null && customerEntity.getCustomerName() != null && customerEntity.getCustomerPhone() != null) {
                CustomerEntity customer = this.customerRepository.findByCustomerCpf(customerCpf);
                if (customer != null && customer.getCustomerId() != 0) {
                    customer.setCustomerBirthDate(customerEntity.getCustomerBirthDate());
                    customer.setCustomerCpf(customerEntity.getCustomerCpf());
                    customer.setCustomerEmail(customerEntity.getCustomerEmail());
                    customer.setCustomerName(customerEntity.getCustomerName());
                    customer.setCustomerPhone(customerEntity.getCustomerPhone());
                    this.customerRepository.save(customer);
                    SuccessResponse successResponse = new SuccessResponse();
                    successResponse.setMessage("Cliente atualizado.");
                    successResponse.setRecords(customer);
                    successResponse.setSuccess(true);
                    return new RequestResponse(successResponse, HttpStatus.OK);
                } else {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Não foi possivel atualizar este cliente. Por favor insira as informações corretamente.");
                    errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    errorResponse.setSuccess(false);
                    return new RequestResponse(errorResponse, HttpStatus.NOT_FOUND);
                }
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Não foi possivel atualizar este cliente. Por favor complete as informações corretamente.");
                errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                errorResponse.setSuccess(false);
                return new RequestResponse(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new RequestResponse(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
