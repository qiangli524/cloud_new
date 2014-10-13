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
	var api = frameElement.api;
	 	var w = api.opener;
	 	api.button({
	     	id:'Ok',
	     	callback:submitRequest,
	     	name: '删除',
	     	focus: true
		 },
		 {
	     id:'cancle',
	     name: '取消'
	 	});
	
	function submitRequest(){
	
	   var project_id = '<s:property value="vmHostObj.PROJECT_ID" />';
	   var boxId = "";
	   var vhuuid = "";
	   var connID = "";
	    $('[name=checkboxId]:checkbox:checked').each(function(){
	   		vhuuid += $(this).attr("vhuuid")+",";
	   		connID += $(this).attr("connID")+",";
	   		boxId += $(this).val();
	   });
	   if(boxId==null || boxId ==''){
  			alert("请选择要删除的虚拟机!");
			return false;
		}
		alert("删除成功");
	   w.selectDelVM(project_id,vhuuid,connID);
	    
	}
	//通过虚拟机名字,IP,虚拟机类型查询虚拟机
	function queryVM(){
		var project_id = '<s:property value="vmHostObj.PROJECT_ID" />';
		vmHostObj.action='departproject_queryDelVMByObj.do?vmHostObj.PROJECT_ID='+project_id;
		vmHostObj.submit();
	}
	//重置
	function resetVM(){
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
<body onLoad="self.focus();document.vmHostObj.VH_NAME.focus()" class="pop-body scrollbody">
<s:form action="" id="vmHostObj" method="post"
	cssClass="vmHostObj">
<div class="scrollbody">
	<%-- <div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>--%>
	<div class="box on">
        <%-- <div class="query-form">
                 <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">
								虚拟机名称:
							</td>
							<td>
								<s:textfield name="vmHostObj.VH_NAME" cssClass="txt" id="VH_NAME"></s:textfield>
							</td>
							<td class="til">
								IP地址:
							</td>
							<td>
								<s:textfield name="vmHostObj.VH_IP" cssClass="txt" id="VH_IP"></s:textfield>
							</td>
							<td class="til">
								虚拟机类型:
							</td>
							<td>
								<s:select list="#{'':'--请选择--','1':'Vmware','3':'XEN'}" name="vmHostObj.VH_TYPE" id="VH_TYPE"></s:select>
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
        </div><!--query-form end -->--%>
		<div class="clearfix mgt-10">
				<label class="til">虚拟机名称：</label>		
				<s:textfield name="vmHostObj.VH_NAME" cssClass="txt" id="VH_NAME"></s:textfield>
				<label class="til">IP地址：</label>	
				<s:textfield name="vmHostObj.VH_IP" cssClass="txt" id="VH_IP"></s:textfield>	
				<label class="til">虚拟机类型：</label>
				<s:select list="#{'':'--请选择--','1':'Vmware','3':'XEN'}" name="vmHostObj.VH_TYPE" id="VH_TYPE" cssClass="select-1 til"></s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:queryVM()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetVM()" value="重置" /></span>	
			</div>
			<div class="utt-2 mgt-20"></div>
	<div class="blue-wrap noborder">
		<%-- <div class="table-head">
			<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=vmHostObj" />
		</div> --%>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				    <th>选择</th>
					<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,1,'string')">IP</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<th onclick="sort(theTable,3,'string')">操作系统</th>
					<th onclick="sort(theTable,4,'string')">CPU</th>
					<th onclick="sort(theTable,5,'string')">内存</th>
					<th onclick="sort(theTable,6,'string')">存储</th>
					<!-- <th onclick="sort(theTable,6,'string')">状态</th> -->
             </tr>
			  </thead>
			  <tbody>
			    <s:iterator value="selectedList" id="theBean">
			  	<tr>
				  	<td>
				  	<input name="checkboxId"  type="checkbox" value='<s:property value="#theBean.VH_ID"/>' 
				  		vhuuid='<s:property value="#theBean.VH_UUID"/>' connID='<s:property value="#theBean.connectId"/>'
				  	/></td>
			  		<td><s:property value="#theBean.VH_NAME"/></td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td><s:if test="#theBean.VH_TYPE == 3">
			  			XEN
			  		</s:if><s:else>
			  			Vmware
			  		</s:else>
			  		</td>
			  		<td><s:property value="#theBean.VH_SYSTEM"/></td>
			  		<td><s:property value="#theBean.VH_CPU"/>核</td>
			  		<td><s:property value="#theBean.VH_MEM"/>MB</td>
			  		<td><s:property value="#theBean.VH_STORAGE"/>MB</td>
			  		<!-- <td id="stateText">
			  			<s:if test="#theBean.VH_STAT==1">
							正在运行
						</s:if>
						<s:if test="#theBean.VH_STAT==0">
							已关闭
						</s:if>
						<s:if test="#theBean.VH_STAT==2">
							挂起
						</s:if>
			  		</td> -->
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=vmHostObj" />
			</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
