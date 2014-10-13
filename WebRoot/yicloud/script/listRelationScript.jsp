<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
 	function resetForm(theForm){
 		var $_checkboxid = $("[name='checkboxid']");
		if(!$_checkboxid.length>0 ){
			return;
		}
		$.each($_checkboxid,function(k,v){
			$(v).attr("checked",false);
		});
	}
	function submitRequest(theForm){
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    var templet_name = '<%=request.getAttribute("templet_name")%>';
 	    var templet_id = '<%=request.getAttribute("templet_id")%>';
 	    var chks= "";
 	    var allIds = "";
		if(checkboxids!=null&&checkboxids.length>0){
		    for(var i=0;i<checkboxids.length;i++){
		    	if(i==checkboxids.length-1){
		    		allIds += checkboxids[i].value;
				    if(checkboxids[i].checked){
						chks += checkboxids[i].value;
				    }
		    	}else{
		    		allIds += checkboxids[i].value+":";
				    if(checkboxids[i].checked){
						chks += checkboxids[i].value+":";
				    }
		    	}

		    }
		}
		var url = 'templettree_relationRequest.do?allIds='+allIds+"&chks="+chks+"&templet_id="+templet_id+"&templet_name="+encodeURI(encodeURI(templet_name));
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				alert("关联成功！");
				theForm.action='templettree_flashMenu.do?allIds='+allIds+"&chks="+chks+"&templet_id="+templet_id+"&templet_name="+encodeURI(encodeURI(templet_name));
	   			theForm.submit();
			}else{
				alert("关联失败！" );
				theForm.action='templettree_flashMenu.do?allIds='+allIds+"&chks="+chks+"&templet_id="+templet_id+"&templet_name="+encodeURI(encodeURI(templet_name));
	    		theForm.submit();
			}
		});
	}
	/*
		保持复选框状态
		zhangwj 2012-4-8
	*/	
	$(function(){
		var chks = document.getElementById("theForm").chks.value;
		var $_checkboxid = $("[name='checkboxid']");
		if(!$_checkboxid.length>0 ){
			return;
		}
		if( null != chks && "" != chks){
			$.each($_checkboxid,function(k,v){
				var id = $(v).val();
				var arr = chks.split(":");
				for(var j=0;j<arr.length;j++){
					if(id == arr[j]){
						$(v).attr("checked",true);
					}
				}
			});
		}
	})
</script>
</head>
<body>
<s:form action="templettree_listScript.do" method="post" id="theForm">
<s:hidden name="theScriptForm.id" id="id"></s:hidden>
<s:hidden name="theScriptForm.chks" id="chks"></s:hidden>
<div class="scrollbody">
	<div class="blue-wrap noborder">
		<div class="tit-zzi">
			<div>
				设置与模板"<s:property value="#request.templet_name"/>"关联
			</div>
		</div>
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
			  	   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">脚本名称</th>
				   <th onclick="sort(theTable,2,'string')">脚本内容</th>                
                   <th onclick="sort(theTable,3,'string')">脚本描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theScriptForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
			  		<td> <s:property value="#theBean.name"/></td>
			  		<td> <s:property value="#theBean.content"/></td>
			  		<td> <s:property value="#theBean.des"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			  <tfoot>
			  	<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="关联"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:resetForm(document.getElementById('theForm'));return false;" />
					</td>
				</tr>
			  </tfoot>
			</table>
		</div>
	</div><!--blue-wrap end -->
</div>
</s:form>
</body>
