package com.example.xu.myapplication.moduleShopping.bean;

import java.io.Serializable;

/**
 * Created by 逝 on 2017/09/14.
 */

public class FruitBean implements Serializable {
    private int id;//id 号
    private String fruit;//水果名
    private int goodId;
    private double price;//单价
    private int number;//购买数量
    private String fruit_img;//水果图片网址
    private boolean isChecked;//在购物车中是否选中  默认false

    private GoodsBean goods;

    public FruitBean() {
    }

    public FruitBean(int id, String fruit, int goodId, double price, int number, String
            fruit_img, boolean isChecked, GoodsBean goods) {
        this.id = id;
        this.fruit = fruit;
        this.goodId = goodId;
        this.price = price;
        this.number = number;
        this.fruit_img = fruit_img;
        this.isChecked = isChecked;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFruit_img() {
        return fruit_img;
    }

    public void setFruit_img(String fruit_img) {
        this.fruit_img = fruit_img;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class GoodsBean implements Serializable {
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

        @Override
        public String toString() {
            return "GoodsBean{" +
                    "id=" + id +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsPrice='" + goodsPrice + '\'' +
                    ", goodsImage='" + goodsImage + '\'' +
                    ", goodsClassify=" + goodsClassify +
                    ", goodsIntroduction='" + goodsIntroduction + '\'' +
                    ", temperature=" + temperature +
                    ", nutritionInfo='" + nutritionInfo + '\'' +
                    ", effect='" + effect + '\'' +
                    ", hot=" + hot +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }
    }
}
