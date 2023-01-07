package com.prathamesh.kartmalldata.repository;

import com.prathamesh.kartmalldata.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
    ProductInfo findByProductTitle(String productTitle);
}
