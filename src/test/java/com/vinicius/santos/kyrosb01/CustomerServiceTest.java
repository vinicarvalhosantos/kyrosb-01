package com.vinicius.santos.kyrosb01;

import com.vinicius.santos.kyrosb01.entity.CustomerEntity;
import com.vinicius.santos.kyrosb01.repository.CustomerRepository;
import com.vinicius.santos.kyrosb01.response.RequestResponse;
import com.vinicius.santos.kyrosb01.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerService.class)
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void it_should_create_a_customer() throws Exception {
        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.save(customerEntityRequest)).thenReturn(customerEntityExpected);

        RequestResponse requestResponse = customerService.newCustomer(customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.OK, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_create_a_customer() throws Exception {
        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.save(customerEntityRequest)).thenReturn(null);

        RequestResponse requestResponse = customerService.newCustomer(customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_get_exception_when_create_a_customer() throws Exception {

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.save(customerEntityRequest)).thenThrow();

        RequestResponse requestResponse = customerService.newCustomer(customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_get_all_customers() throws Exception {
        CustomerEntity customerEntityExpected1 = new CustomerEntity();
        customerEntityExpected1.setCustomerId(1);
        customerEntityExpected1.setCustomerName("Test First Customer Name");
        customerEntityExpected1.setCustomerEmail("test.first.customer.email@gmail.com");
        customerEntityExpected1.setCustomerCpf("11111111111");
        customerEntityExpected1.setCustomerPhone("11111111111");
        customerEntityExpected1.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        CustomerEntity customerEntityExpected2 = new CustomerEntity();
        customerEntityExpected2.setCustomerId(2);
        customerEntityExpected2.setCustomerName("Test Second Customer Name");
        customerEntityExpected2.setCustomerEmail("test.second.customer.email@gmail.com");
        customerEntityExpected2.setCustomerCpf("22222222222");
        customerEntityExpected2.setCustomerPhone("22222222222");
        customerEntityExpected2.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2000"));

        List<CustomerEntity> customers = new ArrayList<>();
        customers.add(customerEntityExpected1);
        customers.add(customerEntityExpected2);

        when(customerRepository.findAll()).thenReturn(customers);

        RequestResponse requestResponse = customerService.getAllCustomers();

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.OK, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_get_all_customers() {

        when(customerRepository.findAll()).thenThrow();

        RequestResponse requestResponse = customerService.getAllCustomers();

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_get_a_customer_by_cpf() throws Exception {
        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.findByCustomerCpf("11111111111")).thenReturn(customerEntityExpected);

        RequestResponse requestResponse = customerService.getCustomerByCpf("11111111111");

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.OK, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_get_a_customer_by_cpf() throws Exception {
        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.findByCustomerCpf("11111121111")).thenReturn(null);

        RequestResponse requestResponse = customerService.getCustomerByCpf("11111121111");

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.NOT_FOUND, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_get_exception_when_get_a_customer_by_cpf() {

        when(customerRepository.findByCustomerCpf("11111121111")).thenThrow();

        RequestResponse requestResponse = customerService.getCustomerByCpf("11111121111");

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_update_a_customer() throws Exception {
        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.save(customerEntityRequest)).thenReturn(customerEntityExpected);

        when(customerRepository.findByCustomerCpf("11111111111")).thenReturn(customerEntityExpected);

        RequestResponse requestResponse = customerService.updateCustomer("11111111111", customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.OK, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_update_a_customer() throws Exception {
        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.save(customerEntityRequest)).thenReturn(customerEntityExpected);

        when(customerRepository.findByCustomerCpf("11111111111")).thenReturn(null);

        RequestResponse requestResponse = customerService.updateCustomer("11111111111", customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.NOT_FOUND, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_update_a_customer_bad_request() throws Exception {

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.save(customerEntityRequest)).thenReturn(null);

        RequestResponse requestResponse = customerService.updateCustomer("11111111111", customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_update_a_customer_exception() throws Exception {

        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.findByCustomerCpf("11111111111")).thenThrow();

        RequestResponse requestResponse = customerService.updateCustomer("11111111111", customerEntityRequest);

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_delete_a_customer() throws Exception {

        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.findByCustomerCpf("11111111111")).thenReturn(customerEntityExpected);

        RequestResponse requestResponse = customerService.deleteCustomer("11111111111");

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.OK, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_delete_a_customer() throws Exception {

        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.findByCustomerCpf("11111111111")).thenReturn(null);

        RequestResponse requestResponse = customerService.deleteCustomer("11111111111");

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.NOT_FOUND, requestResponse.getHttpStatus());
    }

    @Test
    public void it_should_not_delete_a_customer_exception() throws Exception {

        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerRepository.findByCustomerCpf("11111111111")).thenThrow();

        RequestResponse requestResponse = customerService.deleteCustomer("11111111111");

        Assert.assertNotNull(requestResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, requestResponse.getHttpStatus());
    }

}
