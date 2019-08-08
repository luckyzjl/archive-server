package com.xingyu.domain.vo;

import com.xingyu.domain.dto.SdIEPCatalogDto;
import com.xingyu.domain.dto.SdMonthRecordDto;
import com.xingyu.domain.dto.SdProgressDto;
import com.xingyu.domain.po.*;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * created by zhujl @2019-07-18
 */
public class BizParameter {

    private String archiveNo;

    //学生
    private String assessTimes;
    private String month;
    private Integer assessCatalog;
    private SdArchiveInfo sdArchiveInfo;
    private SdFamilyInfo sdFamilyInfo;
    private SdSelfInfo sdSelfInfo;
    private SdAssessTimes sdAssessTimes;
    private SdAssessScoreVo sdAssessScoreVo;
    private List<SdAbilityAnalyse> sdAbilityAnalyseList;
    private List<SdIEPCatalogDto> sdIEPCatalogDtoList;
    private SdTrainningRecord sdTrainningRecord;
    private SdProgressDto sdProgressDto;
    private SdMonthRecordDto sdMonthRecordDto;
    private SdYearSummary sdYearSummary;
    private SdEduTrack sdEduTrack;

    //班级
    List<String> sdArchiveNos;
    private ClassArchiveInfo classArchiveInfo;

    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo;
    }

    public String getAssessTimes() {
        return assessTimes;
    }

    public void setAssessTimes(String assessTimes) {
        this.assessTimes = assessTimes;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public SdArchiveInfo getSdArchiveInfo() {
        return sdArchiveInfo;
    }

    public void setSdArchiveInfo(SdArchiveInfo sdArchiveInfo) {
        this.sdArchiveInfo = sdArchiveInfo;
    }

    public Integer getAssessCatalog() {
        return assessCatalog;
    }

    public void setAssessCatalog(Integer assessCatalog) {
        this.assessCatalog = assessCatalog;
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

    public SdAssessTimes getSdAssessTimes() {
        return sdAssessTimes;
    }

    public void setSdAssessTimes(SdAssessTimes sdAssessTimes) {
        this.sdAssessTimes = sdAssessTimes;
    }

    public SdAssessScoreVo getSdAssessScoreVo() {
        return sdAssessScoreVo;
    }

    public void setSdAssessScoreVo(SdAssessScoreVo sdAssessScoreVo) {
        this.sdAssessScoreVo = sdAssessScoreVo;
    }

    public List<SdAbilityAnalyse> getSdAbilityAnalyseList() {
        return sdAbilityAnalyseList;
    }

    public void setSdAbilityAnalyseList(List<SdAbilityAnalyse> sdAbilityAnalyseList) {
        this.sdAbilityAnalyseList = sdAbilityAnalyseList;
    }

    public List<SdIEPCatalogDto> getSdIEPCatalogDtoList() {
        return sdIEPCatalogDtoList;
    }

    public void setSdIEPCatalogDtoList(List<SdIEPCatalogDto> sdIEPCatalogDtoList) {
        this.sdIEPCatalogDtoList = sdIEPCatalogDtoList;
    }

    public SdTrainningRecord getSdTrainningRecord() {
        return sdTrainningRecord;
    }

    public void setSdTrainningRecord(SdTrainningRecord sdTrainningRecord) {
        this.sdTrainningRecord = sdTrainningRecord;
    }

    public SdProgressDto getSdProgressDto() {
        return sdProgressDto;
    }

    public void setSdProgressDto(SdProgressDto sdProgressDto) {
        this.sdProgressDto = sdProgressDto;
    }

    public SdMonthRecordDto getSdMonthRecordDto() {
        return sdMonthRecordDto;
    }

    public void setSdMonthRecordDto(SdMonthRecordDto sdMonthRecordDto) {
        this.sdMonthRecordDto = sdMonthRecordDto;
    }

    public SdYearSummary getSdYearSummary() {
        return sdYearSummary;
    }

    public void setSdYearSummary(SdYearSummary sdYearSummary) {
        this.sdYearSummary = sdYearSummary;
    }

    public SdEduTrack getSdEduTrack() {
        return sdEduTrack;
    }

    public void setSdEduTrack(SdEduTrack sdEduTrack) {
        this.sdEduTrack = sdEduTrack;
    }


    public List<String> getSdArchiveNos() {
        return sdArchiveNos;
    }

    public void setSdArchiveNos(List<String> sdArchiveNos) {
        this.sdArchiveNos = sdArchiveNos;
    }

    public ClassArchiveInfo getClassArchiveInfo() {
        return classArchiveInfo;
    }

    public void setClassArchiveInfo(ClassArchiveInfo classArchiveInfo) {
        this.classArchiveInfo = classArchiveInfo;
    }
}
