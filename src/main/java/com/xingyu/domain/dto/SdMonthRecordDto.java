package com.xingyu.domain.dto;

import com.xingyu.domain.po.SdLessonRecord;
import com.xingyu.domain.po.SdMonthRecord;

import java.util.List;

/**
 * created by zhujl @2019-08-03
 */
public class SdMonthRecordDto extends SdMonthRecord {
    List<SdLessonRecord> lessonRecordList;

    public List<SdLessonRecord> getLessonRecordList() {
        return lessonRecordList;
    }

    public void setLessonRecordList(List<SdLessonRecord> lessonRecordList) {
        this.lessonRecordList = lessonRecordList;
    }
}
