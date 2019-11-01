package com.zsf.zsfblog.po;

import java.util.List;

public class PageBean {

    /**
     * 当前是第几页
     */
    private Integer currentPage;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总记录数
     */
    private Integer totalRecords;

    /**
     * 每页记录数
     */
    private Integer pageLines;

    /**
     * 存放实体对象的容器
     */
    private List<?> entitys;

    /**
     * 页码数，控制不让页面将所有页码显示，默认最多5个页码，能够修改
     */
    private int[] pageBar;

    public PageBean(Integer currentPage) {
        this.currentPage = (currentPage <= 0 || currentPage == null) ? 1 : currentPage;
        //默认当前每页显示3条记录
        this.pageLines = 3;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    /**
     * 设置了总记录数后，会自动设置总页数和页码条数组
     * @param totalRecords
     */
    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
        this.totalPage = (totalRecords % pageLines == 0) ? (totalRecords / pageLines) : (totalRecords / pageLines + 1);
        int startPage;
        int endPage;
        if (this.totalPage <= 6) {
            this.pageBar = new int[this.totalPage];
            startPage = 1;
            endPage = this.totalPage;
        } else {
            this.pageBar = new int[6];
            startPage = this.currentPage - 2;
            endPage = this.currentPage + 3;
            if (startPage < 1) {
                startPage = 1;
                endPage = 6;
            }
            if (endPage > this.totalPage) {
                endPage = this.totalPage;
                startPage = this.totalPage - 5;
            }
        }
        int index = 0;
        for (int i = startPage; i < endPage + 1; i++) {
            this.pageBar[index++] = i;
        }
    }

    public Integer getPageLines() {
        return pageLines;
    }

    public void setPageLines(Integer pageLines) {
        this.pageLines = pageLines;
    }

    public List<?> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<?> entitys) {
        this.entitys = entitys;
    }

    /**
     * //获取数据库查询开始的索引值
     * @return
     */
    public Integer getStartIndex() {
        return (this.currentPage - 1) * this.pageLines;
    }

    public int[] getPageBar() {
        return pageBar;
    }

}
