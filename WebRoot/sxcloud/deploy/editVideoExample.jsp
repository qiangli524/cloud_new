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
	function submitRequest(thisForm){
	    if(thisForm.selectHOSTNAME.value==null||thisForm.selectHOSTNAME.value==''){
	     alert("请选择主机名称！");
	     return false  ;
	    }
	    if(thisForm.selectHOSTUSERNAME.value==null||thisForm.selectHOSTUSERNAME.value==''){
	     alert("请选择主机用户名！");
	     return false  ;
	    }
	    if(thisForm.VIDEONAME.value.length ==0){
	     alert("录像名称不能为空！");
	     thisForm.VIDEONAME.focus;
	     return false  ;
	    }
	    thisForm.submit();
	}
	
	
	
	
</script>
</head>
<body class="pop-body scrollbody">
	<html:form action="saveVideoExample" method="post" styleId="theForm">
	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
		<bean:define id="theForm" name="VideoExampleForm" />
		<html:hidden name="theForm" property="ID" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						主机名称<font color="red">*</font>
                      <input type="hidden" id="HOSTIP" value="<bean:write name="theForm" property="HOSTIP" />"/> 
					</td>
					<td>
                      <select id="selectHOSTNAME" name="HOSTID" onchange="select2(this.value)">
                      </select>
					</td>
				    <td class="til">
						主机用户名<font color="red">*</font>
					</td>
					<td>
                      <select id="selectHOSTUSERNAME" name="HOSTUSERNAME">
                              <option selected="selected" value="">请选择</option>
                      </select>
                      <input type="hidden" id="HOSTUSERNAME" name="HOSTUSERNAME" value="<bean:write name="theForm" property="HOSTUSERNAME"/>"/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						录像名称<font color="red">*</font>
					</td>
					<td>
					    <html:text property="VIDEONAME" styleClass="txt" />              
					</td>
				    <td class="til">
						录像人
					</td>
					<td>
						<bean:write name="theForm" property="CREATEUSER"/>
						<input type="hidden" name="CREATEUSER" id="CREATEUSER" value="<bean:write name="theForm" property="CREATEUSER"/>"/>
					</td>
				</tr>
		       
				<tr>
				    <td class="til">
						备注
					</td>
					<td colspan="3"><html:textarea property="REMARK" cols="77" rows="5"></html:textarea>
					</td>		
				</tr>
				
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>

			</table>
	</html:form>
	<script>
	  select1();
      function select1(){
	  var HOSTIP=document.getElementById("HOSTIP").value;
	  var HOSTID='';
	  html="<option selected='selected' value=''>请选择</option>";
	  $.getJSON("findAllHost.do",function(data){
		for(var i=0;i<data.length;i++)
		{
			if(HOSTIP==data[i].IP){
				HOSTID=data[i].ID;
				html=html+"<option selected='selected' value='"+data[i].ID+"'>"+data[i].HOSTNAME+"</option>";
			}else{
				html=html+"<option value='"+data[i].ID+"'>"+data[i].HOSTNAME+"</option>";
			}		
		}
		$("#selectHOSTNAME").html(html);
		if(HOSTID!=null&&HOSTID!=""){
	   		select2(HOSTID);
	   }
	   });
	   
	}
	
	 function select2(HOSTID){
		html='';
        var HOSTUSERNAME=document.getElementById("HOSTUSERNAME").value;
        if(HOSTID==''||HOSTID==null){
        html="<option selected='selected' value=''>请选择</option>";
		$("#selectHOSTUSERNAME").html(html);
        }else{
        $.getJSON("findHostConfigListByHOSTID.do?HOSTID="+HOSTID,function(data){
		for(var i=0;i<data.length;i++)
		{
			if(HOSTUSERNAME==data[i].HOSTUSERNAME)
			{html=html+"<option selected='selected' value='"+data[i].HOSTUSERNAME+"'>"+data[i].HOSTUSERNAME+"</option>";}
			else
			{html=html+"<option value='"+data[i].HOSTUSERNAME+"'>"+data[i].HOSTUSERNAME+"</option>";}		
		}
		$("#selectHOSTUSERNAME").html(html);
	   });
       } 
	    
	}
	
</script>
</body>

</html:html>
