package com.prathamesh.kartmalldata.model;

import com.prathamesh.kartmalldata.model.entity.SellerIdProductIdEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "seller_inventory")
public class SellerInventory {

    @EmbeddedId
    private SellerIdProductIdEntity sellerIdProductIdEntity;

    @Column(name = "stock")
    private Long stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SellerInventory that = (SellerInventory) o;
        return sellerIdProductIdEntity != null && Objects.equals(sellerIdProductIdEntity, that.sellerIdProductIdEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerIdProductIdEntity);
    }
}
