package com.prathamesh.kartmalldata.repository;

import com.prathamesh.kartmalldata.model.SellerInventory;
import com.prathamesh.kartmalldata.model.entity.SellerIdProductIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInventoryRepository extends JpaRepository<SellerInventory, SellerIdProductIdEntity> {
}
