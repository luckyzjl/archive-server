package com.xingyu.mapper;

import com.xingyu.domain.po.SdFamilyInfo;
import com.xingyu.domain.po.SdFamilyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SdFamilyInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int countByExample(SdFamilyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int deleteByExample(SdFamilyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int deleteByPrimaryKey(String archiveNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int insert(SdFamilyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int insertSelective(SdFamilyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    List<SdFamilyInfo> selectByExample(SdFamilyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    SdFamilyInfo selectByPrimaryKey(String archiveNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int updateByExampleSelective(@Param("record") SdFamilyInfo record, @Param("example") SdFamilyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int updateByExample(@Param("record") SdFamilyInfo record, @Param("example") SdFamilyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int updateByPrimaryKeySelective(SdFamilyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_family_info
     *
     * @mbggenerated Mon Jul 22 22:51:50 CST 2019
     */
    int updateByPrimaryKey(SdFamilyInfo record);
}