package com.yao.common.entity;

import java.util.List;

/**
 * @Entity: TestBo
 * @Author:
 * @Date: 2023/7/13
 * @Time: 11:55
 * @Description：<描述>
 **/
public class TestBo {
    private String id;
    private List<Page> pages;

    public TestBo() {
    }

    public TestBo(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
