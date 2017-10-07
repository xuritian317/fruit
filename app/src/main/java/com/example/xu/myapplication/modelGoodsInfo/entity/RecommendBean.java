package com.example.xu.myapplication.modelGoodsInfo.entity;

/**
 * Created by xu on 2017/10/7.
 */

public class RecommendBean {

    /**
     * id : 1
     * recommendId : 1
     * createTime : 2017-10-07T11:26:33+08:00
     * updateTime : 2017-10-07T11:26:33+08:00
     * goods : {"id":5,"goodsName":"丰水梨","goodsPrice":"29.90","goodsImage":"https://imgjd4.fruitday.com/images/product_pic/3355/1/1-270x270-3355-8S9CY37A.jpg","goodsClassify":0,"goodsIntroduction":"丰水梨是一种原产于日本的梨，该品种树冠中大，幼树期生长旺，结果后树势中庸，萌芽力高，成枝力弱，成花容易，结果早，以短果枝结果为主，座果率极高。其果实多汁，口感极佳。是日本三水梨之一，其品质优于幸水和新水。[1]  品质上等，平均单果重240克，最大单果重750克。","temperature":0,"nutritionInfo":"富含维生素C","effect":"味美，多汁。性寒， 有润肺、消痰、止咳、降火功效。","hot":0,"createTime":null,"updateTime":null}
     */

    private int id;
    private int recommendId;
    private String createTime;
    private String updateTime;
    private GoodsBean goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(int recommendId) {
        this.recommendId = recommendId;
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
         * id : 5
         * goodsName : 丰水梨
         * goodsPrice : 29.90
         * goodsImage : https://imgjd4.fruitday.com/images/product_pic/3355/1/1-270x270-3355-8S9CY37A.jpg
         * goodsClassify : 0
         * goodsIntroduction : 丰水梨是一种原产于日本的梨，该品种树冠中大，幼树期生长旺，结果后树势中庸，萌芽力高，成枝力弱，成花容易，结果早，以短果枝结果为主，座果率极高。其果实多汁，口感极佳。是日本三水梨之一，其品质优于幸水和新水。[1]  品质上等，平均单果重240克，最大单果重750克。
         * temperature : 0
         * nutritionInfo : 富含维生素C
         * effect : 味美，多汁。性寒， 有润肺、消痰、止咳、降火功效。
         * hot : 0
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
