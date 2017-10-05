package com.example.xu.myapplication.moduleMy.fragment.bean;

/**
 * Created by 逝 on 2017/10/04.
 */

public class OrdersBean {

    /**
     * id : 2
     * orderNumber : f84f2e450ee048cf8c043a9e3e012847
     * orderPay : 100.00
     * goodsCount : 10
     * orderState : 0
     * reviewState : 0
     * receiveTime : null
     * receivePlace : null
     * createTime : 2017-10-04T11:55:31+08:00
     * updateTime : 2017-10-04T11:55:31+08:00
     * receiveAddressId : 12
     * userId : 1
     * goodsId : 1
     * receiveAddress : {"id":12,"address":"北京市北京市东城区","street":"xihuan","receiveUser":"shen",
     * "receivePhoneNumber":"15150570952","createTime":"2017-10-04T11:48:23+08:00",
     * "updateTime":"2017-10-04T11:48:23+08:00","user":{"id":2,"phoneNumber":"18851190000",
     * "password":"123456789","nickName":"烦啊","birthday":"1995-02-22T22:22:00+08:00",
     * "email":"1617318433@qq.com","sex":1,"headImage":null,"receiveAddress":"南京工业职业技术学院",
     * "createTime":null,"updateTime":null}}
     * user : {"id":1,"phoneNumber":"18851190037","password":"123456","nickName":"无聊",
     * "birthday":"1995-12-30T00:00:00+08:00","email":"1617318433","sex":0,"headImage":null,
     * "receiveAddress":"南京工业职业技术学院","createTime":null,"updateTime":null}
     * goods : {"id":1,"goodsName":"台湾凤梨","goodsPrice":"19.90","goodsImage":"http://img.guocool
     * .com/product/2017/06/23/c76c6087d3154f9aa86c23ad6be604d6.jpg","goodsClassify":0,
     * "goodsIntroduction":"产自台湾，香甜可口","temperature":20,
     * "nutritionInfo":"含有大量的果糖，葡萄糖，维生素A、B、C，磷，柠檬酸和蛋白酶等物",
     * "effect":"清热解暑、生津止渴、利小便的功效，可用于伤暑、身热烦渴、腹中痞闷、消化不良、小便不利、头昏眼花等症","hot":10,"createTime":null,"updateTime":null}
     */

    private int id;
    private String orderNumber;
    private String orderPay;
    private int goodsCount;
    private int orderState;
    private int reviewState;
    private String receiveTime;
    private String receivePlace;
    private String createTime;
    private String updateTime;
    private int receiveAddressId;
    private int userId;
    private int goodsId;
    private ReceiveAddressBean receiveAddress;
    private UserBeanX user;
    private GoodsBean goods;

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

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getReviewState() {
        return reviewState;
    }

    public void setReviewState(int reviewState) {
        this.reviewState = reviewState;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceivePlace() {
        return receivePlace;
    }

    public void setReceivePlace(String receivePlace) {
        this.receivePlace = receivePlace;
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

    public int getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(int receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public ReceiveAddressBean getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(ReceiveAddressBean receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public UserBeanX getUser() {
        return user;
    }

    public void setUser(UserBeanX user) {
        this.user = user;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class ReceiveAddressBean {
        /**
         * id : 12
         * address : 北京市北京市东城区
         * street : xihuan
         * receiveUser : shen
         * receivePhoneNumber : 15150570952
         * createTime : 2017-10-04T11:48:23+08:00
         * updateTime : 2017-10-04T11:48:23+08:00
         * user : {"id":2,"phoneNumber":"18851190000","password":"123456789","nickName":"烦啊",
         * "birthday":"1995-02-22T22:22:00+08:00","email":"1617318433@qq.com","sex":1,
         * "headImage":null,"receiveAddress":"南京工业职业技术学院","createTime":null,"updateTime":null}
         */

        private int id;
        private String address;
        private String street;
        private String receiveUser;
        private String receivePhoneNumber;
        private String createTime;
        private String updateTime;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getReceiveUser() {
            return receiveUser;
        }

        public void setReceiveUser(String receiveUser) {
            this.receiveUser = receiveUser;
        }

        public String getReceivePhoneNumber() {
            return receivePhoneNumber;
        }

        public void setReceivePhoneNumber(String receivePhoneNumber) {
            this.receivePhoneNumber = receivePhoneNumber;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 2
             * phoneNumber : 18851190000
             * password : 123456789
             * nickName : 烦啊
             * birthday : 1995-02-22T22:22:00+08:00
             * email : 1617318433@qq.com
             * sex : 1
             * headImage : null
             * receiveAddress : 南京工业职业技术学院
             * createTime : null
             * updateTime : null
             */

            private int id;
            private String phoneNumber;
            private String password;
            private String nickName;
            private String birthday;
            private String email;
            private int sex;
            private Object headImage;
            private String receiveAddress;
            private Object createTime;
            private Object updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getHeadImage() {
                return headImage;
            }

            public void setHeadImage(Object headImage) {
                this.headImage = headImage;
            }

            public String getReceiveAddress() {
                return receiveAddress;
            }

            public void setReceiveAddress(String receiveAddress) {
                this.receiveAddress = receiveAddress;
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

    public static class UserBeanX {
        /**
         * id : 1
         * phoneNumber : 18851190037
         * password : 123456
         * nickName : 无聊
         * birthday : 1995-12-30T00:00:00+08:00
         * email : 1617318433
         * sex : 0
         * headImage : null
         * receiveAddress : 南京工业职业技术学院
         * createTime : null
         * updateTime : null
         */

        private int id;
        private String phoneNumber;
        private String password;
        private String nickName;
        private String birthday;
        private String email;
        private int sex;
        private Object headImage;
        private String receiveAddress;
        private Object createTime;
        private Object updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getHeadImage() {
            return headImage;
        }

        public void setHeadImage(Object headImage) {
            this.headImage = headImage;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
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

    public static class GoodsBean {
        /**
         * id : 1
         * goodsName : 台湾凤梨
         * goodsPrice : 19.90
         * goodsImage : http://img.guocool.com/product/2017/06/23/c76c6087d3154f9aa86c23ad6be604d6.jpg
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
