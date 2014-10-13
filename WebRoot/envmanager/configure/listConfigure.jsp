<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
 <style type="text/css">
	div.yincang{
		width:100px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest(){
		theForm.submit();
	}
	function resetForm(theForm){
	document.getElementById("CF_DOMAIN").value="";
	document.getElementById("CF_SOURCE").value="";
	document.getElementById("CF_CLASS").value="";
	document.getElementById("CF_HOSTTYPE").value="";
	}
	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'configure_insertConfigureObj.do';
	  	theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.cf_env.value = checkboxids[i].value;
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
 	    theForm.action = 'configure_updateConfigureObj.do';
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.cf_env.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    
 	    theForm.action = 'configure_deleteConfigureObj.do'; 
		theForm.submit();
		
 	}
</script>
</head>
<body>
<s:form action="configure_queryConfigureObj.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.cf_env" id="cf_env"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID"/>

 <div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%"  border="0">
                  <tr>
                 
					<td class="til">系统域</td>
					<td>
						<s:textfield name="theForm.CF_DOMAIN" cssClass="txt" id="CF_DOMAIN"></s:textfield>
					</td>
					<td class="til">资源域</td>
					<td>
						<s:textfield name="theForm.CF_SOURCE" cssClass="txt" id="CF_SOURCE"></s:textfield>
					</td>
					<td class="til">分类</td>
					<td>
						<s:textfield name="theForm.CF_CLASS" cssClass="txt" id="CF_CLASS"></s:textfield>
					</td>
					<td class="til">主机类型</td>
					<td>
						<s:textfield name="theForm.CF_HOSTTYPE" cssClass="txt" id="CF_HOSTTYPE"></s:textfield>
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
			<tr >
			<th rowspan="3" columnId='ENV' align="center">选择</th>
			<th rowspan="3" columnId='DOMAIN' align="center">系统域</th>
			<th rowspan="3" columnId='RESOURCE' align="center">资源域</th>
			<th rowspan="3" columnId='CLASS' align="center">分类</th>
			<th rowspan="3" columnId='HOSTTYPE' align="center">主机类型</th>
			<th rowspan="3" columnId='HOSTNUM'align="center" style="width:80px;">型号</th>
			<th rowspan="3" columnId='DESCRIPTION' align="center" style="width:800px;">配置</th>
			<th rowspan="3" columnId='SYSTEM' align="center">操作系统</th>
			<th rowspan="3" columnId='HOSTNAME' align="center">主机名</th>
			<th rowspan="3" columnId='IP'align="center" >IP地址</th>
			<th rowspan="3" columnId='PRODUCT' align="center">使用产品线</th>
			<th rowspan="3" columnId='DEVEPROD'align="center" >研发产品</th>
			<th colspan="4" align="center">申请资源情况</th>
			<th colspan="8" align="center">使用情况</th>
			<th rowspan="2" colspan="3" align="center">剩余资源情况</th>
			<th rowspan="3" columnId='CFDATE' align="center">更新日期</th>
		</tr>
		<tr height="10">
			<th colspan="2" align="center" style="background:#4682B4" stype="">数据库</th>
			<th colspan="2" align="center" style="background:#4682B4">应用</th>
			<th colspan="4" align="center" style="background:#4682B4">应用</th>
			<th colspan="4" align="center" style="background:#4682B4">数据库</th>
		</tr>
		<tr height="10">
			<th align="center" style="background:#36648B">实例</th>
			<th align="center" style="background:#36648B">表空间（G）</th>
			<th align="center" style="background:#36648B">文件系统</th>
			<th align="center" style="background:#36648B">文件系统大小（G）</th>
			<th align="center" style="background:#36648B">CPU</th>
			<th align="center" style="background:#36648B">内存（M）</th>
			<th align="center" style="background:#36648B">文件系统（G）</th>
			<th align="center" style="background:#36648B">文件系统使用率</th>
			<th align="center" style="background:#36648B">实例</th>
			<th align="center" style="background:#36648B">表空间（G）</th>
			<th align="center" style="background:#36648B">表空间使用率</th>
			<th align="center" style="background:#36648B">内存（G）</th>
			<th align="center" style="background:#36648B">CPU</th>
			<th align="center" style="background:#36648B">内存（M）</th>
			<th align="center" style="background:#36648B">存储（G）</th>
		</tr>
			  
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.con_List" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.cf_env"/>" /></td>
			  		<td><s:property value="#theBean.CF_DOMAIN"/></td>
			  		<td><s:property value="#theBean.CF_SOURCE"/></td>
			  		<td><s:property value="#theBean.CF_CLASS"/></td>
			  		<td><s:property value="#theBean.CF_HOSTTYPE"/></td>
			  		<td ><div style="color: black;" class="yincang" title='<s:property value="#theBean.CF_HOSTNUM"/>'><s:property value="#theBean.CF_HOSTNUM"/></div></td><!-- <div class="yincang"></div> 文本内容过长只显示部分但不能显示其它部分 没有使用-->
			  		<td ><div style="color: black;" class="yincang" title='<s:property value="#theBean.CF_DESCRIPTION"/>'><s:property value="#theBean.CF_DESCRIPTION"/></div></td>
			  		
			  		<td><s:property value="#theBean.CF_SYSTEM" /></td>
			  		<td><s:property value="#theBean.CF_HOSTNAME"/></td>
			  		<td><s:property value="#theBean.CF_IP"/></td>
			  		<td><s:property value="#theBean.CF_PRODUCT"/></td>
			  		
			  		<td><s:property value="#theBean.CF_DEVEPROD"/></td>
			  		<td><s:property value="#theBean.CF_SID"/></td>
			  		<td><s:property value="#theBean.CF_TABLESPACE"/></td>
			  		<td><s:property value="#theBean.CF_FILESYSNAM"/></td>
			  		<td><s:property value="#theBean.CF_FILEAPPNUM"/></td>
			  		<td><s:property value="#theBean.CF_CPUUSED"/></td>
			  		<td><s:property value="#theBean.CF_MEM"/></td>
			  		<td><s:property value="#theBean.CF_FILEUSERD"/></td>
			  		<td><s:property value="#theBean.CF_FILEUSEPER"/></td>
			  		<td><s:property value="#theBean.CF_SID1"/></td>
			  	
			  		<td><s:property value="#theBean.CF_TABSPAUSED"/></td>
			  		<td><s:property value="#theBean.CF_TABSPAUSEPER"/></td>
			  		<td><s:property value="#theBean.CF_SGA"/></td>
			  		<td><s:property value="#theBean.CF_CPULEFT"/></td>
			  		<td><s:property value="#theBean.CF_MEMLEFT"/></td>
			  		<td><s:property value="#theBean.CF_STORAGE"/></td>
			  		<td><s:property value="#theBean.CF_DATE"/></td>
			  		
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
