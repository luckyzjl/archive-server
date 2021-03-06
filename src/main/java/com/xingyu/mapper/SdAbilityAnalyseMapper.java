package com.xingyu.mapper;

import com.xingyu.domain.po.SdAbilityAnalyse;
import com.xingyu.domain.po.SdAbilityAnalyseExample;
import com.xingyu.domain.po.SdAbilityAnalyseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SdAbilityAnalyseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int countByExample(SdAbilityAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int deleteByExample(SdAbilityAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int deleteByPrimaryKey(SdAbilityAnalyseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int insert(SdAbilityAnalyse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int insertSelective(SdAbilityAnalyse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    List<SdAbilityAnalyse> selectByExample(SdAbilityAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    SdAbilityAnalyse selectByPrimaryKey(SdAbilityAnalyseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int updateByExampleSelective(@Param("record") SdAbilityAnalyse record, @Param("example") SdAbilityAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int updateByExample(@Param("record") SdAbilityAnalyse record, @Param("example") SdAbilityAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int updateByPrimaryKeySelective(SdAbilityAnalyse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    int updateByPrimaryKey(SdAbilityAnalyse record);
}