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

public class SupplierItem {
    @EmbeddedId
    private CompositePK_SupplierItem compositePKSupplierItem ;
    private String name;
    private String category;
}
