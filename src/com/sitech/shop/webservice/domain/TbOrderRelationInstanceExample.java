package com.sitech.shop.webservice.domain;

import java.util.*;

public class TbOrderRelationInstanceExample extends TbOrderRelationInstance{

	private static final long serialVersionUID = 1L;

	protected String orderByClause;

	protected List oredCriteria;

	public TbOrderRelationInstanceExample() {
		oredCriteria = new ArrayList();
	}

	protected TbOrderRelationInstanceExample(TbOrderRelationInstanceExample example) {
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

		public Criteria andCustomerIdIsNull() {
			addCriterion("customer_id is null");
			return this;
		}

		public Criteria andCustomerIdIsNotNull() {
			addCriterion("customer_id is not null");
			return this;
		}

		public Criteria andCustomerIdEqualTo(String value) {
			addCriterion("customer_id =", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdNotEqualTo(String value) {
			addCriterion("customer_id <>", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdGreaterThan(String value) {
			addCriterion("customer_id >", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
			addCriterion("customer_id >=", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdLessThan(String value) {
			addCriterion("customer_id <", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdLessThanOrEqualTo(String value) {
			addCriterion("customer_id <=", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdLike(String value) {
			addCriterion("customer_id like", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdNotLike(String value) {
			addCriterion("customer_id not like", value, "customerId");
			return this;
		}

		public Criteria andCustomerIdIn(List values) {
			addCriterion("customer_id in", values, "customerId");
			return this;
		}

		public Criteria andCustomerIdNotIn(List values) {
			addCriterion("customer_id not in", values, "customerId");
			return this;
		}

		public Criteria andCustomerIdBetween(String value1, String value2) {
			addCriterion("customer_id between", value1, value2, "customerId");
			return this;
		}

		public Criteria andCustomerIdNotBetween(String value1, String value2) {
			addCriterion("customer_id not between", value1, value2, "customerId");
			return this;
		}

		public Criteria andIfPrimaryIsNull() {
			addCriterion("if_primary is null");
			return this;
		}

		public Criteria andIfPrimaryIsNotNull() {
			addCriterion("if_primary is not null");
			return this;
		}

		public Criteria andIfPrimaryEqualTo(String value) {
			addCriterion("if_primary =", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryNotEqualTo(String value) {
			addCriterion("if_primary <>", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryGreaterThan(String value) {
			addCriterion("if_primary >", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryGreaterThanOrEqualTo(String value) {
			addCriterion("if_primary >=", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryLessThan(String value) {
			addCriterion("if_primary <", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryLessThanOrEqualTo(String value) {
			addCriterion("if_primary <=", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryLike(String value) {
			addCriterion("if_primary like", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryNotLike(String value) {
			addCriterion("if_primary not like", value, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryIn(List values) {
			addCriterion("if_primary in", values, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryNotIn(List values) {
			addCriterion("if_primary not in", values, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryBetween(String value1, String value2) {
			addCriterion("if_primary between", value1, value2, "ifPrimary");
			return this;
		}

		public Criteria andIfPrimaryNotBetween(String value1, String value2) {
			addCriterion("if_primary not between", value1, value2, "ifPrimary");
			return this;
		}

		public Criteria andProductIdIsNull() {
			addCriterion("product_id is null");
			return this;
		}

		public Criteria andProductIdIsNotNull() {
			addCriterion("product_id is not null");
			return this;
		}

		public Criteria andProductIdEqualTo(String value) {
			addCriterion("product_id =", value, "productId");
			return this;
		}

		public Criteria andProductIdNotEqualTo(String value) {
			addCriterion("product_id <>", value, "productId");
			return this;
		}

		public Criteria andProductIdGreaterThan(String value) {
			addCriterion("product_id >", value, "productId");
			return this;
		}

		public Criteria andProductIdGreaterThanOrEqualTo(String value) {
			addCriterion("product_id >=", value, "productId");
			return this;
		}

		public Criteria andProductIdLessThan(String value) {
			addCriterion("product_id <", value, "productId");
			return this;
		}

		public Criteria andProductIdLessThanOrEqualTo(String value) {
			addCriterion("product_id <=", value, "productId");
			return this;
		}

		public Criteria andProductIdLike(String value) {
			addCriterion("product_id like", value, "productId");
			return this;
		}

		public Criteria andProductIdNotLike(String value) {
			addCriterion("product_id not like", value, "productId");
			return this;
		}

		public Criteria andProductIdIn(List values) {
			addCriterion("product_id in", values, "productId");
			return this;
		}

		public Criteria andProductIdNotIn(List values) {
			addCriterion("product_id not in", values, "productId");
			return this;
		}

		public Criteria andProductIdBetween(String value1, String value2) {
			addCriterion("product_id between", value1, value2, "productId");
			return this;
		}

		public Criteria andProductIdNotBetween(String value1, String value2) {
			addCriterion("product_id not between", value1, value2, "productId");
			return this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return this;
		}

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return this;
		}

		public Criteria andStatusIn(List values) {
			addCriterion("status in", values, "status");
			return this;
		}

		public Criteria andStatusNotIn(List values) {
			addCriterion("status not in", values, "status");
			return this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
			return this;
		}

		public Criteria andAcctIdIsNull() {
			addCriterion("acct_id is null");
			return this;
		}

		public Criteria andAcctIdIsNotNull() {
			addCriterion("acct_id is not null");
			return this;
		}

		public Criteria andAcctIdEqualTo(String value) {
			addCriterion("acct_id =", value, "acctId");
			return this;
		}

		public Criteria andAcctIdNotEqualTo(String value) {
			addCriterion("acct_id <>", value, "acctId");
			return this;
		}

		public Criteria andAcctIdGreaterThan(String value) {
			addCriterion("acct_id >", value, "acctId");
			return this;
		}

		public Criteria andAcctIdGreaterThanOrEqualTo(String value) {
			addCriterion("acct_id >=", value, "acctId");
			return this;
		}

		public Criteria andAcctIdLessThan(String value) {
			addCriterion("acct_id <", value, "acctId");
			return this;
		}

		public Criteria andAcctIdLessThanOrEqualTo(String value) {
			addCriterion("acct_id <=", value, "acctId");
			return this;
		}

		public Criteria andAcctIdLike(String value) {
			addCriterion("acct_id like", value, "acctId");
			return this;
		}

		public Criteria andAcctIdNotLike(String value) {
			addCriterion("acct_id not like", value, "acctId");
			return this;
		}

		public Criteria andAcctIdIn(List values) {
			addCriterion("acct_id in", values, "acctId");
			return this;
		}

		public Criteria andAcctIdNotIn(List values) {
			addCriterion("acct_id not in", values, "acctId");
			return this;
		}

		public Criteria andAcctIdBetween(String value1, String value2) {
			addCriterion("acct_id between", value1, value2, "acctId");
			return this;
		}

		public Criteria andAcctIdNotBetween(String value1, String value2) {
			addCriterion("acct_id not between", value1, value2, "acctId");
			return this;
		}

		public Criteria andServIdIsNull() {
			addCriterion("serv_id is null");
			return this;
		}

		public Criteria andServIdIsNotNull() {
			addCriterion("serv_id is not null");
			return this;
		}

		public Criteria andServIdEqualTo(String value) {
			addCriterion("serv_id =", value, "servId");
			return this;
		}

		public Criteria andServIdNotEqualTo(String value) {
			addCriterion("serv_id <>", value, "servId");
			return this;
		}

		public Criteria andServIdGreaterThan(String value) {
			addCriterion("serv_id >", value, "servId");
			return this;
		}

		public Criteria andServIdGreaterThanOrEqualTo(String value) {
			addCriterion("serv_id >=", value, "servId");
			return this;
		}

		public Criteria andServIdLessThan(String value) {
			addCriterion("serv_id <", value, "servId");
			return this;
		}

		public Criteria andServIdLessThanOrEqualTo(String value) {
			addCriterion("serv_id <=", value, "servId");
			return this;
		}

		public Criteria andServIdLike(String value) {
			addCriterion("serv_id like", value, "servId");
			return this;
		}

		public Criteria andServIdNotLike(String value) {
			addCriterion("serv_id not like", value, "servId");
			return this;
		}

		public Criteria andServIdIn(List values) {
			addCriterion("serv_id in", values, "servId");
			return this;
		}

		public Criteria andServIdNotIn(List values) {
			addCriterion("serv_id not in", values, "servId");
			return this;
		}

		public Criteria andServIdBetween(String value1, String value2) {
			addCriterion("serv_id between", value1, value2, "servId");
			return this;
		}

		public Criteria andServIdNotBetween(String value1, String value2) {
			addCriterion("serv_id not between", value1, value2, "servId");
			return this;
		}

		public Criteria andCreateStatusIsNull() {
			addCriterion("create_status is null");
			return this;
		}

		public Criteria andCreateStatusIsNotNull() {
			addCriterion("create_status is not null");
			return this;
		}

		public Criteria andCreateStatusEqualTo(String value) {
			addCriterion("create_status =", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusNotEqualTo(String value) {
			addCriterion("create_status <>", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusGreaterThan(String value) {
			addCriterion("create_status >", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusGreaterThanOrEqualTo(String value) {
			addCriterion("create_status >=", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusLessThan(String value) {
			addCriterion("create_status <", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusLessThanOrEqualTo(String value) {
			addCriterion("create_status <=", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusLike(String value) {
			addCriterion("create_status like", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusNotLike(String value) {
			addCriterion("create_status not like", value, "createStatus");
			return this;
		}

		public Criteria andCreateStatusIn(List values) {
			addCriterion("create_status in", values, "createStatus");
			return this;
		}

		public Criteria andCreateStatusNotIn(List values) {
			addCriterion("create_status not in", values, "createStatus");
			return this;
		}

		public Criteria andCreateStatusBetween(String value1, String value2) {
			addCriterion("create_status between", value1, value2, "createStatus");
			return this;
		}

		public Criteria andCreateStatusNotBetween(String value1, String value2) {
			addCriterion("create_status not between", value1, value2, "createStatus");
			return this;
		}

		public Criteria andVmIdIsNull() {
			addCriterion("vm_id is null");
			return this;
		}

		public Criteria andVmIdIsNotNull() {
			addCriterion("vm_id is not null");
			return this;
		}

		public Criteria andVmIdEqualTo(String value) {
			addCriterion("vm_id =", value, "vmId");
			return this;
		}

		public Criteria andVmIdNotEqualTo(String value) {
			addCriterion("vm_id <>", value, "vmId");
			return this;
		}

		public Criteria andVmIdGreaterThan(String value) {
			addCriterion("vm_id >", value, "vmId");
			return this;
		}

		public Criteria andVmIdGreaterThanOrEqualTo(String value) {
			addCriterion("vm_id >=", value, "vmId");
			return this;
		}

		public Criteria andVmIdLessThan(String value) {
			addCriterion("vm_id <", value, "vmId");
			return this;
		}

		public Criteria andVmIdLessThanOrEqualTo(String value) {
			addCriterion("vm_id <=", value, "vmId");
			return this;
		}

		public Criteria andVmIdLike(String value) {
			addCriterion("vm_id like", value, "vmId");
			return this;
		}

		public Criteria andVmIdNotLike(String value) {
			addCriterion("vm_id not like", value, "vmId");
			return this;
		}

		public Criteria andVmIdIn(List values) {
			addCriterion("vm_id in", values, "vmId");
			return this;
		}

		public Criteria andVmIdNotIn(List values) {
			addCriterion("vm_id not in", values, "vmId");
			return this;
		}

		public Criteria andVmIdBetween(String value1, String value2) {
			addCriterion("vm_id between", value1, value2, "vmId");
			return this;
		}

		public Criteria andVmIdNotBetween(String value1, String value2) {
			addCriterion("vm_id not between", value1, value2, "vmId");
			return this;
		}

		public Criteria andVmNameIsNull() {
			addCriterion("vm_name is null");
			return this;
		}

		public Criteria andVmNameIsNotNull() {
			addCriterion("vm_name is not null");
			return this;
		}

		public Criteria andVmNameEqualTo(String value) {
			addCriterion("vm_name =", value, "vmName");
			return this;
		}

		public Criteria andVmNameNotEqualTo(String value) {
			addCriterion("vm_name <>", value, "vmName");
			return this;
		}

		public Criteria andVmNameGreaterThan(String value) {
			addCriterion("vm_name >", value, "vmName");
			return this;
		}

		public Criteria andVmNameGreaterThanOrEqualTo(String value) {
			addCriterion("vm_name >=", value, "vmName");
			return this;
		}

		public Criteria andVmNameLessThan(String value) {
			addCriterion("vm_name <", value, "vmName");
			return this;
		}

		public Criteria andVmNameLessThanOrEqualTo(String value) {
			addCriterion("vm_name <=", value, "vmName");
			return this;
		}

		public Criteria andVmNameLike(String value) {
			addCriterion("vm_name like", value, "vmName");
			return this;
		}

		public Criteria andVmNameNotLike(String value) {
			addCriterion("vm_name not like", value, "vmName");
			return this;
		}

		public Criteria andVmNameIn(List values) {
			addCriterion("vm_name in", values, "vmName");
			return this;
		}

		public Criteria andVmNameNotIn(List values) {
			addCriterion("vm_name not in", values, "vmName");
			return this;
		}

		public Criteria andVmNameBetween(String value1, String value2) {
			addCriterion("vm_name between", value1, value2, "vmName");
			return this;
		}

		public Criteria andVmNameNotBetween(String value1, String value2) {
			addCriterion("vm_name not between", value1, value2, "vmName");
			return this;
		}

		public Criteria andVmLoginNameIsNull() {
			addCriterion("vm_login_name is null");
			return this;
		}

		public Criteria andVmLoginNameIsNotNull() {
			addCriterion("vm_login_name is not null");
			return this;
		}

		public Criteria andVmLoginNameEqualTo(String value) {
			addCriterion("vm_login_name =", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameNotEqualTo(String value) {
			addCriterion("vm_login_name <>", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameGreaterThan(String value) {
			addCriterion("vm_login_name >", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameGreaterThanOrEqualTo(String value) {
			addCriterion("vm_login_name >=", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameLessThan(String value) {
			addCriterion("vm_login_name <", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameLessThanOrEqualTo(String value) {
			addCriterion("vm_login_name <=", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameLike(String value) {
			addCriterion("vm_login_name like", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameNotLike(String value) {
			addCriterion("vm_login_name not like", value, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameIn(List values) {
			addCriterion("vm_login_name in", values, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameNotIn(List values) {
			addCriterion("vm_login_name not in", values, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameBetween(String value1, String value2) {
			addCriterion("vm_login_name between", value1, value2, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginNameNotBetween(String value1, String value2) {
			addCriterion("vm_login_name not between", value1, value2, "vmLoginName");
			return this;
		}

		public Criteria andVmLoginPasswordIsNull() {
			addCriterion("vm_login_password is null");
			return this;
		}

		public Criteria andVmLoginPasswordIsNotNull() {
			addCriterion("vm_login_password is not null");
			return this;
		}

		public Criteria andVmLoginPasswordEqualTo(String value) {
			addCriterion("vm_login_password =", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordNotEqualTo(String value) {
			addCriterion("vm_login_password <>", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordGreaterThan(String value) {
			addCriterion("vm_login_password >", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("vm_login_password >=", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordLessThan(String value) {
			addCriterion("vm_login_password <", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordLessThanOrEqualTo(String value) {
			addCriterion("vm_login_password <=", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordLike(String value) {
			addCriterion("vm_login_password like", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordNotLike(String value) {
			addCriterion("vm_login_password not like", value, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordIn(List values) {
			addCriterion("vm_login_password in", values, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordNotIn(List values) {
			addCriterion("vm_login_password not in", values, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordBetween(String value1, String value2) {
			addCriterion("vm_login_password between", value1, value2, "vmLoginPassword");
			return this;
		}

		public Criteria andVmLoginPasswordNotBetween(String value1, String value2) {
			addCriterion("vm_login_password not between", value1, value2, "vmLoginPassword");
			return this;
		}

		public Criteria andPublicIpAddressIsNull() {
			addCriterion("Public_Ip_address is null");
			return this;
		}

		public Criteria andPublicIpAddressIsNotNull() {
			addCriterion("Public_Ip_address is not null");
			return this;
		}

		public Criteria andPublicIpAddressEqualTo(String value) {
			addCriterion("Public_Ip_address =", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressNotEqualTo(String value) {
			addCriterion("Public_Ip_address <>", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressGreaterThan(String value) {
			addCriterion("Public_Ip_address >", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressGreaterThanOrEqualTo(String value) {
			addCriterion("Public_Ip_address >=", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressLessThan(String value) {
			addCriterion("Public_Ip_address <", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressLessThanOrEqualTo(String value) {
			addCriterion("Public_Ip_address <=", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressLike(String value) {
			addCriterion("Public_Ip_address like", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressNotLike(String value) {
			addCriterion("Public_Ip_address not like", value, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressIn(List values) {
			addCriterion("Public_Ip_address in", values, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressNotIn(List values) {
			addCriterion("Public_Ip_address not in", values, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressBetween(String value1, String value2) {
			addCriterion("Public_Ip_address between", value1, value2, "publicIpAddress");
			return this;
		}

		public Criteria andPublicIpAddressNotBetween(String value1, String value2) {
			addCriterion("Public_Ip_address not between", value1, value2, "publicIpAddress");
			return this;
		}

		public Criteria andIpAddressIsNull() {
			addCriterion("Ip_address is null");
			return this;
		}

		public Criteria andIpAddressIsNotNull() {
			addCriterion("Ip_address is not null");
			return this;
		}

		public Criteria andIpAddressEqualTo(String value) {
			addCriterion("Ip_address =", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressNotEqualTo(String value) {
			addCriterion("Ip_address <>", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressGreaterThan(String value) {
			addCriterion("Ip_address >", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
			addCriterion("Ip_address >=", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressLessThan(String value) {
			addCriterion("Ip_address <", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressLessThanOrEqualTo(String value) {
			addCriterion("Ip_address <=", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressLike(String value) {
			addCriterion("Ip_address like", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressNotLike(String value) {
			addCriterion("Ip_address not like", value, "ipAddress");
			return this;
		}

		public Criteria andIpAddressIn(List values) {
			addCriterion("Ip_address in", values, "ipAddress");
			return this;
		}

		public Criteria andIpAddressNotIn(List values) {
			addCriterion("Ip_address not in", values, "ipAddress");
			return this;
		}

		public Criteria andIpAddressBetween(String value1, String value2) {
			addCriterion("Ip_address between", value1, value2, "ipAddress");
			return this;
		}

		public Criteria andIpAddressNotBetween(String value1, String value2) {
			addCriterion("Ip_address not between", value1, value2, "ipAddress");
			return this;
		}

		public Criteria andIsSendsmsIsNull() {
			addCriterion("is_sendsms is null");
			return this;
		}

		public Criteria andIsSendsmsIsNotNull() {
			addCriterion("is_sendsms is not null");
			return this;
		}

		public Criteria andIsSendsmsEqualTo(String value) {
			addCriterion("is_sendsms =", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsNotEqualTo(String value) {
			addCriterion("is_sendsms <>", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsGreaterThan(String value) {
			addCriterion("is_sendsms >", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsGreaterThanOrEqualTo(String value) {
			addCriterion("is_sendsms >=", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsLessThan(String value) {
			addCriterion("is_sendsms <", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsLessThanOrEqualTo(String value) {
			addCriterion("is_sendsms <=", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsLike(String value) {
			addCriterion("is_sendsms like", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsNotLike(String value) {
			addCriterion("is_sendsms not like", value, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsIn(List values) {
			addCriterion("is_sendsms in", values, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsNotIn(List values) {
			addCriterion("is_sendsms not in", values, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsBetween(String value1, String value2) {
			addCriterion("is_sendsms between", value1, value2, "isSendsms");
			return this;
		}

		public Criteria andIsSendsmsNotBetween(String value1, String value2) {
			addCriterion("is_sendsms not between", value1, value2, "isSendsms");
			return this;
		}

		public Criteria andIsSendshopIsNull() {
			addCriterion("is_sendshop is null");
			return this;
		}

		public Criteria andIsSendshopIsNotNull() {
			addCriterion("is_sendshop is not null");
			return this;
		}

		public Criteria andIsSendshopEqualTo(String value) {
			addCriterion("is_sendshop =", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopNotEqualTo(String value) {
			addCriterion("is_sendshop <>", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopGreaterThan(String value) {
			addCriterion("is_sendshop >", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopGreaterThanOrEqualTo(String value) {
			addCriterion("is_sendshop >=", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopLessThan(String value) {
			addCriterion("is_sendshop <", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopLessThanOrEqualTo(String value) {
			addCriterion("is_sendshop <=", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopLike(String value) {
			addCriterion("is_sendshop like", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopNotLike(String value) {
			addCriterion("is_sendshop not like", value, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopIn(List values) {
			addCriterion("is_sendshop in", values, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopNotIn(List values) {
			addCriterion("is_sendshop not in", values, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopBetween(String value1, String value2) {
			addCriterion("is_sendshop between", value1, value2, "isSendshop");
			return this;
		}

		public Criteria andIsSendshopNotBetween(String value1, String value2) {
			addCriterion("is_sendshop not between", value1, value2, "isSendshop");
			return this;
		}

		public Criteria andVCenterCodeIsNull() {
			addCriterion("v_center_code is null");
			return this;
		}

		public Criteria andVCenterCodeIsNotNull() {
			addCriterion("v_center_code is not null");
			return this;
		}

		public Criteria andVCenterCodeEqualTo(String value) {
			addCriterion("v_center_code =", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeNotEqualTo(String value) {
			addCriterion("v_center_code <>", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeGreaterThan(String value) {
			addCriterion("v_center_code >", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeGreaterThanOrEqualTo(String value) {
			addCriterion("v_center_code >=", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeLessThan(String value) {
			addCriterion("v_center_code <", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeLessThanOrEqualTo(String value) {
			addCriterion("v_center_code <=", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeLike(String value) {
			addCriterion("v_center_code like", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeNotLike(String value) {
			addCriterion("v_center_code not like", value, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeIn(List values) {
			addCriterion("v_center_code in", values, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeNotIn(List values) {
			addCriterion("v_center_code not in", values, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeBetween(String value1, String value2) {
			addCriterion("v_center_code between", value1, value2, "vCenterCode");
			return this;
		}

		public Criteria andVCenterCodeNotBetween(String value1, String value2) {
			addCriterion("v_center_code not between", value1, value2, "vCenterCode");
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

		public Criteria andReturnShopDateIsNull() {
			addCriterion("return_shop_date is null");
			return this;
		}

		public Criteria andReturnShopDateIsNotNull() {
			addCriterion("return_shop_date is not null");
			return this;
		}

		public Criteria andReturnShopDateEqualTo(Date value) {
			addCriterion("return_shop_date =", value, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateNotEqualTo(Date value) {
			addCriterion("return_shop_date <>", value, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateGreaterThan(Date value) {
			addCriterion("return_shop_date >", value, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateGreaterThanOrEqualTo(Date value) {
			addCriterion("return_shop_date >=", value, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateLessThan(Date value) {
			addCriterion("return_shop_date <", value, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateLessThanOrEqualTo(Date value) {
			addCriterion("return_shop_date <=", value, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateIn(List values) {
			addCriterion("return_shop_date in", values, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateNotIn(List values) {
			addCriterion("return_shop_date not in", values, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateBetween(Date value1, Date value2) {
			addCriterion("return_shop_date between", value1, value2, "returnShopDate");
			return this;
		}

		public Criteria andReturnShopDateNotBetween(Date value1, Date value2) {
			addCriterion("return_shop_date not between", value1, value2, "returnShopDate");
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

		public Criteria andChangeStatusIsNull() {
			addCriterion("change_status is null");
			return this;
		}

		public Criteria andChangeStatusIsNotNull() {
			addCriterion("change_status is not null");
			return this;
		}

		public Criteria andChangeStatusEqualTo(String value) {
			addCriterion("change_status =", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusNotEqualTo(String value) {
			addCriterion("change_status <>", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusGreaterThan(String value) {
			addCriterion("change_status >", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusGreaterThanOrEqualTo(String value) {
			addCriterion("change_status >=", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusLessThan(String value) {
			addCriterion("change_status <", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusLessThanOrEqualTo(String value) {
			addCriterion("change_status <=", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusLike(String value) {
			addCriterion("change_status like", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusNotLike(String value) {
			addCriterion("change_status not like", value, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusIn(List values) {
			addCriterion("change_status in", values, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusNotIn(List values) {
			addCriterion("change_status not in", values, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusBetween(String value1, String value2) {
			addCriterion("change_status between", value1, value2, "changeStatus");
			return this;
		}

		public Criteria andChangeStatusNotBetween(String value1, String value2) {
			addCriterion("change_status not between", value1, value2, "changeStatus");
			return this;
		}

		public Criteria andServiceBeginTimeIsNull() {
			addCriterion("service_begin_time is null");
			return this;
		}

		public Criteria andServiceBeginTimeIsNotNull() {
			addCriterion("service_begin_time is not null");
			return this;
		}

		public Criteria andServiceBeginTimeEqualTo(Date value) {
			addCriterion("service_begin_time =", value, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeNotEqualTo(Date value) {
			addCriterion("service_begin_time <>", value, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeGreaterThan(Date value) {
			addCriterion("service_begin_time >", value, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("service_begin_time >=", value, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeLessThan(Date value) {
			addCriterion("service_begin_time <", value, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeLessThanOrEqualTo(Date value) {
			addCriterion("service_begin_time <=", value, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeIn(List values) {
			addCriterion("service_begin_time in", values, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeNotIn(List values) {
			addCriterion("service_begin_time not in", values, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeBetween(Date value1, Date value2) {
			addCriterion("service_begin_time between", value1, value2, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceBeginTimeNotBetween(Date value1, Date value2) {
			addCriterion("service_begin_time not between", value1, value2, "serviceBeginTime");
			return this;
		}

		public Criteria andServiceEndTimeIsNull() {
			addCriterion("service_end_time is null");
			return this;
		}

		public Criteria andServiceEndTimeIsNotNull() {
			addCriterion("service_end_time is not null");
			return this;
		}

		public Criteria andServiceEndTimeEqualTo(Date value) {
			addCriterion("service_end_time =", value, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeNotEqualTo(Date value) {
			addCriterion("service_end_time <>", value, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeGreaterThan(Date value) {
			addCriterion("service_end_time >", value, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("service_end_time >=", value, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeLessThan(Date value) {
			addCriterion("service_end_time <", value, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeLessThanOrEqualTo(Date value) {
			addCriterion("service_end_time <=", value, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeIn(List values) {
			addCriterion("service_end_time in", values, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeNotIn(List values) {
			addCriterion("service_end_time not in", values, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeBetween(Date value1, Date value2) {
			addCriterion("service_end_time between", value1, value2, "serviceEndTime");
			return this;
		}

		public Criteria andServiceEndTimeNotBetween(Date value1, Date value2) {
			addCriterion("service_end_time not between", value1, value2, "serviceEndTime");
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