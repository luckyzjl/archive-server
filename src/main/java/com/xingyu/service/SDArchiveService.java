package com.xingyu.service;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.SdArchiveDto;
import com.xingyu.domain.dto.SdAssessItemDto;
import com.xingyu.domain.po.SdFamilyInfo;
import com.xingyu.domain.po.SdSelfInfo;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.domain.po.SdArchiveInfo;
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
    void newStudentArchive(String sdArchiveNo, SdArchiveInfo sdArchiveInfo, SdFamilyInfo sdFamilyInfo) throws BizException;
    SdArchiveDto getStudentArchiveInfo(String archiveNo) throws BizException;
    List<SdArchiveInfo> getStudentArchiveList(SdArchiveInfo sdArchiveVo) throws BizException;
    //获取分页信息
    ApiResponse.PageInfo getSDArchivePageInfo(ReqParameter reqParameter, SdArchiveInfo sdArchiveVo) throws BizException;
    void updateStudentArchiveInfo(String sdArchiveNo,SdArchiveInfo sdArchiveInfo,SdFamilyInfo sdFamilyInfo) throws BizException;

    /**
     * 学生入学登记：家庭信息/自身信息
     */
    void newSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException;
    SdArchiveDto getSdRegistrationInfo(String sdArchiveNo) throws BizException;
    void updateSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException;

    /**
     * 学生评估记分：评估记分，统计
     */
    void insertSdAssessScore(SdAssessScoreVo sdAssessScoreVo) throws BizException;
    void updateSdAssessScore(Integer firstCatalog, SdAssessScoreVo sdAssessScoreVo) throws BizException;
    SdArchiveDto sdAssessScoreStat(String sdArchiveNo) throws BizException;

}
