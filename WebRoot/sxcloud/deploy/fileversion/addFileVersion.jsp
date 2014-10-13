<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%> 
<%@ include file="../../../sxcloud/common/view.jsp"%>
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
		callback:commitScript,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	}); 

	function commitScript() {
		if (theForm.NAME.value.length == 0) {
	        alert("请选择版本包！");
			return false;
	    } 
	    if (theForm.APPNAME.value.length == 0) {
	    	alert("版本所属的应用不存在，请确认！");
	    	return false;
	    }
	    if (theForm.DESCRIBTION.value.length == 0) {
	    	alert("请输入版本描述！");
	    	return false;
	    }
	    $("#NO").attr("disabled",false);
    	$("#LOCATION").attr("disabled",false);
    	$("#DESCRIBTION").attr("disabled",false); 
    	
    	$.ajax({
			type:"GET",
			url:"fileversion_saveFileVersion.do?" + $("#theForm").serialize(),
			dataType:"json",
			success:function(data){
				if (data.result == 1)
				{
					alert("更新版本信息出错！"); 
					return false;
				} else if (data.result == 2) {
					alert("版本主机IP串配置信息有误，请确认！"); 
					return false;
				} else if (data.result == 3) {
					alert("新增插入版本信息表出错！"); 
					return false;
				} else if (data.result == 4) {
					alert("软件版本包不存在！"); 
					return false;
				} else {
					alert("保存成功！");
		            w.searchRequest();
				}
			}  
		});
		return false;
	}

    $(function(){ 
	   $("[name='selectSoft']").unbind().live("click",function(){
	   		var sysid = theForm.SYSID.value;
    		w.$.dialog({
    			id:'selectSoft',
    			title:'选择升级包',
    			width: '800px',
    			height: '300px',
    			max: true,
    		    min: true,
    			content: 'url:software_listSoftwareInfoForSel.do?bizid='+sysid
    			});
              });
		});
	 
	function getSoftware(versionname,versionno,appid,softparth,softId){
	  theForm.NAME.value=versionname;
	  theForm.APPID.value=appid;
	  theForm.SOFRTPARTH.value=softparth;
	  theForm.SOFTID.value=softId;
	  theForm.action = 'fileversion_getAppInfo.do';
      theForm.submit();
    }
</script> 
</head>
<body 
<s:if test="theForm.flag==0">
onLoad="self.focus();document.theForm.selectSoft.focus()"
</s:if>
<s:else>
onLoad="self.focus();document.theForm.DESCRIBTION.focus()"
</s:else>
>
<s:form action="fileversion_saveFileVersion.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden> 
	<s:hidden name="theForm.SOFRTPARTH" id="SOFRTPARTH"></s:hidden> 
	<s:hidden name="theForm.SYSID" id="SYSID"></s:hidden>
	<s:hidden name="theForm.SOFTID" id="SOFTID"></s:hidden>
		<!-- 
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	    -->
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			 <thead>
                   <tr>
                    <td class="til">选择版本包<font color="red">*</font></td>
                  	 <td>
						<s:if test="theForm.flag==0">
						    <s:hidden name="theForm.APPID" id="APPID"></s:hidden>
							<s:textfield name="theForm.NAME" id="NAME" cssClass="txt" readonly="true"></s:textfield>
						    <input type="button"  value="选择"  class="btn-style02" name="selectSoft"/>
						    <!-- 
						    <ul class="btns">
			                    <li colspan="1" class="btnCenter">
			                           <input type="button"  value="选择"  class="btn-style02" name="add"/>
			                    </li>
			                </ul>
			                 -->
						 </s:if>
						<s:else>
							<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
							<s:textfield name="theForm.NAME" id="NAME" cssClass="txt" readonly="true" disabled="true"></s:textfield>
						</s:else>
                    </td>
                    </tr>
                    <tr>
                    <td class="til">所属应用</td>
                    <td>
						<s:textfield name="theForm.APPNAME" cssClass="txt" id="APPNAME" readonly="true" disabled="true"></s:textfield>
                    </td>
                    </tr> 
                    <tr>
                    <td class="til">版本号</td>
                   <td>
						<s:textfield name="theForm.NO" cssClass="txt" id="NO" readonly="true" disabled="true"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">版本包路径</td>
                   <td colspan="3"><s:textarea name="theForm.LOCATION" cols="77" 
						rows="2" cssStyle="txt" id="LOCATION" readonly="true" disabled="true"></s:textarea></td>
                    </tr>
                    <tr>
                    <td class="til">版本描述<font color="red">*</font></td>
					<td colspan="3"><s:textarea name="theForm.DESCRIBTION" cols="77" 
						rows="3" cssStyle="txt" id="DESCRIBTION"></s:textarea></td>
                    </tr>
                   <tr>
                   <!-- 
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
				 			onclick="window.history.back()" />
							
					</td>
					 -->
				</tr>
               </thead>
               </table>
</s:form>
</body>
