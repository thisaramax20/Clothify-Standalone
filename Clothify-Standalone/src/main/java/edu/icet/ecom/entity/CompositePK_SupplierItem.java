package edu.icet.ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompositePK_SupplierItem implements Serializable {
    private Integer supplierId;
    private Integer itemCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositePK_SupplierItem that = (CompositePK_SupplierItem) o;
        return Objects.equals(supplierId, that.supplierId) && Objects.equals(itemCode, that.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, itemCode);
    }
}
