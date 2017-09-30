package com.example.xu.myapplication.moduleHome.fragment.bean;

/**
 * Created by 逝 on 2017/09/30.
 */

public class BargainBean {

    /**
     * id : 1
     * bargainName : 拇指青桔
     * bargainPrice : 9.90
     * bargainImage : https://imgjd2.fruitday
     * .com/images/product_pic/11851/1/1-270x270-11851-8DA2FRK7.jpg
     * bargainCount : 2
     * bargainPlace : 双手合十
     * endTime : null
     * createTime : null
     * updateTime : null
     */

    private int id;
    private String bargainName;
    private String bargainPrice;
    private String bargainImage;
    private int bargainCount;
    private String bargainPlace;
    private Object endTime;
    private Object createTime;
    private Object updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBargainName() {
        return bargainName;
    }

    public void setBargainName(String bargainName) {
        this.bargainName = bargainName;
    }

    public String getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(String bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public String getBargainImage() {
        return bargainImage;
    }

    public void setBargainImage(String bargainImage) {
        this.bargainImage = bargainImage;
    }

    public int getBargainCount() {
        return bargainCount;
    }

    public void setBargainCount(int bargainCount) {
        this.bargainCount = bargainCount;
    }

    public String getBargainPlace() {
        return bargainPlace;
    }

    public void setBargainPlace(String bargainPlace) {
        this.bargainPlace = bargainPlace;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BargainBean{" +
                "id=" + id +
                ", bargainName='" + bargainName + '\'' +
                ", bargainPrice='" + bargainPrice + '\'' +
                ", bargainImage='" + bargainImage + '\'' +
                ", bargainCount=" + bargainCount +
                ", bargainPlace='" + bargainPlace + '\'' +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
