package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdArchiveInfo;
import com.xingyu.domain.po.SdFamilyInfo;
import com.xingyu.domain.po.SdSelfInfo;

import java.util.Date;
import java.util.List;

/**
 * created by zhujl @2019-07-19
 */
public class SdArchiveDto {
    private String archiveNo;
    private String name;
    private String sex;
    private Date birthTime;
    private Date enrollTime;
    private String diagnoseResult;
    private String mobile;

    private SdArchiveInfo sdArchiveInfo;
    private SdFamilyInfo sdFamilyInfo;
    private SdSelfInfo sdSelfInfo;
    private List<SdAssessTimesDto> assessTimesDtoList;

    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Date birthTime) {
        this.birthTime = birthTime;
    }

    public Date getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }

    public String getDiagnoseResult() {
        return diagnoseResult;
    }

    public void setDiagnoseResult(String diagnoseResult) {
        this.diagnoseResult = diagnoseResult;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SdArchiveInfo getSdArchiveInfo() {
        return sdArchiveInfo;
    }

    public void setSdArchiveInfo(SdArchiveInfo sdArchiveInfo) {
        this.sdArchiveInfo = sdArchiveInfo;
    }

    public SdFamilyInfo getSdFamilyInfo() {
        return sdFamilyInfo;
    }

    public void setSdFamilyInfo(SdFamilyInfo sdFamilyInfo) {
        this.sdFamilyInfo = sdFamilyInfo;
    }

    public SdSelfInfo getSdSelfInfo() {
        return sdSelfInfo;
    }

    public void setSdSelfInfo(SdSelfInfo sdSelfInfo) {
        this.sdSelfInfo = sdSelfInfo;
    }

    public List<SdAssessTimesDto> getAssessTimesDtoList() {
        return assessTimesDtoList;
    }

    public void setAssessTimesDtoList(List<SdAssessTimesDto> assessTimesDtoList) {
        this.assessTimesDtoList = assessTimesDtoList;
    }
}
