package cn.code.common.page;

import java.util.Collections;
import java.util.List;

//分页
public class PageQueryBean<T> {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private int totalRows;
    private Integer startRow;
    private Integer totalPage;
    private List<T> items;
    private Integer subRows;

    public final Integer getStartRow() {
        if (this.startRow == null) {
            this.startRow = (this.pageNum != null && this.pageNum != 0 ? (this.pageNum - 1) * this.getPageSize() : 0) - (this.subRows == null ? 0 : this.subRows);
        }

        return this.startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public final Integer getPageSize() {
        return this.pageSize != null && this.pageSize != 0 ? this.pageSize : 10;
    }

    public final void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public final int getTotalRows() {
        return this.totalRows;
    }

    public final void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        int totalPage = totalRows % this.getPageSize() == 0 ? totalRows / this.getPageSize() : totalRows / this.getPageSize() + 1;
        this.setTotalPage(totalPage);
    }

    public final List<T> getItems() {
        return this.items == null ? Collections.emptyList() : this.items;
    }

    public final void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public final Integer getTotalPage() {
        return this.totalPage != null && this.totalPage != 0 ? this.totalPage : 1;
    }

    public final void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getSubRows() {
        return this.subRows;
    }

    public void setSubRows(Integer subRows) {
        this.subRows = subRows;
    }

    public String toString() {
        return "PageQueryBean [pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", totalRows=" + this.totalRows + ", startRow=" + this.startRow + ", totalPage=" + this.totalPage + ", items=" + this.items + ", subRows=" + this.subRows + "]";
    }

    public static <T> PageQueryBean.PageQueryBeanBuilder<T> builder() {
        return new PageQueryBean.PageQueryBeanBuilder();
    }

    public PageQueryBean(final Integer pageNum, final Integer pageSize, final int totalRows, final Integer startRow, final Integer totalPage, final List<T> items, final Integer subRows) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        this.startRow = startRow;
        this.totalPage = totalPage;
        this.items = items;
        this.subRows = subRows;
    }

    public PageQueryBean() {
    }

    public static class PageQueryBeanBuilder<T> {
        private Integer pageNum;
        private Integer pageSize;
        private int totalRows;
        private Integer startRow;
        private Integer totalPage;
        private List<T> items;
        private Integer subRows;

        PageQueryBeanBuilder() {
        }

        public PageQueryBean.PageQueryBeanBuilder<T> pageNum(final Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public PageQueryBean.PageQueryBeanBuilder<T> pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PageQueryBean.PageQueryBeanBuilder<T> totalRows(final int totalRows) {
            this.totalRows = totalRows;
            return this;
        }

        public PageQueryBean.PageQueryBeanBuilder<T> startRow(final Integer startRow) {
            this.startRow = startRow;
            return this;
        }

        public PageQueryBean.PageQueryBeanBuilder<T> totalPage(final Integer totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public PageQueryBean.PageQueryBeanBuilder<T> items(final List<T> items) {
            this.items = items;
            return this;
        }

        public PageQueryBean.PageQueryBeanBuilder<T> subRows(final Integer subRows) {
            this.subRows = subRows;
            return this;
        }

        public PageQueryBean<T> build() {
            return new PageQueryBean(this.pageNum, this.pageSize, this.totalRows, this.startRow, this.totalPage, this.items, this.subRows);
        }

        public String toString() {
            return "PageQueryBean.PageQueryBeanBuilder(pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", totalRows=" + this.totalRows + ", startRow=" + this.startRow + ", totalPage=" + this.totalPage + ", items=" + this.items + ", subRows=" + this.subRows + ")";
        }
    }
}
