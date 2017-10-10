package com.example.xu.myapplication.moduleMy.bean;

/**
 * Created by 逝 on 2017/10/04.
 */

public class OrdersBean {

    /**
     * id : 50
     * orderNumber : e5fd48d731cb413b89bdbf0a02e2c3b1
     * orderPay : 19.9
     * goodsCount : 1
     * orderState : 1
     * reviewState : 0
     * receiveTime : 2017-10-07T23:59:59+08:00
     * receivePlace : null
     * createTime : 2017-10-07T21:59:05+08:00
     * updateTime : 2017-10-07T21:59:05+08:00
     * receiveAddress : {"id":18,"address":"江苏省徐州市睢宁县","street":"pinglou",
     * "receiveUser":"shenben","receivePhoneNumber":"15150570952",
     * "createTime":"2017-10-07T21:54:01+08:00","updateTime":"2017-10-07T21:54:01+08:00",
     * "user":{"id":5,"phoneNumber":"15150570952","password":"123456","nickName":null,
     * "birthday":null,"email":null,"sex":null,"headImage":null,"receiveAddress":null,
     * "createTime":"2017-10-07T21:49:15+08:00","updateTime":"2017-10-07T21:49:15+08:00"}}
     * user : {"id":5,"phoneNumber":"15150570952","password":"123456","nickName":null,
     * "birthday":null,"email":null,"sex":null,"headImage":null,"receiveAddress":null,
     * "createTime":"2017-10-07T21:49:15+08:00","updateTime":"2017-10-07T21:49:15+08:00"}
     * goods : {"id":1,"goodsName":"台湾凤梨","goodsPrice":"19.90","goodsImage":"http://img.guocool
     * .com/product/2017/06/23/c76c6087d3154f9aa86c23ad6be604d6.jpg","goodsClassify":0,
     * "goodsIntroduction":"产自台湾，香甜可口","temperature":20,
     * "nutritionInfo":"含有大量的果糖，葡萄糖，维生素A、B、C，磷，柠檬酸和蛋白酶等物",
     * "effect":"清热解暑、生津止渴、利小便的功效，可用于伤暑、身热烦渴、腹中痞闷、消化不良、小便不利、头昏眼花等症","hot":10,"createTime":null,
     * "updateTime":null}
     */

    private int id;
    private String orderNumber;
    private String orderPay;
    private int goodsCount;
    private int orderState;
    private int reviewState;
    private String receiveTime;
    private Object receivePlace;
    private String createTime;
    private String updateTime;
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

    public Object getReceivePlace() {
        return receivePlace;
    }

    public void setReceivePlace(Object receivePlace) {
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
         * id : 18
         * address : 江苏省徐州市睢宁县
         * street : pinglou
         * receiveUser : shenben
         * receivePhoneNumber : 15150570952
         * createTime : 2017-10-07T21:54:01+08:00
         * updateTime : 2017-10-07T21:54:01+08:00
         * user : {"id":5,"phoneNumber":"15150570952","password":"123456","nickName":null,
         * "birthday":null,"email":null,"sex":null,"headImage":null,"receiveAddress":null,
         * "createTime":"2017-10-07T21:49:15+08:00","updateTime":"2017-10-07T21:49:15+08:00"}
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
             * id : 5
             * phoneNumber : 15150570952
             * password : 123456
             * nickName : null
             * birthday : null
             * email : null
             * sex : null
             * headImage : null
             * receiveAddress : null
             * createTime : 2017-10-07T21:49:15+08:00
             * updateTime : 2017-10-07T21:49:15+08:00
             */

            private int id;
            private String phoneNumber;
            private String password;
            private Object nickName;
            private Object birthday;
            private Object email;
            private Object sex;
            private Object headImage;
            private Object receiveAddress;
            private String createTime;
            private String updateTime;

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

            public Object getNickName() {
                return nickName;
            }

            public void setNickName(Object nickName) {
                this.nickName = nickName;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getHeadImage() {
                return headImage;
            }

            public void setHeadImage(Object headImage) {
                this.headImage = headImage;
            }

            public Object getReceiveAddress() {
                return receiveAddress;
            }

            public void setReceiveAddress(Object receiveAddress) {
                this.receiveAddress = receiveAddress;
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

    public static class UserBeanX {
        /**
         * id : 5
         * phoneNumber : 15150570952
         * password : 123456
         * nickName : null
         * birthday : null
         * email : null
         * sex : null
         * headImage : null
         * receiveAddress : null
         * createTime : 2017-10-07T21:49:15+08:00
         * updateTime : 2017-10-07T21:49:15+08:00
         */

        private int id;
        private String phoneNumber;
        private String password;
        private Object nickName;
        private Object birthday;
        private Object email;
        private Object sex;
        private Object headImage;
        private Object receiveAddress;
        private String createTime;
        private String updateTime;

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

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getHeadImage() {
            return headImage;
        }

        public void setHeadImage(Object headImage) {
            this.headImage = headImage;
        }

        public Object getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(Object receiveAddress) {
            this.receiveAddress = receiveAddress;
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
