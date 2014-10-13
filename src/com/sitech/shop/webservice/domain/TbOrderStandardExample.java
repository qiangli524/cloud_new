package com.sitech.shop.webservice.domain;

import java.util.*;

public class TbOrderStandardExample {

	protected String orderByClause;

	protected List oredCriteria;

	public TbOrderStandardExample() {
		oredCriteria = new ArrayList();
	}

	protected TbOrderStandardExample(TbOrderStandardExample example) {
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

		public Criteria andPrdctSpecIdIsNull() {
			addCriterion("prdct_spec_id is null");
			return this;
		}

		public Criteria andPrdctSpecIdIsNotNull() {
			addCriterion("prdct_spec_id is not null");
			return this;
		}

		public Criteria andPrdctSpecIdEqualTo(String value) {
			addCriterion("prdct_spec_id =", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdNotEqualTo(String value) {
			addCriterion("prdct_spec_id <>", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdGreaterThan(String value) {
			addCriterion("prdct_spec_id >", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdGreaterThanOrEqualTo(String value) {
			addCriterion("prdct_spec_id >=", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdLessThan(String value) {
			addCriterion("prdct_spec_id <", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdLessThanOrEqualTo(String value) {
			addCriterion("prdct_spec_id <=", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdLike(String value) {
			addCriterion("prdct_spec_id like", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdNotLike(String value) {
			addCriterion("prdct_spec_id not like", value, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdIn(List values) {
			addCriterion("prdct_spec_id in", values, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdNotIn(List values) {
			addCriterion("prdct_spec_id not in", values, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdBetween(String value1, String value2) {
			addCriterion("prdct_spec_id between", value1, value2, "prdctSpecId");
			return this;
		}

		public Criteria andPrdctSpecIdNotBetween(String value1, String value2) {
			addCriterion("prdct_spec_id not between", value1, value2, "prdctSpecId");
			return this;
		}

		public Criteria andProductInstanceIdIsNull() {
			addCriterion("product_instance_id is null");
			return this;
		}

		public Criteria andProductInstanceIdIsNotNull() {
			addCriterion("product_instance_id is not null");
			return this;
		}

		public Criteria andProductInstanceIdEqualTo(String value) {
			addCriterion("product_instance_id =", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdNotEqualTo(String value) {
			addCriterion("product_instance_id <>", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdGreaterThan(String value) {
			addCriterion("product_instance_id >", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdGreaterThanOrEqualTo(String value) {
			addCriterion("product_instance_id >=", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdLessThan(String value) {
			addCriterion("product_instance_id <", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdLessThanOrEqualTo(String value) {
			addCriterion("product_instance_id <=", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdLike(String value) {
			addCriterion("product_instance_id like", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdNotLike(String value) {
			addCriterion("product_instance_id not like", value, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdIn(List values) {
			addCriterion("product_instance_id in", values, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdNotIn(List values) {
			addCriterion("product_instance_id not in", values, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdBetween(String value1, String value2) {
			addCriterion("product_instance_id between", value1, value2, "productInstanceId");
			return this;
		}

		public Criteria andProductInstanceIdNotBetween(String value1, String value2) {
			addCriterion("product_instance_id not between", value1, value2, "productInstanceId");
			return this;
		}

		public Criteria andSpecIdIsNull() {
			addCriterion("spec_id is null");
			return this;
		}

		public Criteria andSpecIdIsNotNull() {
			addCriterion("spec_id is not null");
			return this;
		}

		public Criteria andSpecIdEqualTo(String value) {
			addCriterion("spec_id =", value, "specId");
			return this;
		}

		public Criteria andSpecIdNotEqualTo(String value) {
			addCriterion("spec_id <>", value, "specId");
			return this;
		}

		public Criteria andSpecIdGreaterThan(String value) {
			addCriterion("spec_id >", value, "specId");
			return this;
		}

		public Criteria andSpecIdGreaterThanOrEqualTo(String value) {
			addCriterion("spec_id >=", value, "specId");
			return this;
		}

		public Criteria andSpecIdLessThan(String value) {
			addCriterion("spec_id <", value, "specId");
			return this;
		}

		public Criteria andSpecIdLessThanOrEqualTo(String value) {
			addCriterion("spec_id <=", value, "specId");
			return this;
		}

		public Criteria andSpecIdLike(String value) {
			addCriterion("spec_id like", value, "specId");
			return this;
		}

		public Criteria andSpecIdNotLike(String value) {
			addCriterion("spec_id not like", value, "specId");
			return this;
		}

		public Criteria andSpecIdIn(List values) {
			addCriterion("spec_id in", values, "specId");
			return this;
		}

		public Criteria andSpecIdNotIn(List values) {
			addCriterion("spec_id not in", values, "specId");
			return this;
		}

		public Criteria andSpecIdBetween(String value1, String value2) {
			addCriterion("spec_id between", value1, value2, "specId");
			return this;
		}

		public Criteria andSpecIdNotBetween(String value1, String value2) {
			addCriterion("spec_id not between", value1, value2, "specId");
			return this;
		}

		public Criteria andSpecValueIsNull() {
			addCriterion("spec_value is null");
			return this;
		}

		public Criteria andSpecValueIsNotNull() {
			addCriterion("spec_value is not null");
			return this;
		}

		public Criteria andSpecValueEqualTo(String value) {
			addCriterion("spec_value =", value, "specValue");
			return this;
		}

		public Criteria andSpecValueNotEqualTo(String value) {
			addCriterion("spec_value <>", value, "specValue");
			return this;
		}

		public Criteria andSpecValueGreaterThan(String value) {
			addCriterion("spec_value >", value, "specValue");
			return this;
		}

		public Criteria andSpecValueGreaterThanOrEqualTo(String value) {
			addCriterion("spec_value >=", value, "specValue");
			return this;
		}

		public Criteria andSpecValueLessThan(String value) {
			addCriterion("spec_value <", value, "specValue");
			return this;
		}

		public Criteria andSpecValueLessThanOrEqualTo(String value) {
			addCriterion("spec_value <=", value, "specValue");
			return this;
		}

		public Criteria andSpecValueLike(String value) {
			addCriterion("spec_value like", value, "specValue");
			return this;
		}

		public Criteria andSpecValueNotLike(String value) {
			addCriterion("spec_value not like", value, "specValue");
			return this;
		}

		public Criteria andSpecValueIn(List values) {
			addCriterion("spec_value in", values, "specValue");
			return this;
		}

		public Criteria andSpecValueNotIn(List values) {
			addCriterion("spec_value not in", values, "specValue");
			return this;
		}

		public Criteria andSpecValueBetween(String value1, String value2) {
			addCriterion("spec_value between", value1, value2, "specValue");
			return this;
		}

		public Criteria andSpecValueNotBetween(String value1, String value2) {
			addCriterion("spec_value not between", value1, value2, "specValue");
			return this;
		}

		public Criteria andSpecValueDescIsNull() {
			addCriterion("spec_value_desc is null");
			return this;
		}

		public Criteria andSpecValueDescIsNotNull() {
			addCriterion("spec_value_desc is not null");
			return this;
		}

		public Criteria andSpecValueDescEqualTo(String value) {
			addCriterion("spec_value_desc =", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescNotEqualTo(String value) {
			addCriterion("spec_value_desc <>", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescGreaterThan(String value) {
			addCriterion("spec_value_desc >", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescGreaterThanOrEqualTo(String value) {
			addCriterion("spec_value_desc >=", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescLessThan(String value) {
			addCriterion("spec_value_desc <", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescLessThanOrEqualTo(String value) {
			addCriterion("spec_value_desc <=", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescLike(String value) {
			addCriterion("spec_value_desc like", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescNotLike(String value) {
			addCriterion("spec_value_desc not like", value, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescIn(List values) {
			addCriterion("spec_value_desc in", values, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescNotIn(List values) {
			addCriterion("spec_value_desc not in", values, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescBetween(String value1, String value2) {
			addCriterion("spec_value_desc between", value1, value2, "specValueDesc");
			return this;
		}

		public Criteria andSpecValueDescNotBetween(String value1, String value2) {
			addCriterion("spec_value_desc not between", value1, value2, "specValueDesc");
			return this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return this;
		}

		public Criteria andUserIdEqualTo(String value) {
			addCriterion("user_id =", value, "userId");
			return this;
		}

		public Criteria andUserIdNotEqualTo(String value) {
			addCriterion("user_id <>", value, "userId");
			return this;
		}

		public Criteria andUserIdGreaterThan(String value) {
			addCriterion("user_id >", value, "userId");
			return this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("user_id >=", value, "userId");
			return this;
		}

		public Criteria andUserIdLessThan(String value) {
			addCriterion("user_id <", value, "userId");
			return this;
		}

		public Criteria andUserIdLessThanOrEqualTo(String value) {
			addCriterion("user_id <=", value, "userId");
			return this;
		}

		public Criteria andUserIdLike(String value) {
			addCriterion("user_id like", value, "userId");
			return this;
		}

		public Criteria andUserIdNotLike(String value) {
			addCriterion("user_id not like", value, "userId");
			return this;
		}

		public Criteria andUserIdIn(List values) {
			addCriterion("user_id in", values, "userId");
			return this;
		}

		public Criteria andUserIdNotIn(List values) {
			addCriterion("user_id not in", values, "userId");
			return this;
		}

		public Criteria andUserIdBetween(String value1, String value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return this;
		}

		public Criteria andUserIdNotBetween(String value1, String value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return this;
		}

		public Criteria andBookIdIsNull() {
			addCriterion("book_id is null");
			return this;
		}

		public Criteria andBookIdIsNotNull() {
			addCriterion("book_id is not null");
			return this;
		}

		public Criteria andBookIdEqualTo(String value) {
			addCriterion("book_id =", value, "bookId");
			return this;
		}

		public Criteria andBookIdNotEqualTo(String value) {
			addCriterion("book_id <>", value, "bookId");
			return this;
		}

		public Criteria andBookIdGreaterThan(String value) {
			addCriterion("book_id >", value, "bookId");
			return this;
		}

		public Criteria andBookIdGreaterThanOrEqualTo(String value) {
			addCriterion("book_id >=", value, "bookId");
			return this;
		}

		public Criteria andBookIdLessThan(String value) {
			addCriterion("book_id <", value, "bookId");
			return this;
		}

		public Criteria andBookIdLessThanOrEqualTo(String value) {
			addCriterion("book_id <=", value, "bookId");
			return this;
		}

		public Criteria andBookIdLike(String value) {
			addCriterion("book_id like", value, "bookId");
			return this;
		}

		public Criteria andBookIdNotLike(String value) {
			addCriterion("book_id not like", value, "bookId");
			return this;
		}

		public Criteria andBookIdIn(List values) {
			addCriterion("book_id in", values, "bookId");
			return this;
		}

		public Criteria andBookIdNotIn(List values) {
			addCriterion("book_id not in", values, "bookId");
			return this;
		}

		public Criteria andBookIdBetween(String value1, String value2) {
			addCriterion("book_id between", value1, value2, "bookId");
			return this;
		}

		public Criteria andBookIdNotBetween(String value1, String value2) {
			addCriterion("book_id not between", value1, value2, "bookId");
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
        public Criteria andDurationUnitIsNull() {
            addCriterion("duration_unit is null");
            return this;
        }

        public Criteria andDurationUnitIsNotNull() {
            addCriterion("duration_unit is not null");
            return this;
        }

        public Criteria andDurationUnitEqualTo(String value) {
            addCriterion("duration_unit =", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitNotEqualTo(String value) {
            addCriterion("duration_unit <>", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitGreaterThan(String value) {
            addCriterion("duration_unit >", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitGreaterThanOrEqualTo(String value) {
            addCriterion("duration_unit >=", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitLessThan(String value) {
            addCriterion("duration_unit <", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitLessThanOrEqualTo(String value) {
            addCriterion("duration_unit <=", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitLike(String value) {
            addCriterion("duration_unit like", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitNotLike(String value) {
            addCriterion("duration_unit not like", value, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitIn(List values) {
            addCriterion("duration_unit in", values, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitNotIn(List values) {
            addCriterion("duration_unit not in", values, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitBetween(String value1, String value2) {
            addCriterion("duration_unit between", value1, value2, "durationUnit");
            return this;
        }

        public Criteria andDurationUnitNotBetween(String value1, String value2) {
            addCriterion("duration_unit not between", value1, value2, "durationUnit");
            return this;
        }

        public Criteria andPayDurationIsNull() {
            addCriterion("pay_duration is null");
            return this;
        }

        public Criteria andPayDurationIsNotNull() {
            addCriterion("pay_duration is not null");
            return this;
        }

        public Criteria andPayDurationEqualTo(String value) {
            addCriterion("pay_duration =", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationNotEqualTo(String value) {
            addCriterion("pay_duration <>", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationGreaterThan(String value) {
            addCriterion("pay_duration >", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationGreaterThanOrEqualTo(String value) {
            addCriterion("pay_duration >=", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationLessThan(String value) {
            addCriterion("pay_duration <", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationLessThanOrEqualTo(String value) {
            addCriterion("pay_duration <=", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationLike(String value) {
            addCriterion("pay_duration like", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationNotLike(String value) {
            addCriterion("pay_duration not like", value, "payDuration");
            return this;
        }

        public Criteria andPayDurationIn(List values) {
            addCriterion("pay_duration in", values, "payDuration");
            return this;
        }

        public Criteria andPayDurationNotIn(List values) {
            addCriterion("pay_duration not in", values, "payDuration");
            return this;
        }

        public Criteria andPayDurationBetween(String value1, String value2) {
            addCriterion("pay_duration between", value1, value2, "payDuration");
            return this;
        }

        public Criteria andPayDurationNotBetween(String value1, String value2) {
            addCriterion("pay_duration not between", value1, value2, "payDuration");
            return this;
        }

        public Criteria andExpiringDateIsNull() {
            addCriterion("expiring_date is null");
            return this;
        }

        public Criteria andExpiringDateIsNotNull() {
            addCriterion("expiring_date is not null");
            return this;
        }

        public Criteria andExpiringDateEqualTo(Date value) {
            addCriterion("expiring_date =", value, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateNotEqualTo(Date value) {
            addCriterion("expiring_date <>", value, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateGreaterThan(Date value) {
            addCriterion("expiring_date >", value, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateGreaterThanOrEqualTo(Date value) {
            addCriterion("expiring_date >=", value, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateLessThan(Date value) {
            addCriterion("expiring_date <", value, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateLessThanOrEqualTo(Date value) {
            addCriterion("expiring_date <=", value, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateIn(List values) {
            addCriterion("expiring_date in", values, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateNotIn(List values) {
            addCriterion("expiring_date not in", values, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateBetween(Date value1, Date value2) {
            addCriterion("expiring_date between", value1, value2, "expiringDate");
            return this;
        }

        public Criteria andExpiringDateNotBetween(Date value1, Date value2) {
            addCriterion("expiring_date not between", value1, value2, "expiringDate");
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