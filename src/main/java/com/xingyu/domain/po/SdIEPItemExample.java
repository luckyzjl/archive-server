package com.xingyu.domain.po;

import java.util.ArrayList;
import java.util.List;

public class SdIEPItemExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public SdIEPItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
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
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andPurposeItemIdIsNull() {
            addCriterion("purpose_item_id is null");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdIsNotNull() {
            addCriterion("purpose_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdEqualTo(Integer value) {
            addCriterion("purpose_item_id =", value, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdNotEqualTo(Integer value) {
            addCriterion("purpose_item_id <>", value, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdGreaterThan(Integer value) {
            addCriterion("purpose_item_id >", value, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("purpose_item_id >=", value, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdLessThan(Integer value) {
            addCriterion("purpose_item_id <", value, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("purpose_item_id <=", value, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdIn(List<Integer> values) {
            addCriterion("purpose_item_id in", values, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdNotIn(List<Integer> values) {
            addCriterion("purpose_item_id not in", values, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdBetween(Integer value1, Integer value2) {
            addCriterion("purpose_item_id between", value1, value2, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("purpose_item_id not between", value1, value2, "purposeItemId");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameIsNull() {
            addCriterion("purpose_item_name is null");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameIsNotNull() {
            addCriterion("purpose_item_name is not null");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameEqualTo(String value) {
            addCriterion("purpose_item_name =", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameNotEqualTo(String value) {
            addCriterion("purpose_item_name <>", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameGreaterThan(String value) {
            addCriterion("purpose_item_name >", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("purpose_item_name >=", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameLessThan(String value) {
            addCriterion("purpose_item_name <", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameLessThanOrEqualTo(String value) {
            addCriterion("purpose_item_name <=", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameLike(String value) {
            addCriterion("purpose_item_name like", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameNotLike(String value) {
            addCriterion("purpose_item_name not like", value, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameIn(List<String> values) {
            addCriterion("purpose_item_name in", values, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameNotIn(List<String> values) {
            addCriterion("purpose_item_name not in", values, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameBetween(String value1, String value2) {
            addCriterion("purpose_item_name between", value1, value2, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andPurposeItemNameNotBetween(String value1, String value2) {
            addCriterion("purpose_item_name not between", value1, value2, "purposeItemName");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultIsNull() {
            addCriterion("iep_assess_result is null");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultIsNotNull() {
            addCriterion("iep_assess_result is not null");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultEqualTo(Integer value) {
            addCriterion("iep_assess_result =", value, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultNotEqualTo(Integer value) {
            addCriterion("iep_assess_result <>", value, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultGreaterThan(Integer value) {
            addCriterion("iep_assess_result >", value, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("iep_assess_result >=", value, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultLessThan(Integer value) {
            addCriterion("iep_assess_result <", value, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultLessThanOrEqualTo(Integer value) {
            addCriterion("iep_assess_result <=", value, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultIn(List<Integer> values) {
            addCriterion("iep_assess_result in", values, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultNotIn(List<Integer> values) {
            addCriterion("iep_assess_result not in", values, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultBetween(Integer value1, Integer value2) {
            addCriterion("iep_assess_result between", value1, value2, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andIepAssessResultNotBetween(Integer value1, Integer value2) {
            addCriterion("iep_assess_result not between", value1, value2, "iepAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultIsNull() {
            addCriterion("progress_assess_result is null");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultIsNotNull() {
            addCriterion("progress_assess_result is not null");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultEqualTo(String value) {
            addCriterion("progress_assess_result =", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultNotEqualTo(String value) {
            addCriterion("progress_assess_result <>", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultGreaterThan(String value) {
            addCriterion("progress_assess_result >", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultGreaterThanOrEqualTo(String value) {
            addCriterion("progress_assess_result >=", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultLessThan(String value) {
            addCriterion("progress_assess_result <", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultLessThanOrEqualTo(String value) {
            addCriterion("progress_assess_result <=", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultLike(String value) {
            addCriterion("progress_assess_result like", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultNotLike(String value) {
            addCriterion("progress_assess_result not like", value, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultIn(List<String> values) {
            addCriterion("progress_assess_result in", values, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultNotIn(List<String> values) {
            addCriterion("progress_assess_result not in", values, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultBetween(String value1, String value2) {
            addCriterion("progress_assess_result between", value1, value2, "progressAssessResult");
            return (Criteria) this;
        }

        public Criteria andProgressAssessResultNotBetween(String value1, String value2) {
            addCriterion("progress_assess_result not between", value1, value2, "progressAssessResult");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sd_iep_item
     *
     * @mbggenerated do_not_delete_during_merge Tue Jul 30 23:38:17 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sd_iep_item
     *
     * @mbggenerated Tue Jul 30 23:38:17 CST 2019
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