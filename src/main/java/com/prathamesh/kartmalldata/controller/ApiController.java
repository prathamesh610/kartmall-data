package com.prathamesh.kartmalldata.controller;

import com.prathamesh.kartmalldata.dto.MetaDTO;
import com.prathamesh.kartmalldata.dto.ResponseDTO;
import com.prathamesh.kartmalldata.exception.codes.SuccessCode;
import com.prathamesh.kartmalldata.model.ProductImages;
import com.prathamesh.kartmalldata.model.ProductInfo;
import com.prathamesh.kartmalldata.repository.ProductImagesRepository;
import com.prathamesh.kartmalldata.repository.ProductInfoRepository;
import com.prathamesh.kartmalldata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

@Controller
public class ApiController {

    @Autowired
    ApiService apiService;

    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductImagesRepository productImagesRepository;


    @GetMapping("/test")
    public ResponseDTO testApp(){
        ProductImages productImages = new ProductImages();
        productImages.setImageUrl("http:213.com");
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductBrand("Apple");
        productInfo.setCategory("Phone");
        productInfo.setDescription("Apple iphone");
        productInfo.setPrice(12.29);
        productInfo.setProductTitle("Smartphone Apple");
        productImages.setProduct(productInfo);
        productInfo.setProductImages(Collections.singletonList(productImages));

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(true);
        responseDTO.setMeta(new MetaDTO(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getMessage(), UUID.randomUUID()));
        return responseDTO;
    }


}
