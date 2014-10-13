package com.sitech.basd.scheduler.domain.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceTaskObjExample extends ResourceTaskObj {
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

	protected List oredCriteria;

	public ResourceTaskObjExample() {
		oredCriteria = new ArrayList();
	}

	protected ResourceTaskObjExample(ResourceTaskObjExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public List getOredCriteria() {
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
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0 || criteriaWithListValue.size() > 0 || criteriaWithBetweenValue.size() > 0;
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

		public Criteria andTaskIdIsNull() {
			addCriterion("task_id is null");
			return this;
		}

		public Criteria andTaskIdIsNotNull() {
			addCriterion("task_id is not null");
			return this;
		}

		public Criteria andTaskIdEqualTo(String value) {
			addCriterion("task_id =", value, "taskId");
			return this;
		}

		public Criteria andTaskIdNotEqualTo(String value) {
			addCriterion("task_id <>", value, "taskId");
			return this;
		}

		public Criteria andTaskIdGreaterThan(String value) {
			addCriterion("task_id >", value, "taskId");
			return this;
		}

		public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
			addCriterion("task_id >=", value, "taskId");
			return this;
		}

		public Criteria andTaskIdLessThan(String value) {
			addCriterion("task_id <", value, "taskId");
			return this;
		}

		public Criteria andTaskIdLessThanOrEqualTo(String value) {
			addCriterion("task_id <=", value, "taskId");
			return this;
		}

		public Criteria andTaskIdLike(String value) {
			addCriterion("task_id like", value, "taskId");
			return this;
		}

		public Criteria andTaskIdNotLike(String value) {
			addCriterion("task_id not like", value, "taskId");
			return this;
		}

		public Criteria andTaskIdIn(List values) {
			addCriterion("task_id in", values, "taskId");
			return this;
		}

		public Criteria andTaskIdNotIn(List values) {
			addCriterion("task_id not in", values, "taskId");
			return this;
		}

		public Criteria andTaskIdBetween(String value1, String value2) {
			addCriterion("task_id between", value1, value2, "taskId");
			return this;
		}

		public Criteria andTaskIdNotBetween(String value1, String value2) {
			addCriterion("task_id not between", value1, value2, "taskId");
			return this;
		}

		public Criteria andVhIdIsNull() {
			addCriterion("vh_id is null");
			return this;
		}

		public Criteria andVhIdIsNotNull() {
			addCriterion("vh_id is not null");
			return this;
		}

		public Criteria andVhIdEqualTo(String value) {
			addCriterion("vh_id =", value, "vhId");
			return this;
		}

		public Criteria andVhIdNotEqualTo(String value) {
			addCriterion("vh_id <>", value, "vhId");
			return this;
		}

		public Criteria andVhIdGreaterThan(String value) {
			addCriterion("vh_id >", value, "vhId");
			return this;
		}

		public Criteria andVhIdGreaterThanOrEqualTo(String value) {
			addCriterion("vh_id >=", value, "vhId");
			return this;
		}

		public Criteria andVhIdLessThan(String value) {
			addCriterion("vh_id <", value, "vhId");
			return this;
		}

		public Criteria andVhIdLessThanOrEqualTo(String value) {
			addCriterion("vh_id <=", value, "vhId");
			return this;
		}

		public Criteria andVhIdLike(String value) {
			addCriterion("vh_id like", value, "vhId");
			return this;
		}

		public Criteria andVhIdNotLike(String value) {
			addCriterion("vh_id not like", value, "vhId");
			return this;
		}

		public Criteria andVhIdIn(List values) {
			addCriterion("vh_id in", values, "vhId");
			return this;
		}

		public Criteria andVhIdNotIn(List values) {
			addCriterion("vh_id not in", values, "vhId");
			return this;
		}

		public Criteria andVhIdBetween(String value1, String value2) {
			addCriterion("vh_id between", value1, value2, "vhId");
			return this;
		}

		public Criteria andVhIdNotBetween(String value1, String value2) {
			addCriterion("vh_id not between", value1, value2, "vhId");
			return this;
		}

		public Criteria andVhNameIsNull() {
			addCriterion("vh_name is null");
			return this;
		}

		public Criteria andVhNameIsNotNull() {
			addCriterion("vh_name is not null");
			return this;
		}

		public Criteria andVhNameEqualTo(String value) {
			addCriterion("vh_name =", value, "vhName");
			return this;
		}

		public Criteria andVhNameNotEqualTo(String value) {
			addCriterion("vh_name <>", value, "vhName");
			return this;
		}

		public Criteria andVhNameGreaterThan(String value) {
			addCriterion("vh_name >", value, "vhName");
			return this;
		}

		public Criteria andVhNameGreaterThanOrEqualTo(String value) {
			addCriterion("vh_name >=", value, "vhName");
			return this;
		}

		public Criteria andVhNameLessThan(String value) {
			addCriterion("vh_name <", value, "vhName");
			return this;
		}

		public Criteria andVhNameLessThanOrEqualTo(String value) {
			addCriterion("vh_name <=", value, "vhName");
			return this;
		}

		public Criteria andVhNameLike(String value) {
			addCriterion("vh_name like", value, "vhName");
			return this;
		}

		public Criteria andVhNameNotLike(String value) {
			addCriterion("vh_name not like", value, "vhName");
			return this;
		}

		public Criteria andVhNameIn(List values) {
			addCriterion("vh_name in", values, "vhName");
			return this;
		}

		public Criteria andVhNameNotIn(List values) {
			addCriterion("vh_name not in", values, "vhName");
			return this;
		}

		public Criteria andVhNameBetween(String value1, String value2) {
			addCriterion("vh_name between", value1, value2, "vhName");
			return this;
		}

		public Criteria andVhNameNotBetween(String value1, String value2) {
			addCriterion("vh_name not between", value1, value2, "vhName");
			return this;
		}

		public Criteria andVhUuidIsNull() {
			addCriterion("vh_uuid is null");
			return this;
		}

		public Criteria andVhUuidIsNotNull() {
			addCriterion("vh_uuid is not null");
			return this;
		}

		public Criteria andVhUuidEqualTo(String value) {
			addCriterion("vh_uuid =", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidNotEqualTo(String value) {
			addCriterion("vh_uuid <>", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidGreaterThan(String value) {
			addCriterion("vh_uuid >", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidGreaterThanOrEqualTo(String value) {
			addCriterion("vh_uuid >=", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidLessThan(String value) {
			addCriterion("vh_uuid <", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidLessThanOrEqualTo(String value) {
			addCriterion("vh_uuid <=", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidLike(String value) {
			addCriterion("vh_uuid like", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidNotLike(String value) {
			addCriterion("vh_uuid not like", value, "vhUuid");
			return this;
		}

		public Criteria andVhUuidIn(List values) {
			addCriterion("vh_uuid in", values, "vhUuid");
			return this;
		}

		public Criteria andVhUuidNotIn(List values) {
			addCriterion("vh_uuid not in", values, "vhUuid");
			return this;
		}

		public Criteria andVhUuidBetween(String value1, String value2) {
			addCriterion("vh_uuid between", value1, value2, "vhUuid");
			return this;
		}

		public Criteria andVhUuidNotBetween(String value1, String value2) {
			addCriterion("vh_uuid not between", value1, value2, "vhUuid");
			return this;
		}

		public Criteria andVhIpIsNull() {
			addCriterion("vh_ip is null");
			return this;
		}

		public Criteria andVhIpIsNotNull() {
			addCriterion("vh_ip is not null");
			return this;
		}

		public Criteria andVhIpEqualTo(String value) {
			addCriterion("vh_ip =", value, "vhIp");
			return this;
		}

		public Criteria andVhIpNotEqualTo(String value) {
			addCriterion("vh_ip <>", value, "vhIp");
			return this;
		}

		public Criteria andVhIpGreaterThan(String value) {
			addCriterion("vh_ip >", value, "vhIp");
			return this;
		}

		public Criteria andVhIpGreaterThanOrEqualTo(String value) {
			addCriterion("vh_ip >=", value, "vhIp");
			return this;
		}

		public Criteria andVhIpLessThan(String value) {
			addCriterion("vh_ip <", value, "vhIp");
			return this;
		}

		public Criteria andVhIpLessThanOrEqualTo(String value) {
			addCriterion("vh_ip <=", value, "vhIp");
			return this;
		}

		public Criteria andVhIpLike(String value) {
			addCriterion("vh_ip like", value, "vhIp");
			return this;
		}

		public Criteria andVhIpNotLike(String value) {
			addCriterion("vh_ip not like", value, "vhIp");
			return this;
		}

		public Criteria andVhIpIn(List values) {
			addCriterion("vh_ip in", values, "vhIp");
			return this;
		}

		public Criteria andVhIpNotIn(List values) {
			addCriterion("vh_ip not in", values, "vhIp");
			return this;
		}

		public Criteria andVhIpBetween(String value1, String value2) {
			addCriterion("vh_ip between", value1, value2, "vhIp");
			return this;
		}

		public Criteria andVhIpNotBetween(String value1, String value2) {
			addCriterion("vh_ip not between", value1, value2, "vhIp");
			return this;
		}

		public Criteria andExecuteStartDateIsNull() {
			addCriterion("execute_start_date is null");
			return this;
		}

		public Criteria andExecuteStartDateIsNotNull() {
			addCriterion("execute_start_date is not null");
			return this;
		}

		public Criteria andExecuteStartDateEqualTo(Date value) {
			addCriterion("execute_start_date =", value, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateNotEqualTo(Date value) {
			addCriterion("execute_start_date <>", value, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateGreaterThan(Date value) {
			addCriterion("execute_start_date >", value, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateGreaterThanOrEqualTo(Date value) {
			addCriterion("execute_start_date >=", value, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateLessThan(Date value) {
			addCriterion("execute_start_date <", value, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateLessThanOrEqualTo(Date value) {
			addCriterion("execute_start_date <=", value, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateIn(List values) {
			addCriterion("execute_start_date in", values, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateNotIn(List values) {
			addCriterion("execute_start_date not in", values, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateBetween(Date value1, Date value2) {
			addCriterion("execute_start_date between", value1, value2, "executeStartDate");
			return this;
		}

		public Criteria andExecuteStartDateNotBetween(Date value1, Date value2) {
			addCriterion("execute_start_date not between", value1, value2, "executeStartDate");
			return this;
		}

		public Criteria andExecuteEndDateIsNull() {
			addCriterion("execute_end_date is null");
			return this;
		}

		public Criteria andExecuteEndDateIsNotNull() {
			addCriterion("execute_end_date is not null");
			return this;
		}

		public Criteria andExecuteEndDateEqualTo(Date value) {
			addCriterion("execute_end_date =", value, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateNotEqualTo(Date value) {
			addCriterion("execute_end_date <>", value, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateGreaterThan(Date value) {
			addCriterion("execute_end_date >", value, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateGreaterThanOrEqualTo(Date value) {
			addCriterion("execute_end_date >=", value, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateLessThan(Date value) {
			addCriterion("execute_end_date <", value, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateLessThanOrEqualTo(Date value) {
			addCriterion("execute_end_date <=", value, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateIn(List values) {
			addCriterion("execute_end_date in", values, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateNotIn(List values) {
			addCriterion("execute_end_date not in", values, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateBetween(Date value1, Date value2) {
			addCriterion("execute_end_date between", value1, value2, "executeEndDate");
			return this;
		}

		public Criteria andExecuteEndDateNotBetween(Date value1, Date value2) {
			addCriterion("execute_end_date not between", value1, value2, "executeEndDate");
			return this;
		}

		public Criteria andExecuteCronExpressionIsNull() {
			addCriterion("execute_cron_expression is null");
			return this;
		}

		public Criteria andExecuteCronExpressionIsNotNull() {
			addCriterion("execute_cron_expression is not null");
			return this;
		}

		public Criteria andExecuteCronExpressionEqualTo(String value) {
			addCriterion("execute_cron_expression =", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionNotEqualTo(String value) {
			addCriterion("execute_cron_expression <>", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionGreaterThan(String value) {
			addCriterion("execute_cron_expression >", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionGreaterThanOrEqualTo(String value) {
			addCriterion("execute_cron_expression >=", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionLessThan(String value) {
			addCriterion("execute_cron_expression <", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionLessThanOrEqualTo(String value) {
			addCriterion("execute_cron_expression <=", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionLike(String value) {
			addCriterion("execute_cron_expression like", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionNotLike(String value) {
			addCriterion("execute_cron_expression not like", value, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionIn(List values) {
			addCriterion("execute_cron_expression in", values, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionNotIn(List values) {
			addCriterion("execute_cron_expression not in", values, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionBetween(String value1, String value2) {
			addCriterion("execute_cron_expression between", value1, value2, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteCronExpressionNotBetween(String value1, String value2) {
			addCriterion("execute_cron_expression not between", value1, value2, "executeCronExpression");
			return this;
		}

		public Criteria andExecuteActionIsNull() {
			addCriterion("execute_action is null");
			return this;
		}

		public Criteria andExecuteActionIsNotNull() {
			addCriterion("execute_action is not null");
			return this;
		}

		public Criteria andExecuteActionEqualTo(String value) {
			addCriterion("execute_action =", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionNotEqualTo(String value) {
			addCriterion("execute_action <>", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionGreaterThan(String value) {
			addCriterion("execute_action >", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionGreaterThanOrEqualTo(String value) {
			addCriterion("execute_action >=", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionLessThan(String value) {
			addCriterion("execute_action <", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionLessThanOrEqualTo(String value) {
			addCriterion("execute_action <=", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionLike(String value) {
			addCriterion("execute_action like", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionNotLike(String value) {
			addCriterion("execute_action not like", value, "executeAction");
			return this;
		}

		public Criteria andExecuteActionIn(List values) {
			addCriterion("execute_action in", values, "executeAction");
			return this;
		}

		public Criteria andExecuteActionNotIn(List values) {
			addCriterion("execute_action not in", values, "executeAction");
			return this;
		}

		public Criteria andExecuteActionBetween(String value1, String value2) {
			addCriterion("execute_action between", value1, value2, "executeAction");
			return this;
		}

		public Criteria andExecuteActionNotBetween(String value1, String value2) {
			addCriterion("execute_action not between", value1, value2, "executeAction");
			return this;
		}

		public Criteria andExecuteStateIsNull() {
			addCriterion("execute_state is null");
			return this;
		}

		public Criteria andExecuteStateIsNotNull() {
			addCriterion("execute_state is not null");
			return this;
		}

		public Criteria andExecuteStateEqualTo(String value) {
			addCriterion("execute_state =", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateNotEqualTo(String value) {
			addCriterion("execute_state <>", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateGreaterThan(String value) {
			addCriterion("execute_state >", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateGreaterThanOrEqualTo(String value) {
			addCriterion("execute_state >=", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateLessThan(String value) {
			addCriterion("execute_state <", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateLessThanOrEqualTo(String value) {
			addCriterion("execute_state <=", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateLike(String value) {
			addCriterion("execute_state like", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateNotLike(String value) {
			addCriterion("execute_state not like", value, "executeState");
			return this;
		}

		public Criteria andExecuteStateIn(List values) {
			addCriterion("execute_state in", values, "executeState");
			return this;
		}

		public Criteria andExecuteStateNotIn(List values) {
			addCriterion("execute_state not in", values, "executeState");
			return this;
		}

		public Criteria andExecuteStateBetween(String value1, String value2) {
			addCriterion("execute_state between", value1, value2, "executeState");
			return this;
		}

		public Criteria andExecuteStateNotBetween(String value1, String value2) {
			addCriterion("execute_state not between", value1, value2, "executeState");
			return this;
		}

		public Criteria andExcludeDateIsNull() {
			addCriterion("exclude_date is null");
			return this;
		}

		public Criteria andExcludeDateIsNotNull() {
			addCriterion("exclude_date is not null");
			return this;
		}

		public Criteria andExcludeDateEqualTo(String value) {
			addCriterion("exclude_date =", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateNotEqualTo(String value) {
			addCriterion("exclude_date <>", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateGreaterThan(String value) {
			addCriterion("exclude_date >", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateGreaterThanOrEqualTo(String value) {
			addCriterion("exclude_date >=", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateLessThan(String value) {
			addCriterion("exclude_date <", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateLessThanOrEqualTo(String value) {
			addCriterion("exclude_date <=", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateLike(String value) {
			addCriterion("exclude_date like", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateNotLike(String value) {
			addCriterion("exclude_date not like", value, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateIn(List values) {
			addCriterion("exclude_date in", values, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateNotIn(List values) {
			addCriterion("exclude_date not in", values, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateBetween(String value1, String value2) {
			addCriterion("exclude_date between", value1, value2, "excludeDate");
			return this;
		}

		public Criteria andExcludeDateNotBetween(String value1, String value2) {
			addCriterion("exclude_date not between", value1, value2, "excludeDate");
			return this;
		}

		public Criteria andEveryTypeIsNull() {
			addCriterion("every_type is null");
			return this;
		}

		public Criteria andEveryTypeIsNotNull() {
			addCriterion("every_type is not null");
			return this;
		}

		public Criteria andEveryTypeEqualTo(String value) {
			addCriterion("every_type =", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeNotEqualTo(String value) {
			addCriterion("every_type <>", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeGreaterThan(String value) {
			addCriterion("every_type >", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeGreaterThanOrEqualTo(String value) {
			addCriterion("every_type >=", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeLessThan(String value) {
			addCriterion("every_type <", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeLessThanOrEqualTo(String value) {
			addCriterion("every_type <=", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeLike(String value) {
			addCriterion("every_type like", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeNotLike(String value) {
			addCriterion("every_type not like", value, "everyType");
			return this;
		}

		public Criteria andEveryTypeIn(List values) {
			addCriterion("every_type in", values, "everyType");
			return this;
		}

		public Criteria andEveryTypeNotIn(List values) {
			addCriterion("every_type not in", values, "everyType");
			return this;
		}

		public Criteria andEveryTypeBetween(String value1, String value2) {
			addCriterion("every_type between", value1, value2, "everyType");
			return this;
		}

		public Criteria andEveryTypeNotBetween(String value1, String value2) {
			addCriterion("every_type not between", value1, value2, "everyType");
			return this;
		}

		public Criteria andVmTypeIsNull() {
			addCriterion("vm_type is null");
			return this;
		}

		public Criteria andVmTypeIsNotNull() {
			addCriterion("vm_type is not null");
			return this;
		}

		public Criteria andVmTypeEqualTo(String value) {
			addCriterion("vm_type =", value, "vmType");
			return this;
		}

		public Criteria andVmTypeNotEqualTo(String value) {
			addCriterion("vm_type <>", value, "vmType");
			return this;
		}

		public Criteria andVmTypeGreaterThan(String value) {
			addCriterion("vm_type >", value, "vmType");
			return this;
		}

		public Criteria andVmTypeGreaterThanOrEqualTo(String value) {
			addCriterion("vm_type >=", value, "vmType");
			return this;
		}

		public Criteria andVmTypeLessThan(String value) {
			addCriterion("vm_type <", value, "vmType");
			return this;
		}

		public Criteria andVmTypeLessThanOrEqualTo(String value) {
			addCriterion("vm_type <=", value, "vmType");
			return this;
		}

		public Criteria andVmTypeLike(String value) {
			addCriterion("vm_type like", value, "vmType");
			return this;
		}

		public Criteria andVmTypeNotLike(String value) {
			addCriterion("vm_type not like", value, "vmType");
			return this;
		}

		public Criteria andVmTypeIn(List values) {
			addCriterion("vm_type in", values, "vmType");
			return this;
		}

		public Criteria andVmTypeNotIn(List values) {
			addCriterion("vm_type not in", values, "vmType");
			return this;
		}

		public Criteria andVmTypeBetween(String value1, String value2) {
			addCriterion("vm_type between", value1, value2, "vmType");
			return this;
		}

		public Criteria andVmTypeNotBetween(String value1, String value2) {
			addCriterion("vm_type not between", value1, value2, "vmType");
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

		public Criteria andCreatedDateIsNull() {
			addCriterion("created_date is null");
			return this;
		}

		public Criteria andCreatedDateIsNotNull() {
			addCriterion("created_date is not null");
			return this;
		}

		public Criteria andCreatedDateEqualTo(Date value) {
			addCriterion("created_date =", value, "createdDate");
			return this;
		}

		public Criteria andCreatedDateNotEqualTo(Date value) {
			addCriterion("created_date <>", value, "createdDate");
			return this;
		}

		public Criteria andCreatedDateGreaterThan(Date value) {
			addCriterion("created_date >", value, "createdDate");
			return this;
		}

		public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
			addCriterion("created_date >=", value, "createdDate");
			return this;
		}

		public Criteria andCreatedDateLessThan(Date value) {
			addCriterion("created_date <", value, "createdDate");
			return this;
		}

		public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
			addCriterion("created_date <=", value, "createdDate");
			return this;
		}

		public Criteria andCreatedDateIn(List values) {
			addCriterion("created_date in", values, "createdDate");
			return this;
		}

		public Criteria andCreatedDateNotIn(List values) {
			addCriterion("created_date not in", values, "createdDate");
			return this;
		}

		public Criteria andCreatedDateBetween(Date value1, Date value2) {
			addCriterion("created_date between", value1, value2, "createdDate");
			return this;
		}

		public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
			addCriterion("created_date not between", value1, value2, "createdDate");
			return this;
		}

		public Criteria andCreatedByIsNull() {
			addCriterion("created_by is null");
			return this;
		}

		public Criteria andCreatedByIsNotNull() {
			addCriterion("created_by is not null");
			return this;
		}

		public Criteria andCreatedByEqualTo(String value) {
			addCriterion("created_by =", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByNotEqualTo(String value) {
			addCriterion("created_by <>", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByGreaterThan(String value) {
			addCriterion("created_by >", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
			addCriterion("created_by >=", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByLessThan(String value) {
			addCriterion("created_by <", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByLessThanOrEqualTo(String value) {
			addCriterion("created_by <=", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByLike(String value) {
			addCriterion("created_by like", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByNotLike(String value) {
			addCriterion("created_by not like", value, "createdBy");
			return this;
		}

		public Criteria andCreatedByIn(List values) {
			addCriterion("created_by in", values, "createdBy");
			return this;
		}

		public Criteria andCreatedByNotIn(List values) {
			addCriterion("created_by not in", values, "createdBy");
			return this;
		}

		public Criteria andCreatedByBetween(String value1, String value2) {
			addCriterion("created_by between", value1, value2, "createdBy");
			return this;
		}

		public Criteria andCreatedByNotBetween(String value1, String value2) {
			addCriterion("created_by not between", value1, value2, "createdBy");
			return this;
		}

		public Criteria andLastUpdatedDateIsNull() {
			addCriterion("last_updated_date is null");
			return this;
		}

		public Criteria andLastUpdatedDateIsNotNull() {
			addCriterion("last_updated_date is not null");
			return this;
		}

		public Criteria andLastUpdatedDateEqualTo(Date value) {
			addCriterion("last_updated_date =", value, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateNotEqualTo(Date value) {
			addCriterion("last_updated_date <>", value, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateGreaterThan(Date value) {
			addCriterion("last_updated_date >", value, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateGreaterThanOrEqualTo(Date value) {
			addCriterion("last_updated_date >=", value, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateLessThan(Date value) {
			addCriterion("last_updated_date <", value, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateLessThanOrEqualTo(Date value) {
			addCriterion("last_updated_date <=", value, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateIn(List values) {
			addCriterion("last_updated_date in", values, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateNotIn(List values) {
			addCriterion("last_updated_date not in", values, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateBetween(Date value1, Date value2) {
			addCriterion("last_updated_date between", value1, value2, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedDateNotBetween(Date value1, Date value2) {
			addCriterion("last_updated_date not between", value1, value2, "lastUpdatedDate");
			return this;
		}

		public Criteria andLastUpdatedByIsNull() {
			addCriterion("last_updated_by is null");
			return this;
		}

		public Criteria andLastUpdatedByIsNotNull() {
			addCriterion("last_updated_by is not null");
			return this;
		}

		public Criteria andLastUpdatedByEqualTo(String value) {
			addCriterion("last_updated_by =", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByNotEqualTo(String value) {
			addCriterion("last_updated_by <>", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByGreaterThan(String value) {
			addCriterion("last_updated_by >", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByGreaterThanOrEqualTo(String value) {
			addCriterion("last_updated_by >=", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByLessThan(String value) {
			addCriterion("last_updated_by <", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByLessThanOrEqualTo(String value) {
			addCriterion("last_updated_by <=", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByLike(String value) {
			addCriterion("last_updated_by like", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByNotLike(String value) {
			addCriterion("last_updated_by not like", value, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByIn(List values) {
			addCriterion("last_updated_by in", values, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByNotIn(List values) {
			addCriterion("last_updated_by not in", values, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByBetween(String value1, String value2) {
			addCriterion("last_updated_by between", value1, value2, "lastUpdatedBy");
			return this;
		}

		public Criteria andLastUpdatedByNotBetween(String value1, String value2) {
			addCriterion("last_updated_by not between", value1, value2, "lastUpdatedBy");
			return this;
		}
	}
}