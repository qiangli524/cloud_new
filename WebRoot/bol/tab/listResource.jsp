<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#pname").val("");
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="bolresource_listResourceForProcess.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="type"></s:hidden>
    <s:hidden name="pid"></s:hidden>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">资源名称:</td>
		                  		<td>
		                  			<s:textfield name="bolResourceObj.NAME" id="pname"></s:textfield> 
		                  		</td>
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
			</div>
      <div class="blue-wrap noborder">
      				<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th onclick="sort(theTable,0,'string')">资源标识</th>
						<th onclick="sort(theTable,1,'string')">资源名称</th>
						<th onclick="sort(theTable,2,'string')">资源类型</th>
						<th onclick="sort(theTable,3,'string')">资源大小</th>
						<th onclick="sort(theTable,4,'string')">资源位置</th>
						<th onclick="sort(theTable,5,'string')">所属主机</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr >
                			<td>
                				<s:property value="#theBean.ID"/>
                			</td>
                			<td>
	                			<s:property value="#theBean.NAME" default="--"></s:property>
                			</td>
                			<td>
                				<s:if test='#theBean.TYPE=="M"'>内存</s:if>
                				<s:elseif test='#theBean.TYPE=="MT"'>内存汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="S"'>存储</s:elseif>
                				<s:elseif test='#theBean.TYPE=="ST"'>存储汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="DH"'>文件句柄数(指定目录)</s:elseif>
                				<s:elseif test='#theBean.TYPE=="DHT"'>文件句柄数汇总(指定目录)</s:elseif>
                				<s:elseif test='#theBean.TYPE=="PH"'>文件句柄数(指定进程)</s:elseif>
                				<s:elseif test='#theBean.TYPE=="PHT"'>文件句柄数汇总(指定进程)</s:elseif>
                				<s:elseif test='#theBean.TYPE=="Q"'>消息队列</s:elseif>
                				<s:elseif test='#theBean.TYPE=="QT"'>消息队列汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="SN"'>信号量</s:elseif>
                				<s:elseif test='#theBean.TYPE=="SNT"'>信号量汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="N"'>网络连接</s:elseif>
                				<s:elseif test='#theBean.TYPE=="NT"'>网络连接汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="P"'>进程</s:elseif>
                				<s:elseif test='#theBean.TYPE=="PT"'>进程汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="DB"'>数据库连接</s:elseif>
                				<s:elseif test='#theBean.TYPE=="DBT"'>数据库连接汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="C"'>CPU</s:elseif>
                				<s:elseif test='#theBean.TYPE=="CT"'>CPU汇总</s:elseif>
                				<s:elseif test='#theBean.TYPE=="CH"'>文件(指定目录)</s:elseif>
                				<s:elseif test='#theBean.TYPE=="CHT"'>文件汇总(指定目录)</s:elseif>
                			</td>
                			<td>
                				<s:property value="#theBean.RSIZE"/>
                			</td>
                			<td>
                				<s:property value="#theBean.LOC"/>
                			</td>
                			<td>
                				<s:property value="#theBean.HOST"/>
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div>
    </div>
    </s:form>
</body>