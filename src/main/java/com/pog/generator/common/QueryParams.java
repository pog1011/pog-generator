package com.pog.generator.common;

import lombok.Data;

@Data
public class QueryParams {

    private Long pageNum;
    private Long pageSize;
    private String keyWord;

}
