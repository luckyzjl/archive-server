package com.xingyu.mapper;

import com.xingyu.domain.po.ClassArchiveInfo;
import com.xingyu.domain.po.ClassArchiveInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassArchiveInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int countByExample(ClassArchiveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int deleteByExample(ClassArchiveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int deleteByPrimaryKey(String archiveNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int insert(ClassArchiveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int insertSelective(ClassArchiveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    List<ClassArchiveInfo> selectByExample(ClassArchiveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    ClassArchiveInfo selectByPrimaryKey(String archiveNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByExampleSelective(@Param("record") ClassArchiveInfo record, @Param("example") ClassArchiveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByExample(@Param("record") ClassArchiveInfo record, @Param("example") ClassArchiveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByPrimaryKeySelective(ClassArchiveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_archive_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByPrimaryKey(ClassArchiveInfo record);
}