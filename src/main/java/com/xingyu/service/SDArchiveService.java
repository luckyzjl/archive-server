package com.xingyu.service;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.*;
import com.xingyu.domain.po.*;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.domain.vo.SdAssessScoreVo;
import com.xingyu.wrapper.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-17
 */
public interface SDArchiveService {
    /**
     * 学生建档，查询，修改
     */
    void newStudentArchive(String sdArchiveNo,SdArchiveInfo sdArchiveInfo, SdFamilyInfo sdFamilyInfo) throws BizException;
    SdArchiveDto getStudentArchiveInfo(String archiveNo) throws BizException;
    List<SdArchiveInfo> getStudentArchiveList(Integer pageNo,Integer pageSize,SdArchiveInfo sdArchiveVo) throws BizException;
    //获取分页信息
    ApiResponse.PageInfo getSDArchivePageInfo(ReqParameter reqParameter, SdArchiveInfo sdArchiveVo) throws BizException;
    void updateStudentArchiveInfo(String sdArchiveNo,SdArchiveInfo sdArchiveInfo,SdFamilyInfo sdFamilyInfo) throws BizException;

    /**
     * 学生入学登记：家庭信息/自身信息
     */
//    void newSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException;
    SdArchiveDto getSdRegistrationInfo(String sdArchiveNo) throws BizException;
    void submitSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException;

    /**
     * 学生评估记分：评估记分，统计
     */
    void submitSdAssessTimes(SdAssessTimes sdAssessTimes) throws BizException;
    SdArchiveDto getSdAssessTimesList(String sdArchiveNo) throws BizException;
    void submitSdAssessScore(SdAssessScoreVo sdAssessScoreVo) throws BizException;
    List<SdAssessCatalogDto> getSdAssessScoreInfo(String sdArchiveNo, String assessTimes) throws BizException;
    SdArchiveDto sdAssessScoreResult(String sdArchiveNo) throws BizException;


    /**
     * 评估结果分析
     */
    SdArchiveDto getSdAnalyseInfo(String sdArchiveNo,String assessTimes) throws BizException;
    void submitSdAnalyseInfo(SdAssessTimes sdAssessTimes,List<SdAbilityAnalyse> sdAbilityAnalyseList) throws BizException;


    /**
     * 04个别化教育计划（IEP）
     */
    SdArchiveDto getIEPInfo(String sdArchiveNo,String assessTimes) throws BizException;
    void submitIEPInfo(SdAssessTimes sdAssessTimes,List<SdIEPCatalogDto> iepCatalogDtoList) throws BizException;

    /**
     * 05个别化语言训练 月教育教案记录
     */
    SdMonthRecordDto getSdMonthRecord(String sdArchiveNo,String month) throws BizException;
    void submitSdMonthRecord(String sdArchiveNo,SdMonthRecordDto monthRecordDto) throws BizException;
    SdArchiveDto getSdMonthRecordList(String sdArchiveNo) throws BizException;

    /**
     * 06个别训练记录
     */
//    void newSdTrainningRecord(SdAssessTimes sdAssessTimes,SdTrainningRecord sdTrainningRecord) throws BizException;
    SdArchiveDto getSdTrainningRecord(String sdArchiveNo,String assessTimes) throws BizException;
    void submitSdTrainningRecord(SdAssessTimes sdAssessTimes,SdTrainningRecord sdTrainningRecord) throws BizException;


    /**
     * 07训练进度报告
     */
//    void newSdProgressReport(SdAssessTimes sdAssessTimes,SdProgressReport sdProgressReport) throws BizException;
    SdArchiveDto getSdProgressReport(String sdArchiveNo,String assessTimes) throws BizException;
    void submitSdProgressReport(SdAssessTimes sdAssessTimes, SdProgressDto sdProgressDto) throws BizException;

    /**
     * 年度评估与总结
     */
    SdArchiveDto getSdYearSummary(String sdArchiveNo) throws BizException;
    void submitSdYearSummary(String sdArchiveNo,SdYearSummary yearSummary) throws BizException;

    /**
     * 后续教育跟踪
     */
    SdArchiveDto getSdEduTrack(String sdArchiveNo) throws BizException;
    void submitSdEduTrack(String sdArchiveNo,SdEduTrack eduTrack) throws BizException;
}
