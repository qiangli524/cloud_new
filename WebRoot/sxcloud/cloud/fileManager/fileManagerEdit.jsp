<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
   function BtnSave_OnClick(thisForm){
	
		if( confirm("确定要保存吗？")  && checkValid(thisForm) ){
		  if(thisForm.FLAG.value == "add"){
		  	thisForm.action="file_fileManagerAdd.do";
		  }else{
		  	thisForm.action="file_fileManagerUpdate.do";
		  }
	 	  thisForm.submit();
		}

	}

	function checkValid(obj){
		if (obj.TYPE_NAME.value==null||obj.TYPE_NAME.value==""){
			alert('类型名称不能为空');
			return false;
		} 
		return true;
	}

          
     function back(thisForm){
     	thisForm.TYPE_NAME.value = '';
     	thisForm.ENABLE.value= '';
     	thisForm.action="file_FileManagerList.do";
		thisForm.submit();
     }
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="file_showFileManagerAdd.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.FLAG" id="FLAG"></s:hidden>
<s:hidden name="theForm.TYPE_ID" id="TYPE_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				     	类型名:<font color="red">*</font>
				    </td>
				    <td>
				    	<s:textfield name="theForm.TYPE_NAME" cssClass="txt" id="TYPE_NAME"></s:textfield>
				    </td>					
				    <td class="til">
						是否生效:<font color="red">*</font>
					</td>
					<td>
    					<s:select list="#{'1':'有效','0':'无效'}" name="theForm.ENABLE" id="ENABLE"></s:select>
    				</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="BtnSave_OnClick(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="back(document.getElementById('theForm'));return false;" />
					</td>
				</tr>

			</table>
	</s:form>
</body>
