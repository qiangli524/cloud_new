<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
  String.prototype.Trim  = function(){return this.replace(/^\s+|\s+$/g,"");}
	function BtnSave_OnClick(theForm){
		if( confirm("确定要保存吗？")  && checkValid(theForm) ){
			  theForm.action = "feedback_saveFeedBackType.do";
		 	  theForm.submit();
		 
	      return true;
		}
		return false;
	}
	function BtnSelect_OnSelectAll(obj){
		if(obj != null && obj.length > 0 ){
           for(var i=0;i<obj.length;i++){
               obj[i].selected="true";
           }
        }
	}
	function BtnNew_OnClick(theForm){
		theForm.submit();
		return true;
	}

	function checkValid(obj){

		if (obj.TYPE_NAME==null||obj.TYPE_NAME==""){
			alert('类型名称不能为空');
			return false;
		} 
		if (obj.TYPE_DESC==null||obj.TYPE_DESC==""){
			alert('类型描述不能为空');
			return false;
		}
	
		return true;
	}
	 function isdigit(s)
            {
            var r,re;
            re = /\d*/i; 
            r = s.match(re);
            return (r==s)?1:0;
            }
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="feedback_saveFeedBackType.do" method="post" id="theForm">
  		<s:hidden name="theForm.action_type" value=""/>
  		<s:hidden name="theForm.TYPE_CODE"  />
  		<s:hidden name="theForm.TYPE_ID" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				     	类型名:<font color="red">*</font>
				    </td>
				    <td>
				    	<s:textfield name="theForm.TYPE_NAME" id="TYPE_NAME" />
				    </td>					
				    <td class="til">
						是否生效:<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'1':'有效','0':'无效'}" name="theForm.ENABLE" id="ENABLE" headerKey="" headerValue="请选择"></s:select>
    				</td>
				</tr>
				<tr>
    				<td class="til">类型描述：</td>
    				<td colspan="3"><s:textarea name="theForm.TYPE_DESC" cols="40" rows="3" id="TYPE_DESC" /></td>
  				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="BtnSave_OnClick(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()" />
					</td>
				</tr>

			</table>
	</s:form>
</body>
</html:html>