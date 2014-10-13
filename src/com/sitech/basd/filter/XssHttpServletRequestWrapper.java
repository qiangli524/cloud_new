package com.sitech.basd.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <code>{@link XssHttpServletRequestWrapper}</code>
 * 
 * TODO : document me
 * 
 * @author Administrator
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	HttpServletRequest orgRequest = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	@Override
	public void setAttribute(String name, Object o) {
		if(o instanceof String){
		     String str=o.toString();
		     o=xssEncode(str);
		}
		super.setAttribute(name, o);
	}
	
/**
	 *
	 * @see javax.servlet.ServletRequestWrapper#getParameterMap()
	 */
	@Override
	public Map getParameterMap() {
		Map rmap = new HashMap();
		Map<String,String[]> map = orgRequest.getParameterMap();
		for(String key :map.keySet()) {
			String[] values = map.get(key);
			for(int i = 0;i<values.length;i++){
				values[i] = xssEncode(values[i]);
			}
			rmap.put(key, values);
		}
		return rmap;
	}
	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	 * getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * 
	 * @param s
	 * @return
	 */
	private static String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}else{
			/**
			 * 替换特殊字符串
			 */
			s=replaceAll(s);
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
//				|（竖线符号）
//				& （& 符号）
//				;（分号）
//				$（美元符号）
//				%（百分比符号）
//				@（at 符号）
//				'（单引号）
//				"（引号）
//				\'（反斜杠转义单引号）
//				\"（反斜杠转义引号）
//				<>（尖括号）
//				()（括号）
//				+（加号）
//				CR（回车符，ASCII 0x0d）\r
//				LF（换行，ASCII 0x0a）\n
//				,（逗号）
//				\（反斜杠）

			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '@':
				sb.append('@');// at 字符
				break;
			case '+':
				sb.append('+');// 加号
				break;
			case '%':
				sb.append('%');// 百分号
				break;
			case '$':
				sb.append('$');// 美元符号
				break;
			case '(':
				sb.append('(');// 全角井号
				break;
			case ')':
				sb.append(')');// 全角井号
				break;
			case '|':
				sb.append('|');// 全角井号
				break;
			case ';':
				sb.append(';');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 特殊字符串过滤
	 * @Title: replaceAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-3-3 上午10:23:06
	 */
	public static String replaceAll(String value){
		/**
		 * 特殊字符串集合，暂时写成这样，以后提取到配置文件中
		 * 尽量将“:”,“=”，“%0D”，“%0A”，,on*等都进行
		 */
		String[] strs=new String[]{"%0D","%0A"};
		for(String str:strs){
			value.replaceAll(str, value);
		}
		return value;
	}

	/**
	 * 获取最原始的request
	 * 
	 * @return
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request的静态方法
	 * 
	 * @return
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) req).getOrgRequest();
		}

		return req;
	}

}