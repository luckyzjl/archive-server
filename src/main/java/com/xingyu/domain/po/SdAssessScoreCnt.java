package com.xingyu.domain.po;

/**
 * created by zhujl @2019-07-25
 */
public class SdAssessScoreCnt extends SdAssessTimes{
    private Integer itemId;
    private String itemName;
    private String score;
    private Integer scoreCnt;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
