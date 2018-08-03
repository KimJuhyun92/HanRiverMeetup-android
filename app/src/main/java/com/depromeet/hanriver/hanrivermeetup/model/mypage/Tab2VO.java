package com.depromeet.hanriver.hanrivermeetup.model.mypage;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tab2VO {

    @SerializedName("meeting_detail")
    @Expose
    private MeetingDetail meetingDetail;
    @SerializedName("application_seq")
    @Expose
    private Integer applicationSeq;

    public MeetingDetail getMeetingDetail() {
        return meetingDetail;
    }

    public void setMeetingDetail(MeetingDetail meetingDetail) {
        this.meetingDetail = meetingDetail;
    }

    public Integer getApplicationSeq() {
        return applicationSeq;
    }

    public void setApplicationSeq(Integer applicationSeq) {
        this.applicationSeq = applicationSeq;
    }

}
