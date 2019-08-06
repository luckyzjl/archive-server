package com.xingyu.domain.dto;

import java.util.List;

/**
 * created by zhujl @2019-07-31
 */
public class SdAssessCatalogDto {
    private Integer catalogFirstId;
    private String catalogFirstName;
    private List<SdAssessItemDto> assessItemList;
    private String remark;

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

    public List<SdAssessItemDto> getAssessItemList() {
        return assessItemList;
    }

    public void setAssessItemList(List<SdAssessItemDto> assessItemList) {
        this.assessItemList = assessItemList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
