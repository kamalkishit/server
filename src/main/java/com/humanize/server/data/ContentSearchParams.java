package com.humanize.server.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public class ContentSearchParams {

    private long createdDate;
    private List<String> categories;
    private boolean refresh;

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Required
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean getRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}