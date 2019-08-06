package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdAssessScore;

/**
 * created by zhujl @2019-07-30
 */
public class SdAssessScoreDto extends SdAssessScore {
    private String itemName;
    private String catalogFirst;
    private String catalogSecond;

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String getCatalogFirst() {
        return catalogFirst;
    }

    @Override
    public void setCatalogFirst(String catalogFirst) {
        this.catalogFirst = catalogFirst;
    }

    @Override
    public String getCatalogSecond() {
        return catalogSecond;
    }

    @Override
    public void setCatalogSecond(String catalogSecond) {
        this.catalogSecond = catalogSecond;
    }
}
