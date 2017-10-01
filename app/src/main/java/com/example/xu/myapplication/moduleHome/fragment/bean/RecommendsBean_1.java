package com.example.xu.myapplication.moduleHome.fragment.bean;

/**
 * Created by 逝 on 2017/10/01.
 */

public class RecommendsBean_1 {

    /**
     * id : 12
     * place : null
     * goodsType : 1
     * createTime : null
     * updateTime : null
     * goods : {"id":26,"goodsName":"薄荷轻食专享套餐 ","goodsPrice":"118.00",
     * "goodsImage":"https://imgjd2.fruitday
     * .com/images/product_pic/420/1/1-270x270-420-WKDS9C15.jpg","goodsClassify":1,
     * "goodsIntroduction":"商品组成（有哪些商品组成）","temperature":0,"nutritionInfo":"营养价值（后期填写）",
     * "effect":"功效（后期填写）","hot":null,"createTime":null,"updateTime":null}
     */

    private int id;
    private Object place;
    private int goodsType;
    private Object createTime;
    private Object updateTime;
    private GoodsBean goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getPlace() {
        return place;
    }

    public void setPlace(Object place) {
        this.place = place;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
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

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * id : 26
         * goodsName : 薄荷轻食专享套餐
         * goodsPrice : 118.00
         * goodsImage : https://imgjd2.fruitday
         * .com/images/product_pic/420/1/1-270x270-420-WKDS9C15.jpg
         * goodsClassify : 1
         * goodsIntroduction : 商品组成（有哪些商品组成）
         * temperature : 0
         * nutritionInfo : 营养价值（后期填写）
         * effect : 功效（后期填写）
         * hot : null
         * createTime : null
         * updateTime : null
         */

        private int id;
        private String goodsName;
        private String goodsPrice;
        private String goodsImage;
        private int goodsClassify;
        private String goodsIntroduction;
        private int temperature;
        private String nutritionInfo;
        private String effect;
        private Object hot;
        private Object createTime;
        private Object updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public int getGoodsClassify() {
            return goodsClassify;
        }

        public void setGoodsClassify(int goodsClassify) {
            this.goodsClassify = goodsClassify;
        }

        public String getGoodsIntroduction() {
            return goodsIntroduction;
        }

        public void setGoodsIntroduction(String goodsIntroduction) {
            this.goodsIntroduction = goodsIntroduction;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public String getNutritionInfo() {
            return nutritionInfo;
        }

        public void setNutritionInfo(String nutritionInfo) {
            this.nutritionInfo = nutritionInfo;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public Object getHot() {
            return hot;
        }

        public void setHot(Object hot) {
            this.hot = hot;
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
    }
}
