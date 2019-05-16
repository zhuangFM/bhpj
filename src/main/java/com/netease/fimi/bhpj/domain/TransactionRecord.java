package com.netease.fimi.bhpj.domain;

public class TransactionRecord {
    private Integer id;
    private Integer userId;
    private Integer contentId;
    private Integer amount;
    private Integer totalMoney;
    private String address;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", contentId=" + contentId +
                ", amount=" + amount +
                ", totalMoney=" + totalMoney +
                ", address='" + address + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}



