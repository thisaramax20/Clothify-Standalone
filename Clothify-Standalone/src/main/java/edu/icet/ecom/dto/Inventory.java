package edu.icet.ecom.dto;

import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.entity.SupplierItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private CompositePK_SupplierItem id;
    private String name;
    private String size;
    private Double price;
    private String category;
    private Integer quantity;
    private String imagePath;
    private SupplierItem supplierItem;
    private String supplierIdTemp;
}
