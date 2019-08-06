package com.xingyu.domain.vo;

import com.xingyu.domain.po.SdAssessScore;
import com.xingyu.domain.po.SdAssessTimes;

import java.util.List;

/**
 * created by zhujl @2019-07-23
 */
public class SdAssessScoreVo extends SdAssessTimes {
    private String catalogFirst;
    private String catalogSecond;
    private List<SdAssessScore> assessScoreList;

    public String getCatalogFirst() {
        return catalogFirst;
    }

    public void setCatalogFirst(String catalogFirst) {
        this.catalogFirst = catalogFirst;
    }

    public String getCatalogSecond() {
        return catalogSecond;
    }

    public void setCatalogSecond(String catalogSecond) {
        this.catalogSecond = catalogSecond;
    }

    public List<SdAssessScore> getAssessScoreList() {
        return assessScoreList;
    }

    public void setAssessScoreList(List<SdAssessScore> assessScoreList) {
        this.assessScoreList = assessScoreList;
    }
}
