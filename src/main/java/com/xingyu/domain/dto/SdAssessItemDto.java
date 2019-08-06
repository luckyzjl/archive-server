package com.xingyu.domain.dto;

import java.util.List;

/**
 * created by zhujl @2019-07-23
 */
public class SdAssessItemDto {

    private Integer itemId;
    private String itemNo;
    private String itemName;
    private Integer catalogFirstId;
    private String catalogFirstName;
    private Integer catalogSecondId;
    private String catalogSecondName;
    private Integer catalogThirdId;
    private String catalogThirdName;
    private String monthAge;
    private String score;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getCatalogFirstId() {
        return catalogFirstId;
    }

    public void setCatalogFirstId(Integer catalogFirstId) {
        this.catalogFirstId = catalogFirstId;
    }

    public String getCatalogFirstName() {
        return catalogFirstName;
    }

    public void setCatalogFirstName(String catalogFirstName) {
        this.catalogFirstName = catalogFirstName;
    }

    public Integer getCatalogSecondId() {
        return catalogSecondId;
    }

    public void setCatalogSecondId(Integer catalogSecondId) {
        this.catalogSecondId = catalogSecondId;
    }

    public String getCatalogSecondName() {
        return catalogSecondName;
    }

    public void setCatalogSecondName(String catalogSecondName) {
        this.catalogSecondName = catalogSecondName;
    }

    public Integer getCatalogThirdId() {
        return catalogThirdId;
    }

    public void setCatalogThirdId(Integer catalogThirdId) {
        this.catalogThirdId = catalogThirdId;
    }

    public String getCatalogThirdName() {
        return catalogThirdName;
    }

    public void setCatalogThirdName(String catalogThirdName) {
        this.catalogThirdName = catalogThirdName;
    }

    public String getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(String monthAge) {
        this.monthAge = monthAge;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
