<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest(){
		theForm.submit();
	}
	function resetForm(theForm){
	document.getElementById("MECH_ROOM").value="";
	document.getElementById("CAPITAL_TYPE").value="";
	document.getElementById("MECH_ID").value="";
	document.getElementById("CAPITAL_ID").value="";
	}
	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'ledger_insertLedgerObj.do';
	  	theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.LD_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'ledger_updateLedgerObj.do';
		theForm.submit();
 	}
 	function delRequest() {
 	var theForm = document.getElementById("theForm");
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.LD_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    
 	    theForm.action = 'ledger_deleteLedgerObj.do'; 
		theForm.submit();
		
 	}
</script>
</head>
<body>
<s:form action="ledger_queryLedgerList.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.LD_ID" id="LD_ID"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>

 <div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%"  border="0">
                  <tr>
                 
					<td class="til">机房</td>
					<td>
						<s:textfield name="theForm.MECH_ROOM" cssClass="txt" id="MECH_ROOM"></s:textfield>
					</td>
					<td class="til">主机类型</td>
					<td>
						<s:textfield name="theForm.CAPITAL_TYPE" cssClass="txt" id="CAPITAL_TYPE"></s:textfield>
					</td>
					<td class="til">设备ID</td>
					<td>
						<s:textfield name="theForm.MECH_ID" cssClass="txt" id="MECH_ID"></s:textfield>
					</td>
					<td class="til">资产编号</td>
					<td>
						<s:textfield name="theForm.CAPITAL_ID" cssClass="txt" id="CAPITAL_ID"></s:textfield>
					</td>
				</tr>
			
				<tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                 </tr>
                </table>
        </div>
  <div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li>
				<input type="button" class="thickbox btn-style02" value="添加"
									onclick = "addRequest();return false;"  />
				</li>
				<li>
				<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;"  />
				</li>
				<li>
				<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;"/>
				</li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			<tr>
			<th rowspan="2">选择</th>
			<th rowspan="2">机房</th>
			<th rowspan="2">主机类型</th>
			<th rowspan="2">设备ID</th>
			<th rowspan="2">资产编号</th>
			<th rowspan="2">型号</th>
			<th rowspan="2">配置</th>
			<th rowspan="2">操作系统</th>
			<th rowspan="2">主机名</th>
			<th rowspan="2">VM分区</th>
			<th colspan="3">IP地址</th>
<%--			<th rowspan="2">系统口令</th>--%>
<%--			<th rowspan="2">console用户/口令</th>--%>
			<th rowspan="2">对应存储</th>
			<th colspan="4">用途</th>
			<th rowspan="2" >责任人</th>
			<th rowspan="2">借用记录</th>
			<th rowspan="2" >维修记录</th>
			<th rowspan="2" >是否在保</th>
			<th rowspan="2">是否需要报废</th>
			<th rowspan="2">管理说明</th>
		</tr>
		<tr>
			<th style="background:#36648B" columnId='IP_PHYSICS'>物理IP地址</th>
			<th style="background:#36648B" columnId='IP_VIRTUAL'>虚拟机IP地址</th>
			<th style="background:#36648B" columnId='IP_ILO'>管理地址</th>
			<th style="background:#36648B" columnId='USE_DOMAN'>归属域</th>
			<th style="background:#36648B" columnId='USE_RES'>资源池</th>
			<th style="background:#36648B" columnId='USE_DEPARTMENT'>使用部门</th>
			<th style="background:#36648B" columnId='USE_DESCRIP'>用户说明</th>
		</tr>
			  
			  
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.ledger_List" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.LD_ID"/>" /></td>
			  		<td><s:property value="#theBean.MECH_ROOM"/></td>
			  		<td><s:property value="#theBean.CAPITAL_TYPE"/></td>
			  		
			  		<td><s:property value="#theBean.MECH_ID"/></td>
			  		<td><s:property value="#theBean.CAPITAL_ID"/></td>
			  		
			  		<td><s:property value="#theBean.MECH_TYPE"/></td>
			  		<td><s:property value="#theBean.MECH_CONF"/></td>
			  		<td><s:property value="#theBean.SYS_SYSTEM"/></td>
			  		<td><s:property value="#theBean.SYS_HOSTNAME"/></td>
			  		<td><s:property value="#theBean.SYS_VM"/></td>
			  		<td><s:property value="#theBean.IP_PHYSICS"/></td>
			  		<td><s:property value="#theBean.IP_VIRTUAL"/></td>
			  		<td><s:property value="#theBean.IP_ILO"/></td>
<%--			  		<td><s:property value="#theBean.PWD_SYSTEM"/></td>--%>
<%--			  		<td><s:property value="#theBean.PWD_CONSOLE"/></td>--%>
			  		<td><s:property value="#theBean.STORE"/></td>
			  		<td><s:property value="#theBean.USE_DOMAN"/></td>
			  		
			  		<td><s:property value="#theBean.USE_RES"/></td>
			  		<td><s:property value="#theBean.USE_DEPART"/></td>
			  		<td><s:property value="#theBean.USE_DESCRIP"/></td>
			  		<td><s:property value="#theBean.MANAG_PERSON"/></td>
			  		<td><s:property value="#theBean.MANAG_RECORD"/></td>
			  		<td><s:property value="#theBean.MANAG_REPAIR"/></td>
			  		<td><s:property value="#theBean.MANAG_USE"/></td>
			  		<td><s:property value="#theBean.MANAG_USABLE"/></td>
			  		<td><s:property value="#theBean.MANAG_DESCRIP"/></td>
			  		
			  		
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
