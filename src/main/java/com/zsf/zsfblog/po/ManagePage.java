package com.zsf.zsfblog.po;

import java.util.Arrays;
import java.util.List;

public class ManagePage {
    //当前页数
    private Integer pageNumber;
    //总条数
    private Integer pageCount;
    //总页数
    private Integer totalPage;
    //显示条数
    private Integer pageSize;
    //当前页开始处
    private Integer  pageStart;
    //数据
    private List<?> data;
    //显示的页码样式
    private String[] pageShow;

    public ManagePage(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageStart=(pageNumber-1)*pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.totalPage=pageCount%pageSize==0?pageCount/pageSize:pageCount/pageSize+1;
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String[] getPageShow() {
        return pageShow;
    }


    public void getPageShow(Integer showCount) {
        if(totalPage<=showCount) {
            pageShow=new String[totalPage];
            for (int i=0;i<totalPage;i++) {
                pageShow[i]=String.valueOf(i+1);
            }
            return;
        }
        /**
         * 15 16 17 18 19 20
         * showCount   6
         * count       20
         * (showCount/2)
         * showCount-(showCount/2)-1
         */
        if(totalPage-pageNumber<showCount-(showCount/2)-1) {
            pageShow=new String[showCount+1];
            pageShow[0]="...";
            for(int i=1;i<=showCount;i++) {
                pageShow[i]=String.valueOf(totalPage-showCount+i);
            }
            return;
        }

        if(pageNumber<=(showCount/2)+1) {
            pageShow=new String[showCount+1];
            for (int i=0;i<showCount;i++) {
                pageShow[i]=String.valueOf(i+1);
            }
            pageShow[showCount]="...";
            return;
        }
        /**
         * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
         * pageNumber前面留(showCount/2)个
         */
        pageShow=new String[showCount+2];
        pageShow[0]="...";
        pageShow[showCount+1]="...";
        for(int i=pageNumber-(showCount/2),j=1;j<showCount+1;j++,i++) {
            pageShow[j]=String.valueOf(i);
        }
    }

    @Override
    public String toString() {
        return "ManagePage{" +
                "pageNumber=" + pageNumber +
                ", pageCount=" + pageCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", pageStart=" + pageStart +
                ", data=" + data +
                ", pageShow=" + Arrays.toString(pageShow) +
                '}';
    }
}
