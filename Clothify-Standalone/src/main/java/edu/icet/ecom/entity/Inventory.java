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
public class Inventory {
    @EmbeddedId
    private CompositePK_SupplierItem id;

    private String name;
    private String size;
    private Double price;
    private String category;
    private Integer quantity;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "itemCode",updatable = false,insertable = false),
            @JoinColumn(name = "supplierId",insertable = false,updatable = false)
    })
    private SupplierItem supplierItem;
    private String supplierIdTemp;
}
