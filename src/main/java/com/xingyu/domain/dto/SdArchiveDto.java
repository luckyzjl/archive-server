package com.xingyu.domain.dto;

import com.xingyu.domain.po.*;

import java.util.Date;
import java.util.List;

/**
 * created by zhujl @2019-07-19
 */
public class SdArchiveDto extends SdAssessTimes {
    private String archiveNo;
    private String assessTimes;
    private String name;
    private String sex;
    private Date birthTime;
    private Date enrollTime;
    private String diagnoseResult;
    private String mobile;

    private SdArchiveInfo sdArchiveInfo;
    private SdFamilyInfo sdFamilyInfo;
    private SdSelfInfo sdSelfInfo;
    private List<SdAssessTimes> assessTimesList;            //期数评估列表
    private List<SdAssessTimesDto> assessTimesDtoList;      //按期数评估记分
    private List<SdAbilityAnalyse> abilityAnalyseList;      //评估结果分析
    private List<SdIEPCatalogDto> iepCatalogDtoList;              //IEP计划
    private List<SdMonthRecord> monthRecordList;                //月教案记录列表
    private SdTrainningRecord trainningRecord;                  //训练记录
    private SdProgressDto sdProgressDto;                          //学生学习进度报告
    private SdYearSummary yearSummary;                          //年度总结
    private SdEduTrack eduTrack;                                //教育跟踪

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

    public List<SdAbilityAnalyse> getAbilityAnalyseList() {
        return abilityAnalyseList;
    }

    public void setAbilityAnalyseList(List<SdAbilityAnalyse> abilityAnalyseList) {
        this.abilityAnalyseList = abilityAnalyseList;
    }

    public List<SdIEPCatalogDto> getIepCatalogDtoList() {
        return iepCatalogDtoList;
    }

    public void setIepCatalogDtoList(List<SdIEPCatalogDto> iepCatalogDtoList) {
        this.iepCatalogDtoList = iepCatalogDtoList;
    }

    public SdTrainningRecord getTrainningRecord() {
        return trainningRecord;
    }

    public void setTrainningRecord(SdTrainningRecord trainningRecord) {
        this.trainningRecord = trainningRecord;
    }

    public SdProgressDto getSdProgressDto() {
        return sdProgressDto;
    }

    public void setSdProgressDto(SdProgressDto sdProgressDto) {
        this.sdProgressDto = sdProgressDto;
    }

    public SdYearSummary getYearSummary() {
        return yearSummary;
    }

    public void setYearSummary(SdYearSummary yearSummary) {
        this.yearSummary = yearSummary;
    }

    public SdEduTrack getEduTrack() {
        return eduTrack;
    }

    public void setEduTrack(SdEduTrack eduTrack) {
        this.eduTrack = eduTrack;
    }

    public List<SdAssessTimes> getAssessTimesList() {
        return assessTimesList;
    }

    public void setAssessTimesList(List<SdAssessTimes> assessTimesList) {
        this.assessTimesList = assessTimesList;
    }

    public List<SdMonthRecord> getMonthRecordList() {
        return monthRecordList;
    }

    public void setMonthRecordList(List<SdMonthRecord> monthRecordList) {
        this.monthRecordList = monthRecordList;
    }
}
