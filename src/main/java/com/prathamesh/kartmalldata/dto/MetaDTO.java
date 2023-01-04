package com.prathamesh.kartmalldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MetaDTO {
    private String responseCode;
    private String responseMessage;
    private UUID responseId;

}
