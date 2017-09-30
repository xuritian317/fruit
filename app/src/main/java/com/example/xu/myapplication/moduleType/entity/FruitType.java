package com.example.xu.myapplication.moduleType.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/9/29.
 */

public class FruitType implements Parcelable {

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
    private ArrayList<GoodsBean> goods;

    protected FruitType(Parcel in) {
        classifyName = in.readString();
        classifyType = in.readInt();
        createTime = in.readString();
        id = in.readInt();
        updateTime = in.readString();
        goods = in.readArrayList(null);
    }

    public static final Creator<FruitType> CREATOR = new Creator<FruitType>() {
        @Override
        public FruitType createFromParcel(Parcel in) {
            return new FruitType(in);
        }

        @Override
        public FruitType[] newArray(int size) {
            return new FruitType[size];
        }
    };

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

    public ArrayList<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<GoodsBean> goods) {
        this.goods = goods;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(classifyName);
        dest.writeInt(classifyType);
        dest.writeString(createTime);
        dest.writeInt(id);
        dest.writeString(updateTime);
        dest.writeList(goods);

    }

    public static class GoodsBean implements Parcelable {
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

        protected GoodsBean(Parcel in) {
            createTime = in.readString();
            effect = in.readString();
            goodsClassify = in.readInt();
            goodsImage = in.readString();
            goodsIntroduction = in.readString();
            goodsName = in.readString();
            goodsPrice = in.readString();
            hot = in.readInt();
            id = in.readInt();
            nutritionInfo = in.readString();
            temperature = in.readInt();
            updateTime = in.readString();
        }

        @Override
        public String toString() {
            return "GoodsBean{" +
                    "createTime='" + createTime + '\'' +
                    ", effect='" + effect + '\'' +
                    ", goodsClassify=" + goodsClassify +
                    ", goodsImage='" + goodsImage + '\'' +
                    ", goodsIntroduction='" + goodsIntroduction + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsPrice='" + goodsPrice + '\'' +
                    ", hot=" + hot +
                    ", id=" + id +
                    ", nutritionInfo='" + nutritionInfo + '\'' +
                    ", temperature=" + temperature +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }

        public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
            @Override
            public GoodsBean createFromParcel(Parcel in) {
                return new GoodsBean(in);
            }

            @Override
            public GoodsBean[] newArray(int size) {
                return new GoodsBean[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(createTime);
            dest.writeString(effect);
            dest.writeInt(goodsClassify);
            dest.writeString(goodsImage);
            dest.writeString(goodsIntroduction);
            dest.writeString(goodsName);
            dest.writeString(goodsPrice);
            dest.writeInt(hot);
            dest.writeInt(id);
            dest.writeString(nutritionInfo);
            dest.writeInt(temperature);
            dest.writeString(updateTime);
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
