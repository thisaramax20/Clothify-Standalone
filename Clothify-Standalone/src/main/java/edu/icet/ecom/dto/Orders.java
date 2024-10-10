package edu.icet.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;
    private String customerEmail;
    private String orderId;
    private String paymentType;
    private Double total;
    private Integer adminId;
    private String adminName;
}
