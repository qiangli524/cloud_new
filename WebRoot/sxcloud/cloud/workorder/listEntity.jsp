<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
    $(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
 	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectEntity,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});

   function selectEntity(){
	   var type = $("#type").val();
	   if (1==type) {
		   var vmcode = "";
		   var connectcode = "";
		   var vmname = "";
		   
		   var cpu = 0;
		   var mem = 0;
		   var sr = 0;
		   var ip = "";
		   $(":checkbox:checked").each(function(){
			   vmcode += $(this).attr("vhuuid");
			   connectcode += $(this).attr("connectId");
			   vmname += $(this).attr("vhname");
			   
			   cpu = $(this).attr("cpu");
			   mem = $(this).attr("mem");
			   sr = $(this).attr("sr");
			   ip = $(this).attr("ip");
		   });
		   var oper = $("#oper").val();
		   if (oper == "add") {
		   	   api.get("addResource").selectRecoverResouce(vmcode,connectcode,vmname,cpu,mem,sr,ip);
			} else {
		   	   api.get("editResource").selectRecoverResouce(vmcode,connectcode,vmname,cpu,mem,sr,ip);
			}
	   } else {
		   var vmcode = "";
		   var connectcode = "";
		   var vmname = "";
		   $(":checkbox:checked").each(function(){
			   vmcode += $(this).attr("vhuuid");
			   connectcode += $(this).attr("connectId");
			   vmname += $(this).attr("vhname");
		   });
		   var oper = $("#oper").val();
		   if (oper == "add") {
		   	   api.get("addResource").selectRecoverResouce(vmcode,connectcode,vmname);
			} else {
		   	   api.get("editResource").selectRecoverResouce(vmcode,connectcode,vmname);
			}
	   }
   }
   
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#vh_name").val("");
	   });
   });
   
    </script>
</head>
<body class="pop-body scrollbody">
<div class="mainbody">
    <s:form action="workorder/workorder_selectRecoverResouce.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="oper" id="oper"></s:hidden>
    <s:hidden name="type" id="type"></s:hidden>
    <s:hidden name="projectid" id="projectid"></s:hidden>
    
    <div class="pd-20 bgcolor-1">
				<div class="bord-1 pd-10">
					<div class="clearfix mgt-10">
						<label class="vm">虚拟机名称:</label>
						<s:textfield name="vmHostObj.VH_NAME" id="vh_name" cssClass="inpt-1 vm"></s:textfield> 
						<%--<label class="vm">项目编号：</label>
						<s:textfield name="projectObj.PROJECT_NO" id="project_no"
							cssClass="inpt-1 vm"></s:textfield>
						--%><span class="ubtn-1 mgl-20"><input type="button" value="查询"
							id="searchForm" />
						</span> <span class="ubtn-2 mgl-20"><input type="button"
							value="重置" id="resetForm" />
						</span>
					</div>
					<div class="utt-2 mgt-20"></div>
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">虚拟机名称</th>
						<th onclick="sort(theTable,2,'string')">CPU个数</th>
						<th onclick="sort(theTable,3,'string')">内存大小</th>
						<th onclick="sort(theTable,4,'string')">存储大小</th>
						<th onclick="sort(theTable,5,'string')">操作系统</th>
						<th onclick="sort(theTable,6,'string')">IP地址</th>
						<th onclick="sort(theTable,7,'string')">所属主机</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="vmhostList" id="theBean">
                		<tr >
                			<td><input type="checkbox" connectId="<s:property value="#theBean.connectId"/>" 
                				vhuuid="<s:property value='#theBean.VH_UUID'/>" vhname="<s:property value='#theBean.VH_NAME'/>"
                				cpu="<s:property value='#theBean.VH_CPU'/>" mem="<s:property value='#theBean.VH_MEM'/>"
                				sr="<s:property value='#theBean.VH_STORAGE'/>" ip="<s:property value='#theBean.VH_IP'/>"
                				/></td>
                			<td><s:property value="#theBean.VH_NAME"/></td>
                			<td><s:property value="#theBean.VH_CPU"/>个</td>
                			<td><s:property value="#theBean.VH_MEM"/>M</td>
                			<td><s:property value="#theBean.VH_STORAGE"/>M</td>
                			<td><s:property value="#theBean.VH_SYSTEM"/></td>
                			<td><s:property value="#theBean.VH_IP"/></td>
                			<td><s:property value="#theBean.EQ_NAME"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
       <div class="pages">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
    </s:form>
    </div>
</body>