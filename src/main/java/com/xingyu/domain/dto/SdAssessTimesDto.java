package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdAssessTimes;

import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-25
 */
public class SdAssessTimesDto extends SdAssessTimes {
    private List<SdAssessScoreCntDto> scoreCntList;

    public List<SdAssessScoreCntDto> getScoreCntList() {
        return scoreCntList;
    }

    public void setScoreCntList(List<SdAssessScoreCntDto> scoreCntList) {
        this.scoreCntList = scoreCntList;
    }

    public static class SdAssessScoreCntDto {
        private Integer itemId;
        private String itemName;
        private Map<String,Integer> scoreCnt;

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

        public Map<String, Integer> getScoreCnt() {
            return scoreCnt;
        }

        public void setScoreCnt(Map<String, Integer> scoreCnt) {
            this.scoreCnt = scoreCnt;
        }
    }
}
