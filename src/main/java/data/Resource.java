package data;

import java.util.List;
/**
 * Класс для создания POJO-объекта
 * @author Сытина Елена
 */
public class Resource {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<DataUser> data;
    private Support support;

    public Resource(Integer page, Integer per_page, Integer total, Integer total_pages, List<DataUser> data, Support support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }

    public Resource() {
        super();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<DataUser> getData() {
        return data;
    }

    public void setData(List<DataUser> data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}


