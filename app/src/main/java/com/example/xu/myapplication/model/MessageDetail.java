package com.example.xu.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xu on 2017/10/10.
 */

public class MessageDetail implements Parcelable{

    /**
     * content : string
     * createTime : 2017-10-10T02:57:34.015Z
     * id : 0
     * messageType : 0
     * updateTime : 2017-10-10T02:57:34.015Z
     * user : {"birthday":"2017-10-10T02:57:34.015Z","createTime":"2017-10-10T02:57:34.015Z","email":"string","headImage":"string","id":0,"nickName":"string","password":"string","phoneNumber":"string","receiveAddress":"string","sex":0,"updateTime":"2017-10-10T02:57:34.015Z"}
     * userId : 0
     */

    private String content;
    private String createTime;
    private int id;
    private int messageType;
    private String updateTime;
    private UserBean user;
    private int userId;

    protected MessageDetail(Parcel in) {
        content = in.readString();
        createTime = in.readString();
        id = in.readInt();
        messageType = in.readInt();
        updateTime = in.readString();
        userId = in.readInt();
    }

    public static final Creator<MessageDetail> CREATOR = new Creator<MessageDetail>() {
        @Override
        public MessageDetail createFromParcel(Parcel in) {
            return new MessageDetail(in);
        }

        @Override
        public MessageDetail[] newArray(int size) {
            return new MessageDetail[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(createTime);
        dest.writeInt(id);
        dest.writeInt(messageType);
        dest.writeString(updateTime);
        dest.writeInt(userId);
    }

    public static class UserBean {
        /**
         * birthday : 2017-10-10T02:57:34.015Z
         * createTime : 2017-10-10T02:57:34.015Z
         * email : string
         * headImage : string
         * id : 0
         * nickName : string
         * password : string
         * phoneNumber : string
         * receiveAddress : string
         * sex : 0
         * updateTime : 2017-10-10T02:57:34.015Z
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
