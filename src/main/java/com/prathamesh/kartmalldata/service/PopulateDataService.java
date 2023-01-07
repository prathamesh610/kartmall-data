package com.prathamesh.kartmalldata.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prathamesh.kartmalldata.model.*;
import com.prathamesh.kartmalldata.model.entity.SellerIdProductIdEntity;
import com.prathamesh.kartmalldata.repository.ProductImagesRepository;
import com.prathamesh.kartmalldata.repository.ProductInfoRepository;
import com.prathamesh.kartmalldata.repository.SellerInfoRepository;
import com.prathamesh.kartmalldata.repository.SellerInventoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PopulateDataService {

    @Value("${product.data.url}")
    private String productDataUrl;
    @Value("${product.data.objects}")
    private String totalProducts;
    private final SellerInfoRepository sellerInfoRepository;
    private final ProductInfoRepository productInfoRepository;
    private final ProductImagesRepository productImagesRepository;
    private final SellerInventoryRepository sellerInventoryRepository;

    public PopulateDataService(SellerInfoRepository sellerInfoRepository, ProductInfoRepository productInfoRepository, ProductImagesRepository productImagesRepository, SellerInventoryRepository sellerInventoryRepository) {
        this.sellerInfoRepository = sellerInfoRepository;
        this.productInfoRepository = productInfoRepository;
        this.productImagesRepository = productImagesRepository;
        this.sellerInventoryRepository = sellerInventoryRepository;
    }

    public void populateProductData(){
//         get data from url https://dummyjson.com/products
//         map objects with objectMapper
//         save data to table
//        save stock
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            FinalProductModel baseProductModel = objectMapper.readValue(new URL(productDataUrl+totalProducts), FinalProductModel.class);

            List<BaseProductModel> productModels = baseProductModel.getProducts();
//            List<ProductInfo> productInfoList = new ArrayList<>();
//            List<ProductImages> productImagesList = new ArrayList<>();
            productModels.forEach(model -> {
                if(Objects.isNull(productInfoRepository.findByProductTitle(model.getTitle()))) {
                    SellerInfo sellerInfo = sellerInfoRepository.findBySellerName(model.getBrand());
                    if (Objects.isNull(sellerInfo)) {
                        sellerInfo = new SellerInfo();
                        sellerInfo.setSellerName(model.getBrand());
                        sellerInfo = sellerInfoRepository.save(sellerInfo);
                    }
                    ProductInfo productInfo = new ProductInfo();
                    SellerInventory sellerInventory = new SellerInventory();
                    List<ProductImages> productImagesList = new ArrayList<>();
                    BeanUtils.copyProperties(model, productInfo);
                    productInfo.setProductBrand(model.getBrand());
                    productInfo.setSellerId(sellerInfo.getId());
                    productInfo.setProductTitle(model.getTitle());
                    Double originalPrice = productInfo.getPrice() / (1 - productInfo.getDiscountPercentage() / 100);
                    productInfo.setOriginalPrice(originalPrice);
                    productInfo = productInfoRepository.save(productInfo);
                    ProductInfo finalProductInfo = productInfo;
                    model.getImages().forEach(
                            image -> {
                                ProductImages productImages = new ProductImages();
                                productImages.setImageUrl(image);
                                productImages.setProduct(finalProductInfo);
                                ProductImages productImagesImported = productImagesRepository.findByImageUrl(image);
                                if(Objects.isNull(productImagesImported))
                                    productImagesImported = productImagesRepository.save(productImages);
                                else productImagesImported = productImagesRepository.findByImageUrl(image);
                                productImagesList.add(productImagesImported);
                            }
                    );
                    productInfo.setProductImages(productImagesList);

                    sellerInventory.setSellerIdProductIdEntity(new SellerIdProductIdEntity(sellerInfo.getId(),productInfo.getProductId()));
                    sellerInventory.setStock(model.getStock());

                    sellerInventoryRepository.save(sellerInventory);
                    productInfoRepository.save(productInfo);
                }});
        } catch (Exception e) {
            // malformedUrlException
            // jsonparsingexception

            throw new RuntimeException(e);
        }
    }
}
