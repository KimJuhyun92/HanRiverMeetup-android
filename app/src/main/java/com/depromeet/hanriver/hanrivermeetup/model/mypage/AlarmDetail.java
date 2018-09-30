package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlarmDetail {

    @SerializedName("notification_seq")
    @Expose
    private Integer notificationSeq;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("creation_time")
    @Expose
    private String creationTime;
    @SerializedName("isdeleted")
    @Expose
    private Boolean isdeleted;

    public Integer getNotificationSeq() {
        return notificationSeq;
    }

    public void setNotificationSeq(Integer notificationSeq) {
        this.notificationSeq = notificationSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}