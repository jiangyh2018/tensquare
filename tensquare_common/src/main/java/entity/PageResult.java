package entity;

import java.util.List;

/**
 * @description: 分页的结果类
 * @author:
 * @create: 2019-01-28 22:28
 **/
public class PageResult<T> {
    private int total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}