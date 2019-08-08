package com.xingyu.domain.dto;

import com.xingyu.domain.po.ClassArchiveInfo;
import com.xingyu.domain.po.SdArchiveInfo;

import java.util.List;

/**
 * created by zhujl @2019-08-07
 */
public class ClassArchiveDto extends ClassArchiveInfo {
    private Integer sdCount;
    private Integer recureSdCount;
    private List<SdArchiveInfo> sdArchiveInfoList;

    public Integer getSdCount() {
        return sdCount;
    }

    public void setSdCount(Integer sdCount) {
        this.sdCount = sdCount;
    }

    public Integer getRecureSdCount() {
        return recureSdCount;
    }

    public void setRecureSdCount(Integer recureSdCount) {
        this.recureSdCount = recureSdCount;
    }

    public List<SdArchiveInfo> getSdArchiveInfoList() {
        return sdArchiveInfoList;
    }

    public void setSdArchiveInfoList(List<SdArchiveInfo> sdArchiveInfoList) {
        this.sdArchiveInfoList = sdArchiveInfoList;
    }
}
