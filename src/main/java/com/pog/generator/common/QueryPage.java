package com.pog.generator.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class QueryPage<T> {

    public IPage<T> getPage(QueryParams queryParams) {

        Page<T> page = new Page<>(queryParams.getPageNum(),queryParams.getPageSize());

        return page;
    }

}
