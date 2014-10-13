<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<title></title>
	<style>
	font,img{
		vertical-align: middle;
	}
	</style>
<script type="text/javaScript">
<% int i=1;%>
	//明细
	function desc(a){
			var extEqId = $(a).parent().parent().find("td").eq(1).text();
			var dirName =  $(a).parent().parent().find("td").eq(3).text();
			$.dialog({
					id:'add',
					title:'目录明细',
					width: '850px',
					height: '750px',
					lock:true,
					content: 'url:bossBusiAction_queryListForDirDesc.do?cfgObj.extEqId='+extEqId+'&cfgObj.kpiCfgValue='+dirName
				});	
	}
	$(function(){
			$("#search").click(function(){
				theForm.submit();
		});
	});
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="bossBusiAction_queryListForMonitorDir.do" method="post" cssClass="theForm" id="theForm" name="theForm">
        <div class="pd-20 bgcolor-1">
        	<h2 class="utt-1">待监控目录</h2>  
            <div class="bord-1 pd-20" >
	            <label class="vm">主机IP：</label>
	            	<s:textfield name="cfgObj.hostIp" id="hostIp" />
	            <label class=" mgl-20 vm">积压状态：</label>
	            	<s:select cssClass="select-1 vm" list="#{'':'请选择','0':'正常','1':'一般积压','2':'严重积压'}" name="cfgObj.alarm_flag" id="alarm_flag" ></s:select>
	            <span class="ubtn-1 mgl-20"><input type="button" id="search" value="查询" /></span>
			<div style="margin: 10px;"></div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>编号</th>
				   <th>主机IP</th>
				   <th>目录路径</th>
				   <th>当前文件数</th>
				   <th>是否积压</th>
				   <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="cfgObjList" id="theBean">
			  	<tr>
			  	 	<td><%= i++%></td>
			  	 	<td style="display: none;"><s:property value="#theBean.extEqId"/></td>
					<td width="10%;"><s:property value="#theBean.hostIp"/></td>
			  		<td><s:property value="#theBean.kpiCfgValue"/></td>
			  		<td class="vm" style="width: 40px;">
				  		<s:if test="#theBean.alarm_flag == 0 || #theBean.alarm_flag == null"><!-- 正常 -->
				  			<img style="margin-top: 4px;" src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/Ok.png" /><font><s:property value="#theBean.inCount"/></font>
				  		</s:if>
				  		<s:elseif test="#theBean.alarm_flag == 1"><!-- 一般 -->
				  			<img style="margin-top: 4px;" src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/Warning.png"  /><s:property value="#theBean.inCount"/>
				  		</s:elseif>
				  		<s:elseif test="#theBean.alarm_flag == 2"><!-- 严重 -->
				  			<img style="margin-top: 4px;" src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/Danger.png" /><s:property value="#theBean.inCount"/>
				  		</s:elseif>
				  		<s:else><s:property value="#theBean.inCount"/></s:else>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.alarm_flag == 0 || #theBean.alarm_flag == null">
			  				无
			  			</s:if>
			  			<s:elseif test="#theBean.alarm_flag == 3">
				  			没有配置额度
				  		</s:elseif>
			  			<s:else>
			  				是
			  			</s:else>
			  		</td>
			  		<td>
			  			<a href="#" onclick="javascript:desc(this);" >明细</a>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
                    <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm"/>
            </div>
            </div>
	</div>
</s:form>
</body>
</html:html>	
