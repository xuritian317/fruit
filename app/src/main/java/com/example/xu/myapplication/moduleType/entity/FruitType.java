package com.example.xu.myapplication.moduleType.entity;

import java.util.List;

/**
 * Created by xu on 2017/9/29.
 */

public class FruitType {

    /**
     * classifyName : string
     * classifyType : 0
     * createTime : 2017-09-29T08:13:08.844Z
     * goods : [{"createTime":"2017-09-29T08:13:08.844Z","effect":"string","goodsClassify":0,"goodsImage":"string","goodsIntroduction":"string","goodsName":"string","goodsPrice":"string","hot":0,"id":0,"nutritionInfo":"string","temperature":0,"updateTime":"2017-09-29T08:13:08.845Z"}]
     * id : 0
     * updateTime : 2017-09-29T08:13:08.845Z
     */

    private String classifyName;
    private int classifyType;
    private String createTime;
    private int id;
    private String updateTime;
    private List<GoodsBean> goods;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public int getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(int classifyType) {
        this.classifyType = classifyType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * createTime : 2017-09-29T08:13:08.844Z
         * effect : string
         * goodsClassify : 0
         * goodsImage : string
         * goodsIntroduction : string
         * goodsName : string
         * goodsPrice : string
         * hot : 0
         * id : 0
         * nutritionInfo : string
         * temperature : 0
         * updateTime : 2017-09-29T08:13:08.845Z
         */

        private String createTime;
        private String effect;
        private int goodsClassify;
        private String goodsImage;
        private String goodsIntroduction;
        private String goodsName;
        private String goodsPrice;
        private int hot;
        private int id;
        private String nutritionInfo;
        private int temperature;
        private String updateTime;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public int getGoodsClassify() {
            return goodsClassify;
        }

        public void setGoodsClassify(int goodsClassify) {
            this.goodsClassify = goodsClassify;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getGoodsIntroduction() {
            return goodsIntroduction;
        }

        public void setGoodsIntroduction(String goodsIntroduction) {
            this.goodsIntroduction = goodsIntroduction;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNutritionInfo() {
            return nutritionInfo;
        }

        public void setNutritionInfo(String nutritionInfo) {
            this.nutritionInfo = nutritionInfo;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    @Override
    public String toString() {
        return "FruitType{" +
                "classifyName='" + classifyName + '\'' +
                ", classifyType=" + classifyType +
                ", createTime='" + createTime + '\'' +
                ", id=" + id +
                ", updateTime='" + updateTime + '\'' +
                ", goods=" + goods +
                '}';
    }
}
