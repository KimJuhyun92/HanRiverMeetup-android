package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tab3VO {

    @SerializedName("join_detail")
    @Expose
    private JoinDetail joinDetail;
    @SerializedName("meeting_detail")
    @Expose
    private MeetingDetail meetingDetail;

    public JoinDetail getJoinDetail() {
        return joinDetail;
    }

    public void setJoinDetail(JoinDetail joinDetail) {
        this.joinDetail = joinDetail;
    }

    public MeetingDetail getMeetingDetail() {
        return meetingDetail;
    }

    public void setMeetingDetail(MeetingDetail meetingDetail) {
        this.meetingDetail = meetingDetail;
    }

}