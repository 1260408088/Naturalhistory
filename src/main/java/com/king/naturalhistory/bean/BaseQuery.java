package com.king.naturalhistory.bean;

public class BaseQuery {
    private int start;
    private int PageSize;

    public int getStart() {
        return start;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}
