<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<style type="text/css">
.blue-table .selfStyle{background-color:#EEAD0E;}
.btn-styleSelf{width:70px; height:19px; line-height:19px; border:none; cursor:pointer}
.inputBorderRed{border:1px solid red}
</style>

<script type="text/javascript">
	$(function(){
		 $("#deleteOption").unbind().live("click",function(){
	     	$(this).parent().parent().remove();
		 });
	})
	
	function selectScriptFun(param){
		if(param==2){
			$("#pathTr").hide();
			$("#scriptTypeTr").hide();
			$("#dataTable tr #relateScript").show();
			$("#dataTable tr #relateScriptType").show();
		}else if(param==1){
			$("#pathTr").show();
			$("#scriptTypeTr").show();
			$("#dataTable tr #relateScript").hide();
			$("#dataTable tr #relateScriptType").hide();
		}
		//changeThLength();
	}
	function selectUseeFun(param){
		if(param==2){
			$("#userTr").hide();
			$("#passwordTr").hide();
			$("#dataTable tr #relateUser").show();
			$("#dataTable tr #relatePass").show();
		}else if(param==1){
			$("#userTr").show();
			$("#passwordTr").show();
			$("#dataTable tr #relateUser").hide();
			$("#dataTable tr #relatePass").hide();
		}
	}
	
	function addNewOption(){
		var scriptOp = $("[name='startAndStopObj.selectScript']:checked").val();
		var useeOp = $("[name='startAndStopObj.selectUsee']:checked").val();
		var $_tr = $("<tr></tr>");
		var $_deployIp = $("<td><input type='text' name='startAndStopObj.aloneDeployeIp'/></td>");
		var $_useId = null;
		var $_password = null;
		if(useeOp==1){
			$_useId = $("<td id='relateUser' style='display: none'><input type='text' name='startAndStopObj.aloneUserId'/></td>");
			$_password = $("<td id='relatePass' style='display: none'><input type='password' name='startAndStopObj.alonePassword'/></td>");
		}else{
			$_useId = $("<td id='relateUser'><input type='text' name='startAndStopObj.aloneUserId'/></td>");
			$_password = $("<td id='relatePass'><input type='password' name='startAndStopObj.alonePassword'/></td>");
		}
		var $_scriptPath = null;
		var $_scriptType = null;
		if(scriptOp==1){
			var select = createSelect();
			$_scriptType = $("<td id='relateScriptType' style='display: none'></td>").append($(select));
			$_scriptPath = $("<td id='relateScript' style='display: none'><input type='text' name='startAndStopObj.aloneScriptPath'/></td>");
		}else{
			var select = createSelect();
			$_scriptType = $("<td id='relateScriptType'></td>").append($(select));
			$_scriptPath = $("<td id='relateScript'><input type='text' name='startAndStopObj.aloneScriptPath'/></td>");
		}
		$("#dataTable").append($_tr.append($_deployIp).append($_useId).append($_password).append($_scriptType).append($_scriptPath).append($("<td><a id='deleteOption'>删除</a></td>")));
	}
	function createSelect(){
		var select=document.createElement("select");
		select.setAttribute("style","width:60px");
		select.setAttribute("name","aloneScriptType");
		var opt1=document.createElement("option");
		opt1.setAttribute("value","1");
		opt1.appendChild(document.createTextNode("启动"));
		var opt2=document.createElement("option");
		opt2.setAttribute("value","2");
		opt2.appendChild(document.createTextNode("停止"));
		select.appendChild(opt1);
		select.appendChild(opt2);
		return select;
	}
	
	function submitRequest(theForm){
		var scriptOp = $("[name='startAndStopObj.selectScript']:checked").val();
		var useeOp = $("[name='startAndStopObj.selectUsee']:checked").val();
		var breakFun = false;
		$("[name='startAndStopObj.aloneDeployeIp']").each(function(index,obj){
			if($(obj).val().length==0){
				$(obj).addClass("inputBorderRed");
				alert("部署机IP不能为空！");
				breakFun = true;
				return false;
			}else{
				$(obj).removeClass("inputBorderRed");
			}
		});
		if(breakFun){
			return false;
		}
		if(useeOp==1){
			var $_commonUseId = $("[name='startAndStopObj.commonUseId']");
			if($_commonUseId.val().length==0){
				$_commonUseId.addClass("inputBorderRed");
				alert("用户名不能为空！");
				return false;
			}else{
				$_commonUseId.removeClass("inputBorderRed");
			}
			var $_commonPassword = $("[name='startAndStopObj.commonPassword']");
			if($_commonPassword.val().length==0){
				$_commonPassword.addClass("inputBorderRed");
				alert("密码不能为空！");
				return false;
			}else{
				$_commonPassword.removeClass("inputBorderRed");
			}
		}else{
			$("[name='startAndStopObj.aloneUserId']").each(function(index,obj){
				if($(obj).val().length==0){
					$(obj).addClass("inputBorderRed");
					alert("用户名不能为空！");
					breakFun = true;
					return false;
				}else{
					$(obj).removeClass("inputBorderRed");
				}
			});
			if(breakFun){
				return false;
			}
			$("[name='startAndStopObj.alonePassword']").each(function(index,obj){
				if($(obj).val().length==0){
					$(obj).addClass("inputBorderRed");
					alert("密码不能为空！");
					breakFun = true;
					return false;
				}else{
					$(obj).removeClass("inputBorderRed");
				}
			});
			if(breakFun){
				return false;
			}
		}
		if(scriptOp==1){
			var $_commonScriptPath = $("[name='startAndStopObj.commonScriptPath']");
			if($_commonScriptPath.val().length==0){
				$_commonScriptPath.addClass("inputBorderRed");
				alert("脚本路径不能为空！");
				return false;
			}else{
				$_commonScriptPath.removeClass("inputBorderRed");
			}
		}else{
			$("[name='startAndStopObj.aloneScriptPath']").each(function(index,obj){
				if($(obj).val().length==0){
					$(obj).addClass("inputBorderRed");
					alert("脚本路径不能为空！");
					breakFun=true;
					return false;
				}else{
					$(obj).removeClass("inputBorderRed");
				}
			});
			if(breakFun){
				return false;
			}
		}
		$.getJSON('dep_startAndStopApp.do?'+$("form").serialize(),{'time':new Date().toString()},function(data){
			alert(data.result);
			theForm.action = "dep_akeyStartAndStop.do";
			theForm.submit();
		});
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="dep_startAndStopApp.do" method="post" cssStyle="theForm" id="theForm" >
		
		<div id="secondPage">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				<tr>
					<th colspan="2" class="selfStyle">
						基本设置
					</th>
				</tr>
			  	<tr align="left">
			  		<td>
			  			用户名、密码选择
			  		</td>
			  		<td>
						<input name="startAndStopObj.selectUsee" type="radio" id="selectUsee1" checked="checked" value="1" onclick="selectUseeFun(1);"/><label for="storageId1">使用通用用户</label>
						<input name="startAndStopObj.selectUsee" type="radio" id="selectUsee2" value="2" onclick="selectUseeFun(2);" style="margin-left: 30px"/><label for="storageId2">使用单独用户</label>
			  		</td>
			  	</tr>
			  	<tr align="left" id="userTr">
			  		<td >
			  			用户名：
			  		</td>
			  		<td>
			  			<input type="text" name="startAndStopObj.commonUseId"/>
			  		</td>
			  	</tr>
			  	<tr align="left" id="passwordTr">
			  		<td>
			  			密码：
			  		</td>
			  		<td>
			  			<input type="password" name="startAndStopObj.commonPassword"/>
			  		</td>
			  	</tr>
			  	
			  	<tr align="left">
			  		<td>
			  			脚本选择
			  		</td>
			  		<td>
						<input name="startAndStopObj.selectScript" type="radio" id="selectScript1" checked="checked" value="1" onclick="selectScriptFun(1);"/><label for="storageId1">使用通用脚本</label>
						<input name="startAndStopObj.selectScript" type="radio" id="selectScript2" value="2" onclick="selectScriptFun(2);" style="margin-left: 40px"/><label for="storageId2">使用单独脚本</label>			  		
			  		</td>
			  	</tr>
				<tr align="left" id="scriptTypeTr">
					<td class="til">类型</td>
					<td>
						<s:select list="#{'1':'启动','2':'停止'}" name="startAndStopObj.commonScriptType" id="type" style="width:150px" ></s:select>
					</td>
				</tr>
				<tr align="left" id="pathTr">
					<td class="til">路径</td>
					<td>
					<s:textfield name="startAndStopObj.commonScriptPath" id="path" style="width:330px;   height:20px;"></s:textfield>
					</td>
				</tr>  
			</table>
		</div>
		
		<div id="firstPage">
			<table id="dataTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				<th colspan="6" class="selfStyle">
					部署实例设置、选择
				</th>
				</tr>
			  <tr>
                   <th>部署机IP</th>
                   <th id="relateUser" style="display: none">启停用户</th>
                   <th id="relatePass" style="display: none">启停密码</th>
                   <th id="relateScriptType" style="display: none">脚本类型</th>
                   <th id="relateScript" style="display: none">脚本路径</th>
                   <th>
				   		<a onclick="addNewOption();">新添加一项</a>
				   </th>
                   <!-- 
                   <th>服务状态</th>
                    -->
			  </tr>
			  </thead>
			  <tbody>
						<tr>
							<td>
								<s:textfield name="startAndStopObj.aloneDeployeIp"></s:textfield>
							</td>
							<td id="relateUser" style="display: none">
								<s:textfield  name="startAndStopObj.aloneUserId"></s:textfield>
							</td>
							<td id="relatePass" style="display: none">
								<s:password name="startAndStopObj.alonePassword"></s:password>
							</td>
							<td id="relateScriptType" style="display: none">
								<s:select list="#{'1':'启动','2':'停止'}" name="startAndStopObj.aloneScriptType" id="type" style="width:60px" ></s:select>
							</td>
							<td id="relateScript" style="display: none">
								<s:textfield name="startAndStopObj.aloneScriptPath"></s:textfield>
							</td>
							<td>
								<a id="deleteOption">删除</a>
							</td>
						</tr>
			  </tbody>
			  <tr>
				<td colspan="6" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="启停"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
				</td>
			</tr>
			</table>
		</div>
	</s:form>
</body>

</html:html>
