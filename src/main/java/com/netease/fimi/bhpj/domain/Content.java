package com.netease.fimi.bhpj.domain;

public class Content {
    private Integer id;
    private String title;
    private Integer imageType;
    private String imagePath;
    private String summary;
    private String detail;
    private Integer price;
    private Integer selled;
    private String createTime;
    private String buyerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSelled() {
        return selled;
    }

    public void setSelled(Integer selled) {
        this.selled = selled;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageType=" + imageType +
                ", imagePath='" + imagePath + '\'' +
                ", summary='" + summary + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", selled=" + selled +
                ", createTime='" + createTime + '\'' +
                ", buyerId='" + buyerId + '\'' +
                '}';
    }
}
