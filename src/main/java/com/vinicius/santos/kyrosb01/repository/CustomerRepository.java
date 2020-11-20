package com.vinicius.santos.kyrosb01.repository;

import com.vinicius.santos.kyrosb01.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByCustomerCpf(String customerCpf);

}
