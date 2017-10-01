package com.example.xu.myapplication.moduleType.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/29.
 */

public class Fruit implements Parcelable {

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
    private ArrayList<GoodsDetail> goods;

    protected Fruit(Parcel in) {
        classifyName = in.readString();
        classifyType = in.readInt();
        createTime = in.readString();
        id = in.readInt();
        updateTime = in.readString();
        goods = in.readArrayList(null);
    }

    public static final Creator<Fruit> CREATOR = new Creator<Fruit>() {
        @Override
        public Fruit createFromParcel(Parcel in) {
            return new Fruit(in);
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
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

    public ArrayList<GoodsDetail> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<GoodsDetail> goods) {
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

    public static class GoodsDetail implements Parcelable {
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

        protected GoodsDetail(Parcel in) {
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


        public static final Creator<GoodsDetail> CREATOR = new Creator<GoodsDetail>() {
            @Override
            public GoodsDetail createFromParcel(Parcel in) {
                return new GoodsDetail(in);
            }

            @Override
            public GoodsDetail[] newArray(int size) {
                return new GoodsDetail[size];
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
        return "Fruit{" +
                "classifyName='" + classifyName + '\'' +
                ", classifyType=" + classifyType +
                ", createTime='" + createTime + '\'' +
                ", id=" + id +
                ", updateTime='" + updateTime + '\'' +
                ", goods=" + goods +
                '}';
    }
}
