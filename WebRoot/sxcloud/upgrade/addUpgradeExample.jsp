<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function isdigit(s)
            {
            var r,re;
            re = /\d*/i; //\d表示数字,*表示匹配多个数字
            r = s.match(re);
            return (r==s)?1:0;
            }
            
	function submitRequest(theForm){    
	 
	    if(theForm.APPID.value.length ==0){
	     alert("部署应用不能为空！");
	     theForm.APPID.focus;
	     return false  ;
	    }
	    if(theForm.HOSTID.value.length ==0){
	     alert("部署主机不能为空！");
	     theForm.HOSTID.focus;
	     return false  ;
	    }
	    if(theForm.KEYNAME.value.length ==0){
	     alert("部署中间件不能为空！");
	     theForm.KEYNAME.focus;
	     return false  ;
	    }
	    theForm.submit();
	}
	



</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="example_sureAddUpgradeExample.do" method="post"
		id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				    <td class="til">
				       部署应用<font color="red">*</font>
				    </td>
				    <td>
				    		<s:select list="theForm.appList" headerKey="" headerValue="-请选择-" 
				    		name="theForm.APPID" listKey="ID" listValue="APPNAME" id="APPID" onchange="app_port()"></s:select>
				    </td>					
				    <td class="til">
						端口<font color="red">*</font>
					</td>
					<td>
					    <s:select id="APPPORT" name="thForm.APPPORT" list="#{}"  headerKey="" headerValue="请选择">
                        </s:select>
					</td>
				</tr>
		        <tr>
				    <td class="til">
						部署主机<font color="red">*</font>
					</td>
					<td>
							<s:select list="theForm.hostList" name="theForm.HOSTID" headerKey="" headerValue="-请选择-"
							listKey="ID" listValue="HOSTNAME" id="HOSTID"></s:select>
					</td>
				    <td class="til">
						中间件<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'0':'中间件1','1':'中间件2'}" name="theForm.KEYNAME" headerKey="" headerValue="-请选择-" id="KEYNAME"></s:select>
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
	</s:form>
</body>
<script>
	  function app_port(){
	  var form=document.getElementById('theForm');
	  var appport=document.getElementById('APPPORT').value;
	  var html="";
	  var appid=form.APPID.value;
	   if(appid==null||appid==0){
	  	 html="<option selected='selected' value=''>请选择</option>";
	  	 $("#APPPORT").html(html);
	  	return;
	  }
	  $.getJSON("app_port.do?id="+appid,function(data){
			for(var i=0;i<data.length;i++)
			{
				if(data[i].PORT==appport)
				{html=html+"<option selected='selected' value='"+data[i].PORT+"'>"+data[i].PORT+"</option>";}
				else
				{html=html+"<option value='"+data[i].PORT+"'>"+data[i].PORT+"</option>";}		
			}
			$("#APPPORT").html(html);
	   });
	  }
	</script>
</html:html>
