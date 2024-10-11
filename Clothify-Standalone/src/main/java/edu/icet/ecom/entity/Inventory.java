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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemCode;
    private String name;
    private String size;
    private Double price;
    private String category;
    private Integer quantity;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;
}
