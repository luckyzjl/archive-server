package com.xingyu.service;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.SdAssessCatalogDto;
import com.xingyu.domain.dto.SdAssessItemDto;
import com.xingyu.domain.dto.TeacherDto;
import com.xingyu.domain.po.SdAssessItem;

import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-21
 */
public interface CustomService {

    List<TeacherDto> getTeacher() throws BizException;

    List<SdAssessCatalogDto> getSdAssessCatalogList() throws BizException;

    String getSdArchiveNo() throws BizException;

    String getClassArchiveNo() throws BizException;

}
