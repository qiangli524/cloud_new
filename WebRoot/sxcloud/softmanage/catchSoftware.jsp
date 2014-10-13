<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
		rel="stylesheet" type="text/css" />
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
		rel="stylesheet" type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	    var api = frameElement.api;
			w = api.opener;
	    $(function(){
			 api.button({
			     id:'OkAnd',
			     name: '捕获',
			     callback:catchsoftware,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
			 
			$("#appid").change(function(){
				var appid =$(this).find('option:selected').val();
				//var appname =$(this).find('option:selected').text();
				$.ajax({
					type:"post",
					dataType:"text",
					url:"software_attchbasepath.do?appid="+appid,//+"&appname="+appname,
					success:function(msg){
						$("#basepath").val(msg);
				        $("#basepath").attr("readonly",true);
					}		
				});
			});
			
		});
		
			
		function catchsoftware(){
			var appid = $("#appid").find('option:selected').val();
	    	if(appid==0){
	    		alert("所属应用不能为空！");
			    return false  ;
	    	}
	    	var tarName = $("#tarname").val();
	    	if (tarName == null || tarName == "") {
				alert("保存名称不能为空!");
				return false;
			}
			var softpath = $("#softpath").val();
			if(softpath ==null || softpath==""){
				alert("请填写文件清单!");
				return false;
			}
			w.catchSysSoftware(softpath,appid,tarName);
	    }
		
		$("#softpath").live("click",function(){
			var appid = $("#appid").find('option:selected').val();
			if(appid==0){
	    		alert("所属应用不能为空！");
			    return false  ;
	    	}
			w.$.dialog({
        		id:'baseAppFileTree',
        		title:'基准应用文件树',
        		width: '900px',
    			height: '400px',
    		    lock:true,
    		    content:'url:baseappfile_baseAppFileTree.do?baseappid='+appid
        	});
			
			
		})
		function listFilePath(file_url){
			
			$("#softpath").val(file_url);
			
			
		}
		
		
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					基准应用名称
					<font color="red">*</font>
				</td>
				<td>
					<s:select list="sysappList" listKey="ID" listValue="APPNAME"
						id="appid" name="tbBusiSoftwareInfoObj.APPID" headerKey="0"
						headerValue="-请选择-"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					生成的tar包名称
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="tbBusiSoftwareInfoObj.TARNAME" id="tarname"></s:textfield>
					<font color="red">不需要后缀名</font>
				</td>
			</tr>
			<tr>
				<td class="til">
					基准应用路径
				</td>
				<td>
					<s:textarea name="BASEPATH" id="basepath" cols="50" rows="5"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">
					文件清单
					<font color="red">*</font>
				</td>
				<td>
					<s:textarea cols="50" rows="5"
						name="tbBusiSoftwareInfoObj.SOFTPARTH" id="softpath"></s:textarea>
						
				</td>
			</tr>
			<tr>
			     <td class="til">			
					<font color="red">注意:</font>
				</td>
				<td>
					<font color="red">如基准路径/application/web，捕获文件或目录应该是(web/index.jsp,web/js)</font>
				</td>
			</tr>
		</table>
	</s:form>
</body>