package com.example.xu.myapplication.moduleHome.fragment.bean;

/**
 * Created by 逝 on 2017/09/30.
 */

public class BargainBean {

    /**
     * id : 1
     * bargainName : null
     * bargainPrice : 10
     * bargainImage : null
     * bargainCount : 1
     * bargainPlace : 南京工业职业技术学院
     * endTime : 2017-10-03T22:02:00+08:00
     * createTime : null
     * updateTime : null
     * goodsId : 1
     * goods : {"id":1,"goodsName":"台湾凤梨","goodsPrice":"19.90","goodsImage":"http://img.guocool
     * .com/product/2017/06/23/c76c6087d3154f9aa86c23ad6be604d6.jpg","goodsClassify":0,
     * "goodsIntroduction":"产自台湾，香甜可口","temperature":20,
     * "nutritionInfo":"含有大量的果糖，葡萄糖，维生素A、B、C，磷，柠檬酸和蛋白酶等物",
     * "effect":"清热解暑、生津止渴、利小便的功效，可用于伤暑、身热烦渴、腹中痞闷、消化不良、小便不利、头昏眼花等症","hot":10,"createTime":null,
     * "updateTime":null}
     */

    private int id;
    private Object bargainName;
    private String bargainPrice;
    private Object bargainImage;
    private int bargainCount;
    private String bargainPlace;
    private String endTime;
    private Object createTime;
    private Object updateTime;
    private int goodsId;
    private GoodsBean goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getBargainName() {
        return bargainName;
    }

    public void setBargainName(Object bargainName) {
        this.bargainName = bargainName;
    }

    public String getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(String bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public Object getBargainImage() {
        return bargainImage;
    }

    public void setBargainImage(Object bargainImage) {
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * id : 1
         * goodsName : 台湾凤梨
         * goodsPrice : 19.90
         * goodsImage : http://img.guocool
         * .com/product/2017/06/23/c76c6087d3154f9aa86c23ad6be604d6.jpg
         * goodsClassify : 0
         * goodsIntroduction : 产自台湾，香甜可口
         * temperature : 20
         * nutritionInfo : 含有大量的果糖，葡萄糖，维生素A、B、C，磷，柠檬酸和蛋白酶等物
         * effect : 清热解暑、生津止渴、利小便的功效，可用于伤暑、身热烦渴、腹中痞闷、消化不良、小便不利、头昏眼花等症
         * hot : 10
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

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
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
