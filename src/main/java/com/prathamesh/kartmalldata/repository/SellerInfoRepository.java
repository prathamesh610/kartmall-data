package com.prathamesh.kartmalldata.repository;

import com.prathamesh.kartmalldata.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, Long> {
    SellerInfo findBySellerName(String sellerName);
}
