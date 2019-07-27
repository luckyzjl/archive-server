package com.xingyu.mapper;


import com.xingyu.domain.po.SdAssessScore;
import com.xingyu.domain.po.SdAssessScoreCnt;
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
}
