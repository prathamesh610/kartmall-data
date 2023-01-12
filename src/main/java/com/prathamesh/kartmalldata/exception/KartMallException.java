package com.prathamesh.kartmalldata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class KartMallException extends Exception{
    private String errorCode;
    private String errorMessage;
}
