package edu.icet.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String nic;
    private String name;
    private String address;
    private LocalDate dob;
    private String email;
}
