<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	//通过虚拟机名字,IP,虚拟机类型查询虚拟机
	function queryVM(){
		userAllObj.action='unitedOutline_showVMAll.do';
		userAllObj.submit();
	}
	//重置
	function resetVM(){
		$("#PROJECT_NAME").attr("value","");
		$("#VH_NAME").attr("value","");
		$("#VH_IP").attr("value","");
		$("#VH_TYPE").attr('value','');
	}
</script>
  <style type="text/css">
		div.hidden{
		width:170px;
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
</head>
<body onLoad="self.focus();document.userAllObj.VH_NAME.focus()" class="pop-body scrollbody">
<s:form action="" id="userAllObj" method="post"
	cssClass="userAllObj">
<div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                 <table width="100%" class="querytable" border="0">
                  <tr>
                  	 <td class="til">
						项目名称:
					</td>
					<td>
						<s:textfield name="userAllObj.PROJECT_NAME" cssClass="txt" id="PROJECT_NAME"></s:textfield>
					</td>
                    <td class="til">
						虚拟机名称:
					</td>
					<td>
						<s:textfield name="userAllObj.VH_NAME" cssClass="txt" id="VH_NAME"></s:textfield>
					</td>
					<td class="til">
						IP地址:
					</td>
					<td>
						<s:textfield name="userAllObj.VH_IP" cssClass="txt" id="VH_IP"></s:textfield>
					</td>
					<td class="til">
						虚拟机类型:
					</td>
					<td>
						<s:select list="#{'':'--请选择--','1':'Vmware','3':'XEN'}" name="userAllObj.VH_TYPE" id="VH_TYPE"></s:select>
					</td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:queryVM()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetVM()" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
			<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=userAllObj" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>	
			  		<th onclick="sort(theTable,0,'string')">项目名称</th>
					<th onclick="sort(theTable,1,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<th onclick="sort(theTable,3,'string')">IP</th>
					<th onclick="sort(theTable,4,'string')">CPU</th>
					<th onclick="sort(theTable,5,'string')">内存</th>
					<th onclick="sort(theTable,6,'string')">存储</th>
					<th onclick="sort(theTable,7,'string')">操作系统</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="vmList" id="theBean">
			  	<tr>
			  		<td ><s:property value="#theBean.PROJECT_NAME"/></td>
			  		<td ><s:property value="#theBean.VH_NAME"/></td>
			  		<td><s:if test="#theBean.VH_TYPE == 3">
			  			XEN
			  		</s:if><s:else>
			  			Vmware
			  		</s:else>
			  		</td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td><s:property value="#theBean.VH_CPU"/>核</td>
			  		<td><s:property value="#theBean.VH_MEM"/>MB</td>
			  		<td><s:property value="#theBean.VH_STORAGE"/>MB</td>
			  		<td><s:property value="#theBean.VH_SYSTEM"/></td>
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
