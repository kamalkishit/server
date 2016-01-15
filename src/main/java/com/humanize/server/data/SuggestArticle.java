package com.humanize.server.data;

import org.springframework.beans.factory.annotation.Required;

public class SuggestArticle {

    private String articleUrl;

    public String getArticleUrl() {
        return articleUrl;
    }

    @Required
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}