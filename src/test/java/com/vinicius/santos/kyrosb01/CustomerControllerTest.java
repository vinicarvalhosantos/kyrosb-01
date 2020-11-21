package com.vinicius.santos.kyrosb01;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.santos.kyrosb01.controller.CustomerController;
import com.vinicius.santos.kyrosb01.entity.CustomerEntity;
import com.vinicius.santos.kyrosb01.response.ErrorResponse;
import com.vinicius.santos.kyrosb01.response.RequestResponse;
import com.vinicius.santos.kyrosb01.response.SuccessResponse;
import com.vinicius.santos.kyrosb01.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_create_customer() throws Exception {

        CustomerEntity customerEntityExpected = new CustomerEntity();
        customerEntityExpected.setCustomerId(1);
        customerEntityExpected.setCustomerName("Test Customer Name");
        customerEntityExpected.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityExpected.setCustomerCpf("11111111111");
        customerEntityExpected.setCustomerPhone("11111111111");
        customerEntityExpected.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setSuccess(true);
        successResponse.setMessage("Cliente cadastrado.");
        successResponse.setRecords(customerEntityExpected);

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setBody(successResponse);
        requestResponse.setHttpStatus(HttpStatus.OK);

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerService.newCustomer(any(CustomerEntity.class))).thenReturn(requestResponse);

        mockMvc.perform(post("/customer/addCustomer")
                .content(objectMapper.writeValueAsString(customerEntityRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cliente cadastrado."))
                .andExpect(jsonPath("$.records.customerId").value(1))
                .andExpect(jsonPath("$.records.customerName").value("Test Customer Name"))
                .andExpect(jsonPath("$.records.customerCpf").value("11111111111"))
                .andExpect(jsonPath("$.records.customerEmail").value("test.customer.email@gmail.com"))
                .andExpect(jsonPath("$.records.customerPhone").value("11111111111"))
                .andExpect(jsonPath("$.records.customerBirthDate").value("2000-01-01T02:00:00.000+0000"));
    }

    @Test
    public void it_should_not_create_customer_by_bad_request() throws Exception{
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setMessage("Não foi possivel cadastrar este cliente. Por favor complete as informações corretamente.");
        errorResponse.setStatusCode(400);

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setBody(errorResponse);
        requestResponse.setHttpStatus(HttpStatus.BAD_REQUEST);

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerService.newCustomer(any(CustomerEntity.class))).thenReturn(requestResponse);

        mockMvc.perform(post("/customer/addCustomer")
                .content(objectMapper.writeValueAsString(customerEntityRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Não foi possivel cadastrar este cliente. Por favor complete as informações corretamente."))
                .andExpect(jsonPath("$.statusCode").value(400));

    }

    @Test
    public void it_should_not_create_customer_by_exception() throws Exception{
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setBody(null);
        requestResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        CustomerEntity customerEntityRequest = new CustomerEntity();
        customerEntityRequest.setCustomerName("Test Customer Name");
        customerEntityRequest.setCustomerEmail("test.customer.email@gmail.com");
        customerEntityRequest.setCustomerCpf("11111111111");
        customerEntityRequest.setCustomerPhone("11111111111");
        customerEntityRequest.setCustomerBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));

        when(customerService.newCustomer(any(CustomerEntity.class))).thenReturn(requestResponse).thenThrow();

        mockMvc.perform(post("/customer/addCustomer")
                .content(objectMapper.writeValueAsString(customerEntityRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

}
