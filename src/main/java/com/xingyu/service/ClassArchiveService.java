package com.xingyu.service;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.ClassArchiveDto;
import com.xingyu.domain.po.ClassArchiveInfo;
import com.xingyu.domain.po.SdArchiveInfo;
import com.xingyu.wrapper.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-17
 */
public interface ClassArchiveService {

    /**
     * 班级建档，查询，修改
     */
    void newClassArchiveInfo(ClassArchiveInfo classArchiveInfo,List<String> sdArchiveNoList) throws BizException;
    void updateClassArchiveInfo(ClassArchiveInfo classArchiveInfo, List<String> sdArchiveNoList) throws BizException;
    ClassArchiveDto getClassArchiveInfo(String classArchiveNo) throws BizException;
    ApiResponse.PageInfo getClassArchivePageInfo(Integer pageNo, Integer pageSize, ClassArchiveInfo classArchiveVo) throws BizException;
    List<ClassArchiveDto> getClassArchiveList(Integer pageNo, Integer pageSize,ClassArchiveInfo classArchiveVo) throws BizException;
    Map<String,List<SdArchiveInfo>> getClassMemberList(List<String> classNos) throws BizException;
    ClassArchiveInfo getSdClassInfo(String sdArchiveNo) throws BizException;
}
