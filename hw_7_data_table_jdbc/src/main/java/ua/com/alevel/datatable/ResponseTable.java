package ua.com.alevel.datatable;

import ua.com.alevel.model.BaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseTable<E extends BaseUser>  {
    private List<E> items;
    private long itemSize;
    private Map<Object, Object> otherParamMap;
    private int currPage;
    private int currSize;
    private String sort;
    private String order;


    public ResponseTable() {
        items = new ArrayList<>();
        otherParamMap = new HashMap<>();
        itemSize = 0;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public long getItemSize() {
        return itemSize;
    }

    public void setItemSize(long itemSize) {
        this.itemSize = itemSize;
    }

    public Map<Object, Object> getOtherParamMap() {
        return otherParamMap;
    }

    public void setOtherParamMap(Map<Object, Object> otherParamMap) {
        this.otherParamMap = otherParamMap;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getCurrSize() {
        return currSize;
    }

    public void setCurrSize(int currSize) {
        this.currSize = currSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
