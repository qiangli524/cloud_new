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
	
	function submitRequest(thisForm){   
		if(thisForm.VH_NAME.value.length==0){
			alert("请填写虚拟机名称");
			return false;
		} 
		if(thisForm.eq_id.value==""){
			alert("请选择物理主机编号");
			return false;
		}
		if(thisForm.VH_IP.value.length==0){
			alert("请输入IP地址");
			return false;
		}
		if(thisForm.VH_TYPE.value==""){
			alert("请选择虚拟机类型");
			return false;
		}
		if(thisForm.PROTOCOL.value==""){
			alert("请选择监控方式");
			return false;
		}
		 var ip=thisForm.VH_IP.value;
		 if(ip.indexOf(",") !=-1){
		 	var ips = ip.split(",");
		 	for(var i=0;i<ips.length;i++){
		 		if(!isnumber(ips[i].replace(/(^\s*)|(\s*$)/g, ""))){	//多个IP地址，拆分后去掉空格判断IP地址是否合法
   					alert("IP地址输入不合法,每个IP地址只能为.和数字!");
   					return false;
   				}
		 	}
		 }else  if(!isnumber(ip)){
   			alert("IP地址输入不合法,只能为.和数字!");
   			return false;
   		}
	    thisForm.submit();
	}
	/* 检测输入的字符串是否符合要求 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	   {
	            if (number_chars.indexOf(str.charAt(i))==-1) return false;
	        }
	        return true;
	}
	

</script>
</head>
<body class="pop-body scrollbody">
<s:form action="virpool_saveVirtualToPool.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.VH_ID" id="VH_ID"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						虚拟机名称
					</td>
					<td>
					    <s:textfield name="theForm.VH_NAME" cssClass="txt" id="VH_NAME"></s:textfield>                 
					</td>
					 <td class="til">
						物理主机编号 
					</td>
					<td >
					<s:select list="theForm.eqidList" name="theForm.eq_id" id="eq_id" listKey="eq_id" listValue="eq_id" headerKey="" headerValue="-请选择-" cssStyle= "width:160px;margin-left:0px "></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						虚拟机IP<font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.VH_IP" cssClass="txt" id="VH_IP"></s:textfield>            
					</td>
					<td class="til">虚拟机类型</td>
					<td>
	        	         <s:select list="#{'':'请选择','0':'IBM'}" name="theForm.VH_TYPE" id="VH_TYPE"></s:select>
	        	    </td>
				</tr>
				<tr>
				 <td class="til">
						监控方式
					</td>
					<td>
	        	        <s:select list="#{'':'请选择','ssh':'ssh','telnet':'telnet'}" name="theForm.PROTOCOL" id="PROTOCOL"></s:select>           
					</td>
	        	    <td class="til">端口</td>
	        	    <td>
	        	  	  <s:textfield name="theForm.PORT" cssClass="txt" id="PORT"></s:textfield> 
	        	  	 </td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
</body>
