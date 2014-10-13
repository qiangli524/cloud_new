package com.sitech.shop.webservice.domain;

import java.util.*;

public class TbOrderRelationExample {

	protected String orderByClause;

	protected List oredCriteria;

	public TbOrderRelationExample() {
		oredCriteria = new ArrayList();
	}

	protected TbOrderRelationExample(TbOrderRelationExample example) {
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

		public Criteria andUuidIsNull() {
			addCriterion("uuid is null");
			return this;
		}

		public Criteria andUuidIsNotNull() {
			addCriterion("uuid is not null");
			return this;
		}

		public Criteria andUuidEqualTo(String value) {
			addCriterion("uuid =", value, "uuid");
			return this;
		}

		public Criteria andUuidNotEqualTo(String value) {
			addCriterion("uuid <>", value, "uuid");
			return this;
		}

		public Criteria andUuidGreaterThan(String value) {
			addCriterion("uuid >", value, "uuid");
			return this;
		}

		public Criteria andUuidGreaterThanOrEqualTo(String value) {
			addCriterion("uuid >=", value, "uuid");
			return this;
		}

		public Criteria andUuidLessThan(String value) {
			addCriterion("uuid <", value, "uuid");
			return this;
		}

		public Criteria andUuidLessThanOrEqualTo(String value) {
			addCriterion("uuid <=", value, "uuid");
			return this;
		}

		public Criteria andUuidLike(String value) {
			addCriterion("uuid like", value, "uuid");
			return this;
		}

		public Criteria andUuidNotLike(String value) {
			addCriterion("uuid not like", value, "uuid");
			return this;
		}

		public Criteria andUuidIn(List values) {
			addCriterion("uuid in", values, "uuid");
			return this;
		}

		public Criteria andUuidNotIn(List values) {
			addCriterion("uuid not in", values, "uuid");
			return this;
		}

		public Criteria andUuidBetween(String value1, String value2) {
			addCriterion("uuid between", value1, value2, "uuid");
			return this;
		}

		public Criteria andUuidNotBetween(String value1, String value2) {
			addCriterion("uuid not between", value1, value2, "uuid");
			return this;
		}

		public Criteria andOrderIdIsNull() {
			addCriterion("order_id is null");
			return this;
		}

		public Criteria andOrderIdIsNotNull() {
			addCriterion("order_id is not null");
			return this;
		}

		public Criteria andOrderIdEqualTo(String value) {
			addCriterion("order_id =", value, "orderId");
			return this;
		}

		public Criteria andOrderIdNotEqualTo(String value) {
			addCriterion("order_id <>", value, "orderId");
			return this;
		}

		public Criteria andOrderIdGreaterThan(String value) {
			addCriterion("order_id >", value, "orderId");
			return this;
		}

		public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
			addCriterion("order_id >=", value, "orderId");
			return this;
		}

		public Criteria andOrderIdLessThan(String value) {
			addCriterion("order_id <", value, "orderId");
			return this;
		}

		public Criteria andOrderIdLessThanOrEqualTo(String value) {
			addCriterion("order_id <=", value, "orderId");
			return this;
		}

		public Criteria andOrderIdLike(String value) {
			addCriterion("order_id like", value, "orderId");
			return this;
		}

		public Criteria andOrderIdNotLike(String value) {
			addCriterion("order_id not like", value, "orderId");
			return this;
		}

		public Criteria andOrderIdIn(List values) {
			addCriterion("order_id in", values, "orderId");
			return this;
		}

		public Criteria andOrderIdNotIn(List values) {
			addCriterion("order_id not in", values, "orderId");
			return this;
		}

		public Criteria andOrderIdBetween(String value1, String value2) {
			addCriterion("order_id between", value1, value2, "orderId");
			return this;
		}

		public Criteria andOrderIdNotBetween(String value1, String value2) {
			addCriterion("order_id not between", value1, value2, "orderId");
			return this;
		}

		public Criteria andOrderRelationInsidIsNull() {
			addCriterion("order_relation_insid is null");
			return this;
		}

		public Criteria andOrderRelationInsidIsNotNull() {
			addCriterion("order_relation_insid is not null");
			return this;
		}

		public Criteria andOrderRelationInsidEqualTo(String value) {
			addCriterion("order_relation_insid =", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidNotEqualTo(String value) {
			addCriterion("order_relation_insid <>", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidGreaterThan(String value) {
			addCriterion("order_relation_insid >", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidGreaterThanOrEqualTo(String value) {
			addCriterion("order_relation_insid >=", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidLessThan(String value) {
			addCriterion("order_relation_insid <", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidLessThanOrEqualTo(String value) {
			addCriterion("order_relation_insid <=", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidLike(String value) {
			addCriterion("order_relation_insid like", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidNotLike(String value) {
			addCriterion("order_relation_insid not like", value, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidIn(List values) {
			addCriterion("order_relation_insid in", values, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidNotIn(List values) {
			addCriterion("order_relation_insid not in", values, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidBetween(String value1, String value2) {
			addCriterion("order_relation_insid between", value1, value2, "orderRelationInsid");
			return this;
		}

		public Criteria andOrderRelationInsidNotBetween(String value1, String value2) {
			addCriterion("order_relation_insid not between", value1, value2, "orderRelationInsid");
			return this;
		}

		public Criteria andMasterRelationIdIsNull() {
			addCriterion("master_relation_id is null");
			return this;
		}

		public Criteria andMasterRelationIdIsNotNull() {
			addCriterion("master_relation_id is not null");
			return this;
		}

		public Criteria andMasterRelationIdEqualTo(String value) {
			addCriterion("master_relation_id =", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdNotEqualTo(String value) {
			addCriterion("master_relation_id <>", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdGreaterThan(String value) {
			addCriterion("master_relation_id >", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdGreaterThanOrEqualTo(String value) {
			addCriterion("master_relation_id >=", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdLessThan(String value) {
			addCriterion("master_relation_id <", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdLessThanOrEqualTo(String value) {
			addCriterion("master_relation_id <=", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdLike(String value) {
			addCriterion("master_relation_id like", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdNotLike(String value) {
			addCriterion("master_relation_id not like", value, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdIn(List values) {
			addCriterion("master_relation_id in", values, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdNotIn(List values) {
			addCriterion("master_relation_id not in", values, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdBetween(String value1, String value2) {
			addCriterion("master_relation_id between", value1, value2, "masterRelationId");
			return this;
		}

		public Criteria andMasterRelationIdNotBetween(String value1, String value2) {
			addCriterion("master_relation_id not between", value1, value2, "masterRelationId");
			return this;
		}

		public Criteria andChildRelationIdIsNull() {
			addCriterion("child_relation_id is null");
			return this;
		}

		public Criteria andChildRelationIdIsNotNull() {
			addCriterion("child_relation_id is not null");
			return this;
		}

		public Criteria andChildRelationIdEqualTo(String value) {
			addCriterion("child_relation_id =", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdNotEqualTo(String value) {
			addCriterion("child_relation_id <>", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdGreaterThan(String value) {
			addCriterion("child_relation_id >", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdGreaterThanOrEqualTo(String value) {
			addCriterion("child_relation_id >=", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdLessThan(String value) {
			addCriterion("child_relation_id <", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdLessThanOrEqualTo(String value) {
			addCriterion("child_relation_id <=", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdLike(String value) {
			addCriterion("child_relation_id like", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdNotLike(String value) {
			addCriterion("child_relation_id not like", value, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdIn(List values) {
			addCriterion("child_relation_id in", values, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdNotIn(List values) {
			addCriterion("child_relation_id not in", values, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdBetween(String value1, String value2) {
			addCriterion("child_relation_id between", value1, value2, "childRelationId");
			return this;
		}

		public Criteria andChildRelationIdNotBetween(String value1, String value2) {
			addCriterion("child_relation_id not between", value1, value2, "childRelationId");
			return this;
		}

		public Criteria andRelationTypeIsNull() {
			addCriterion("relation_type is null");
			return this;
		}

		public Criteria andRelationTypeIsNotNull() {
			addCriterion("relation_type is not null");
			return this;
		}

		public Criteria andRelationTypeEqualTo(String value) {
			addCriterion("relation_type =", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeNotEqualTo(String value) {
			addCriterion("relation_type <>", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeGreaterThan(String value) {
			addCriterion("relation_type >", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeGreaterThanOrEqualTo(String value) {
			addCriterion("relation_type >=", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeLessThan(String value) {
			addCriterion("relation_type <", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeLessThanOrEqualTo(String value) {
			addCriterion("relation_type <=", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeLike(String value) {
			addCriterion("relation_type like", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeNotLike(String value) {
			addCriterion("relation_type not like", value, "relationType");
			return this;
		}

		public Criteria andRelationTypeIn(List values) {
			addCriterion("relation_type in", values, "relationType");
			return this;
		}

		public Criteria andRelationTypeNotIn(List values) {
			addCriterion("relation_type not in", values, "relationType");
			return this;
		}

		public Criteria andRelationTypeBetween(String value1, String value2) {
			addCriterion("relation_type between", value1, value2, "relationType");
			return this;
		}

		public Criteria andRelationTypeNotBetween(String value1, String value2) {
			addCriterion("relation_type not between", value1, value2, "relationType");
			return this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return this;
		}

		public Criteria andCreateDateIn(List values) {
			addCriterion("create_date in", values, "createDate");
			return this;
		}

		public Criteria andCreateDateNotIn(List values) {
			addCriterion("create_date not in", values, "createDate");
			return this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return this;
		}

		public Criteria andLastUpdateDateIsNull() {
			addCriterion("last_update_date is null");
			return this;
		}

		public Criteria andLastUpdateDateIsNotNull() {
			addCriterion("last_update_date is not null");
			return this;
		}

		public Criteria andLastUpdateDateEqualTo(Date value) {
			addCriterion("last_update_date =", value, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateNotEqualTo(Date value) {
			addCriterion("last_update_date <>", value, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateGreaterThan(Date value) {
			addCriterion("last_update_date >", value, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("last_update_date >=", value, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateLessThan(Date value) {
			addCriterion("last_update_date <", value, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateLessThanOrEqualTo(Date value) {
			addCriterion("last_update_date <=", value, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateIn(List values) {
			addCriterion("last_update_date in", values, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateNotIn(List values) {
			addCriterion("last_update_date not in", values, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateBetween(Date value1, Date value2) {
			addCriterion("last_update_date between", value1, value2, "lastUpdateDate");
			return this;
		}

		public Criteria andLastUpdateDateNotBetween(Date value1, Date value2) {
			addCriterion("last_update_date not between", value1, value2, "lastUpdateDate");
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