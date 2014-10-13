<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.INTERFACE_A.value = "0";
		theForm.INTERFACE_Z.value = "0";
		theForm.STATUS.value = "0";
	}

	function searchRequest() { 
	 	 theForm.action = 'connectinfo_listConnect.do' 
		theForm.submit();
 	}
 	
	function addRequest() {
 	    theForm.action = 'connectinfo_addConnect.do' 
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
 	    theForm.action = 'connectinfo_modConnect.do';
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
 	    theForm.action = 'connectinfo_delConnect.do'  
		theForm.submit();
 	}

</script>
</head>
<body class="pop-body scrollbody">
<s:form action="datastore_listDataStore.do" method="post" id="theForm"
	cssStyle="theForm">
	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til">
					名称
				</td>
				<td>
					<s:property value="#request.NAME"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					容量
				</td>
				<td>
					<s:property value="#request.CAPACITY"/>GB
				</td>
			</tr>
			<tr>
				<td class="til">
					可用空间
				</td>
				<td>
					<s:property value="#request.FREE_SPACE"/>GB
				</td>
			</tr>
			<tr>
				<td class="til">
					类型
				</td>
				<td>
					<s:property value="#request.TYPE"/>
				</td>
			</tr>
			</table>
		</div>
</s:form>
</body>
