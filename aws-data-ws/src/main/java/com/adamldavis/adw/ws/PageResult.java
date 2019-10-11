package com.adamldavis.adw.ws;

import java.util.List;

public class PageResult<T> {

    final List<T> items;
    final int totalCount;
    final String nextPage;

    public PageResult(List<T> items, int totalCount, String nextPage) {
        this.items = items;
        this.totalCount = totalCount;
        this.nextPage = nextPage;
    }

    public List<T> getItems() {
        return items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getNextPage() {
        return nextPage;
    }
}
