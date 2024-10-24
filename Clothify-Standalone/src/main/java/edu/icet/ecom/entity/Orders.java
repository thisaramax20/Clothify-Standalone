package edu.icet.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerEmail;
    @Column(unique = true)
    private String orderId;
    private String paymentType;
    private Double netTotal;
    private Integer adminId;
    private String adminName;
    @Transient
    private List<OrderDetails> orderDetails;
}
