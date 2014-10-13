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
	    if(thisForm.TYPE.value.length ==0){
	     alert("����������Ͳ���Ϊ�գ�");
	     thisForm.TYPE.focus;
	     return false  ;
	    }else{
	       if(thisForm.TYPE.value=='2'){
	         if(thisForm.BASEPATH.value.length==0){
	            alert("��׼��������Ӧ��·������Ϊ��!")
	            return false;
	         }
	       }
	    }    
	    if(thisForm.STRATEGYNAME.value.length ==0){
	     alert("����������Ʋ���Ϊ�գ�");
	     thisForm.STRATEGYNAME.focus;
	     return false  ;
	    }
	    if(thisForm.TYPE.value==2){
	    	if(thisForm.BASEPATH.value.length==0){
	    		 alert("��׼��������Ӧ��·������Ϊ�գ�");
	    	}
	    }   
	    thisForm.submit();
	}
	
	
	/* ���������ַ����Ƿ����Ҫ�� */
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
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
	<html:form action="saveDeployStrategy" method="post" styleId="theForm">
		<bean:define id="theForm" name="DeployStrategyForm" />
		<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
		<html:hidden name="theForm" property="ID" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    
					<td class="til">
						����������� <font color="red">*</font>
					</td>
					<td>
						<html:select  property="TYPE" onchange="type_switching()" >
					      <html:option value="">��ѡ��</html:option>
					      <html:option value="1">¼����</html:option>
					      <html:option value="2">��׼����</html:option>
					    </html:select>
					</td>
					<td class="til">
						<div id="tfire">¼����</div>
					</td>
					<td>
                      <select id="STRATEGY1" name="STRATEGY" >
                              <option selected="selected" value="">��ѡ��</option>
                              <input type="hidden" id="STRATEGYVALUE" value="<bean:write name="theForm" property="STRATEGY"/>"/>
                      </select>
					</td>
				</tr>
				<tr>
				    <td class="til">����������� <font color="red">*</font></td>
					<td><html:text property="STRATEGYNAME" styleClass="txt" /></td>
				    <td class="til">��׼��������Ӧ��·��</td>
				    <logic:equal name="theForm" property="TYPE" value="2">
						 <td><div id="path_jz" style="display:block;"><html:text property="BASEPATH" styleClass="txt" /></div></td>  
				    </logic:equal>
					<logic:notEqual name="theForm" property="TYPE" value="2">
						 <td><div id="path_jz" style="display:none;"><html:text property="BASEPATH" styleClass="txt" /></div></td>  
					</logic:notEqual>
				</tr>
				
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="ȷ��"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="����"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>
                <div>
                 
                </div>
			</table>
	</html:form>
	<script>
	test_type();
	function test_type(){
		var str0value=document.getElementById('STRATEGYVALUE').value;
		if(str0value==null||str0value==0){
		
		}else{
			type_switching();
		}
	}
	
	function type_switching(){
	  var form=document.getElementById('theForm');
	  var strvalue=document.getElementById('STRATEGYVALUE').value;
	  var html="";
	  var id=form.TYPE.value;
	  if(id==null||id==0){
	  	html="<option selected='selected' value=''>��ѡ��</option>";
	  	$("#STRATEGY1").html(html);
	  	return;
	  }
	  
	  if(id=="1"){
	    document.getElementById("tfire").innerText="¼����";
	    document.getElementById("path_jz").style.display="none";
	    $.getJSON("HostAndVideo.do?id="+id,function(data){
			for(var i=0;i<data.length;i++)
			{
				if(data[i].ID==strvalue)
				{html=html+"<option selected='selected' value='"+data[i].ID+"'>"+data[i].VIDEONAME+"</option>";}
				else
				{html=html+"<option value='"+data[i].ID+"'>"+data[i].VIDEONAME+"</option>";}		
			}
			$("#STRATEGY1").html(html);
		   });
	  }else if(id=="2"){
	      document.getElementById("tfire").innerText="��׼�������";
	      document.getElementById("path_jz").style.display="block";
	      $.getJSON("HostAndVideo.do?id="+id,function(data){
			for(var i=0;i<data.length;i++)
			{
				if(data[i].ID==strvalue)
				{html=html+"<option selected='selected' value='"+data[i].ID+"'>"+data[i].HOSTNAME+"</option>";}
				else
				{html=html+"<option value='"+data[i].ID+"'>"+data[i].HOSTNAME+"</option>";}		
			}
			$("#STRATEGY1").html(html);
		   });
	  } else{
	      document.getElementById("path_jz").style.display="none";
	  }
	  
	    
	}
	</script>
</body>

</html:html>
