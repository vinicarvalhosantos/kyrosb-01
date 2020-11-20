package com.vinicius.santos.kyrosb01.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private int customerId;

    @NotNull
    @Column
    private String customerName;

    @NotNull
    @Column(unique = true)
    private String customerCpf;

    @NotNull
    @Column
    private Date customerBirthDate;

    @NotNull
    @Column
    private String customerEmail;

    @NotNull
    @Column
    private String customerPhone;

}
