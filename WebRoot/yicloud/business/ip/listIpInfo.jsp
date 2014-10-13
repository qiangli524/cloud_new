<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.SELECT_APP_ID.value = 0;
		theForm.SELECT_BIND_DEVICE.value =0;
		theForm.SELECT_ISUSE.value='';
		theForm.MAX_BANDWIDTH.value = '';
		theForm.MIDDLEWARE.value = '';
		theForm.PORT_MAPPING.value = '';
		theForm.LOAD_VIRTUAL_IP.value = '';
		theForm.SELECT_ISLOADBALANCE.value = '';
	 	theForm.SELECT_TELECOM_OPERATOR.value='';
	 	theForm.SELECT_PORT_TYPE.value='';
	 	theForm.SELECT_IP_TYPE.value='';
	 	theForm.SELECT_ISIPOPEN.value='';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	theForm.flag.value = 0;
 	    theForm.action = 'yiip_addIp.do'
	   theForm.submit();
 	}
 	function modRequest() { 
 	
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'yiip_modIp.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'yiip_delIp.do'  
		theForm.submit();
 	}
 	function backupRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IPADDRESS.value = checkboxids[i].value;
 	     // theForm.NET_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	   // theForm.action = 'backupIpInfo.do' 
		theForm.submit();
 	}
 	function issuanceRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IPADDRESS.value = checkboxids[i].value; 
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'issuanceIpInfo.do' 
		theForm.submit();
 	}
 	function checkSelect(){
		theForm.SELECT_APP_ID.value;
		theForm.SELECT_BIND_DEVICE.value;
		theForm.SELECT_ISUSE.value;
	 	theForm.SELECT_TELECOM_OPERATOR.value;
	 	theForm.SELECT_PORT_TYPE.value;
	 	theForm.SELECT_IP_TYPE.value;
		theForm.SELECT_ISIPOPEN.value;
	 	theForm.SELECT_ISLOADBALANCE.value;
	}
</script>
</head>
<body>
<s:form action="yiip_listIp.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                    <tr>
                    <td class="til">业务应用ID:</td>
                    <td>
                        <s:select list="theForm.appIdList" listKey="ID" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_APP_ID" id="SELECT_APP_ID"></s:select>
                    </td>
                    <td class="til">绑定设备主机ID:</td>
                    <td>
                        <s:select list="theForm.bindDeviceList" listKey="DEVICE_ID" listValue="DEVICE_NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_BIND_DEVICE" id="SELECT_BIND_DEVICE"></s:select>
                    </td>
                     <td class="til">是否使用:</td>
                    <td>
                        <s:select list="theForm.isUseList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_ISUSE" id="SELECT_ISUSE"></s:select>
					</td>
					 <td class="til">分配最大带宽:</td>
					 <td>
					    <s:textfield name="theForm.MAX_BANDWIDTH" cssClass="txt" id="MAX_BANDWIDTH"></s:textfield>
					</td>
					</tr>
					<tr>
					<td class="til">运营商:</td>
					 <td>
					    <s:select list="theForm.operatorList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_TELECOM_OPERATOR" id="SELECT_TELECOM_OPERATOR"></s:select>
					</td>
                    	<td class="til">中间件:</td>
					 <td>
					    <s:textfield name="theForm.MIDDLEWARE" cssClass="txt" id="MIDDLEWARE"></s:textfield>
					</td>
					<td class="til">端口类型:</td>
					 <td>
					    <s:select list="theForm.portTypeList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_PORT_TYPE" id="SELECT_PORT_TYPE"></s:select>
					</td>
					<td class="til">公网IP类型:</td>
					 <td>
					    <s:select list="theForm.ipTypeList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_IP_TYPE" id="SELECT_IP_TYPE"></s:select>
					</td>
                  </tr>
                  <tr>
                  <td class="til">公网是否开放:</td>
					 <td>
					    <s:select list="theForm.isOpenList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_ISIPOPEN" id="SELECT_ISIPOPEN"></s:select>
					</td>
					<td class="til">公网映射端口:</td>
					 <td>
					    <s:textfield name="theForm.PORT_MAPPING" cssClass="txt" id="PORT_MAPPING"></s:textfield>
					</td>
					<td class="til">负载虚拟IP:</td>
					 <td>
					    <s:textfield name="theForm.LOAD_VIRTUAL_IP" cssClass="txt" id="LOAD_VIRTUAL_IP"></s:textfield>
					</td>
					<td class="til">是否负载均衡:</td>
					 <td>
					    <s:select list="theForm.isBalanceList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_ISLOADBALANCE" id="SELECT_ISLOADBALANCE"></s:select>
					</td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
							<input type = "button" class="thickbox btn-style02" value = "返回" onclick="javascript:history.go(-1);"/>
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			<ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="添加" onclick = "addRequest();return false;" /></li>
			    <li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
		
			</ul>
		</div>
		
		    
	
		
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'int')">业务应用ID</th>
				   <th onclick="sort(theTable,2,'int')">绑定设备主机ID</th>
				   <th onclick="sort(theTable,3,'string')">是否使用</th>                
                   <th onclick="sort(theTable,4,'string')">分配最大带宽</th>
				   <th onclick="sort(theTable,5,'string')">运营商</th>
				   <th onclick="sort(theTable,6,'string')">中间件</th>
				   <th onclick="sort(theTable,7,'string')">端口类型</th>
				   <th onclick="sort(theTable,8,'string')">公网IP类型</th>
				   <th onclick="sort(theTable,9,'string')">公网是否开放</th>
				   <th onclick="sort(theTable,10,'int')">公网映射端口</th>
				   <th onclick="sort(theTable,11,'string')">负载虚拟IP</th>
				   <th onclick="sort(theTable,12,'string')">是否负载均衡</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="theForm.resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.ID'/>" /></td>
					<td><s:property value="#theBean.APP_ID" /></td>
					<td><s:property value="#theBean.BIND_DEVICE" /></td>
					<td><s:property value="#theBean.ISUSE" /></td>
					<td><s:property value="#theBean.MAX_BANDWIDTH" /></td>
					<td><s:property value="#theBean.TELECOM_OPERATOR" /></td>
					<td><s:property value="#theBean.MIDDLEWARE" /></td>
					<td><s:property value="#theBean.PORT_TYPE" /></td>
					<td><s:property value="#theBean.IP_TYPE" /></td>
					<td><s:property value="#theBean.ISIPOPEN" /></td>
					<td><s:property value="#theBean.PORT_MAPPING" /></td>
					<td><s:property value="#theBean.LOAD_VIRTUAL_IP" /></td>
					<td><s:property value="#theBean.ISLOADBALANCE" /></td>
				</tr>
			   </s:iterator>
			  </tbody>
			</table>
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
