package com.xingyu.mapper;


import com.xingyu.domain.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhujl on 2018/01/16.
 */
public interface CustomSQLMapper {

    /**
     * 批量插入学生评估得分纪录
     */
    int listInsertAssessScore(@Param("assessScoreList") List<SdAssessScore> assessScoreList);

    List<SdAssessScoreCnt> statSdAssessScoreAhead(@Param("archiveNo") String archiveNo);

    List<SdAssessScoreCnt> statSdAssessScoreLast(@Param("archiveNo") String archiveNo);

    /**
     * 批量插入学生评估结果分析表
     */
    int listInsertAbilityAnalyse(@Param("abilityAnalyseList") List<SdAbilityAnalyse> abilityAnalyseList);

    /**
     * 批量插入学生IEP长期目标
     */
    int listInsertIEPCatalog(@Param("iepCatalogList") List<SdIEPCatalog> iepCatalogList);

    /**
     * 批量插入学生IEP短期目标
     */
    int listInsertIEPItem(@Param("iepItemList") List<SdIEPItem> iepItemList);

    /**
     * 批量插入学生课时训练内容记录
     */
    int listInsertLessonRecord(@Param("lessonRecordList") List<SdLessonRecord> lessonRecordList);
}
