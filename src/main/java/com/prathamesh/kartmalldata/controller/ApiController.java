package com.prathamesh.kartmalldata.controller;

import com.prathamesh.kartmalldata.dto.MetaDTO;
import com.prathamesh.kartmalldata.dto.ProductDTO;
import com.prathamesh.kartmalldata.dto.ResponseDTO;
import com.prathamesh.kartmalldata.exception.KartMallException;
import com.prathamesh.kartmalldata.exception.codes.SuccessCode;
import com.prathamesh.kartmalldata.handler.InternalExceptionHandler;
import com.prathamesh.kartmalldata.model.ProductInfo;
import com.prathamesh.kartmalldata.service.ApiService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/data/api/v1/")
public class ApiController {

    private final ApiService apiService;


    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }


    @GetMapping("/get-product-info/id/{id}")
    public @ResponseBody ResponseDTO getProductInfo(@PathVariable Long id) throws Exception{
        ProductDTO productDTO = null;
        try{
            productDTO = apiService.getProductInfoFromId(id);
        }catch (Exception e){
            if(e instanceof KartMallException)
                throw new InternalExceptionHandler.KartMallWrappedException((KartMallException) e);
        }
        return returnResponseDTO(productDTO);
    }






    public static ResponseDTO returnResponseDTO(Object data){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMeta(new MetaDTO(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getMessage(), UUID.randomUUID()));
        responseDTO.setData(data);
        return responseDTO;
    }

}
