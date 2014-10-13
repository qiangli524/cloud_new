<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<%@ include file="../../../common/link.jsp"%>	
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant" %>
	<head>
		<script type="text/javascript"><!--
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
          
			//提交
			function submitForm(){
				var couterNum = 0;
				var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
 	  		//	var checkboxids = document.getElementsByName("checkboxid");
 	  			var subNum = '';
 	  		//	var vlanip = document.getElementsByName("VLANIP");
				var subvlan ='';
				/*
 	  			if(checkboxids!=null&&checkboxids.length>0){
 	    			for(var i=0;i<checkboxids.length;i++){
 	      				if(checkboxids[i].checked){
 	      					couterNum = couterNum + 1 ;
			      			subNum += checkboxids[i].value + ",";
			      			if(vlanip[i].value !='' && vlanip[i].value !=null){
			      				subvlan += vlanip[i].value + ",";
			      			}
 	     				}
 	    			}
				}
				*/
				
				var ip = theForm.chks.value;
				var vlanip = theForm.vlanchks.value;
				if(ip !=null && ip != ""){
					var str = ip.split(":");
					for(var i=0;i<str.length;i++){
						if(str[i] !=null && str[i] !=""){
							subNum += str[i] + ",";
						}
					}
				}
				if(vlanip !=null && vlanip != ""){
					var vlanstr = vlanip.split(":");
					for(var j=0;j<vlanstr.length;j++){
						if(vlanstr[j] !=null && vlanstr[j] !=""){
							subvlan += vlanstr[j] + ",";
						}
					}
				}
				theForm.action = 'appworkflow_saveAppIp.do?NEED_NUMBERS='+NEED_NUMBERS+"&subNum="+subNum+"&subvlan="+subvlan;
					if(theForm.APP_IDVALUE != undefined){
						window.opener.theForm.APP_IDVALUE.value = theForm.APP_IDVALUE.value;
					}else{
						window.opener.theForm.APP_IDVALUE.value = '';
					}
					if(theForm.APP_PORTVALUE != undefined){
						window.opener.theForm.APP_PORTVALUE.value = theForm.APP_PORTVALUE.value;
					}else{
						window.opener.theForm.APP_PORTVALUE.value = '';
					}
						window.opener.theForm.COUNTIP.value = subNum;
						window.opener.theForm.COUNTVLAN.value = subvlan;
					window.close();
					theForm.submit();
			}
	function selectTempletRequest() {
		var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
		var TEM_ID = theForm.TEM_ID.value;
		if(theForm.TEM_ID.value==0){
			return false;
		}else{
			theForm.action = 'appworkflow_selectTempletRequest.do?NEED_NUMBERS='+NEED_NUMBERS + '&SELECT='+TEM_ID;
			theForm.submit();
		}
	}
	
	function checkip(){
		var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
		var TEM_ID = theForm.TEM_ID.value;
		theForm.action = "appworkflow_checkAppIp.do?NEED_NUMBERS="+NEED_NUMBERS +'&SELECT='+TEM_ID;
		theForm.submit();
	}
	
	
	
	
		/*
			保持复选框状态
			zhangwj 2012-4-8
		*/	
		$(function(){
				var chks = document.getElementById("theForm").chks.value;
				var checkboxid = theForm.checkboxid;
				if(	typeof(checkboxid) == 'undefined' || typeof(checkboxid.length) == 'undefined' ){
					return;
				}
				if( null != chks && "" != chks){
					for(var i=0;i<checkboxid.length;i++){
						var ip = checkboxid[i].value;
						var arr = chks.split(":");
						for(var j=0;j<arr.length;j++){
							if(ip == arr[j]){
								checkboxid[i].checked = true;
							}
						}
					}
				}
			})
			/*
				单击时同步选中的IP值到 chks
				zhangwj 2012-4-8
			*/
			function checkboxchange(id){
				var chks = document.getElementById("theForm").chks;
				var vlanchks = document.getElementById("theForm").vlanchks;
				var arr = chks.value.split(":");
				var vlanarr = vlanchks.value.split(":");
				if(id.checked){
					if(arr.length > 0){
						chks.value += ":"+ id.value.split("/")[0];
					}else{
						chks.value = id.value.split("/")[0];
					}
					if(vlanarr.length >0){
						var temp = id.value.split("/")[1];
						if(temp !=null && " "!=temp){
							vlanchks.value +=":"+temp;
						}
					}else{
						if(temp !=null && " "!=temp){
							vlanchks.value += temp;
						}
				    } 
				}else{
					var str = "";
					var vlanstr = "";
					if( arr.length > 0){
						for(var i=0;i<arr.length;i++){
							if(id.value.split("/")[0] != arr[i]){
								if(i == 0){
								    str += arr[i];
								}else{
									str += ":" + arr[i];
								}
							}
						}
					}
					if(vlanarr.length>0){
						for(var j=0;j<vlanarr.length;j++){
							if(id.value.split("/")[1] !=vlanarr[j]){
								if(j==0){
									vlanstr += vlanarr[j];
								}else{	
									vlanstr += ":" + vlanarr[j];
								}
							}
						}
					}
					chks.value = str;
					vlanchks.value = vlanstr;
				}
			//	alert(chks.value);
			//	alert(vlanchks.value);
			}
		--></script>
	</head>
<body class="pop-body scrollbody" onunload="opener.get()">
<s:form action="appworkflow_checkAppIp" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"></s:hidden>
<s:hidden name="theForm.NEED_NUMBERS" id="NEED_NUMBERS"></s:hidden>
<s:hidden name="theForm.TYPE" id="TYPE"></s:hidden>
<s:hidden name="theForm.chks" id="chks"></s:hidden>
<s:hidden name="theForm.vlanchks" id="vlanchks"></s:hidden>
	<div class="tit-zzi" >
		<div id="zi">模板基本信息</div>
		<div id="zi"></div>
	</div>	
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
					<td class="til">
						选择模板
					</td>
					<td>
						<s:select list="theForm.templetList" listKey="TEM_ID" listValue="TEM_NAME" id="TEM_ID" name="theForm.TEM_ID" headerKey="0" headerValue="-请选择-" onchange="selectTempletRequest()">
						</s:select>
					</td>
				</tr>	
			<s:if test="theForm.APP_IDVALUE !=null">	
				<tr>
					<td class="til">
						<s:text name="theForm.APP_ID"></s:text>
							<s:hidden name="theForm.APP_ID" id="APP_ID"></s:hidden>
					</td>
					<td>
						<s:textfield name="theForm.APP_IDVALUE" cssClass="txt" id="APP_IDVALUE"></s:textfield>
					</td>
				</tr>
			</s:if>
			<s:if test="theForm.APP_PORTVALUE !=null">	
				<tr>
					<td class="til">
						<s:text name="theForm.APP_PORT">	</s:text>
							<s:hidden name="theForm.APP_PORT" id="APP_PORT"></s:hidden>
					</td>
					<td>
						<s:textfield name="theForm.APP_PORTVALUE" cssClass="txt" id="APP_PORTVALUE"></s:textfield>
					</td>
				</tr>
			</s:if>
				<s:if test="theForm.APP_NAMEVALUE !=null">	
				<tr>
					<td class="til">
						<s:text name="theForm.APP_NAME"></s:text>
							<s:hidden name="theForm.APP_NAME" id="APP_NAME"></s:hidden>
					</td>
					<td>
						<s:textfield name="theForm.APP_NAMEVALUE" cssClass="txt" id="APP_NAMEVALUE"></s:textfield>
					</td>
				</tr>
			</s:if>
				</table>
			</div>
	<div class="tit-zzi" >
		<div id="zi">选择主机</div>
		<div id="zi"></div>
	</div>	
	<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
			 
				<tr >
					<td class="til">
						IP选择模式
					</td>
					<td class="til">
						<s:radio list="#{'0':'自动分配','1':'手动选择'}" name="theForm.APP_IPMODEL"></s:radio>
						<input type="button" class="thickbox btn-style02-75"
						value="查询主机"	onclick="javascript:checkip();" />  
					</td>
				</tr>
			</table>		
	</div>
	<div class="zzi">
		<div id="zi">主机列表</div>
		<div id="zi"></div>
	</div>	
	<div class="blue-wrap noborder">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th>管理IP</th>
									<th>服务IP</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td>
										<input name="checkboxid" type="checkbox" 
									value="<s:property value="#theBean.IP"/>/<s:property value="#theBean.VLANIP"/>" onclick="javascript:checkboxchange(this);"/>
									</td>
									<td><s:property value="#theBean.IP"/>
									<s:hidden name="theForm.IP" id="ip"></s:hidden>
									</td>
									<td>
										<s:property value="#theBean.VLANIP"/>
										<input type="hidden" name="VLANIP"
										value="<s:property value="#theBean.VLANIP"/>" />
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
	
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input  type="button" class="btn-style02" value="确定" onclick="javascript:submitForm();"/>	
			        <input  type="button" class="btn-style02" value="返回" onclick="window.close();"/>
				</td>
			</tr>
		</table>   	
	</div>
	</s:form>
	</body>
