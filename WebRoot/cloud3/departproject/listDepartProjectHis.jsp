<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
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

	function resetForm(obj){
		obj.PROJECT_NAME.value ='';
		obj.PROJECT_LEADER.value ='';
	}

    function searchRequest() { 
		obj.submit();
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
<body onLoad="self.focus();document.obj.PROJECT_NAME.focus()" class="pop-body scrollbody">
<s:form action="departproject_listDepartProjectHis.do" id="obj" method="post"
	cssClass="obj">
<div >
	<div class="box on">
        <div class="clearfix mgt-10">
		<label class="vm">项目名称：</label>		
		<s:textfield name="obj.PROJECT_NAME" cssClass="txt" id="PROJECT_NAME"></s:textfield>
		<label class="vm">项目负责人：</label>	
		<s:textfield name="obj.PROJECT_LEADERNAME" cssClass="txt" id="PROJECT_LEADER"></s:textfield>	
		<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
		<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('obj'))" value="重置" /></span>
		</div>	
	</div>
	
		<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr> 
				   <th onclick="sort(theTable,1,'string')">项目编号</th>
				   <th onclick="sort(theTable,2,'string')">项目名称</th>
				   <th onclick="sort(theTable,3,'string')">所属部门</th>
				   <th onclick="sort(theTable,4,'int')">预分配CPU</th>                
                   <th onclick="sort(theTable,5,'int')">预分配内存</th>
                   <th onclick="sort(theTable,6,'int')">预分配存储</th>
                   <th onclick="sort(theTable,7,'int')">预分配IP</th>
                   <th onclick="sort(theTable,8,'string')">项目负责人</th>
                   <th onclick="sort(theTable,9,'string')">联系电话</th>
                   <th onclick="sort(theTable,10,'string')">邮箱</th>
                   <th onclick="sort(theTable,11,'string')">网络域</th>
                   <th onclick="sort(theTable,12,'date')">创建时间</th>
                   <th onclick="sort(theTable,13,'string')">创建人</th>
                   <th onclick="sort(theTable,14,'date')">修改时间</th>
                   <th onclick="sort(theTable,15,'string')">修改人</th>
                   <th onclick="sort(theTable,16,'date')">删除时间</th>
                   <th onclick="sort(theTable,17,'string')">删除人</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="resultList" id="theBean">
				<tr>
					<td><s:property value="#theBean.PROJECT_NO" /></td>
					<td><s:property value="#theBean.PROJECT_NAME" /></td>
					<td><s:property value="#theBean.dEPART_NAME" /></td>
					<td><s:property value="#theBean.CPU_COUNT" /></td>
					<td><s:property value="#theBean.MEMORY_SIZE"/>G</td>
					<td><s:property value="#theBean.STORAGE_SIZE"/>G</td>
					<td><s:property value="#theBean.IP_COUNT" /></td> 
					<td><s:property value="#theBean.PROJECT_LEADER" /></td>
					<td><s:property value="#theBean.CONTACT_PHONE" /></td>
					<td><s:property value="#theBean.EMAIL" /></td>
					<td><s:property value="#theBean.NETWORK_DOMAIN" /></td>
					<td><s:property value="#theBean.CREATED_TIME" /></td>
					<td><s:property value="#theBean.CREATED_USER" /></td>
					<td><s:property value="#theBean.UPDATE_TIME" /></td>
					<td><s:property value="#theBean.UPDATE_USER" /></td>
					<td><s:property value="#theBean.DELETE_TIME" /></td>
					<td><s:property value="#theBean.DELETE_BY" /></td>
				</tr>
			   </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>
    </div><!--box end -->
</div>
</s:form>
</body>
