package com.example.model;

public class MailConfig {
    private String language;
    private int pageSize;

    public MailConfig(String language, int pageSize) {
        this.language = language;
        this.pageSize = pageSize;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
