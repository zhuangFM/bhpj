package com.netease.fimi.bhpj.domain;

public class Content {
    private Integer id;
    private String title;
    private String imagePath;
    private String summary;
    private String detail;
    private Integer price;
    private String departure;
    private Integer contain;

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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Integer getContain() {
        return contain;
    }

    public void setContain(Integer contain) {
        this.contain = contain;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", summary='" + summary + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", departure='" + departure + '\'' +
                ", contain=" + contain +
                '}';
    }
}
