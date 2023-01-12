package com.prathamesh.kartmalldata.service;

import com.prathamesh.kartmalldata.dto.ProductDTO;
import com.prathamesh.kartmalldata.exception.KartMallException;
import com.prathamesh.kartmalldata.exception.codes.ErrorCode;
import com.prathamesh.kartmalldata.model.ProductInfo;
import com.prathamesh.kartmalldata.repository.ProductInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {
    private final ProductInfoRepository productInfoRepository;

    public ApiService(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    public ProductDTO getProductInfoFromId(Long id) throws KartMallException {
        ProductDTO productDTO = new ProductDTO();
        Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(id);
        if(productInfoOptional.isPresent()){
            BeanUtils.copyProperties(productInfoOptional.get(),productDTO);
            List<String> images = new ArrayList<>();
            productInfoOptional.get().getProductImages().forEach(image -> {
                images.add(image.getImageUrl());
            });
            productDTO.setProductImages(images);
        }else {
            throw new KartMallException(ErrorCode.PRODUCT_NOT_PRESENT.getErrorCode(), ErrorCode.PRODUCT_NOT_PRESENT.getErrorMessage());
        }


        return productDTO;
    }
}
