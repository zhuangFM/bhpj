package com.netease.fimi.bhpj.domain;

public class TransactionRecordInfo extends TransactionRecord {
    private String contentTitle;
    private String contentImagePath;

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentImagePath() {
        return contentImagePath;
    }

    public void setContentImagePath(String contentImagePath) {
        this.contentImagePath = contentImagePath;
    }

    @Override
    public String toString() {
        return "TransactionRecordInfo{" +
                "contentTitle='" + contentTitle + '\'' +
                ", contentImagePath='" + contentImagePath + '\'' +
                '}';
    }
}
