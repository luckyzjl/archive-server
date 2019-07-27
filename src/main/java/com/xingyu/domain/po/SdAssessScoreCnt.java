package com.xingyu.domain.po;

/**
 * created by zhujl @2019-07-25
 */
public class SdAssessScoreCnt extends SdAssessTimes{
    private Integer itemId;
    private String name;
    private String score;
    private Integer scoreCnt;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getScoreCnt() {
        return scoreCnt;
    }

    public void setScoreCnt(Integer scoreCnt) {
        this.scoreCnt = scoreCnt;
    }
}
