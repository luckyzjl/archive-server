package com.xingyu.domain.dto;

import java.util.List;

/**
 * created by zhujl @2019-07-23
 */
public class SdAssessItemDto {

    private List<AssessItem> assessItemList;
    private String remark;

    public List<AssessItem> getAssessItemList() {
        return assessItemList;
    }

    public void setAssessItemList(List<AssessItem> assessItemList) {
        this.assessItemList = assessItemList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static class AssessItem{
        private Integer itemId;
        private String itemNo;
        private String name;
        private String secondCatalogName;
        private String thirdCatalogName;
        private String monthAge;
        private String remark;

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }

        public String getItemNo() {
            return itemNo;
        }

        public void setItemNo(String itemNo) {
            this.itemNo = itemNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSecondCatalogName() {
            return secondCatalogName;
        }

        public void setSecondCatalogName(String secondCatalogName) {
            this.secondCatalogName = secondCatalogName;
        }

        public String getThirdCatalogName() {
            return thirdCatalogName;
        }

        public void setThirdCatalogName(String thirdCatalogName) {
            this.thirdCatalogName = thirdCatalogName;
        }

        public String getMonthAge() {
            return monthAge;
        }

        public void setMonthAge(String monthAge) {
            this.monthAge = monthAge;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
