package com.xingyu.service.impl;

import com.xingyu.base.BizException;

import com.xingyu.domain.dto.SdAssessItemDto;
import com.xingyu.domain.dto.TeacherDto;
import com.xingyu.domain.po.SdAssessItem;
import com.xingyu.domain.po.SdAssessItemExample;
import com.xingyu.domain.po.Teacher;
import com.xingyu.domain.po.TeacherExample;
import com.xingyu.mapper.SdAssessItemMapper;
import com.xingyu.mapper.TeacherMapper;
import com.xingyu.service.CustomService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-21
 */

@Service
public class CustomServiceImpl implements CustomService {

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

    /**
     * 学生评估记分及统计
     */
    @Override
    public Map<String, SdAssessItemDto> getSdAssessItem() throws BizException{
        SdAssessItemExample example = new SdAssessItemExample();
        example.setOrderByClause("item_id asc");
        List<SdAssessItem> assessItemList = sdAssessItemMapper.selectByExample(example);
        Map<String, SdAssessItemDto> assessItemMap = new HashMap<>();
        Map<Integer,String> firstCatalogMap = new HashMap<>();
        Map<Integer,String> secondCatalogMap = new HashMap<>();
        Map<Integer,String> thirdCatalogMap = new HashMap<>();
        for(SdAssessItem assessItem:assessItemList){
            if (1 == assessItem.getCatalogGrade()){
                firstCatalogMap.put(assessItem.getItemId(),assessItem.getName());
                SdAssessItemDto sdAssessItemDto = new SdAssessItemDto();
                sdAssessItemDto.setRemark(assessItem.getRemark());
                sdAssessItemDto.setAssessItemList(new ArrayList<>());
                assessItemMap.put(assessItem.getName(),sdAssessItemDto);
            }
            if (2 == assessItem.getCatalogGrade()){
                secondCatalogMap.put(assessItem.getItemId(),assessItem.getName());
            }
            if (3 == assessItem.getCatalogGrade()){
                thirdCatalogMap.put(assessItem.getItemId(),assessItem.getName());
            }
            if (4 == assessItem.getCatalogGrade()){
                SdAssessItemDto.AssessItem itemDto = new SdAssessItemDto.AssessItem();
                itemDto.setItemId(assessItem.getItemId());
                itemDto.setItemNo(assessItem.getItemNo());
                itemDto.setName(assessItem.getName());
                itemDto.setMonthAge(assessItem.getMonthAge());
                itemDto.setSecondCatalogName(secondCatalogMap.get(assessItem.getCatalogSecond()));
                itemDto.setThirdCatalogName(thirdCatalogMap.get(assessItem.getCatalogThird()));
                assessItemMap.get(firstCatalogMap.get(assessItem.getCatalogFirst())).getAssessItemList().add(itemDto);
            }
        }
        return assessItemMap;
    }
}
