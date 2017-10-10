package com.example.xu.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.xu.myapplication.modelGoodsInfo.entity.RecommendBean;
import com.example.xu.myapplication.moduleHome.bean.BargainBean;
import com.example.xu.myapplication.moduleHome.bean.RecommendsBean_0;
import com.example.xu.myapplication.moduleHome.bean.RecommendsBean_1;
import com.example.xu.myapplication.moduleShopping.bean.FruitBean;
import com.example.xu.myapplication.moduleType.entity.Bargain;

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


    private int id;
    private String classifyName;
    private int classifyType;
    private String createTime;
    private String updateTime;
    private ArrayList<FruitDetail> goods;

    public Fruit() {
    }

    public Fruit(String classifyName, ArrayList<FruitDetail> goods) {
        this.classifyName = classifyName;
        this.goods = goods;
    }

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

    public ArrayList<FruitDetail> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<FruitDetail> goods) {
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

    public static class FruitDetail implements Parcelable {
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

        protected FruitDetail(Parcel in) {
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

        public FruitDetail(String goodsName, String goodsPrice, String goodsImage) {
            this.goodsName = goodsName;
            this.goodsPrice = goodsPrice;
            this.goodsImage = goodsImage;
        }

        public FruitDetail(Bargain.FruitDetail fruitDetail) {
            this.id = fruitDetail.getId();
            this.goodsName = fruitDetail.getGoodsName();
            this.goodsPrice = fruitDetail.getGoodsPrice();
            this.goodsImage = fruitDetail.getGoodsImage();
            this.goodsClassify = fruitDetail.getGoodsClassify();
            this.goodsIntroduction = fruitDetail.getGoodsIntroduction();
            this.temperature = fruitDetail.getTemperature();
            this.nutritionInfo = fruitDetail.getNutritionInfo();
            this.effect = fruitDetail.getEffect();
            this.hot = fruitDetail.getHot();
            this.createTime = fruitDetail.getCreateTime();
            this.updateTime = fruitDetail.getUpdateTime();
        }

        public FruitDetail(BargainBean.GoodsBean goodsBean) {
            this.id = goodsBean.getId();
            this.goodsName = goodsBean.getGoodsName();
            this.goodsPrice = goodsBean.getGoodsPrice();
            this.goodsImage = goodsBean.getGoodsImage();
            this.goodsClassify = goodsBean.getGoodsClassify();
            this.goodsIntroduction = goodsBean.getGoodsIntroduction();
            this.temperature = goodsBean.getTemperature();
            this.nutritionInfo = goodsBean.getNutritionInfo();
            this.effect = goodsBean.getEffect();
            this.hot = goodsBean.getHot();
            this.createTime = goodsBean.getCreateTime();
            this.updateTime = goodsBean.getUpdateTime();
        }

        public FruitDetail(RecommendsBean_0.GoodsBean goodsBean) {
            this.id = goodsBean.getId();
            this.goodsName = goodsBean.getGoodsName();
            this.goodsPrice = goodsBean.getGoodsPrice();
            this.goodsImage = goodsBean.getGoodsImage();
            this.goodsClassify = goodsBean.getGoodsClassify();
            this.goodsIntroduction = goodsBean.getGoodsIntroduction();
            this.temperature = goodsBean.getTemperature();
            this.nutritionInfo = goodsBean.getNutritionInfo();
            this.effect = goodsBean.getEffect();
            this.hot = goodsBean.getHot();
            this.createTime = goodsBean.getCreateTime();
            this.updateTime = goodsBean.getUpdateTime();
        }

        public FruitDetail(RecommendsBean_1.GoodsBean goodsBean) {
            this.id = goodsBean.getId();
            this.goodsName = goodsBean.getGoodsName();
            this.goodsPrice = goodsBean.getGoodsPrice();
            this.goodsImage = goodsBean.getGoodsImage();
            this.goodsClassify = goodsBean.getGoodsClassify();
            this.goodsIntroduction = goodsBean.getGoodsIntroduction();
            this.temperature = goodsBean.getTemperature();
            this.nutritionInfo = goodsBean.getNutritionInfo();
            this.effect = goodsBean.getEffect();
            this.hot = goodsBean.getHot();
            this.createTime = goodsBean.getCreateTime();
            this.updateTime = goodsBean.getUpdateTime();
        }

        public FruitDetail(RecommendBean.GoodsBean goodsBean) {
            this.id = goodsBean.getId();
            this.goodsName = goodsBean.getGoodsName();
            this.goodsPrice = goodsBean.getGoodsPrice();
            this.goodsImage = goodsBean.getGoodsImage();
            this.goodsClassify = goodsBean.getGoodsClassify();
            this.goodsIntroduction = goodsBean.getGoodsIntroduction();
            this.temperature = goodsBean.getTemperature();
            this.nutritionInfo = goodsBean.getNutritionInfo();
            this.effect = goodsBean.getEffect();
            this.hot = goodsBean.getHot();
            this.createTime = goodsBean.getCreateTime();
            this.updateTime = goodsBean.getUpdateTime();
        }

        public FruitDetail(FruitBean.GoodsBean goodsBean) {
            this.id = goodsBean.getId();
            this.goodsName = goodsBean.getGoodsName();
            this.goodsPrice = goodsBean.getGoodsPrice();
            this.goodsImage = goodsBean.getGoodsImage();
            this.goodsClassify = goodsBean.getGoodsClassify();
            this.goodsIntroduction = goodsBean.getGoodsIntroduction();
            this.temperature = goodsBean.getTemperature();
            this.nutritionInfo = goodsBean.getNutritionInfo();
            this.effect = goodsBean.getEffect();
            this.hot = goodsBean.getHot();
            this.createTime = goodsBean.getCreateTime();
            this.updateTime = goodsBean.getUpdateTime();
        }

        public static final Creator<FruitDetail> CREATOR = new Creator<FruitDetail>() {
            @Override
            public FruitDetail createFromParcel(Parcel in) {
                return new FruitDetail(in);
            }

            @Override
            public FruitDetail[] newArray(int size) {
                return new FruitDetail[size];
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

        @Override
        public String toString() {
            return "FruitDetail{" +
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
