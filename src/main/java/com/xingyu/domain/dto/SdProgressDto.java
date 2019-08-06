package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdProgressReport;

import java.util.List;

/**
 * created by zhujl @2019-08-02
 */
public class SdProgressDto extends SdProgressReport {
    List<SdProgressItemDto> progressItemDtoList;

    public List<SdProgressItemDto> getProgressItemDtoList() {
        return progressItemDtoList;
    }

    public void setProgressItemDtoList(List<SdProgressItemDto> progressItemDtoList) {
        this.progressItemDtoList = progressItemDtoList;
    }
}
