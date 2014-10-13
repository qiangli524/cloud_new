<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.min.js"></script>

<script type="text/javascript">
    var api = frameElement.api;
    var w = api.opener;
    var status = '';
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
	  
	$(function(){ 
		$("#upload").click(function(){
			$("#name").attr("value","");
			$("#basis").uploadify('upload','*');
		});
		$("#basis").uploadify({
			height:20,
			width:100,
            buttonImage   :'<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadbutton.png', 
            fileTypeExts  :'*.zip;*.rar;*.txt;*.xls;*.xlsx;*.doc;*.docx',
            displayData   : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
			swf           : '<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify.swf',
			cancelImg     : '<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify/cancel.png',
			uploader      : 'departproject_saveBasis.do',
            fileObjName   :'obj.basisObj',
            auto          :false, 
            method        :'post',
            /* hideButton    :true, */
           /*  cancel : 'uploadify-cancel.png', */
            fileSizeLimit :'10240',
            multi : false,
            queueSizeLimit : 1,
            /* removeCompleted:false, *///设置上传完成后的操作 默认true:自动删除不显示在页面上；false:继续显示在上面但实际上已不存在
            onQueueFull : function() {
            	alert("一次只能上传单个文件");
            },
            onUploadStart : function(file){
            	status = '0';
            	$("#basis").uploadify("settings", "formData", {'obj.basis':file.name});
            },
            onUploadComplete : function(file) {
            	status = '1';
				$("#name").attr("value",file.name);
            }
		});
	   $("[name='selectStaff']").unbind().live("click",function(){
	   		var id = $("#ID").val();
	   		var flag = $("#flag").val();
    		w.$.dialog({
    			id:'selectStaff',
    			title:'选择项目负责人',
    			width: '800px',
    			height: '550px',
    			max: true,
    		    min: true,
	   		    lock:true,
    			content: 'url:departproject_queryUserInfoList.do?obj.ID='+id+'&flag='+flag
    			});
              });
              
        $("[name='selectDomain']").unbind().live("click",function(){
       		var id = $("#ID").val();
    		w.$.dialog({
    			id:'selectStaff',
    			title:'选择网络域',
    			width: '800px',
    			height: '550px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:departproject_queryNetDomainList.do?obj.ID='+id
    			});
              });
              
		});
	 
	function commitScript(){ 
	    if (obj.PROJECT_NO.value.length == 0) {
	        alert("请输入项目编号！");
			return false;
	    } 
	    if (obj.PROJECT_NAME.value.length == 0) {
	    	alert("请输入项目名称！");
	    	return false;
	    }
	    if($("#depart_id").attr("value")== -1){
	    	alert("请选择所属部门");
	    	return false;
	    }
	    if (obj.CPU_COUNT.value.length == 0) {
	        alert("请输入CPU预分配个数！");
			return false;
	    }  
	    if (checknumber(obj.CPU_COUNT.value) == false) {
	        alert("CPU预分配个数需是数字！");
			return false;
	    }
	    if (obj.MEMORY_SIZE.value.length == 0) {
	    	alert("请输入内存预分配大小！");
	    	return false;
	    }
	    if (checknumber(obj.MEMORY_SIZE.value) == false) {
	        alert("内存预分配大小需是数字！");
			return false;
	    }
	    if (obj.STORAGE_SIZE.value.length == 0) {
	        alert("请输入存储预分配大小！");
			return false;
	    } 
	    if (checknumber(obj.STORAGE_SIZE.value) == false) {
	        alert("存储预分配大小需是数字！");
			return false;
	    }
	    if (obj.IP_COUNT.value.length == 0) {
	    	alert("请输入IP预分配个数！");
	    	return false;
	    }
	    if (checknumber(obj.IP_COUNT.value) == false) {
	        alert("IP预分配个数需是数字！");
			return false;
	    }
	    if (obj.PROJECT_LEADER.value.length == 0) {
	    	alert("请选择项目负责人！");
	    	return false;
	    }
	    if (obj.NETWORK_DOMAIN.value.length == 0) {
	    	alert("请选择网络域！");
	    	return false;
	    }
	    if(status == '0'){
	    	alert("请等待上传完成");
	    	return false;
	    }
		$("#CONTACT_PHONE").attr("disabled",false);
		$("#EMAIL").attr("disabled",false);
		var str = $("#obj").serialize();
		w.showproject(str);
		
	}
	
	function getNetDomain(netId,domainName) {
		obj.NETWORK_DOMAIN.value = domainName;
		obj.netid.value = netId;
	}
	
	function getUserInfo(username,phone,email,account) {
		var name = username.split("_");
		var count = account.split("_");
		var phoneNum = phone.split("_");
		var mail = email.split("_");
		var names = '';
		var accounts = '';
		var phones = '';
		var emails = '';
		for(var i=0;i<name.length;i++){
			if(name[i]!=null&&name[i]!=""){
				names = names + name[i] + " ";
				accounts = accounts + count[i] + " ";
				phones = phones + phoneNum[i] + " ";
				emails = emails + mail[i] + " ";
			}
		}
		obj.PROJECT_LEADER.value = names; 
		obj.CONTACT_PHONE.value = phones;
		obj.EMAIL.value = emails; 
		obj.account.value = accounts;  
	}
	
	function checknumber(str){
		var pattern1=/^([0-9])([0-9])*$/;
		if(!pattern1.test(str)){ 
			return false;
		}
		return true;
	}
</script> 
</head>
<body onLoad="self.focus();document.obj.PROJECT_NO.focus()" class="scrollbody">
<s:form action="" id="obj" method="post"
	cssClass="obj">
	<s:hidden name="flag" id="flag"></s:hidden>
	<s:hidden name="obj.ID" id="ID"></s:hidden> 
	<s:hidden name="obj.netid" id="netid"></s:hidden> 
	<s:hidden name="obj.account" id="account"></s:hidden>  
	<s:hidden name="obj.basis" id="name"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			 <thead>
                   <tr>
                    <td class="til">项目编号<font color="red">*</font></td>
                    <td>
						<s:textfield name="obj.PROJECT_NO" cssClass="txt" id="PROJECT_NO"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                    <td class="til">项目名称<font color="red">*</font></td>
                    <td>
						<s:textfield name="obj.PROJECT_NAME" cssClass="txt" id="PROJECT_NAME"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                    <td class="til">所属科室<font color="red">*</font></td>
                   <td>
						<%-- <s:select list="resultList" listKey="DEPART_ID" listValue="DEPART_NAME" headerKey="-1" headerValue="--请选择--" name="obj.DEPART_ID"></s:select> --%>
                    	<s:select name="obj.DEPART_ID" list="departList" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--" id="depart_id"></s:select>
                    </td>
                    </tr>
                    <tr>
                    <td class="til">CPU预分配核数<font color="red">*</font></td>
                   <td>
						<s:textfield name="obj.CPU_COUNT" cssClass="txt" id="CPU_COUNT"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                    <td class="til">内存预分配大小<font color="red">*</font></td>
                   <td>
						<s:textfield name="obj.MEMORY_SIZE" cssClass="txt" id="MEMORY_SIZE"></s:textfield>G
                    </td>
                    </tr>
                    <tr>
                    <td class="til">存储预分配大小<font color="red">*</font></td>
                   <td>
						<s:textfield name="obj.STORAGE_SIZE" cssClass="txt" id="STORAGE_SIZE"></s:textfield>G
                    </td>
                    </tr>
                    <tr>
                    <td class="til">IP预分配个数<font color="red">*</font></td>
                   <td>
						<s:textfield name="obj.IP_COUNT" cssClass="txt" id="IP_COUNT"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">项目负责人<font color="red">*</font></td>
                   <td>
						<s:textfield name="obj.PROJECT_LEADERNAME" cssClass="txt" id="PROJECT_LEADER" readonly="true"></s:textfield>
						<%-- <input type="button"  value="选择"  class="btn-style02" name="selectStaff"/> --%>
						<span class="ubtn-1 mgl-10"><input type="button"  value="选择" name="selectStaff"/></span>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">联系电话</td>
                   <td>
						<s:textfield name="obj.CONTACT_PHONE" cssClass="txt" id="CONTACT_PHONE" readonly="true" disabled="true"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">邮箱</td>
                   <td>
						<s:textfield name="obj.EMAIL" cssClass="txt" id="EMAIL" readonly="true" disabled="true"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">网络域<font color="red">*</font></td>
                   <td>
						<s:textfield name="obj.NETWORK_DOMAIN" cssClass="txt" id="NETWORK_DOMAIN"  readonly="true"></s:textfield>
                    	<%-- <input type="button"  value="选择"  class="btn-style02" name="selectDomain"/> --%>
                    	<span class="ubtn-1 mgl-10"><input type="button"  value="选择" name="selectDomain"/></span>
                    </td>
                    </tr>
                    <tr>
                       <td class="til">附件</td>
	                   <td >
	                   		<input type="file" name="obj.basisObj" id="basis"/>
	                   		<%-- <input type="button" value="上传" id="upload" class="btn-style02"/> --%>
	                   		<span class="ubtn-1 mgl-10"><input type="button"  value="上传" id="upload"/></span>
	                   		<span>注:文件大小上限为10M</span>
						    <%-- <s:file name="obj.BASIS" id="basis"></s:file> --%>
						</td>
						
                    </tr>
               </thead>
             </table>
</s:form>
</body>
