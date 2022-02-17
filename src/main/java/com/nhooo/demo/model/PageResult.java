package com.nhooo.demo.model;

import java.util.List;

public class PageResult<T> {

    //总记录数
    private Long count;

    //总页数
    private Integer page;

    //结果
    private List<T> result;

    public PageResult() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}