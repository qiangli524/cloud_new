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
	
  String.prototype.Trim  = function(){return this.replace(/^\s+|\s+$/g,"");}
	function BtnSave_OnClick(thisForm){
	
		if( confirm("确定要保存吗？")){
	 	  checkValid(thisForm)
	 	  thisForm.action = "file_saveDirectory.do";
	 	  thisForm.submit();
		}

	}

	function checkValid(obj){
		if (obj.LIST_NAME.value==null||obj.LIST_NAME.value==""){
		 	alert('目录名称不能为空');
		 	return false;
		}
		return true;
	}

          
     function back(thisForm){
     	thisForm.LIST_ID.value = '';
     	thisForm.LIST_NAME.value = '';
     	thisForm.SI_ID.value = '';
     	thisForm.action="file_showDirectoryList.do";
		thisForm.submit();
     }
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="file_showFileManagerAdd.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
<s:hidden name="theForm.LIST_ID" id="LIST_ID"></s:hidden>
<s:hidden name="theForm.OLD_LIST_NAME" id="OLD_LIST_NAME"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				     	目录名称<font color="red">*</font>
				    </td>
				    <td>
				    	<s:textfield name="theForm.LIST_NAME" cssClass="txt" id="LIST_NAME"></s:textfield>
				    </td>
				    <td class="til">所属文件类型<font color="red">*</font></td>
    				<td>
						<s:select list="theForm.SI_LIST" name="theForm.SI_ID" id="SI_ID" listKey="SI_ID" listValue="SI_NAME" ></s:select>
    				</td>
    			</tr>
    			<tr>
				    <td class="til">
						是否生效:<font color="red">*</font>
					</td>
					<td>
    					<s:select list="#{'1':'有效','0':'无效'}" name="theForm.ENABLE" id="ENABLE"></s:select>
    				</td>
    				<td class="til">
    				</td>
    				<td></td>
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
