package com.zsq.common;

import java.io.Serializable;
import java.util.List;

public class LayUIData implements Serializable {
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    private long total;
    private List rows;
    private Integer paages;
    private  Integer currentPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPaages() {
        return paages;
    }

    public void setPaages(Integer paages) {
        this.paages = paages;
    }
}
