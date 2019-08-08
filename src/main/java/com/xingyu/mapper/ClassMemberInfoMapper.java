package com.xingyu.mapper;

import com.xingyu.domain.po.ClassMemberInfo;
import com.xingyu.domain.po.ClassMemberInfoExample;
import com.xingyu.domain.po.ClassMemberInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassMemberInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int countByExample(ClassMemberInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int deleteByExample(ClassMemberInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int deleteByPrimaryKey(ClassMemberInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int insert(ClassMemberInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int insertSelective(ClassMemberInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    List<ClassMemberInfo> selectByExample(ClassMemberInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    ClassMemberInfo selectByPrimaryKey(ClassMemberInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByExampleSelective(@Param("record") ClassMemberInfo record, @Param("example") ClassMemberInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByExample(@Param("record") ClassMemberInfo record, @Param("example") ClassMemberInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByPrimaryKeySelective(ClassMemberInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_member_info
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    int updateByPrimaryKey(ClassMemberInfo record);
}