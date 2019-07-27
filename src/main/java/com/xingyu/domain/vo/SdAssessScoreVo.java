package com.xingyu.domain.vo;

import com.xingyu.domain.po.SdAssessScore;
import com.xingyu.domain.po.SdAssessTimes;

import java.util.List;

/**
 * created by zhujl @2019-07-23
 */
public class SdAssessScoreVo extends SdAssessTimes {
    List<SdAssessScore> assessScoreList;

    public List<SdAssessScore> getAssessScoreList() {
        return assessScoreList;
    }

    public void setAssessScoreList(List<SdAssessScore> assessScoreList) {
        this.assessScoreList = assessScoreList;
    }
}
