<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	function checknumber(str){
		var pattern1=/^([1-9])([0-9])*.([0-9])+G$/;
		var pattern2=/^([1-9])([0-9])*G$/;
		if(!pattern1.test(str)){
			if(!pattern2.test(str)){
				return false;
			}
		}
		return true;
	}
	function resetForm(thisForm){
		thisForm.HOSTUSERNAME.value='';
		thisForm.HOSRPWD.value='';
		thisForm.SPACE.value='';
	}
	function submitRequest(thisForm){   
	
		 if(thisForm.HOSTUSERNAME.value.length ==0){
	     alert("用户名不能为空！");
	     return false  ;
	    }
	    if(thisForm.HOSRPWD.value.length ==0){
	     alert("密码不能为空！");
	     return false  ;
	    }
	  
	    if(thisForm.SPACE.value.length ==0){
	     alert("磁盘空间不能为空！");
	     return false  ;
	    }
   		
   		var space=document.thisForm.SPACE.value;
   		
   		 
   		if(!checknumber(space)){
   			alert("磁盘空间输入不合法,如1.1G!");
   			document.thisForm.SPACE.value='';
   			document.thisForm.SPACE.focus;
   			return false;
   		}
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="virpool_saveVirPoolConfig.do" method="post" cssClass="theForm" id="thisForm">
<s:hidden name="thisForm.ID" id="ID"></s:hidden>
<s:hidden name="thisForm.flag" id="flag"></s:hidden>
<s:hidden name="thisForm.HOSTID" id="HOSTID"></s:hidden>
		     <table  width="100%" border="0" cellspacing="0" class="pop-table nosize">
		       <tr>
				    <td class="til">
						虚拟机编号 
					</td>
					<td >
						<s:property value="thisForm.HOSTID"/>
					</td>
					<td class="til">
						主机用户名 <font color="red">*</font>
					</td>
					<td >
							<s:textfield name="thisForm.HOSTUSERNAME" cssClass="txt" id="HOSTUSERNAME"></s:textfield>
					</td>			
				</tr>
				<tr>
					<td class="til">
						主机密码<font color="red">*</font>
					</td>
					<td>
                        <s:password name="thisForm.HOSRPWD" cssClass="txt" id="HOSRPWD"></s:password>
					</td>
					 <td class="til">
						磁盘空间<font color="red">*</font>
					</td>
					<td>
					<s:textfield name="thisForm.SPACE" cssClass="txt" id="SPACE"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						用户类型<font color="red">*</font>
					</td>
					 <td>
					    	<s:select list="#{'2':'普通用户','1':'超级管理员'}" name="thisForm.TYPE" id="TYPE"></s:select>
					</td>
					<td class="til">
					</td>
					<td>
					</td>
				</tr> 
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('thisForm'));return false;" />
						<input type = "button" class="btn-style02" value = "重置" 
						onclick = "javascript:resetForm(document.getElementById('thisForm'))" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>	
					</td>
				</tr>
		     </table>
</s:form>
</body>
