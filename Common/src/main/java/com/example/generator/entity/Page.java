package com.example.generator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author：RS
 * Date：2023-03-02 14:32
 * Description：<描述>
 */
@Data
@NoArgsConstructor
public class Page {

    private Integer pageNum;

    private Integer pageSize;

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum <= 1 ? 0 : (pageNum-1) *pageSize;
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum <= 1 ? 0 : (pageNum-1) *pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
