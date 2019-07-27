package com.xingyu.service;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.SdAssessItemDto;
import com.xingyu.domain.dto.TeacherDto;

import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-21
 */
public interface CustomService {

    List<TeacherDto> getTeacher() throws BizException;

    Map<String, SdAssessItemDto> getSdAssessItem() throws BizException;
}
