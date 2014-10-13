<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
    api.button({
		id:'OkAnd',
		name: '确定',
		type: 'submit',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	}); 
	function submitRequest(){ 
	    if(theForm.c_name.value.length ==0){
	     alert("机柜名称不能为空！");
	     return false  ;
	    }   
	    if(theForm.c_addr.value.length ==0){
	     alert("机柜位置不能为空！");
	     return false  ;
	    }
	    
	    if(theForm.r_id.value.length ==0){
	     alert("机柜所在房间不能为空！");
	     return false  ;
	    }
	    if(theForm.c_type.value.length ==0){
	     alert("机柜类型不能为空！");
	     return false  ;
	    }
	    if(theForm.c_desc.value.length ==0){
		     alert("机柜描述不能为空！");
		     return false  ;
		    }
	    if(theForm.e_use.value.length ==0){
		     alert("机柜已使用数量不能为空！");
		     return false  ;
		    }
	    if(theForm.e_nums.value.length ==0){
		     alert("机柜机位总数不能为空！");
		     return false  ;
		    }
	    var e_nums=theForm.e_nums.value;
	    if(!isnumber(e_nums)){
   			alert("机柜机位总数输入不合法,只能为数字!");
   			return false;
   		}
	    var e_use=theForm.e_use.value;
	    if(!isnumber(e_use)){
   			alert("机柜已使用数量输入不合法,只能为数字!");
   			return false;
   		}
   		if(parseInt(e_use) > parseInt(e_nums)){
   			alert("机柜已使用数量不能大于机柜机位总数!");
   			return false;
   		} 
   		
	   var theForm1 = $("#theForm").serialize();
	    w.save(theForm1);
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
	<s:form action="cubinet_saveCubinet.do" method="post" id="theForm">
		<s:hidden name="theForm.c_id"  />
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					机柜名称
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.c_name" id="c_name"/>
				</td>
				<td class="til">
					机柜位置
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.c_addr" id="c_addr"/>
				</td>
			</tr>

			<tr>
				<td class="til">
					机柜所在房间编号
					<font color="red">*</font>
				</td>
				<td>
					<s:select headerKey="" headerValue="请选择" list="theForm.roomOptionList" listKey="r_id" listValue="r_name" id="r_id" name="theForm.r_id">
					</s:select>
				</td>
				<td class="til">
					机柜机位总数
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.e_nums" id="e_nums" />
				</td>
			</tr>
			<tr>
				<td class="til">
					机柜已使用数量
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.e_use" id="e_use"/>
				</td>
				<td class="til">
					机柜类型
					<font color="red">*</font>
				</td>
				<td>
					<s:select headerKey="" headerValue="请选择" name="theForm.c_type" id="c_type" list="#{'1':'IBM机柜(Power)','2':'IBM机柜(小刀)','3':'HP机柜(小刀)','4':'磁带库机柜','5':'网络机柜','0':'其他机柜'} ">
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					机柜描述
					<font color="red">*</font>
				</td>
				<td colspan="4">
					<s:textfield name="theForm.c_desc" id="c_desc" />
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
