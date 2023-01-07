package com.prathamesh.kartmalldata.exception.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    SUCCESS("SC_KM_001", "success")
    ;
    private final String code;
    private final String message;


}
