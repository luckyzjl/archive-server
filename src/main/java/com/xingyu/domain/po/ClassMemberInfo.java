package com.xingyu.domain.po;

import java.util.Date;

public class ClassMemberInfo extends ClassMemberInfoKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class_member_info.join_time
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    private Date joinTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class_member_info.join_time
     *
     * @return the value of class_member_info.join_time
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class_member_info.join_time
     *
     * @param joinTime the value for class_member_info.join_time
     *
     * @mbggenerated Thu Aug 08 23:24:36 CST 2019
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}