package com.example.xu.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xu on 2017/10/10.
 */

public class UserOrder implements Parcelable{

    /**
     * createTime : 2017-10-10T07:50:33.334Z
     * goods : {"createTime":"2017-10-10T07:50:33.334Z","effect":"string","goodsClassify":0,"goodsImage":"string","goodsIntroduction":"string","goodsName":"string","goodsPrice":"string","hot":0,"id":0,"nutritionInfo":"string","temperature":0,"updateTime":"2017-10-10T07:50:33.334Z"}
     * goodsCount : 0
     * id : 0
     * orderNumber : string
     * orderPay : string
     * orderState : 0
     * receiveAddress : {"address":"string","createTime":"2017-10-10T07:50:33.334Z","id":0,"receivePhoneNumber":"string","receiveUser":"string","street":"string","updateTime":"2017-10-10T07:50:33.334Z","user":{"birthday":"2017-10-10T07:50:33.334Z","createTime":"2017-10-10T07:50:33.334Z","email":"string","headImage":"string","id":0,"nickName":"string","password":"string","phoneNumber":"string","receiveAddress":"string","sex":0,"updateTime":"2017-10-10T07:50:33.334Z"}}
     * receivePlace : string
     * receiveTime : 2017-10-10T07:50:33.334Z
     * reviewState : 0
     * updateTime : 2017-10-10T07:50:33.334Z
     * user : {"birthday":"2017-10-10T07:50:33.334Z","createTime":"2017-10-10T07:50:33.334Z","email":"string","headImage":"string","id":0,"nickName":"string","password":"string","phoneNumber":"string","receiveAddress":"string","sex":0,"updateTime":"2017-10-10T07:50:33.334Z"}
     */

    private String createTime;
    private GoodsBean goods;
    private int goodsCount;
    private int id;
    private String orderNumber;
    private String orderPay;
    private int orderState;
    private ReceiveAddressBean receiveAddress;
    private String receivePlace;
    private String receiveTime;
    private int reviewState;
    private String updateTime;
    private UserBeanX user;

    @Override
    public String toString() {
        return "UserOrder{" +
                "createTime='" + createTime + '\'' +
                ", goods=" + goods +
                ", goodsCount=" + goodsCount +
                ", id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderPay='" + orderPay + '\'' +
                ", orderState=" + orderState +
                ", receiveAddress=" + receiveAddress +
                ", receivePlace='" + receivePlace + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", reviewState=" + reviewState +
                ", updateTime='" + updateTime + '\'' +
                ", user=" + user +
                '}';
    }

    protected UserOrder(Parcel in) {
        createTime = in.readString();
        goodsCount = in.readInt();
        id = in.readInt();
        orderNumber = in.readString();
        orderPay = in.readString();
        orderState = in.readInt();
        receivePlace = in.readString();
        receiveTime = in.readString();
        reviewState = in.readInt();
        updateTime = in.readString();
    }

    public static final Creator<UserOrder> CREATOR = new Creator<UserOrder>() {
        @Override
        public UserOrder createFromParcel(Parcel in) {
            return new UserOrder(in);
        }

        @Override
        public UserOrder[] newArray(int size) {
            return new UserOrder[size];
        }
    };

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public ReceiveAddressBean getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(ReceiveAddressBean receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePlace() {
        return receivePlace;
    }

    public void setReceivePlace(String receivePlace) {
        this.receivePlace = receivePlace;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public int getReviewState() {
        return reviewState;
    }

    public void setReviewState(int reviewState) {
        this.reviewState = reviewState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public UserBeanX getUser() {
        return user;
    }

    public void setUser(UserBeanX user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createTime);
        dest.writeInt(goodsCount);
        dest.writeInt(id);
        dest.writeString(orderNumber);
        dest.writeString(orderPay);
        dest.writeInt(orderState);
        dest.writeString(receivePlace);
        dest.writeString(receiveTime);
        dest.writeInt(reviewState);
        dest.writeString(updateTime);
    }

    public static class GoodsBean {
        /**
         * createTime : 2017-10-10T07:50:33.334Z
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
         * updateTime : 2017-10-10T07:50:33.334Z
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

    public static class ReceiveAddressBean {
        /**
         * address : string
         * createTime : 2017-10-10T07:50:33.334Z
         * id : 0
         * receivePhoneNumber : string
         * receiveUser : string
         * street : string
         * updateTime : 2017-10-10T07:50:33.334Z
         * user : {"birthday":"2017-10-10T07:50:33.334Z","createTime":"2017-10-10T07:50:33.334Z","email":"string","headImage":"string","id":0,"nickName":"string","password":"string","phoneNumber":"string","receiveAddress":"string","sex":0,"updateTime":"2017-10-10T07:50:33.334Z"}
         */

        private String address;
        private String createTime;
        private int id;
        private String receivePhoneNumber;
        private String receiveUser;
        private String street;
        private String updateTime;
        private UserBean user;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getReceivePhoneNumber() {
            return receivePhoneNumber;
        }

        public void setReceivePhoneNumber(String receivePhoneNumber) {
            this.receivePhoneNumber = receivePhoneNumber;
        }

        public String getReceiveUser() {
            return receiveUser;
        }

        public void setReceiveUser(String receiveUser) {
            this.receiveUser = receiveUser;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * birthday : 2017-10-10T07:50:33.334Z
             * createTime : 2017-10-10T07:50:33.334Z
             * email : string
             * headImage : string
             * id : 0
             * nickName : string
             * password : string
             * phoneNumber : string
             * receiveAddress : string
             * sex : 0
             * updateTime : 2017-10-10T07:50:33.334Z
             */

            private String birthday;
            private String createTime;
            private String email;
            private String headImage;
            private int id;
            private String nickName;
            private String password;
            private String phoneNumber;
            private String receiveAddress;
            private int sex;
            private String updateTime;

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getReceiveAddress() {
                return receiveAddress;
            }

            public void setReceiveAddress(String receiveAddress) {
                this.receiveAddress = receiveAddress;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }

    public static class UserBeanX {
        /**
         * birthday : 2017-10-10T07:50:33.334Z
         * createTime : 2017-10-10T07:50:33.334Z
         * email : string
         * headImage : string
         * id : 0
         * nickName : string
         * password : string
         * phoneNumber : string
         * receiveAddress : string
         * sex : 0
         * updateTime : 2017-10-10T07:50:33.334Z
         */

        private String birthday;
        private String createTime;
        private String email;
        private String headImage;
        private int id;
        private String nickName;
        private String password;
        private String phoneNumber;
        private String receiveAddress;
        private int sex;
        private String updateTime;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
