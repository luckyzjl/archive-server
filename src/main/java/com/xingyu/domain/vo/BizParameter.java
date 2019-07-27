package com.xingyu.domain.vo;

import com.xingyu.domain.po.SdArchiveInfo;
import com.xingyu.domain.po.SdFamilyInfo;
import com.xingyu.domain.po.SdSelfInfo;

/**
 * created by zhujl @2019-07-18
 */
public class BizParameter {
    private String archiveNo;
    private SdArchiveInfo sdArchiveInfo;
    private SdFamilyInfo sdFamilyInfo;
    private SdSelfInfo sdSelfInfo;
    private SdAssessScoreVo sdAssessScoreVo;

    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo;
    }

    public SdArchiveInfo getSdArchiveInfo() {
        return sdArchiveInfo;
    }

    public void setSdArchiveInfo(SdArchiveInfo sdArchiveInfo) {
        this.sdArchiveInfo = sdArchiveInfo;
    }

    public SdFamilyInfo getSdFamilyInfo() {
        return sdFamilyInfo;
    }

    public void setSdFamilyInfo(SdFamilyInfo sdFamilyInfo) {
        this.sdFamilyInfo = sdFamilyInfo;
    }

    public SdSelfInfo getSdSelfInfo() {
        return sdSelfInfo;
    }

    public void setSdSelfInfo(SdSelfInfo sdSelfInfo) {
        this.sdSelfInfo = sdSelfInfo;
    }

    public SdAssessScoreVo getSdAssessScoreVo() {
        return sdAssessScoreVo;
    }

    public void setSdAssessScoreVo(SdAssessScoreVo sdAssessScoreVo) {
        this.sdAssessScoreVo = sdAssessScoreVo;
    }
}
