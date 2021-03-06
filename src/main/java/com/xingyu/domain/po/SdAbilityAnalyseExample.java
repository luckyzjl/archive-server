package com.xingyu.domain.po;

import java.util.ArrayList;
import java.util.List;

public class SdAbilityAnalyseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public SdAbilityAnalyseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andArchiveNoIsNull() {
            addCriterion("archive_no is null");
            return (Criteria) this;
        }

        public Criteria andArchiveNoIsNotNull() {
            addCriterion("archive_no is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveNoEqualTo(String value) {
            addCriterion("archive_no =", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotEqualTo(String value) {
            addCriterion("archive_no <>", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoGreaterThan(String value) {
            addCriterion("archive_no >", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoGreaterThanOrEqualTo(String value) {
            addCriterion("archive_no >=", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoLessThan(String value) {
            addCriterion("archive_no <", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoLessThanOrEqualTo(String value) {
            addCriterion("archive_no <=", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoLike(String value) {
            addCriterion("archive_no like", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotLike(String value) {
            addCriterion("archive_no not like", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoIn(List<String> values) {
            addCriterion("archive_no in", values, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotIn(List<String> values) {
            addCriterion("archive_no not in", values, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoBetween(String value1, String value2) {
            addCriterion("archive_no between", value1, value2, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotBetween(String value1, String value2) {
            addCriterion("archive_no not between", value1, value2, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andAssessTimesIsNull() {
            addCriterion("assess_times is null");
            return (Criteria) this;
        }

        public Criteria andAssessTimesIsNotNull() {
            addCriterion("assess_times is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTimesEqualTo(String value) {
            addCriterion("assess_times =", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesNotEqualTo(String value) {
            addCriterion("assess_times <>", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesGreaterThan(String value) {
            addCriterion("assess_times >", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesGreaterThanOrEqualTo(String value) {
            addCriterion("assess_times >=", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesLessThan(String value) {
            addCriterion("assess_times <", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesLessThanOrEqualTo(String value) {
            addCriterion("assess_times <=", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesLike(String value) {
            addCriterion("assess_times like", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesNotLike(String value) {
            addCriterion("assess_times not like", value, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesIn(List<String> values) {
            addCriterion("assess_times in", values, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesNotIn(List<String> values) {
            addCriterion("assess_times not in", values, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesBetween(String value1, String value2) {
            addCriterion("assess_times between", value1, value2, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andAssessTimesNotBetween(String value1, String value2) {
            addCriterion("assess_times not between", value1, value2, "assessTimes");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIsNull() {
            addCriterion("catalog_id is null");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIsNotNull() {
            addCriterion("catalog_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogIdEqualTo(Integer value) {
            addCriterion("catalog_id =", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotEqualTo(Integer value) {
            addCriterion("catalog_id <>", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdGreaterThan(Integer value) {
            addCriterion("catalog_id >", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("catalog_id >=", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdLessThan(Integer value) {
            addCriterion("catalog_id <", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdLessThanOrEqualTo(Integer value) {
            addCriterion("catalog_id <=", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIn(List<Integer> values) {
            addCriterion("catalog_id in", values, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotIn(List<Integer> values) {
            addCriterion("catalog_id not in", values, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdBetween(Integer value1, Integer value2) {
            addCriterion("catalog_id between", value1, value2, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("catalog_id not between", value1, value2, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogNameIsNull() {
            addCriterion("catalog_name is null");
            return (Criteria) this;
        }

        public Criteria andCatalogNameIsNotNull() {
            addCriterion("catalog_name is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogNameEqualTo(String value) {
            addCriterion("catalog_name =", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameNotEqualTo(String value) {
            addCriterion("catalog_name <>", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameGreaterThan(String value) {
            addCriterion("catalog_name >", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameGreaterThanOrEqualTo(String value) {
            addCriterion("catalog_name >=", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameLessThan(String value) {
            addCriterion("catalog_name <", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameLessThanOrEqualTo(String value) {
            addCriterion("catalog_name <=", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameLike(String value) {
            addCriterion("catalog_name like", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameNotLike(String value) {
            addCriterion("catalog_name not like", value, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameIn(List<String> values) {
            addCriterion("catalog_name in", values, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameNotIn(List<String> values) {
            addCriterion("catalog_name not in", values, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameBetween(String value1, String value2) {
            addCriterion("catalog_name between", value1, value2, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCatalogNameNotBetween(String value1, String value2) {
            addCriterion("catalog_name not between", value1, value2, "catalogName");
            return (Criteria) this;
        }

        public Criteria andCurAbilityIsNull() {
            addCriterion("cur_ability is null");
            return (Criteria) this;
        }

        public Criteria andCurAbilityIsNotNull() {
            addCriterion("cur_ability is not null");
            return (Criteria) this;
        }

        public Criteria andCurAbilityEqualTo(String value) {
            addCriterion("cur_ability =", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityNotEqualTo(String value) {
            addCriterion("cur_ability <>", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityGreaterThan(String value) {
            addCriterion("cur_ability >", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityGreaterThanOrEqualTo(String value) {
            addCriterion("cur_ability >=", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityLessThan(String value) {
            addCriterion("cur_ability <", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityLessThanOrEqualTo(String value) {
            addCriterion("cur_ability <=", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityLike(String value) {
            addCriterion("cur_ability like", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityNotLike(String value) {
            addCriterion("cur_ability not like", value, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityIn(List<String> values) {
            addCriterion("cur_ability in", values, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityNotIn(List<String> values) {
            addCriterion("cur_ability not in", values, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityBetween(String value1, String value2) {
            addCriterion("cur_ability between", value1, value2, "curAbility");
            return (Criteria) this;
        }

        public Criteria andCurAbilityNotBetween(String value1, String value2) {
            addCriterion("cur_ability not between", value1, value2, "curAbility");
            return (Criteria) this;
        }

        public Criteria andSuperiorityIsNull() {
            addCriterion("superiority is null");
            return (Criteria) this;
        }

        public Criteria andSuperiorityIsNotNull() {
            addCriterion("superiority is not null");
            return (Criteria) this;
        }

        public Criteria andSuperiorityEqualTo(String value) {
            addCriterion("superiority =", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityNotEqualTo(String value) {
            addCriterion("superiority <>", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityGreaterThan(String value) {
            addCriterion("superiority >", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityGreaterThanOrEqualTo(String value) {
            addCriterion("superiority >=", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityLessThan(String value) {
            addCriterion("superiority <", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityLessThanOrEqualTo(String value) {
            addCriterion("superiority <=", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityLike(String value) {
            addCriterion("superiority like", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityNotLike(String value) {
            addCriterion("superiority not like", value, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityIn(List<String> values) {
            addCriterion("superiority in", values, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityNotIn(List<String> values) {
            addCriterion("superiority not in", values, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityBetween(String value1, String value2) {
            addCriterion("superiority between", value1, value2, "superiority");
            return (Criteria) this;
        }

        public Criteria andSuperiorityNotBetween(String value1, String value2) {
            addCriterion("superiority not between", value1, value2, "superiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityIsNull() {
            addCriterion("inferiority is null");
            return (Criteria) this;
        }

        public Criteria andInferiorityIsNotNull() {
            addCriterion("inferiority is not null");
            return (Criteria) this;
        }

        public Criteria andInferiorityEqualTo(String value) {
            addCriterion("inferiority =", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityNotEqualTo(String value) {
            addCriterion("inferiority <>", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityGreaterThan(String value) {
            addCriterion("inferiority >", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityGreaterThanOrEqualTo(String value) {
            addCriterion("inferiority >=", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityLessThan(String value) {
            addCriterion("inferiority <", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityLessThanOrEqualTo(String value) {
            addCriterion("inferiority <=", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityLike(String value) {
            addCriterion("inferiority like", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityNotLike(String value) {
            addCriterion("inferiority not like", value, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityIn(List<String> values) {
            addCriterion("inferiority in", values, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityNotIn(List<String> values) {
            addCriterion("inferiority not in", values, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityBetween(String value1, String value2) {
            addCriterion("inferiority between", value1, value2, "inferiority");
            return (Criteria) this;
        }

        public Criteria andInferiorityNotBetween(String value1, String value2) {
            addCriterion("inferiority not between", value1, value2, "inferiority");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeIsNull() {
            addCriterion("train_purpose is null");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeIsNotNull() {
            addCriterion("train_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeEqualTo(String value) {
            addCriterion("train_purpose =", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeNotEqualTo(String value) {
            addCriterion("train_purpose <>", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeGreaterThan(String value) {
            addCriterion("train_purpose >", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("train_purpose >=", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeLessThan(String value) {
            addCriterion("train_purpose <", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeLessThanOrEqualTo(String value) {
            addCriterion("train_purpose <=", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeLike(String value) {
            addCriterion("train_purpose like", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeNotLike(String value) {
            addCriterion("train_purpose not like", value, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeIn(List<String> values) {
            addCriterion("train_purpose in", values, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeNotIn(List<String> values) {
            addCriterion("train_purpose not in", values, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeBetween(String value1, String value2) {
            addCriterion("train_purpose between", value1, value2, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andTrainPurposeNotBetween(String value1, String value2) {
            addCriterion("train_purpose not between", value1, value2, "trainPurpose");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentIsNull() {
            addCriterion("live_environment is null");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentIsNotNull() {
            addCriterion("live_environment is not null");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentEqualTo(String value) {
            addCriterion("live_environment =", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentNotEqualTo(String value) {
            addCriterion("live_environment <>", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentGreaterThan(String value) {
            addCriterion("live_environment >", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentGreaterThanOrEqualTo(String value) {
            addCriterion("live_environment >=", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentLessThan(String value) {
            addCriterion("live_environment <", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentLessThanOrEqualTo(String value) {
            addCriterion("live_environment <=", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentLike(String value) {
            addCriterion("live_environment like", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentNotLike(String value) {
            addCriterion("live_environment not like", value, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentIn(List<String> values) {
            addCriterion("live_environment in", values, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentNotIn(List<String> values) {
            addCriterion("live_environment not in", values, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentBetween(String value1, String value2) {
            addCriterion("live_environment between", value1, value2, "liveEnvironment");
            return (Criteria) this;
        }

        public Criteria andLiveEnvironmentNotBetween(String value1, String value2) {
            addCriterion("live_environment not between", value1, value2, "liveEnvironment");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 05 21:55:37 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sd_ability_analyse
     *
     * @mbggenerated Mon Aug 05 21:55:37 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}