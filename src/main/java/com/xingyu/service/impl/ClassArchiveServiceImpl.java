package com.xingyu.service.impl;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.ClassArchiveDto;
import com.xingyu.domain.po.*;
import com.xingyu.mapper.ClassArchiveInfoMapper;
import com.xingyu.mapper.ClassMemberInfoMapper;
import com.xingyu.mapper.CustomSQLMapper;
import com.xingyu.service.ClassArchiveService;
import com.xingyu.service.CustomService;
import com.xingyu.service.SDArchiveService;
import com.xingyu.wrapper.ApiResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * created by zhujl @2019-07-17
 */
@Service
public class ClassArchiveServiceImpl implements ClassArchiveService {
    private static Log logger = LogFactory.getLog(ClassArchiveService.class);

    @Autowired
    CustomService customService;
    @Autowired
    ClassArchiveInfoMapper classArchiveInfoMapper;
    @Autowired
    ClassMemberInfoMapper classMemberInfoMapper;
    @Autowired
    CustomSQLMapper customSQLMapper;
    @Autowired
    SDArchiveService sdArchiveService;

    /**
     * 班级建档，查询，修改
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newClassArchiveInfo(ClassArchiveInfo classArchiveInfo,List<String> sdArchiveNoList) throws BizException{
        if (null == classArchiveInfo || TextUtils.isBlank(classArchiveInfo.getClassName())){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：班级名称");
        }
        String archiveNo = customService.getClassArchiveNo();
        classArchiveInfo.setArchiveNo(archiveNo);
        classArchiveInfoMapper.insertSelective(classArchiveInfo);
        if (null != sdArchiveNoList && sdArchiveNoList.size() > 0){
            this.sdJoinClass(classArchiveInfo.getArchiveNo(),sdArchiveNoList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClassArchiveInfo(ClassArchiveInfo classArchiveInfo,List<String> sdArchiveNoList) throws BizException{
        if (null == classArchiveInfo || null == classArchiveInfo.getArchiveNo()
                || TextUtils.isBlank(classArchiveInfo.getClassName())){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数");
        }
        classArchiveInfoMapper.updateByPrimaryKeySelective(classArchiveInfo);
        if (null != sdArchiveNoList && sdArchiveNoList.size() > 0){
            this.sdJoinClass(classArchiveInfo.getArchiveNo(),sdArchiveNoList);
        }
    }


    private void sdJoinClass(String classArchiveNo, List<String> sdArchiveNoList) throws BizException{
        if (null == classArchiveNo || null == sdArchiveNoList || sdArchiveNoList.size() == 0){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：classArchiveNo/sdArchiveNoList");
        }
        ClassMemberInfoExample delExample = new ClassMemberInfoExample();
        ClassMemberInfoExample.Criteria delCri = delExample.createCriteria();
        delCri.andSdNoIn(sdArchiveNoList);
        classMemberInfoMapper.deleteByExample(delExample);

        List<ClassMemberInfo> newClassMemberList = new ArrayList<>();
        for (String sdArchiveNo:sdArchiveNoList){
            ClassMemberInfo memberInfo = new ClassMemberInfo();
            memberInfo.setClassNo(classArchiveNo);
            memberInfo.setSdNo(sdArchiveNo);
            memberInfo.setJoinTime(new Date());
            newClassMemberList.add(memberInfo);
        }
        customSQLMapper.listInsertClassMember(newClassMemberList);
    }

    @Override
    public ClassArchiveDto getClassArchiveInfo(String classArchiveNo) throws BizException{
        if (null == classArchiveNo){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：班级ID");
        }
        ClassArchiveInfo classArchiveInfo = classArchiveInfoMapper.selectByPrimaryKey(classArchiveNo);
        if (null == classArchiveInfo){
            throw new BizException(BizException.CODE_PARAM_ERROR,"参数错误，班级不存在");
        }
        ClassArchiveDto classArchiveDto = new ClassArchiveDto();
        BeanUtils.copyProperties(classArchiveInfo,classArchiveDto);

        //获取班级中学生信息列表
        ClassMemberInfoExample memberInfoExample = new ClassMemberInfoExample();
        ClassMemberInfoExample.Criteria memberCri = memberInfoExample.createCriteria();
        memberCri.andClassNoEqualTo(classArchiveNo);
        List<ClassMemberInfo> memberInfoList = classMemberInfoMapper.selectByExample(memberInfoExample);
        if (null == memberInfoList || memberInfoList.size() == 0){
            classArchiveDto.setSdArchiveInfoList(new ArrayList<>());
            return classArchiveDto;
        }
        List<String> sdNoList = new ArrayList<>();
        for (ClassMemberInfo memberInfo:memberInfoList){
            sdNoList.add(memberInfo.getSdNo());
        }
        classArchiveDto.setSdArchiveInfoList(sdArchiveService.getStudentArchiveList(sdNoList));
        return classArchiveDto;
    }

    private ClassArchiveInfoExample _getClassArchiveQueryExample(ClassArchiveInfo classArchiveVo) throws BizException{
        try {
            ClassArchiveInfoExample example = new ClassArchiveInfoExample();
            ClassArchiveInfoExample.Criteria criteria = example.createCriteria();
            if (null != classArchiveVo) {
                if (!TextUtils.isBlank(classArchiveVo.getClassName())) {
                    criteria.andClassNameEqualTo(classArchiveVo.getClassName());
                }
                if (null != classArchiveVo.getStartTime()) {
                    Date date = DateUtils.parseDate(DateFormatUtils.format(classArchiveVo.getStartTime(), "yyyy"), "yyyy");
                    criteria.andStartTimeBetween(date, DateUtils.addYears(date, 1));
                }
                if (!TextUtils.isBlank(classArchiveVo.getChargeTeacher())){
                    criteria.andChargeTeacherEqualTo(classArchiveVo.getChargeTeacher());
                }
            }
            return example;
        }catch (Exception ex){
            logger.error("班级档案查询出错",ex);
            throw new BizException(BizException.CODE_SYSTEM_ERROR,BizException.MSG_SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponse.PageInfo getClassArchivePageInfo(Integer pageNo, Integer pageSize, ClassArchiveInfo classArchiveVo) throws BizException{
        int recordAmount = classArchiveInfoMapper.countByExample(_getClassArchiveQueryExample(classArchiveVo));
        if (null == pageNo) pageNo = 1;
        if (null == pageSize) pageSize = 10;
        ApiResponse.PageInfo pageInfo = new ApiResponse.PageInfo();
        pageInfo.setRecordAmount(recordAmount);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageAmount((recordAmount == 0 || recordAmount % pageSize == 0) ? recordAmount / pageSize : recordAmount / pageSize + 1);
        return pageInfo;
    }

    @Override
    public List<ClassArchiveDto> getClassArchiveList(Integer pageNo, Integer pageSize,ClassArchiveInfo classArchiveVo) throws BizException{
        List<ClassArchiveDto> classArchiveDtoList = new ArrayList<>();
        if (null == pageNo) pageNo = 1;
        if (null == pageSize) pageSize = 10;
        ClassArchiveInfoExample example = _getClassArchiveQueryExample(classArchiveVo);
        example.setOrderByClause("archive_no desc" + " limit " + (pageNo-1)*pageSize + "," + pageSize);
        List<ClassArchiveInfo> classArchiveInfoList = classArchiveInfoMapper.selectByExample(example);
        if (null == classArchiveDtoList || classArchiveInfoList.size() == 0){
            return classArchiveDtoList;
        }
        List<String> classNos = new ArrayList<>();
        Map<String,ClassArchiveDto> classArchiveDtoMap = new HashMap<>();
        for (ClassArchiveInfo classArchiveInfo:classArchiveInfoList){
            ClassArchiveDto classArchiveDto = new ClassArchiveDto();
            BeanUtils.copyProperties(classArchiveInfo,classArchiveDto);
            classArchiveDtoList.add(classArchiveDto);
            classArchiveDtoMap.put(classArchiveDto.getArchiveNo(),classArchiveDto);
            classNos.add(classArchiveInfo.getArchiveNo());
        }

        Map<String,List<SdArchiveInfo>> memberListByClassMap = this.getClassMemberList(classNos);
        for (String classNo:memberListByClassMap.keySet()){
            Integer sdCount = 0;
            Integer recureSdCount = 0;
            for (SdArchiveInfo sdArchiveInfo:memberListByClassMap.get(classNo)){
                sdCount += 1;
                if (null != sdArchiveInfo.getStatus() && 2 == sdArchiveInfo.getStatus()){
                    recureSdCount += 1;
                }
            }
            classArchiveDtoMap.get(classNo).setSdCount(sdCount);
            classArchiveDtoMap.get(classNo).setRecureSdCount(recureSdCount);
            classArchiveDtoMap.get(classNo).setSdArchiveInfoList(memberListByClassMap.get(classNo));
        }
        return classArchiveDtoList;
    }

    @Override
    public Map<String,List<SdArchiveInfo>> getClassMemberList(List<String> classNos) throws BizException{
        Map<String,List<SdArchiveInfo>> memberListByClassMap = new HashMap<>();
        if (null == classNos || classNos.size() == 0){
            return memberListByClassMap;
        }
        ClassMemberInfoExample memberInfoExample = new ClassMemberInfoExample();
        ClassMemberInfoExample.Criteria memberCri = memberInfoExample.createCriteria();
        memberCri.andClassNoIn(classNos);
        List<ClassMemberInfo> memberInfoList = classMemberInfoMapper.selectByExample(memberInfoExample);
        if (null == memberInfoList || memberInfoList.size() == 0){
            return memberListByClassMap;
        }
        List<String> sdNoList = new ArrayList<>();
        for (ClassMemberInfo memberInfo:memberInfoList){
            sdNoList.add(memberInfo.getSdNo());
        }
        Map<String,SdArchiveInfo> sdArchiveInfoMap = new HashMap<>();
        List<SdArchiveInfo> sdArchiveInfoList = sdArchiveService.getStudentArchiveList(sdNoList);
        for (SdArchiveInfo sdArchiveInfo:sdArchiveInfoList){
            sdArchiveInfoMap.put(sdArchiveInfo.getArchiveNo(),sdArchiveInfo);
        }

        for (ClassMemberInfo memberInfo:memberInfoList){
            if (sdArchiveInfoMap.containsKey(memberInfo.getSdNo())) {
                if (memberListByClassMap.containsKey(memberInfo.getClassNo())) {
                    memberListByClassMap.get(memberInfo.getClassNo()).add(sdArchiveInfoMap.get(memberInfo.getSdNo()));
                } else {
                    List<SdArchiveInfo> sdArchiveInfos = new ArrayList<>();
                    sdArchiveInfos.add(sdArchiveInfoMap.get(memberInfo.getSdNo()));
                    memberListByClassMap.put(memberInfo.getClassNo(),sdArchiveInfos);
                }
            }
        }
        return memberListByClassMap;
    }

    @Override
    public ClassArchiveInfo getSdClassInfo(String sdArchiveNo) throws BizException{
        ClassArchiveInfo sdClassInfo = new ClassArchiveInfo();
        if (TextUtils.isBlank(sdArchiveNo)){
            return sdClassInfo;
        }
        ClassMemberInfoExample memberInfoExample = new ClassMemberInfoExample();
        ClassMemberInfoExample.Criteria memberCri = memberInfoExample.createCriteria();
        memberCri.andSdNoEqualTo(sdArchiveNo);
        memberInfoExample.setOrderByClause("join_time desc");
        List<ClassMemberInfo> memberInfoList = classMemberInfoMapper.selectByExample(memberInfoExample);
        if (null == memberInfoList || memberInfoList.size() == 0){
            return sdClassInfo;
        }
        String classNo = memberInfoList.get(0).getClassNo();
        return classArchiveInfoMapper.selectByPrimaryKey(classNo);
    }
}
