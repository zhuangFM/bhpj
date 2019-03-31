package com.netease.fimi.bhpj.domain;

public class ShoppingCartInfo extends ShoppingCart {
    private String contentTitle;
    private Integer contentPrice;

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public Integer getContentPrice() {
        return contentPrice;
    }

    public void setContentPrice(Integer contentPrice) {
        this.contentPrice = contentPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartInfo{" +
                "contentTitle='" + contentTitle + '\'' +
                ", contentPrice=" + contentPrice +
                '}';
    }
}
