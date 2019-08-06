package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdIEPCatalog;
import com.xingyu.domain.po.SdIEPItem;

import java.util.List;

/**
 * created by zhujl @2019-07-30
 */
public class SdIEPCatalogDto extends SdIEPCatalog {
    List<SdIEPItem> iepItemList;

    public List<SdIEPItem> getIepItemList() {
        return iepItemList;
    }

    public void setIepItemList(List<SdIEPItem> iepItemList) {
        this.iepItemList = iepItemList;
    }
}
