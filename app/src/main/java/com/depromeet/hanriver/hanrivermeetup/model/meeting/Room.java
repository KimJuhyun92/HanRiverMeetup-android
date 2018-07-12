package com.depromeet.hanriver.hanrivermeetup.model.meeting;

import android.support.annotation.NonNull;

public class Room {
    @NonNull
    private int profileImage;
    @NonNull
    private String roomName;
    @NonNull
    private String location;
    @NonNull
    private String time;
    @NonNull
    private int numOfmember;
    @NonNull
    private int fee;
    @NonNull
    private String roomContent;
    @NonNull
    private String phonenumber;

    public Room(int profileImage,String roomName,String location,String time,int numOfmember,int fee,String roomContent,String phonenumber){
        this.profileImage=profileImage;
        this.roomName=roomName;
        this.location=location;
        this.time=time;
        this.numOfmember=numOfmember;
        this.fee=fee;
        this.roomContent=roomContent;
        this.phonenumber = phonenumber;
    }

    @NonNull
    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(@NonNull int profileImage) {
        this.profileImage = profileImage;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(@NonNull String roomName) {
        this.roomName = roomName;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }

    @NonNull
    public int getNumOfmember() {
        return numOfmember;
    }

    public void setNumOfmember(@NonNull int numOfmember) {
        this.numOfmember = numOfmember;
    }

    @NonNull
    public int getFee() {
        return fee;
    }

    public void setFee(@NonNull int fee) {
        this.fee = fee;
    }

    @NonNull
    public String getRoomContent() {
        return roomContent;
    }

    public void setRoomContent(@NonNull String roomContent) {
        this.roomContent = roomContent;
    }
    @NonNull
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(@NonNull String phonenumber) {
        this.phonenumber = phonenumber;
    }

}
