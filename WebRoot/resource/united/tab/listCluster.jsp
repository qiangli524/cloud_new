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
<div class="mainbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
    <s:hidden id="vtype" name="vtype"></s:hidden>
    <div class="pd-20 bgcolor-1">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>集群名称</th>
						<th>虚拟化类型</th>
						<th>所属数据中心</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="ulist" id="theBean">
                		<tr >
                			<td>
                				<s:property value="#theBean.name"></s:property>
                			</td>
                			<td>
                				<s:if test="#theBean.vtype==1">
									vmware
								</s:if>
								<s:elseif test="#theBean.vtype==2">
									xen
								</s:elseif>
								<s:elseif test="#theBean.vtype==7">
									未分配
								</s:elseif>
                			</td>
                			<td>
								<s:property value="#theBean.parent_uuid" />
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
				</div>
    </s:form>
    </div>
</body>