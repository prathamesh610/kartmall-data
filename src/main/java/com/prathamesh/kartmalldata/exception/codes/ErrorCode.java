package com.prathamesh.kartmalldata.exception.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PRODUCT_NOT_PRESENT("KM_ER_00001","Product not available.")

    ;


    private String errorCode;
    private String errorMessage;
}
