<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function isdigit(s)
            {
            var r,re;
            re = /\d*/i; //\d��ʾ����,*��ʾƥ��������
            r = s.match(re);
            return (r==s)?1:0;
            }
            
	function submitRequest(theForm){    
	 
	     if(theForm.STRATEGYNAME.value.length ==0){
	     alert("�����������Ʋ���Ϊ�գ�");
	     theForm.name.focus;
	     return false  ;
	    }
	    if(theForm.STRATEGY.value =='all'){
	     alert("��ѡ����Ҫ�������Զ�Ӧ������");
	     return false  ;
	    }
	    if(theForm.BASEPATH.value.length ==0){
	     alert("��׼��������·������Ϊ�գ�");
	     theForm.BASEPATH.focus;
	     return false  ;
	    }
	    theForm.action="strategy_sureAddUpgradeStrategy.do";
	    theForm.submit();
	}
	



</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="strategy_sureAddUpgradeStrategy.do" method="post"
		id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					��������
					<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'2':'��׼����'}" name="theForm.TYPE"></s:select>
				</td>
				<td class="til">
					��׼��������
					<font color="red">*</font>
				</td>
				<td>
					<s:select list="theForm.hostList" headerKey="all" headerValue="-----��ѡ��-----" name="theForm.STRATEGY" listKey="ID" listValue="HOSTNAME" id="STRATEGY"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					������������
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.STRATEGYNAME" id="STRATEGYNAME"/>
				</td>
				<td class="til">��׼��������Ӧ��·��<font color="red">*</font></td>
				<td><s:textfield name="theForm.BASEPATH" id="BASEPATH"/></td> 
			</tr>
			<tr>
				    
					<td></td><td></td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="ȷ��"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="����"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>

		</table>
	</s:form>
</body>

</html:html>
