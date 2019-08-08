package com.xingyu.service.impl;

import com.xingyu.base.BizException;

import com.xingyu.domain.dto.SdAssessCatalogDto;
import com.xingyu.domain.dto.SdAssessItemDto;
import com.xingyu.domain.dto.TeacherDto;
import com.xingyu.domain.po.*;
import com.xingyu.mapper.SdAssessItemMapper;
import com.xingyu.mapper.SysSeqMapper;
import com.xingyu.mapper.TeacherMapper;
import com.xingyu.service.CustomService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * created by zhujl @2019-07-21
 */

@Service
public class CustomServiceImpl implements CustomService {

    @Autowired
    SysSeqMapper sysSeqMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SdAssessItemMapper sdAssessItemMapper;

    @Override
    public List<TeacherDto> getTeacher() throws BizException{
        List<Teacher> teacherList = teacherMapper.selectByExample(new TeacherExample());
        List<TeacherDto> resultList = new ArrayList<>();
        for (Teacher teacher:teacherList){
            if (!TextUtils.isBlank(teacher.getName())) {
                TeacherDto teacherDto = new TeacherDto();
                teacherDto.setName(teacher.getName());
                resultList.add(teacherDto);
            }
        }
        return resultList;
    }

    @Override
    public List<SdAssessCatalogDto> getSdAssessCatalogList() throws BizException{
        List<SdAssessCatalogDto> assessCatalogDtoList = new ArrayList<>();
        SdAssessItemExample example = new SdAssessItemExample();
        example.setOrderByClause("item_id asc");
        List<SdAssessItem> assessItemList = sdAssessItemMapper.selectByExample(example);
        Map<Integer,List<SdAssessItemDto>> assessItemGBFirstMap = new HashMap<>();
        Map<Integer,SdAssessItem> firstCatalogMap = new HashMap<>();
        Map<Integer,SdAssessItem> secondCatalogMap = new HashMap<>();
        Map<Integer,SdAssessItem> thirdCatalogMap = new HashMap<>();
        for(SdAssessItem assessItem:assessItemList) {
            if (1 == assessItem.getCatalogGrade()) {
                firstCatalogMap.put(assessItem.getItemId(), assessItem);
                SdAssessCatalogDto assessCatalogDto = new SdAssessCatalogDto();
                assessCatalogDto.setCatalogFirstId(assessItem.getItemId());
                assessCatalogDto.setCatalogFirstName(assessItem.getItemName());
                assessCatalogDto.setRemark(assessItem.getRemark());
                assessCatalogDtoList.add(assessCatalogDto);

            }
            if (2 == assessItem.getCatalogGrade()) {
                secondCatalogMap.put(assessItem.getItemId(), assessItem);
            }
            if (3 == assessItem.getCatalogGrade()) {
                thirdCatalogMap.put(assessItem.getItemId(), assessItem);
            }
            if (4 == assessItem.getCatalogGrade()) {
                SdAssessItemDto assessItemDto = new SdAssessItemDto();
                assessItemDto.setItemId(assessItem.getItemId());
                assessItemDto.setItemName(assessItem.getItemName());
                if (null != assessItem.getCatalogFirst() && null != firstCatalogMap.get(assessItem.getCatalogFirst())) {
                    assessItemDto.setCatalogFirstId(firstCatalogMap.get(assessItem.getCatalogFirst()).getItemId());
                    assessItemDto.setCatalogFirstName(firstCatalogMap.get(assessItem.getCatalogFirst()).getItemName());
                }
                if (null != assessItem.getCatalogSecond() && null != secondCatalogMap.get(assessItem.getCatalogSecond())) {
                    assessItemDto.setCatalogSecondId(secondCatalogMap.get(assessItem.getCatalogSecond()).getItemId());
                    assessItemDto.setCatalogSecondName(secondCatalogMap.get(assessItem.getCatalogSecond()).getItemName());
                }
                if (null != assessItem.getCatalogThird() && null != thirdCatalogMap.get(assessItem.getCatalogThird())) {
                    assessItemDto.setCatalogThirdId(thirdCatalogMap.get(assessItem.getCatalogThird()).getItemId());
                    assessItemDto.setCatalogThirdName(thirdCatalogMap.get(assessItem.getCatalogThird()).getItemName());
                }
                assessItemDto.setItemNo(assessItem.getItemNo());
                assessItemDto.setMonthAge(assessItem.getMonthAge());
                assessItemDto.setRemark(assessItem.getRemark());

                if (assessItemGBFirstMap.containsKey(assessItem.getCatalogFirst())){
                    assessItemGBFirstMap.get(assessItem.getCatalogFirst()).add(assessItemDto);
                }else {
                    List<SdAssessItemDto> assessItemDtoList = new ArrayList<>();
                    assessItemDtoList.add(assessItemDto);
                    assessItemGBFirstMap.put(assessItem.getCatalogFirst(),assessItemDtoList);
                }
            }
        }
        for (SdAssessCatalogDto assessCatalogDto:assessCatalogDtoList){
            assessCatalogDto.setAssessItemList(assessItemGBFirstMap.get(assessCatalogDto.getCatalogFirstId()));
        }
        return assessCatalogDtoList;
    }

    @Override
    public String getSdArchiveNo() throws BizException{
        String strDate = DateFormatUtils.format(new Date(),"yyyyMM");
        SysSeq archiveNoSeq = sysSeqMapper.selectByPrimaryKey("sdArchiveNo");
        String sdArchiveNo = strDate + "001";
        SysSeq sysSeq = new SysSeq();
        sysSeq.setSeqName("sdArchiveNo");
        if (null == archiveNoSeq){
            sdArchiveNo = strDate + "001";
            sysSeq.setSeq(sdArchiveNo);
            sysSeqMapper.insertSelective(sysSeq);
        }else if (!archiveNoSeq.getSeq().startsWith(strDate)){
            sdArchiveNo = strDate + "001";
            sysSeq.setSeq(sdArchiveNo);
            sysSeqMapper.updateByPrimaryKey(sysSeq);
        }else if (archiveNoSeq.getSeq().startsWith(strDate)){
            sdArchiveNo = String.valueOf(Integer.parseInt(archiveNoSeq.getSeq()) + 1);
            sysSeq.setSeq(sdArchiveNo);
            sysSeqMapper.updateByPrimaryKey(sysSeq);
        }

        return sdArchiveNo;
    }

    @Override
    public String getClassArchiveNo() throws BizException{
        String strDate = DateFormatUtils.format(new Date(),"yyyy");
        SysSeq archiveNoSeq = sysSeqMapper.selectByPrimaryKey("classArchiveNo");
        String classArchiveNo = strDate + "001";
        SysSeq sysSeq = new SysSeq();
        sysSeq.setSeqName("classArchiveNo");
        if (null == archiveNoSeq){
            classArchiveNo = strDate + "001";
            sysSeq.setSeq(classArchiveNo);
            sysSeqMapper.insertSelective(sysSeq);
        }else if (!archiveNoSeq.getSeq().startsWith(strDate)){
            classArchiveNo = strDate + "001";
            sysSeq.setSeq(classArchiveNo);
            sysSeqMapper.updateByPrimaryKey(sysSeq);
        }else if (archiveNoSeq.getSeq().startsWith(strDate)){
            classArchiveNo = String.valueOf(Integer.parseInt(archiveNoSeq.getSeq()) + 1);
            sysSeq.setSeq(classArchiveNo);
            sysSeqMapper.updateByPrimaryKey(sysSeq);
        }

        return classArchiveNo;
    }

}
