<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
	function submitRequest(theForm){ 
	    if(theForm.HOSTNAME.value.length ==0){
	     alert("主机名称不能为空！");
	     document.theForm.HOSTNAME.focus();
	     return false  ;
	    }   
	   
	    if(theForm.IP.value.length ==0){
	     alert("IP地址不能为空！");
	     document.theForm.IP.focus();
	     return false  ;
	    }
	    var ip=theForm.IP.value;
	    if(!ipFormat(ip)){
   			alert("IP地址输入不合法!");
   			document.theForm.IP.focus();
   			return false;
   		}
	    if(theForm.FREQUENCY.value.length ==0){
	     alert("主机主频不能为空！");
	     document.theForm.FREQUENCY.focus();
	     return false  ;
	    }
	    if (!isnumber(theForm.FREQUENCY.value)) {
	    	alert("主机主频只能为数字！");
	    	document.theForm.FREQUENCY.focus();
	     	return false  ;
	    }
	    if(theForm.OPERATE_SYSTEM.value ==-1){
	     alert("操作系统不能为空！");
	     document.theForm.OPERATE_SYSTEM.focus();
	     return false  ;
	    }
	    if(theForm.VLAN.value ==-1){
		     alert("所属VLAN不能为空！");
		     document.theForm.VLAN.focus();
		     return false  ;
		}
	    if(theForm.STATUS.value==-1){
	     alert("主机状态不能为空！");
	     document.theForm.STATUS.focus();
	     return false  ;
	    } 
   		if (!isnumber(theForm.MEMORY.value)) {
	    	alert("主机内存只能为数字！");
	    	document.theForm.MEMORY.focus();
	     	return false  ;
	    }
	    if(Trim(document.getElementById("span_key").innerHTML).length > 0) {
   			alert("请重新输入主机IP地址！");
   			document.theForm.IP.focus();
   			return false;
   		} 
   		
   		 if(Trim(document.getElementById("span_name").innerHTML).length > 0) {
   			alert("请重新输入主机名称！");
   			document.theForm.HOSTNAME.focus();
   			return false;
   		} 
		/* 变量vlanbf根本取不到，看不懂啥意思，屏蔽
	    var sql_type=0;
	    var vlanbf=theForm.VLAN_BF; 
	    var vlan=theForm.VLAN; 
	    if(vlanbf==vlan){
	    	theForm.action='hostmanage_saveBusiHost.do?SQL_TYPE='+sql_type;
	    	theForm.submit();
	    	return false;
	    }else{
	    	sql_type=1;
	    	theForm.action='hostmanage_saveBusiHost.do?SQL_TYPE='+sql_type;
	    	theForm.submit();
	    	return false;
	    }*/
	    var sql_type=0;
	    theForm.action='hostmanage_saveBusiHost.do?SQL_TYPE='+sql_type;
	    theForm.submit();
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
	/* 检测字符串是否为IP地址 */
	function ipFormat(str)	{
		var re=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	  	if (str.match(re)== null){
      		return false;
    	}else{
    		var stn = str.split(".");
    		var re1=/^0\d|0\d\d|00\d$/;
			for(var i=0;i<4;i++){
				if(stn[i].match(re1)!= null){
				//alert("is 0");
				return false;
				break;
				}
			}
		}
    	return true;
    }
    
    /*校验IP是否已存在*/
    function checkHost() {
	    var ip=theForm.IP.value; 
	    var id=theForm.ID.value; 
	    if (ip.length > 0 && ipFormat(ip)) {
	        var url = 'hostmanage_queryAjx_ip.do?ip='+ ip + '&id=' + id + '&flag=ip';
			document.getElementById("span_key").innerHTML="";
			$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.result=='1'){
					document.getElementById("span_key").innerHTML="主机IP"+ip+"已生成！请请重新输入!"; 
				}
			});
	    }
	}
	
	 /*校验主机名是否已存在*/
    function checkHostName() {
	    var name=Trim(theForm.HOSTNAME.value); 
	    var id=theForm.ID.value; 
	    if (name.length > 0) {
	        var url = 'hostmanage_queryAjx_ip.do?name='+ name + '&id=' + id + '&flag=name';
			document.getElementById("span_name").innerHTML="";
			$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.result=='1'){
					document.getElementById("span_name").innerHTML="主机名称"+name+"已生成！请请重新输入!"; 
				}
			});
	    }
	}
	   
</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.HOSTNAME.focus()">
	<s:form action="hostmanage_saveBusiHost.do" method="post" id="theForm" > 
	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
	    <s:hidden name="theForm.ID" id="ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						主机名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.HOSTNAME" id="HOSTNAME" onblur="checkHostName()"  cssClass="required inpt-2"/>
						<span style="color:RED" id="span_name"/>    
					</td>
					<td class="til">
						主机状态 <font color="red">*</font>
					</td>
					<td>
	                   <s:select headerKey="-1" headerValue="请选择" list="#{'1':'空闲','2':'非空闲'}" name="theForm.STATUS" id="STATUS" cssClass="select-1">
	                    	<s:if test="#theForm.STATUS==1">空闲</s:if>
					    	<s:if test="#theForm.STATUS==2">非空闲</s:if>
						</s:select>         	
					</td>
				</tr>

				<tr>
				    <td class="til">
						IP地址 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.IP" id="IP"  onblur="checkHost()"  cssClass="required inpt-2"/>
						<span style="color:RED" id="span_key"/>    
					</td>
					<td class="til">
						主机内存
					</td>
					<td>
                        <s:textfield name="theForm.MEMORY" id="MEMORY"  cssClass="required inpt-2"/>GB
					</td>
				</tr>
				<tr>
				    <td class="til">
						主机主频<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.FREQUENCY" id="FREQUENCY"  cssClass="required inpt-2"/>GHZ
					</td>
					<td class="til">
						主机厂商
					</td>
					<td>
                       <s:textfield name="theForm.MANUFACTURERS" id="MANUFACTURERS"  cssClass="required inpt-2"/>
					</td>
				</tr>
				<tr>
					<td class="til">所属VLAN<font color="red">*</font></td>
					<td>
						<s:select cssClass="select-1" headerKey="-1" headerValue="请选择" list="#{'1':'Default','2':'www_web','3':'inside_web'}" name="theForm.VLAN" id="VLAN">
        				
					    	<s:if test="#theForm.VLAN==1">Default VLAN</s:if>
						     <s:if test="#theForm.VLAN==2">www_web</s:if>
						     <s:if test="#theForm.VLAN==3">inside_web</s:if>
	                   </s:select>
					</td>
					<td class="til">
						操作系统<font color="red">*</font>
					</td>
					<td >
					    <s:select cssClass="select-1" headerKey="-1" headerValue="请选择" name="theForm.OPERATE_SYSTEM" list="#{'1':'Linux','2':'HP-UX'}" id="OPERATE_SYSTEM">
		                     <s:if test="#theForm.OPERATE_SYSTEM==1">Linux</s:if>
						     <s:if test="#theForm.OPERATE_SYSTEM==2">HP-UX</s:if>
						     
	                   </s:select><%--
					    <html:text property="OPERATE_SYSTEM" styleClass="txt" readonly="true"/>--%>
					</td>
					<!--<td class="til">
						交换机端口<font color="red">*</font>
					</td>
					<td >
	                   <select id="HOSTPORT_T" name="HOSTPORT_T" >
                              <option selected="selected" value="">请选择</option>
                        </select>
					</td>
				--></tr>
			
				<tr>
					<td colspan="4" class="btnCenter">
						<span class="ubtn-green"><input type="button" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
						<span class="ubtn-orange mgl-20"><input type="button" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" /></span>
						<span class="ubtn-blue mgl-20"><input type="button" value="返回"
							onclick="window.history.back()"/></span>
					</td>
				</tr>

			</table>
	</s:form>
	<script>
		/*
	    switch_port();
	   	function switch_port(){
		  var form=document.getElementById('theForm1');
		  var switch_port=document.getElementById('HOSTPORT').value;
		  var html="";
		  var vlan=form.VLAN.value;
		   if(vlan==null||vlan==0){
		  	 html="<option selected='selected' value=''>请选择</option>";
		  	 $("#HOSTPORT_T").html(html);
		  	return;
		  }
		  $.getJSON("Switch_port.do?id="+vlan,{'time':new Date().toString()},function(data){
				for(var i=0;i<data.length;i++)
				{
					if(data[i].SWITCHPORT==switch_port)
					{html=html+"<option selected='selected' value='"+data[i].SWITCHPORT+"'>"+data[i].SWITCHPORT+"</option>";}
					else
					{html=html+"<option value='"+data[i].SWITCHPORT+"'>"+data[i].SWITCHPORT+"</option>";}		
				}
				$("#HOSTPORT_T").html(html);
		   });
		}*/
	</script>
</body>

</html:html>
