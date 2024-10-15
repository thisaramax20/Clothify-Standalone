package edu.icet.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CompositePK_SupplierItem.class)
public class SupplierItem {
    @Id
    private String supplierId;
    @Id
    private String itemCode;
    private String name;
    private String category;
}
