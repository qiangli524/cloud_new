package com.sitech.shop.webservice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TbSysUserinfoExample {

    protected String orderByClause;


    protected List oredCriteria;


    public TbSysUserinfoExample() {
        oredCriteria = new ArrayList();
    }


    protected TbSysUserinfoExample(TbSysUserinfoExample example) {
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
            return criteriaWithoutValue.size() > 0
                    || criteriaWithSingleValue.size() > 0
                    || criteriaWithListValue.size() > 0
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("id in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("id not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("ACCOUNT is null");
            return this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("ACCOUNT is not null");
            return this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("ACCOUNT =", value, "account");
            return this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("ACCOUNT <>", value, "account");
            return this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("ACCOUNT >", value, "account");
            return this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT >=", value, "account");
            return this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("ACCOUNT <", value, "account");
            return this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT <=", value, "account");
            return this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("ACCOUNT like", value, "account");
            return this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("ACCOUNT not like", value, "account");
            return this;
        }

        public Criteria andAccountIn(List values) {
            addCriterion("ACCOUNT in", values, "account");
            return this;
        }

        public Criteria andAccountNotIn(List values) {
            addCriterion("ACCOUNT not in", values, "account");
            return this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("ACCOUNT between", value1, value2, "account");
            return this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT not between", value1, value2, "account");
            return this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return this;
        }

        public Criteria andPasswordIn(List values) {
            addCriterion("PASSWORD in", values, "password");
            return this;
        }

        public Criteria andPasswordNotIn(List values) {
            addCriterion("PASSWORD not in", values, "password");
            return this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return this;
        }

        public Criteria andNameIn(List values) {
            addCriterion("NAME in", values, "name");
            return this;
        }

        public Criteria andNameNotIn(List values) {
            addCriterion("NAME not in", values, "name");
            return this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("MOBILE is null");
            return this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("MOBILE is not null");
            return this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("MOBILE =", value, "mobile");
            return this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("MOBILE <>", value, "mobile");
            return this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("MOBILE >", value, "mobile");
            return this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE >=", value, "mobile");
            return this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("MOBILE <", value, "mobile");
            return this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("MOBILE <=", value, "mobile");
            return this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("MOBILE like", value, "mobile");
            return this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("MOBILE not like", value, "mobile");
            return this;
        }

        public Criteria andMobileIn(List values) {
            addCriterion("MOBILE in", values, "mobile");
            return this;
        }

        public Criteria andMobileNotIn(List values) {
            addCriterion("MOBILE not in", values, "mobile");
            return this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("MOBILE between", value1, value2, "mobile");
            return this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("MOBILE not between", value1, value2, "mobile");
            return this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return this;
        }

        public Criteria andEmailIn(List values) {
            addCriterion("EMAIL in", values, "email");
            return this;
        }

        public Criteria andEmailNotIn(List values) {
            addCriterion("EMAIL not in", values, "email");
            return this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return this;
        }

        public Criteria andStatusEqualTo(Long value) {
            addCriterion("STATUS =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(Long value) {
            addCriterion("STATUS <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(Long value) {
            addCriterion("STATUS >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(Long value) {
            addCriterion("STATUS <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(Long value) {
            addCriterion("STATUS <=", value, "status");
            return this;
        }

        public Criteria andStatusIn(List values) {
            addCriterion("STATUS in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("STATUS not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(Long value1, Long value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(Long value1, Long value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CREATETIME is null");
            return this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CREATETIME is not null");
            return this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CREATETIME =", value, "createtime");
            return this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CREATETIME <>", value, "createtime");
            return this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CREATETIME >", value, "createtime");
            return this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATETIME >=", value, "createtime");
            return this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CREATETIME <", value, "createtime");
            return this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATETIME <=", value, "createtime");
            return this;
        }

        public Criteria andCreatetimeIn(List values) {
            addCriterion("CREATETIME in", values, "createtime");
            return this;
        }

        public Criteria andCreatetimeNotIn(List values) {
            addCriterion("CREATETIME not in", values, "createtime");
            return this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CREATETIME between", value1, value2, "createtime");
            return this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATETIME not between", value1, value2, "createtime");
            return this;
        }

        public Criteria andCreateuserIsNull() {
            addCriterion("CREATEUSER is null");
            return this;
        }

        public Criteria andCreateuserIsNotNull() {
            addCriterion("CREATEUSER is not null");
            return this;
        }

        public Criteria andCreateuserEqualTo(Long value) {
            addCriterion("CREATEUSER =", value, "createuser");
            return this;
        }

        public Criteria andCreateuserNotEqualTo(Long value) {
            addCriterion("CREATEUSER <>", value, "createuser");
            return this;
        }

        public Criteria andCreateuserGreaterThan(Long value) {
            addCriterion("CREATEUSER >", value, "createuser");
            return this;
        }

        public Criteria andCreateuserGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATEUSER >=", value, "createuser");
            return this;
        }

        public Criteria andCreateuserLessThan(Long value) {
            addCriterion("CREATEUSER <", value, "createuser");
            return this;
        }

        public Criteria andCreateuserLessThanOrEqualTo(Long value) {
            addCriterion("CREATEUSER <=", value, "createuser");
            return this;
        }

        public Criteria andCreateuserIn(List values) {
            addCriterion("CREATEUSER in", values, "createuser");
            return this;
        }

        public Criteria andCreateuserNotIn(List values) {
            addCriterion("CREATEUSER not in", values, "createuser");
            return this;
        }

        public Criteria andCreateuserBetween(Long value1, Long value2) {
            addCriterion("CREATEUSER between", value1, value2, "createuser");
            return this;
        }

        public Criteria andCreateuserNotBetween(Long value1, Long value2) {
            addCriterion("CREATEUSER not between", value1, value2, "createuser");
            return this;
        }

        public Criteria andDataauthorityIsNull() {
            addCriterion("DATAAUTHORITY is null");
            return this;
        }

        public Criteria andDataauthorityIsNotNull() {
            addCriterion("DATAAUTHORITY is not null");
            return this;
        }

        public Criteria andDataauthorityEqualTo(String value) {
            addCriterion("DATAAUTHORITY =", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityNotEqualTo(String value) {
            addCriterion("DATAAUTHORITY <>", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityGreaterThan(String value) {
            addCriterion("DATAAUTHORITY >", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityGreaterThanOrEqualTo(String value) {
            addCriterion("DATAAUTHORITY >=", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityLessThan(String value) {
            addCriterion("DATAAUTHORITY <", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityLessThanOrEqualTo(String value) {
            addCriterion("DATAAUTHORITY <=", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityLike(String value) {
            addCriterion("DATAAUTHORITY like", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityNotLike(String value) {
            addCriterion("DATAAUTHORITY not like", value, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityIn(List values) {
            addCriterion("DATAAUTHORITY in", values, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityNotIn(List values) {
            addCriterion("DATAAUTHORITY not in", values, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityBetween(String value1, String value2) {
            addCriterion("DATAAUTHORITY between", value1, value2, "dataauthority");
            return this;
        }

        public Criteria andDataauthorityNotBetween(String value1, String value2) {
            addCriterion("DATAAUTHORITY not between", value1, value2, "dataauthority");
            return this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return this;
        }

        public Criteria andDomainIn(List values) {
            addCriterion("domain in", values, "domain");
            return this;
        }

        public Criteria andDomainNotIn(List values) {
            addCriterion("domain not in", values, "domain");
            return this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return this;
        }

        public Criteria andUserlevelIsNull() {
            addCriterion("USERLEVEL is null");
            return this;
        }

        public Criteria andUserlevelIsNotNull() {
            addCriterion("USERLEVEL is not null");
            return this;
        }

        public Criteria andUserlevelEqualTo(String value) {
            addCriterion("USERLEVEL =", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelNotEqualTo(String value) {
            addCriterion("USERLEVEL <>", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelGreaterThan(String value) {
            addCriterion("USERLEVEL >", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelGreaterThanOrEqualTo(String value) {
            addCriterion("USERLEVEL >=", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelLessThan(String value) {
            addCriterion("USERLEVEL <", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelLessThanOrEqualTo(String value) {
            addCriterion("USERLEVEL <=", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelLike(String value) {
            addCriterion("USERLEVEL like", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelNotLike(String value) {
            addCriterion("USERLEVEL not like", value, "userlevel");
            return this;
        }

        public Criteria andUserlevelIn(List values) {
            addCriterion("USERLEVEL in", values, "userlevel");
            return this;
        }

        public Criteria andUserlevelNotIn(List values) {
            addCriterion("USERLEVEL not in", values, "userlevel");
            return this;
        }

        public Criteria andUserlevelBetween(String value1, String value2) {
            addCriterion("USERLEVEL between", value1, value2, "userlevel");
            return this;
        }

        public Criteria andUserlevelNotBetween(String value1, String value2) {
            addCriterion("USERLEVEL not between", value1, value2, "userlevel");
            return this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("USER_TYPE is null");
            return this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("USER_TYPE is not null");
            return this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("USER_TYPE =", value, "userType");
            return this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("USER_TYPE <>", value, "userType");
            return this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("USER_TYPE >", value, "userType");
            return this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_TYPE >=", value, "userType");
            return this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("USER_TYPE <", value, "userType");
            return this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("USER_TYPE <=", value, "userType");
            return this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("USER_TYPE like", value, "userType");
            return this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("USER_TYPE not like", value, "userType");
            return this;
        }

        public Criteria andUserTypeIn(List values) {
            addCriterion("USER_TYPE in", values, "userType");
            return this;
        }

        public Criteria andUserTypeNotIn(List values) {
            addCriterion("USER_TYPE not in", values, "userType");
            return this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("USER_TYPE between", value1, value2, "userType");
            return this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("USER_TYPE not between", value1, value2, "userType");
            return this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("PAYMENT_TYPE is null");
            return this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("PAYMENT_TYPE is not null");
            return this;
        }

        public Criteria andPaymentTypeEqualTo(String value) {
            addCriterion("PAYMENT_TYPE =", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeNotEqualTo(String value) {
            addCriterion("PAYMENT_TYPE <>", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeGreaterThan(String value) {
            addCriterion("PAYMENT_TYPE >", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMENT_TYPE >=", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeLessThan(String value) {
            addCriterion("PAYMENT_TYPE <", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(String value) {
            addCriterion("PAYMENT_TYPE <=", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeLike(String value) {
            addCriterion("PAYMENT_TYPE like", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeNotLike(String value) {
            addCriterion("PAYMENT_TYPE not like", value, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeIn(List values) {
            addCriterion("PAYMENT_TYPE in", values, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeNotIn(List values) {
            addCriterion("PAYMENT_TYPE not in", values, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeBetween(String value1, String value2) {
            addCriterion("PAYMENT_TYPE between", value1, value2, "paymentType");
            return this;
        }

        public Criteria andPaymentTypeNotBetween(String value1, String value2) {
            addCriterion("PAYMENT_TYPE not between", value1, value2, "paymentType");
            return this;
        }

        public Criteria andShopUserIdIsNull() {
            addCriterion("SHOP_USER_ID is null");
            return this;
        }

        public Criteria andShopUserIdIsNotNull() {
            addCriterion("SHOP_USER_ID is not null");
            return this;
        }

        public Criteria andShopUserIdEqualTo(String value) {
            addCriterion("SHOP_USER_ID =", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdNotEqualTo(String value) {
            addCriterion("SHOP_USER_ID <>", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdGreaterThan(String value) {
            addCriterion("SHOP_USER_ID >", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_USER_ID >=", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdLessThan(String value) {
            addCriterion("SHOP_USER_ID <", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdLessThanOrEqualTo(String value) {
            addCriterion("SHOP_USER_ID <=", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdLike(String value) {
            addCriterion("SHOP_USER_ID like", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdNotLike(String value) {
            addCriterion("SHOP_USER_ID not like", value, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdIn(List values) {
            addCriterion("SHOP_USER_ID in", values, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdNotIn(List values) {
            addCriterion("SHOP_USER_ID not in", values, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdBetween(String value1, String value2) {
            addCriterion("SHOP_USER_ID between", value1, value2, "shopUserId");
            return this;
        }

        public Criteria andShopUserIdNotBetween(String value1, String value2) {
            addCriterion("SHOP_USER_ID not between", value1, value2, "shopUserId");
            return this;
        }
    }
}