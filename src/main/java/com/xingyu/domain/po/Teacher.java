package com.xingyu.domain.po;

import java.util.Date;

public class Teacher {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.teacher_id
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    private Integer teacherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.name
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.speciality
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    private String speciality;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.join_time
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    private Date joinTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.teacher_id
     *
     * @return the value of teacher.teacher_id
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.teacher_id
     *
     * @param teacherId the value for teacher.teacher_id
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.name
     *
     * @return the value of teacher.name
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.name
     *
     * @param name the value for teacher.name
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.speciality
     *
     * @return the value of teacher.speciality
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.speciality
     *
     * @param speciality the value for teacher.speciality
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.join_time
     *
     * @return the value of teacher.join_time
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.join_time
     *
     * @param joinTime the value for teacher.join_time
     *
     * @mbggenerated Mon Jul 22 23:24:03 CST 2019
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}