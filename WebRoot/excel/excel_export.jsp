<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="com.sitech.basd.util.excel.SQLListPostInfo"%>
<%@ page import="com.sitech.basd.util.excel.RequestUtil"%>
<%@ page import="com.sitech.basd.util.excel.DBUtil"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%
    //String excelname = request.getParameter("excelname");
	String key = request.getParameter("key");
	response.reset();
	response.setContentType("application/msexcel");
	response.setHeader("Content-disposition", "attachment;filename="+ key + ".xls");//定义文件名
	
	//单次查询导出条数,为能被60000整除的整数
	int rowMaxNum = 1000;
	//sql语句的类型 1：mysql 2：oracle
	int type = 1;
	
	Map map = RequestUtil.getRequestParameterMap(request);
	
	try {
		DBUtil.writeExcel2FileBySXSSF(response.getOutputStream(), map, key, rowMaxNum, type);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		response.getOutputStream().flush();
		response.getOutputStream().close();
		out.clear();
		out = pageContext.pushBody();
	}
%>