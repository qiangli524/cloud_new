package com.sitech.basd.syslog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TbUserOperationLogExample extends TbUserOperationLog {

	private static final long serialVersionUID = 1L;

	protected String orderByClause;

	protected List<Criteria> oredCriteria;

	public TbUserOperationLogExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	protected TbUserOperationLogExample(TbUserOperationLogExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
	}

	public static class Criteria {
		protected List criteriaWithoutValue;

		protected List criteriaWithSingleValue;

		protected List criteriaWithListValue;

		protected List criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList();
			criteriaWithSingleValue = new ArrayList();
			criteriaWithListValue = new ArrayList();
			criteriaWithBetweenValue = new ArrayList();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0 || criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return this;
		}

		public Criteria andIdIn(List<?> values) {
			addCriterion("id in", values, "id");
			return this;
		}

		public Criteria andIdNotIn(List<?> values) {
			addCriterion("id not in", values, "id");
			return this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return this;
		}

		public Criteria andOperatorIdIsNull() {
			addCriterion("operator_id is null");
			return this;
		}

		public Criteria andOperatorIdIsNotNull() {
			addCriterion("operator_id is not null");
			return this;
		}

		public Criteria andOperatorIdEqualTo(String value) {
			addCriterion("operator_id =", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdNotEqualTo(String value) {
			addCriterion("operator_id <>", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdGreaterThan(String value) {
			addCriterion("operator_id >", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
			addCriterion("operator_id >=", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdLessThan(String value) {
			addCriterion("operator_id <", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdLessThanOrEqualTo(String value) {
			addCriterion("operator_id <=", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdLike(String value) {
			addCriterion("operator_id like", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdNotLike(String value) {
			addCriterion("operator_id not like", value, "operatorId");
			return this;
		}

		public Criteria andOperatorIdIn(List values) {
			addCriterion("operator_id in", values, "operatorId");
			return this;
		}

		public Criteria andOperatorIdNotIn(List values) {
			addCriterion("operator_id not in", values, "operatorId");
			return this;
		}

		public Criteria andOperatorIdBetween(String value1, String value2) {
			addCriterion("operator_id between", value1, value2, "operatorId");
			return this;
		}

		public Criteria andOperatorIdNotBetween(String value1, String value2) {
			addCriterion("operator_id not between", value1, value2, "operatorId");
			return this;
		}

		public Criteria andOperatorNameIsNull() {
			addCriterion("operator_name is null");
			return this;
		}

		public Criteria andOperatorNameIsNotNull() {
			addCriterion("operator_name is not null");
			return this;
		}

		public Criteria andOperatorNameEqualTo(String value) {
			addCriterion("operator_name =", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameNotEqualTo(String value) {
			addCriterion("operator_name <>", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameGreaterThan(String value) {
			addCriterion("operator_name >", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
			addCriterion("operator_name >=", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameLessThan(String value) {
			addCriterion("operator_name <", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameLessThanOrEqualTo(String value) {
			addCriterion("operator_name <=", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameLike(String value) {
			addCriterion("operator_name like", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameNotLike(String value) {
			addCriterion("operator_name not like", value, "operatorName");
			return this;
		}

		public Criteria andOperatorNameIn(List values) {
			addCriterion("operator_name in", values, "operatorName");
			return this;
		}

		public Criteria andOperatorNameNotIn(List values) {
			addCriterion("operator_name not in", values, "operatorName");
			return this;
		}

		public Criteria andOperatorNameBetween(String value1, String value2) {
			addCriterion("operator_name between", value1, value2, "operatorName");
			return this;
		}

		public Criteria andOperatorNameNotBetween(String value1, String value2) {
			addCriterion("operator_name not between", value1, value2, "operatorName");
			return this;
		}

		public Criteria andOperationTypeIsNull() {
			addCriterion("operation_type is null");
			return this;
		}

		public Criteria andOperationTypeIsNotNull() {
			addCriterion("operation_type is not null");
			return this;
		}

		public Criteria andOperationTypeEqualTo(String value) {
			addCriterion("operation_type =", value, "operationType");
			return this;
		}

		public Criteria andOperationTypeNotEqualTo(String value) {
			addCriterion("operation_type <>", value, "operationType");
			return this;
		}

		public Criteria andOperationTypeGreaterThan(String value) {
			addCriterion("operation_type >", value, "operationType");
			return this;
		}

		public Criteria andOperationTypeGreaterThanOrEqualTo(String value) {
			addCriterion("operation_type >=", value, "operationType");
			return this;
		}

		public Criteria andOperationTypeLessThan(String value) {
			addCriterion("operation_type <", value, "operationType");
			return this;
		}

		public Criteria andOperationTypeLessThanOrEqualTo(String value) {
			addCriterion("operation_type <=", value, "operationType");
			return this;
		}

		public Criteria andOperationTypeIn(List<?> values) {
			addCriterion("operation_type in", values, "operationType");
			return this;
		}

		public Criteria andOperationTypeNotIn(List<?> values) {
			addCriterion("operation_type not in", values, "operationType");
			return this;
		}

		public Criteria andOperationResultIsNull() {
			addCriterion("operation_result is null");
			return this;
		}

		public Criteria andOperationResultIsNotNull() {
			addCriterion("operation_result is not null");
			return this;
		}

		public Criteria andOperationResultEqualTo(Integer value) {
			addCriterion("operation_result =", value, "operationResult");
			return this;
		}

		public Criteria andOperationResultNotEqualTo(Integer value) {
			addCriterion("operation_result <>", value, "operationResult");
			return this;
		}

		public Criteria andOperationResultGreaterThan(Integer value) {
			addCriterion("operation_result >", value, "operationResult");
			return this;
		}

		public Criteria andOperationResultGreaterThanOrEqualTo(Integer value) {
			addCriterion("operation_result >=", value, "operationResult");
			return this;
		}

		public Criteria andOperationResultLessThan(Integer value) {
			addCriterion("operation_result <", value, "operationResult");
			return this;
		}

		public Criteria andOperationResultLessThanOrEqualTo(Integer value) {
			addCriterion("operation_result <=", value, "operationResult");
			return this;
		}

		public Criteria andOperationResultIn(List<?> values) {
			addCriterion("operation_result in", values, "operationResult");
			return this;
		}

		public Criteria andOperationResultNotIn(List<?> values) {
			addCriterion("operation_result not in", values, "operationResult");
			return this;
		}

		public Criteria andOperationResultBetween(Integer value1, Integer value2) {
			addCriterion("operation_result between", value1, value2, "operationResult");
			return this;
		}

		public Criteria andOperationResultNotBetween(Integer value1, Integer value2) {
			addCriterion("operation_result not between", value1, value2, "operationResult");
			return this;
		}

		public Criteria andOperationObjectIsNull() {
			addCriterion("operation_object is null");
			return this;
		}

		public Criteria andOperationObjectIsNotNull() {
			addCriterion("operation_object is not null");
			return this;
		}

		public Criteria andOperationObjectEqualTo(String value) {
			addCriterion("operation_object =", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectNotEqualTo(String value) {
			addCriterion("operation_object <>", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectGreaterThan(String value) {
			addCriterion("operation_object >", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectGreaterThanOrEqualTo(String value) {
			addCriterion("operation_object >=", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectLessThan(String value) {
			addCriterion("operation_object <", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectLessThanOrEqualTo(String value) {
			addCriterion("operation_object <=", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectLike(String value) {
			addCriterion("operation_object like", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectNotLike(String value) {
			addCriterion("operation_object not like", value, "operationObject");
			return this;
		}

		public Criteria andOperationObjectIn(List<?> values) {
			addCriterion("operation_object in", values, "operationObject");
			return this;
		}

		public Criteria andOperationObjectNotIn(List<?> values) {
			addCriterion("operation_object not in", values, "operationObject");
			return this;
		}

		public Criteria andOperationObjectBetween(String value1, String value2) {
			addCriterion("operation_object between", value1, value2, "operationObject");
			return this;
		}

		public Criteria andOperationObjectNotBetween(String value1, String value2) {
			addCriterion("operation_object not between", value1, value2, "operationObject");
			return this;
		}

		public Criteria andOperationContentIsNull() {
			addCriterion("operation_content is null");
			return this;
		}

		public Criteria andOperationContentIsNotNull() {
			addCriterion("operation_content is not null");
			return this;
		}

		public Criteria andOperationContentEqualTo(String value) {
			addCriterion("operation_content =", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentNotEqualTo(String value) {
			addCriterion("operation_content <>", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentGreaterThan(String value) {
			addCriterion("operation_content >", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentGreaterThanOrEqualTo(String value) {
			addCriterion("operation_content >=", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentLessThan(String value) {
			addCriterion("operation_content <", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentLessThanOrEqualTo(String value) {
			addCriterion("operation_content <=", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentLike(String value) {
			addCriterion("operation_content like", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentNotLike(String value) {
			addCriterion("operation_content not like", value, "operationContent");
			return this;
		}

		public Criteria andOperationContentIn(List<?> values) {
			addCriterion("operation_content in", values, "operationContent");
			return this;
		}

		public Criteria andOperationContentNotIn(List<?> values) {
			addCriterion("operation_content not in", values, "operationContent");
			return this;
		}

		public Criteria andOperationContentBetween(String value1, String value2) {
			addCriterion("operation_content between", value1, value2, "operationContent");
			return this;
		}

		public Criteria andOperationContentNotBetween(String value1, String value2) {
			addCriterion("operation_content not between", value1, value2, "operationContent");
			return this;
		}

		public Criteria andOperationStartDateIsNull() {
			addCriterion("operation_start_date is null");
			return this;
		}

		public Criteria andOperationStartDateIsNotNull() {
			addCriterion("operation_start_date is not null");
			return this;
		}

		public Criteria andOperationStartDateEqualTo(Date value) {
			addCriterion("operation_start_date =", value, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateNotEqualTo(Date value) {
			addCriterion("operation_start_date <>", value, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateGreaterThan(Date value) {
			addCriterion("operation_start_date >", value, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateGreaterThanOrEqualTo(Date value) {
			addCriterion("operation_start_date >=", value, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateLessThan(Date value) {
			addCriterion("operation_start_date <", value, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateLessThanOrEqualTo(Date value) {
			addCriterion("operation_start_date <=", value, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateIn(List<?> values) {
			addCriterion("operation_start_date in", values, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateNotIn(List<?> values) {
			addCriterion("operation_start_date not in", values, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateBetween(Date value1, Date value2) {
			addCriterion("operation_start_date between", value1, value2, "operationStartDate");
			return this;
		}

		public Criteria andOperationStartDateNotBetween(Date value1, Date value2) {
			addCriterion("operation_start_date not between", value1, value2, "operationStartDate");
			return this;
		}

		public Criteria andOperationEndDateIsNull() {
			addCriterion("operation_end_date is null");
			return this;
		}

		public Criteria andOperationEndDateIsNotNull() {
			addCriterion("operation_end_date is not null");
			return this;
		}

		public Criteria andOperationEndDateEqualTo(Date value) {
			addCriterion("operation_end_date =", value, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateNotEqualTo(Date value) {
			addCriterion("operation_end_date <>", value, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateGreaterThan(Date value) {
			addCriterion("operation_end_date >", value, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateGreaterThanOrEqualTo(Date value) {
			addCriterion("operation_end_date >=", value, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateLessThan(Date value) {
			addCriterion("operation_end_date <", value, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateLessThanOrEqualTo(Date value) {
			addCriterion("operation_end_date <=", value, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateIn(List values) {
			addCriterion("operation_end_date in", values, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateNotIn(List values) {
			addCriterion("operation_end_date not in", values, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateBetween(Date value1, Date value2) {
			addCriterion("operation_end_date between", value1, value2, "operationEndDate");
			return this;
		}

		public Criteria andOperationEndDateNotBetween(Date value1, Date value2) {
			addCriterion("operation_end_date not between", value1, value2, "operationEndDate");
			return this;
		}

		public Criteria andElapsedTimeIsNull() {
			addCriterion("elapsed_time is null");
			return this;
		}

		public Criteria andElapsedTimeIsNotNull() {
			addCriterion("elapsed_time is not null");
			return this;
		}

		public Criteria andElapsedTimeEqualTo(String value) {
			addCriterion("elapsed_time =", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeNotEqualTo(String value) {
			addCriterion("elapsed_time <>", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeGreaterThan(String value) {
			addCriterion("elapsed_time >", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeGreaterThanOrEqualTo(String value) {
			addCriterion("elapsed_time >=", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeLessThan(String value) {
			addCriterion("elapsed_time <", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeLessThanOrEqualTo(String value) {
			addCriterion("elapsed_time <=", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeLike(String value) {
			addCriterion("elapsed_time like", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeNotLike(String value) {
			addCriterion("elapsed_time not like", value, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeIn(List values) {
			addCriterion("elapsed_time in", values, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeNotIn(List values) {
			addCriterion("elapsed_time not in", values, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeBetween(String value1, String value2) {
			addCriterion("elapsed_time between", value1, value2, "elapsedTime");
			return this;
		}

		public Criteria andElapsedTimeNotBetween(String value1, String value2) {
			addCriterion("elapsed_time not between", value1, value2, "elapsedTime");
			return this;
		}

		public Criteria andBoattr1IsNull() {
			addCriterion("boAttr1 is null");
			return this;
		}

		public Criteria andBoattr1IsNotNull() {
			addCriterion("boAttr1 is not null");
			return this;
		}

		public Criteria andBoattr1EqualTo(String value) {
			addCriterion("boAttr1 =", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1NotEqualTo(String value) {
			addCriterion("boAttr1 <>", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1GreaterThan(String value) {
			addCriterion("boAttr1 >", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1GreaterThanOrEqualTo(String value) {
			addCriterion("boAttr1 >=", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1LessThan(String value) {
			addCriterion("boAttr1 <", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1LessThanOrEqualTo(String value) {
			addCriterion("boAttr1 <=", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1Like(String value) {
			addCriterion("boAttr1 like", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1NotLike(String value) {
			addCriterion("boAttr1 not like", value, "boattr1");
			return this;
		}

		public Criteria andBoattr1In(List values) {
			addCriterion("boAttr1 in", values, "boattr1");
			return this;
		}

		public Criteria andBoattr1NotIn(List values) {
			addCriterion("boAttr1 not in", values, "boattr1");
			return this;
		}

		public Criteria andBoattr1Between(String value1, String value2) {
			addCriterion("boAttr1 between", value1, value2, "boattr1");
			return this;
		}

		public Criteria andBoattr1NotBetween(String value1, String value2) {
			addCriterion("boAttr1 not between", value1, value2, "boattr1");
			return this;
		}

		public Criteria andBoattr2IsNull() {
			addCriterion("boAttr2 is null");
			return this;
		}

		public Criteria andBoattr2IsNotNull() {
			addCriterion("boAttr2 is not null");
			return this;
		}

		public Criteria andBoattr2EqualTo(String value) {
			addCriterion("boAttr2 =", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2NotEqualTo(String value) {
			addCriterion("boAttr2 <>", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2GreaterThan(String value) {
			addCriterion("boAttr2 >", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2GreaterThanOrEqualTo(String value) {
			addCriterion("boAttr2 >=", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2LessThan(String value) {
			addCriterion("boAttr2 <", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2LessThanOrEqualTo(String value) {
			addCriterion("boAttr2 <=", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2Like(String value) {
			addCriterion("boAttr2 like", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2NotLike(String value) {
			addCriterion("boAttr2 not like", value, "boattr2");
			return this;
		}

		public Criteria andBoattr2In(List values) {
			addCriterion("boAttr2 in", values, "boattr2");
			return this;
		}

		public Criteria andBoattr2NotIn(List values) {
			addCriterion("boAttr2 not in", values, "boattr2");
			return this;
		}

		public Criteria andBoattr2Between(String value1, String value2) {
			addCriterion("boAttr2 between", value1, value2, "boattr2");
			return this;
		}

		public Criteria andBoattr2NotBetween(String value1, String value2) {
			addCriterion("boAttr2 not between", value1, value2, "boattr2");
			return this;
		}

		public Criteria andBoattr3IsNull() {
			addCriterion("boAttr3 is null");
			return this;
		}

		public Criteria andBoattr3IsNotNull() {
			addCriterion("boAttr3 is not null");
			return this;
		}

		public Criteria andBoattr3EqualTo(String value) {
			addCriterion("boAttr3 =", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3NotEqualTo(String value) {
			addCriterion("boAttr3 <>", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3GreaterThan(String value) {
			addCriterion("boAttr3 >", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3GreaterThanOrEqualTo(String value) {
			addCriterion("boAttr3 >=", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3LessThan(String value) {
			addCriterion("boAttr3 <", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3LessThanOrEqualTo(String value) {
			addCriterion("boAttr3 <=", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3Like(String value) {
			addCriterion("boAttr3 like", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3NotLike(String value) {
			addCriterion("boAttr3 not like", value, "boattr3");
			return this;
		}

		public Criteria andBoattr3In(List values) {
			addCriterion("boAttr3 in", values, "boattr3");
			return this;
		}

		public Criteria andBoattr3NotIn(List values) {
			addCriterion("boAttr3 not in", values, "boattr3");
			return this;
		}

		public Criteria andBoattr3Between(String value1, String value2) {
			addCriterion("boAttr3 between", value1, value2, "boattr3");
			return this;
		}

		public Criteria andBoattr3NotBetween(String value1, String value2) {
			addCriterion("boAttr3 not between", value1, value2, "boattr3");
			return this;
		}

		public Criteria andBoattr4IsNull() {
			addCriterion("boAttr4 is null");
			return this;
		}

		public Criteria andBoattr4IsNotNull() {
			addCriterion("boAttr4 is not null");
			return this;
		}

		public Criteria andBoattr4EqualTo(String value) {
			addCriterion("boAttr4 =", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4NotEqualTo(String value) {
			addCriterion("boAttr4 <>", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4GreaterThan(String value) {
			addCriterion("boAttr4 >", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4GreaterThanOrEqualTo(String value) {
			addCriterion("boAttr4 >=", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4LessThan(String value) {
			addCriterion("boAttr4 <", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4LessThanOrEqualTo(String value) {
			addCriterion("boAttr4 <=", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4Like(String value) {
			addCriterion("boAttr4 like", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4NotLike(String value) {
			addCriterion("boAttr4 not like", value, "boattr4");
			return this;
		}

		public Criteria andBoattr4In(List values) {
			addCriterion("boAttr4 in", values, "boattr4");
			return this;
		}

		public Criteria andBoattr4NotIn(List values) {
			addCriterion("boAttr4 not in", values, "boattr4");
			return this;
		}

		public Criteria andBoattr4Between(String value1, String value2) {
			addCriterion("boAttr4 between", value1, value2, "boattr4");
			return this;
		}

		public Criteria andBoattr4NotBetween(String value1, String value2) {
			addCriterion("boAttr4 not between", value1, value2, "boattr4");
			return this;
		}

		public Criteria andBoattr5IsNull() {
			addCriterion("boAttr5 is null");
			return this;
		}

		public Criteria andBoattr5IsNotNull() {
			addCriterion("boAttr5 is not null");
			return this;
		}

		public Criteria andBoattr5EqualTo(String value) {
			addCriterion("boAttr5 =", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5NotEqualTo(String value) {
			addCriterion("boAttr5 <>", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5GreaterThan(String value) {
			addCriterion("boAttr5 >", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5GreaterThanOrEqualTo(String value) {
			addCriterion("boAttr5 >=", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5LessThan(String value) {
			addCriterion("boAttr5 <", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5LessThanOrEqualTo(String value) {
			addCriterion("boAttr5 <=", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5Like(String value) {
			addCriterion("boAttr5 like", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5NotLike(String value) {
			addCriterion("boAttr5 not like", value, "boattr5");
			return this;
		}

		public Criteria andBoattr5In(List values) {
			addCriterion("boAttr5 in", values, "boattr5");
			return this;
		}

		public Criteria andBoattr5NotIn(List values) {
			addCriterion("boAttr5 not in", values, "boattr5");
			return this;
		}

		public Criteria andBoattr5Between(String value1, String value2) {
			addCriterion("boAttr5 between", value1, value2, "boattr5");
			return this;
		}

		public Criteria andBoattr5NotBetween(String value1, String value2) {
			addCriterion("boAttr5 not between", value1, value2, "boattr5");
			return this;
		}
	}
}