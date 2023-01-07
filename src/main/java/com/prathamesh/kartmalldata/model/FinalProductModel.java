package com.prathamesh.kartmalldata.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FinalProductModel implements Serializable {
    private List<BaseProductModel> products;
    private Integer total;
    private Integer skip;
    private Integer limit;
}
