package com.wzg.creat.common.page;

/**
 * @Description: 分页信息
 * @author wangzg_csd
 * @version 1.0
 * @createDate 2019/11/28 13:40
 * @Return
 */

public class Page {
    private int pageSize;
    private int pageNum;
    private int count;
    private int currentSize;


    public void Page(){}
    public void Page(int pageNum, int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
    }
    public void Page(int pageNum, int pageSize, int count){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
    }
    public void Page(int pageNum, int pageSize, int count, int currentSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        this.currentSize = currentSize;
    }
    public static Page setPageBefore(Page page){
        if (page.getPageSize() == 0){
            page.setPageSize(10);
        }
        if (page.getPageNum() == 0){
            page.setPageNum(1);
        }
        return page;
    }


    public void setCount(int count) { this.count = count; }
    public void setPageSize(int pageSize){ this.pageSize = pageSize;}
    public void setPageNum(int pageNum){ this.pageNum = pageNum;}
    public void setCurrentSize(int currentSize){ this.currentSize = currentSize;}
    public int getPageSize(){return this.pageSize;}
    public int getPageNum() {return this.pageNum;}
    public int getCount() {return this.count;}
    public int getCurrentSize(){ return this.currentSize;}
}
