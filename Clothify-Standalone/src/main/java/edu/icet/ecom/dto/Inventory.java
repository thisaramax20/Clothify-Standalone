package edu.icet.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Integer id;
    private String itemCode;
    private String name;
    private String size;
    private Double price;
    private String category;
    private Integer quantity;
    private String imagePath;
}
