package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdIEPItem;
import com.xingyu.domain.po.SdProgressReport;

import java.util.List;

/**
 * created by zhujl @2019-08-01
 */
public class SdProgressItemDto {
    private Integer catalogId;
    private String catalogName;
    List<SdIEPItem> iepItemList;

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<SdIEPItem> getIepItemList() {
        return iepItemList;
    }

    public void setIepItemList(List<SdIEPItem> iepItemList) {
        this.iepItemList = iepItemList;
    }
}
