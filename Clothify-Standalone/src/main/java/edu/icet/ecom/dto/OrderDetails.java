package edu.icet.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private Integer id;
    private String orderId;
    private String itemCode;
    private String description;
    private Double price;
    private Integer quantity;
}
