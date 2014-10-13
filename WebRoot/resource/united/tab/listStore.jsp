<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
	.percentage { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/percentage-bg.gif) no-repeat; 
	width:105px; 
	height:14px; 
	margin-top: 4px;}
	.percentage b{ display:block; height:14px; background:url(sxcloud/images/nresources/percentage-bg4.gif) no-repeat; }
	.percentage b.red {background:url(sxcloud/images/nresources/percentage-bg3.gif) no-repeat;}
	
	.percentage2 { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/percentage-bg7.gif) no-repeat; 
	width:430px; 
	height:35px; 
	margin-top: 4px;}
	.percentage2 b{ display:block; height:35px; background:url(sxcloud/images/nresources/percentage-bg8.gif) no-repeat; }
	.percentage2 b.red {background:url(sxcloud/images/nresources/percentage-bg3.gif) no-repeat;}
	
.totalResource {
    background-color: #F0F0F0;
    border: 1px solid #CECECE;
    height: 50px;
    overflow: hidden;
    width: 99%;
}
.font-more{ width:115px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;
}
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript">
	$(function(){
		$("#searchForm").click(function(){
			theForm.submit();
		});
		
		$("#resetForm").click(function(){
			$("#storename").val("");
			$("#storetype").val("");
		});
	});
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="resourcestatistics_showStorePage.do" method="post" id="theForm">
<s:hidden name="uuid" id="uuid"></s:hidden>
<div class="scrollbody">
	<div class="totalResource">
		<table width="100%" border="0" style="position: relative;right: -10px">
			<tr>
				<td >
					总量:<s:property value="srallcount"/>G</td>	
				<td >
					已使用:<s:property value="srusedcount"/>G</td>
				<td rowspan="2">
					<div class="percentage2" style="position: relative;left: 90px;color: blue">
					<b style='width:<s:property value="@java.lang.Math@round(srusedcount*100*100/srallcount)/100.0"/>%'></b></div>
				</td>
			</tr>
			<tr>
				<td>
					可用:<s:property value="srallcount-srusedcount"/>G</td>
				<td>
					使用率:<s:property value="@java.lang.Math@round(srusedcount*100*100/srallcount)/100.0"/>%</td>			
			</tr>
		</table>
	</div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">存储块名称:</td>
                    <td>
						<s:textfield name="dataStoreObj.NAME" id="storename"></s:textfield>
                    </td>
                    <td class="til">存储块格式:</td>
                    <td>
						<s:select id="storetype" name="dataStoreObj.TYPE" list="#{'':'--请选择--','NFS':'NFS','LVM':'LVM','ISO':'ISO','VMFS':'VMFS','UDEV':'UDEV'}"></s:select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
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
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">存储块名称</th>
					<th onclick="sort(theTable,1,'string')">存储块路径</th>
					<th onclick="sort(theTable,2,'string')">状态</th>
					<th>所属存储设备</th>
					<th>存储设备类型</th>
					<th onclick="sort(theTable,4,'string')">存储块格式</th>
					<th onclick="sort(theTable,5,'string')">总容量(G)</th>
					<th onclick="sort(theTable,6,'string')">可用容量(G)</th>
					<th>使用情况</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="storeList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.NAME"/></td>
			  		<td align="center">
			  			<s:if test="#theBean.STORAGE_URL==null">
			  				-
			  			</s:if>
			  			<s:elseif test="#theBean.STORAGE_URL==''">
			  				-
			  			</s:elseif>
			  			<s:else>
			  				<a style="color: black;text-decoration: none;" class="font-more" title='<s:property value="#theBean.STORAGE_URL"/>' >
								<s:property value="#theBean.STORAGE_URL"/>
							</a>
			  			</s:else>
			  			
			  		</td>
			  		<td align="center">
			  			<s:if test="#theBean.STATE=='connection'">
			  				<img src="sxcloud/images/virtual/accept.png" style="margin-top: 6px"/>正常
			  			</s:if>
			  			<s:elseif test="#theBean.STATE=='detach'">
			  				异常
			  			</s:elseif>
			  			<s:elseif test="#theBean.STATE=='destroy'">
			  				损坏
			  			</s:elseif>
			  			<s:elseif test="#theBean.STATE==''">
			  				无
			  			</s:elseif>
			  			<s:else>
			  				<img src="sxcloud/images/virtual/accept.png" style="margin-top: 6px"/>正常
			  			</s:else>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.deviceName==null || #theBean.deviceName==''">
			  				-
			  			</s:if>
			  			<s:else>
							<s:property value="#theBean.deviceName"/>			  			
			  			</s:else>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.deviceType==null || #theBean.deviceType==''">
			  				-
			  			</s:if>
			  			<s:elseif test="#theBean.deviceType==0">
			  				本地存储
			  			</s:elseif>
			  			<s:elseif test="#theBean.deviceType==1">
			  				SAN
			  			</s:elseif>
			  			<s:elseif test="#theBean.deviceType==2">
			  				NAS
			  			</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.TYPE"/></td>
			  		<td><s:property value="#theBean.CAPACITY"/></td>
			  		<td><s:property value="#theBean.FREE_SPACE"/></td>
			  		<td>
			  			<div class="percentage"><b style='width:<s:property 
			  				value="@java.lang.Math@round((#theBean.CAPACITY-#theBean.FREE_SPACE)*100*100/#theBean.CAPACITY)/100.0"/>%'></b></div>
			  			<s:property value="@java.lang.Math@round((#theBean.CAPACITY-#theBean.FREE_SPACE)*100*100/#theBean.CAPACITY)/100.0"/>%
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div>
    </div>
</div>
</s:form>
</body>
