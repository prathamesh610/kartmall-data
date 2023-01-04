package com.prathamesh.kartmalldata.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO implements Serializable {
    private Object data;
    private Object meta;
}
