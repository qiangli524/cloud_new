<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%> 
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function resetForm(theForm){
		theForm.NAME.value ='';
		theForm.STATUS.value = '';
		theForm.DESCRIBTION.value='';
	}
    
    function searchRequest() {
		theForm.submit();
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
<body onLoad="self.focus();document.theForm.NAME.focus()">
<s:form action="fileversion_queryFileVersionHisList.do" id="theForm" method="post"
	cssClass="theForm">
<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">文件版本管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                <label class="vm">版本包：</label>
						<s:textfield name="theForm.NAME" cssClass="txt" id="NAME"></s:textfield>
                    <label class="mgl-20 vm">版本状态:</label>
						<s:select cssClass="select-1 vm" id="STATUS" name="theForm.STATUS" list="#{'':'--请选择--','0':'未使用','1':'已使用','2':'版本错误'}"></s:select>
                    <label class="vm">版本描述：</label>
						<s:textfield name="theForm.DESCRIBTION" cssClass="txt" id="DESCRIBTION"></s:textfield>
                    <span class="ubtn-1 mgl-20"><input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input id="resetForm" type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
	
        
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">版本包</th>
				   <th onclick="sort(theTable,1,'string')">所属应用</th>
				   <th onclick="sort(theTable,2,'float')">版本号</th>                
                   <th onclick="sort(theTable,3,'string')">版本状态</th>
                   <th onclick="sort(theTable,4,'string')">版本包路径</th>
                   <th onclick="sort(theTable,5,'string')">版本描述</th>
                   <th onclick="sort(theTable,6,'date')">创建时间</th>
                   <th onclick="sort(theTable,7,'string')">创建人</th>
                   <th onclick="sort(theTable,8,'date')">使用时间</th>
                   <th onclick="sort(theTable,9,'string')">删除人</th>
                   <th onclick="sort(theTable,10,'date')">删除时间</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="theForm.fileVersionList" id="theBean">
				<tr>
					<td><s:property value="#theBean.NAME" /></td>
					<td><s:property value="#theBean.APPNAME" /></td>
					<td><s:property value="#theBean.NO" /></td>
					<td>
			  			<s:if test="#theBean.STATUS==0">
			  				未使用
			  			</s:if>
			  			<s:elseif test="#theBean.STATUS==1">
			  				已使用
			  			</s:elseif>
			  			<s:elseif test="#theBean.STATUS==2">
			  				版本错误
			  			</s:elseif>
			  		</td>
			  		<td width="170"> 
			  			<div class="hidden" title='<s:property value="#theBean.LOCATION"/>'>
			  				<s:property value="#theBean.LOCATION"/></a>
			  			</div> 
			  		</td>
			  		<td width="170"> 
			  			<div class="hidden" title='<s:property value="#theBean.DESCRIBTION"/>'>
			  				<s:property value="#theBean.DESCRIBTION"/></a>
			  			</div> 
			  		</td>
					<td><s:property value="#theBean.CREATED_TIME" /></td>
					<td><s:property value="#theBean.CREATED_USER" /></td> 
					<td><s:property value="#theBean.USED_TIME" /></td>
					<td><s:property value="#theBean.DELETE_BY" /></td>
					<td><s:property value="#theBean.DELETE_TIME" /></td>
				</tr>
			   </s:iterator>
			  </tbody>
			</table>
			<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
