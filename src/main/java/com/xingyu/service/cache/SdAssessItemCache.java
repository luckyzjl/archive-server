package com.xingyu.service.cache;

import com.xingyu.domain.dto.SdAssessCatalogDto;
import com.xingyu.domain.dto.SdAssessItemDto;
import com.xingyu.domain.po.SdAssessItem;
import com.xingyu.domain.po.SdAssessItemExample;
import com.xingyu.mapper.SdAssessItemMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-31
 */
@Scope("singleton")
@Service
public class SdAssessItemCache {
    private static Log logger = LogFactory.getLog(SdAssessItemCache.class);

    @Autowired
    SdAssessItemMapper assessItemMapper;

    private List<SdAssessCatalogDto> assessCatalogDtoList = new ArrayList<>();
    private Map<Integer, SdAssessItemDto> assessItemDtoMap = new HashMap<>();
    private List<SdAssessItem> catalogFirstList = new ArrayList<>();
    private List<SdAssessItem> catalogSecondInLastCatalogFirstList = new ArrayList<>();

    @PostConstruct
    public void init() {
        SdAssessItemExample example = new SdAssessItemExample();
        example.setOrderByClause("item_id asc");
        List<SdAssessItem> assessItemList = assessItemMapper.selectByExample(example);
        assessCatalogDtoList.clear();
        assessItemDtoMap.clear();
        catalogFirstList.clear();
        catalogSecondInLastCatalogFirstList.clear();
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
//                assessCatalogDto.setAssessItemList(new ArrayList<>());
                assessCatalogDtoList.add(assessCatalogDto);
                catalogFirstList.add(assessItem);
            }
            if (2 == assessItem.getCatalogGrade()) {
                secondCatalogMap.put(assessItem.getItemId(), assessItem);
                if (8 == assessItem.getCatalogFirst()){
                    catalogSecondInLastCatalogFirstList.add(assessItem);
                }
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
                assessItemDtoMap.put(assessItem.getItemId(),assessItemDto);

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
    }

    public void refresh() {
        logger.debug("reload student assess item");
        init();
    }

    public List<SdAssessCatalogDto> getAssessCatalogDtoList() {
        return assessCatalogDtoList;
    }

    public void setAssessCatalogDtoList(List<SdAssessCatalogDto> assessCatalogDtoList) {
        this.assessCatalogDtoList = assessCatalogDtoList;
    }

    public Map<Integer, SdAssessItemDto> getAssessItemDtoMap() {
        return assessItemDtoMap;
    }

    public void setAssessItemDtoMap(Map<Integer, SdAssessItemDto> assessItemDtoMap) {
        this.assessItemDtoMap = assessItemDtoMap;
    }

    public List<SdAssessItem> getCatalogFirstList() {
        return catalogFirstList;
    }

    public void setCatalogFirstList(List<SdAssessItem> catalogFirstList) {
        this.catalogFirstList = catalogFirstList;
    }

    public List<SdAssessItem> getCatalogSecondInLastCatalogFirstList() {
        return catalogSecondInLastCatalogFirstList;
    }

    public void setCatalogSecondInLastCatalogFirstList(List<SdAssessItem> catalogSecondInLastCatalogFirstList) {
        this.catalogSecondInLastCatalogFirstList = catalogSecondInLastCatalogFirstList;
    }
}
