package com.sitech.basd.util.excel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

public class FilterTextManipulator {

	private String startToken = "/~";
	private String endToken = "~/";

	/**
	 * 
	 * @param text
	 * @param model
	 * @return
	 */
	public StringBuffer manipulate(StringBuffer text, Map map) {

		if (map == null)
			map = Collections.EMPTY_MAP;

		for (int start = 0, end = text.indexOf(endToken); ((end = text.indexOf(endToken)) >= 0);) {
			start = text.lastIndexOf(startToken, end);
			String key = text.substring(start + 2, text.indexOf(":", start));

			Object modelValue = map.get(key.trim());

			// If its an empty string replace it with a null.
			if (modelValue instanceof String && (((String) modelValue).length() == 0))
				modelValue = null;

			if (modelValue != null)
				text.replace(start, end + 2, replaceName(text.substring(
						text.indexOf(":", start) + 1, end), modelValue));// text.substring(text.indexOf(":",
			// start)
			// + 1,
			// end));
			else
				text.replace(start, end + 2, "");
		}

		return text;
	}

	/**
	 * add by Mumu Lee at 20080218
	 * 
	 * @param text
	 * @param map
	 * @return
	 */
	public StringBuffer manipulate(StringBuffer text, Map map, List sqlPara) {

		if (map == null)
			map = Collections.EMPTY_MAP;

		for (int start = 0, end = text.indexOf(endToken); ((end = text.indexOf(endToken)) >= 0);) {
			start = text.lastIndexOf(startToken, end);
			String key = text.substring(start + 2, text.indexOf(":", start));

			Object modelValue = map.get(key.trim());

			// If its an empty string replace it with a null.
			if (modelValue instanceof String && (((String) modelValue).length() == 0))
				modelValue = null;

			if (modelValue != null)
				text.replace(start, end + 2, replaceName(text.substring(
						text.indexOf(":", start) + 1, end), modelValue, sqlPara));// text.substring(text.indexOf(":",
			// start)
			// + 1,
			// end));
			else
				text.replace(start, end + 2, "");
		}

		return text;
	}

	/**
	 * add by Mumu Lee at 20080218
	 * 
	 * @param str
	 * @param value
	 * @param sqlPara
	 * @return
	 */
	private String replaceName(String str, Object value, List sqlPara) {
		StringBuffer strbuf = new StringBuffer(str);
		int start = strbuf.indexOf("{");
		int end = strbuf.indexOf("}");

		if (start < end) {

			String type = strbuf.substring(start, end + 1);
			String tmpValue;

			if (type.equals("{string}")) {
				tmpValue = (String) value;
				// strbuf.replace(start,end+1,"'"+ value+"'");
			} else if (type.equals("{number}")) {
				tmpValue = (String) value;
				// strbuf.replace(start,end+1,(String)value);
			} else if (type.equals("{%string%}")) {
				tmpValue = "%" + value + "%";
				// strbuf.replace(start,end+1,"'%"+ value+"%'");
			} else if (type.equals("{string%}")) {
				tmpValue = value + "%";
				// strbuf.replace(start,end+1,"'"+ value+"%'");
			} else if (type.equals("{%string}")) {
				tmpValue = "%" + value;
				// strbuf.replace(start,end+1,"'%"+ value+"'");
			} else {
				tmpValue = (String) value;
			}

			/*
			 * strbuf.replace(start,end+1,"?"); sqlPara.add(new
			 * SqlParameter("sqlpara",SqlTypes.JSTRING,tmpValue));
			 */

			if (!type.equals("{tablename}")) {
				strbuf.replace(start, end + 1, "?");
				// sqlPara.add(new SqlParameter("sqlpara", SqlTypes.JSTRING,
				// tmpValue));
				sqlPara.add(new SqlParameter("sqlpara", 2, tmpValue));
			} else {
				strbuf.replace(start, end + 1, tmpValue);
			}
		} else {
			start = strbuf.indexOf("[");
			end = strbuf.indexOf("]");
			if (start < end) {
				strbuf.replace(start, end + 1, (String) value);
			}
		}
		return strbuf.toString();
	}

	private String replaceName(String str, Object value) {
		StringBuffer strbuf = new StringBuffer(str);
		int start = strbuf.indexOf("{");
		int end = strbuf.indexOf("}");

		if (start < end) {

			String type = strbuf.substring(start, end + 1);

			if (type.equals("{string}"))
				strbuf.replace(start, end + 1, "'" + value + "'");
			if (type.equals("{number}")) {
				strbuf.replace(start, end + 1, (String) value);
			}
			if (type.equals("{%string%}"))
				strbuf.replace(start, end + 1, "'%" + value + "%'");
			if (type.equals("{string%}"))
				strbuf.replace(start, end + 1, "'" + value + "%'");
			if (type.equals("{%string}"))
				strbuf.replace(start, end + 1, "'%" + value + "'");
		}
		return strbuf.toString();
	}

	/**
	 * @param endToken
	 *            The endToken to set.
	 */
	public void setEndToken(String endToken) {
		this.endToken = endToken;
	}

	/**
	 * @param startToken
	 *            The startToken to set.
	 */
	public void setStartToken(String startToken) {
		this.startToken = startToken;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		buf
				.append("select year,class_code,group_id,group_name,")
				.append(" belong_code,year_score,award_score,total_score,rownum year_num")
				.append(
						" from (SELECT   year,a.class_code,a.group_id,b.group_name,a.belong_code,a.year_score,a.award_score,a.total_score")
				.append(" FROM dchngrpmarkyear a,dchngroupmsg b").append(
						" /~table_name: dchnlogopr{number} ~/").append(
						" WHERE a.group_id=b.group_id and b.is_active=0").append(
						"  /~year: and year = {year} ~/ ").append(
						" /~classCode: and a.class_code = {string} ~/ ").append(
						" /~regionCode: and substr(a.belong_code,1,2)={regionCode} ~/").append(
						"    /~dis_code: and substr(a.belong_code,3,2)={dis_code} ~/	").append(
						" 	order by total_score)");

		FilterTextManipulator f = new FilterTextManipulator();
		java.util.HashMap map = new java.util.HashMap();
		map.put("table_name", "200610");
		map.put("classCode", "2001-1-1");

	}

}
