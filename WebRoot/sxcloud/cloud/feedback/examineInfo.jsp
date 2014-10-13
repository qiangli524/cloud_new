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
	function BtnSave_OnClick(thisForm){
		if( confirm("确定要保存吗？")  && checkValid(thisForm) ){
			  thisForm.action_type.value = "saveFeedBackType";
		 	  thisForm.submit();
		 
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
	function BtnNew_OnClick(thisForm){
		thisForm.submit();
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
	<s:form action="feedback_loadListFeedBackInfo.do" method="post" id="pfbForm">
  		<s:hidden name="pfbForm.action_type" value=""/>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				     	ID:
				    </td>
				    <td>
				    	<s:textfield name="pfbForm.ID"  styleClass="input disable" readonly="true" />
				    </td>					
				    <td class="til">
						提交时间:
					</td>
					<td>
					    <s:textfield  name="pfbForm.SUBMIT_TIME" styleClass="input diaable" readonly="true" />
    				</td>
				</tr>
				<tr>
    				<td class="til">反馈信息提交人：</td>
    				<td>
    					<s:textfield name="pfbForm.LOGIN_ID"  styleClass="input disable" readonly="true" />
    				</td>
    				<td class="til">反馈信息接收人：</td>
    				<td>
    					<s:textfield name="pfbForm.SENTTO_EMPLOYE"  styleClass="input disable" readonly="true" />
    				</td>
  				</tr>
  				<tr>
    				<td class="til">反馈类型: </td>
    				<td>
    					<s:textfield name="pfbForm.TYPE_ID"  styleClass="input disable" readonly="true" />
    				</td>
    				<td class="til">反馈信息标题:</td>
    				<td>
    					<s:textfield name="pfbForm.TITLE"  styleClass="input disable" readonly="true" />
    				</td>
  				</tr>
  				<tr>
  					<td class="input_bg">反馈信息内容：</td>
    				<td class="input_bg" colspan="3"><s:textarea name="pfbForm.DF_INFO" cols="40" rows="3"  styleClass="input disable" readonly="true" /></td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="history.back();return false;" />
					</td>
				</tr>

			</table>
	</s:form>
</body>
</html:html>