package com.xingyu.mapper;

import com.xingyu.domain.po.SdEduTrack;
import com.xingyu.domain.po.SdEduTrackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SdEduTrackMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int countByExample(SdEduTrackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int deleteByExample(SdEduTrackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int deleteByPrimaryKey(String archiveNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int insert(SdEduTrack record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int insertSelective(SdEduTrack record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    List<SdEduTrack> selectByExample(SdEduTrackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    SdEduTrack selectByPrimaryKey(String archiveNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int updateByExampleSelective(@Param("record") SdEduTrack record, @Param("example") SdEduTrackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int updateByExample(@Param("record") SdEduTrack record, @Param("example") SdEduTrackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int updateByPrimaryKeySelective(SdEduTrack record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_edu_track
     *
     * @mbggenerated Sat Aug 03 13:22:26 CST 2019
     */
    int updateByPrimaryKey(SdEduTrack record);
}