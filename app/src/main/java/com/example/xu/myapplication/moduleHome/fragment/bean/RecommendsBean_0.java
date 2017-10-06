package com.example.xu.myapplication.moduleHome.fragment.bean;

/**
 * Created by 逝 on 2017/10/01.
 */

public class RecommendsBean_0 {
    /**
     * id : 1
     * place : null
     * goodsType : 0
     * createTime : 2017-07-07T04:56:00+08:00
     * updateTime : 2017-07-18T04:55:00+08:00
     * goods : {"id":17,"goodsName":"拇指青桔","goodsPrice":"9.90",
     * "goodsImage":"https://imgjd2.fruitday
     * .com/images/product_pic/11851/1/1-270x270-11851-8DA2FRK7.jpg","goodsClassify":0,
     * "goodsIntroduction":"青金桔 （又名青桔、山桔、年桔、绿桔)英文：lime,
     * 海南人俗称公孙桔、桔仔或酸桔，台湾人称金桔，新加坡人则称为酸柑，用来做酸柑水卖；为芸香科柑橘族金柑属，个头较小，一颗平均在15克左右。","temperature":0,
     * "nutritionInfo":"每100克果肉内含维生素C40～50毫克及维生素A、P和芳香油、类胡罗萝卜素等多种物质。",
     * "effect":"有治疗眼疾、咳嗽、哮喘、高血压、防止动脉硬化等特殊功效。","hot":null,"createTime":null,"updateTime":null}
     */

    private int id;
    private Object place;
    private int goodsType;
    private String createTime;
    private String updateTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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
         * id : 17
         * goodsName : 拇指青桔
         * goodsPrice : 9.90
         * goodsImage : https://imgjd2.fruitday
         * .com/images/product_pic/11851/1/1-270x270-11851-8DA2FRK7.jpg
         * goodsClassify : 0
         * goodsIntroduction : 青金桔 （又名青桔、山桔、年桔、绿桔)英文：lime,
         * 海南人俗称公孙桔、桔仔或酸桔，台湾人称金桔，新加坡人则称为酸柑，用来做酸柑水卖；为芸香科柑橘族金柑属，个头较小，一颗平均在15克左右。
         * temperature : 0
         * nutritionInfo : 每100克果肉内含维生素C40～50毫克及维生素A、P和芳香油、类胡罗萝卜素等多种物质。
         * effect : 有治疗眼疾、咳嗽、哮喘、高血压、防止动脉硬化等特殊功效。
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
        private int hot;
        private String createTime;
        private String updateTime;

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

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
