package com.depromeet.hanriver.hanrivermeetup.model.mypage;

public class ApplicantVO {

    String name;
    int applicantImg;

    public ApplicantVO(String name, int applicantImg){
        this.name = name;
        this.applicantImg = applicantImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApplicantImg() {
        return applicantImg;
    }

    public void setApplicantImg(int applicantImg) {
        this.applicantImg = applicantImg;
    }
}
