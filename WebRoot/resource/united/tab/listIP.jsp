<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
</head>
<body class="pop-body scrollbody">
    <s:form action="resourcestatistics_showVlanPage.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="id" id="id"></s:hidden>
	<div class="box on">
      <div class="blue-wrap noborder">
      				<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th onclick="sort(theTable,0,'string')"><span onmouseover="this.style.cursor='hand'" style="cursor:hand">IP地址</span></th>
						<th onclick="sort(theTable,1,'string')">虚拟机名称</th>
						<th onclick="sort(theTable,2,'string')">所属业务</th>
						<th onclick="sort(theTable,3,'string')">是否使用</th>
						<!-- <th onclick="sort(theTable,3,'string')">是否被阻塞</th> -->
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="ipList" id="theBean">
                		<tr >
                			<td>
                				<s:property value="#theBean.ip"></s:property>
                			</td>
                			<td>
                				<s:property value="#theBean.vm"></s:property>
                			</td>
                			<td>
                				<s:property value="#theBean.busi"></s:property>
                			</td>
                			<td>
                				<s:if test="#theBean.isUsed== 0 && #theBean.isLocked == 0">
									否
								</s:if>
								<s:else>
									是
								</s:else>
                			</td>
                			<%-- <td>
								<s:if test="#theBean.ISBLOCKED==1">
									是
								</s:if>
								<s:elseif test="#theBean.ISBLOCKED==0">
									否
								</s:elseif>
                			</td> --%>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div>
    </div>
    </s:form>
</body>