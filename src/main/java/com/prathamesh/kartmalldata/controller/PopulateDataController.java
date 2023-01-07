package com.prathamesh.kartmalldata.controller;

import com.prathamesh.kartmalldata.dto.MetaDTO;
import com.prathamesh.kartmalldata.dto.ResponseDTO;
import com.prathamesh.kartmalldata.exception.codes.SuccessCode;
import com.prathamesh.kartmalldata.service.PopulateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Controller
@RequestMapping(value = "/populate")
public class PopulateDataController {

    private final PopulateDataService populateDataService;

    public PopulateDataController(PopulateDataService populateDataService) {
        this.populateDataService = populateDataService;
    }

    @GetMapping(value = "/allData")
    public @ResponseBody ResponseDTO populateData(){
        ResponseDTO responseDTO = new ResponseDTO();

        populateDataService.populateProductData();

        responseDTO.setData(true);
        responseDTO.setMeta(new MetaDTO(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getMessage(), UUID.randomUUID()));
        return responseDTO;
    }
}
