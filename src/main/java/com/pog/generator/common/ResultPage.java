package com.pog.generator.common;


import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public class ResultPage {

    private Long pageSize;
    private Long totalPage;
    private Long pageNum;
    private List<?> list;

    public ResultPage(Long pageSize, Long totalPage, Long pageNum, List<?> list) {
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.pageNum = pageNum;
        this.list = list;
    }

    public ResultPage(IPage<?> iPage){
        this.list = iPage.getRecords();
        this.pageSize = iPage.getSize();
        this.pageNum = iPage.getCurrent();
        this.totalPage = iPage.getTotal();
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
