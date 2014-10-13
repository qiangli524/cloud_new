<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %> 
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
     	var api = frameElement.api;
     	var w = api.opener; 
     	
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectSoftware,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		  
       //点击添加软件包的时候，把应用ID和软件包名、软件包个数形成已逗号分隔的字符串返回,
       function selectSoftware(){
    	   
    	   if ($(":checkbox:checked").length == 0) {
				alert("你好,请至少选择一项");
				return false;
			}
       		var versionname = $("[name='checkboxid']:checked").attr("versionname");//包名
       		var versionno = $("[name='checkboxid']:checked").attr("versionno");//版本号
       		var appid = $("[name='checkboxid']:checked").attr("appid");//应用ID
       		var softparth = $("[name='checkboxid']:checked").attr("softparth");//包的路径
       		var softId = $("[name='checkboxid']:checked").attr("softId");//软件包ID
       	    
       		api.get("add").getSoftware(versionname,versionno,appid,softparth,softId);
       }
       
	  $(function(){
		  	$("#searchForm").click(function(){
				$("#theForm").submit();
			});
			
			$("#resetForm").click(function(){
				$("#NAME").val("");
				$("#PROVIDERS").val("");
			});
	  });
	  $(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		})
</script>
</head>
  <style type="text/css">
		div.hidden{
		width:400px;
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
<body onLoad="self.focus();document.theForm.NAME.focus()">
<div class="mainbody">
<s:form action="software_listSoftwareInfoForSel.do" method="post" cssClass="theForm" id="theForm">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">软件管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">软件名称：</label>
				<s:textfield name="tbBusiSoftwareInfoObj.NAME" id="NAME" cssClass="inpt-1 vm"></s:textfield>
				<label class="mgl-20 vm">软件提供者：</label>
				 <s:textfield name="tbBusiSoftwareInfoObj.PROVIDERS" id="PROVIDERS" cssClass="inpt-1 vm"></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0" style="margin-top:10px">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">软件名称</th>                
                   <th onclick="sort(theTable,2,'string')">捕获时间</th>
                   <th onclick="sort(theTable,3,'string')">软件提供者</th>
                   <th onclick="sort(theTable,4,'string')">软件路径</th>
			  </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="resultList" id="theBean">
			  		<tr>
			  			<td width="50">
			  			<input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>" versionname='<s:property value="#theBean.NAME"/>'
			  			versionno='<s:property value="#theBean.VERSION"/>' appid='<s:property value="#theBean.APPID"/>' softparth='<s:property value="#theBean.SOFTPARTH"/>' softId='<s:property value="#theBean.ID"/>'/>
			  			</td>
			  			<td width="100"><s:property value="#theBean.NAME"/></td>
			  			<td width="100"><s:property value="#theBean.UPDATETIME"/></td>
			  			<td width="100"><s:property value="#theBean.PROVIDERS"/></td>
			  			<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.SOFTPARTH"/>'>
			  				<s:property value="#theBean.SOFTPARTH"/>
			  			</div> 
			  		    </td>
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
