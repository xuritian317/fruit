package com.example.xu.myapplication.moduleMy.fragment.bean;

import java.io.Serializable;

/**
 * Created by 逝 on 2017/10/03.
 */

public class ReceiveAddressBean implements Serializable{


    /**
     * id : 10
     * address : 江苏省徐州市睢宁县
     * street : pinglou
     * receiveUser : shenben
     * receivePhoneNumber : 15150570952
     * createTime : 2017-10-04T11:34:42+08:00
     * updateTime : 2017-10-04T11:34:42+08:00
     * userId : 2
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
    private int userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        private String headImage;
        private String receiveAddress;
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

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
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
