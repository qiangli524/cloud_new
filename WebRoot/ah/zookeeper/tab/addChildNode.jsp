<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<title></title>
<script>
	//添加
	function addRequest(){
		  var newName = $("#objName").val();
		  if(Trim(newName) == ""){
		  	alert("请填写节点名称！");
		  	return false;
		  }
		  var currPath = $("#fullPath").val();//当前目录路径
		  var newDataValue;
		  var flagt = $("#flagT").val();
		  if(flagt == "root"){
		  newDataValue = $("#objValue").val();
		  }else if(flagt == "s"){
		  //添加静态树属性
		  	var dName = $("#ddName").val();
		  	var staSto = $("#staSto").val();
		  	if(staSto == ""){
		  		alert("请选择启停标识！");
		  		return false;
		  	}
		  	var processID = $("#processID").val(); 
		  	var dirMessage = $("#dirMessage").val();
		  	var hickyMessage = $("#hickyMessage").val();
		  	newDataValue = dName+";"+staSto+";"+processID+";"+cleanBr(dirMessage)+";"+cleanBr(hickyMessage)+";";
		  }else if(flagt == "d"){
		  //添加动态树属性
		  	var cpuUse = $("#cpuUse").val();
		  	var menUse = $("#menUse").val();
		  	var ip = $("#ip").val();
		  	var port = $("#port").val();
		  	var pid = $("#pid").val();
		  	var processName = $("#processName").val();
		  	newDataValue = cpuUse+";"+menUse+";"+ip+";"+port+";"+pid+";"+processName+";";
		  }else if(flagt == "c"){
		  	var ipAndYm = $("#ipAndYm").val();
		  	var areaName =$("#areaName").val();
		  	newDataValue = ipAndYm+";"+areaName+";";
		  }else{
		  	var mlbID = $("#mlbID").val();
		  	var serverPortArr = $("#serverPortArr").val();
		  	newDataValue = mlbID+";"+serverPortArr+";";
		  }
		  var aliasName = $("#objAliasName").val();
		  var newPath;
		  if(currPath == "/"){
		  	 newPath = currPath+newName;
		  }else{
		  	 newPath = currPath+"/"+newName;//需要添加的目录
		  }
		  //先判断当前目录是否存在(防止并发操作)，以及目录下新添节点名是否已存在，
		  var flag = true;
		  bar("addChild","正在添加节点，请稍候...");
		  var url = "zookeeperTree_isExistsContent.do?newPath="+newPath+"&currPath="+currPath+"&flag=add";
		  $.getJSON(url,
					{"time":new Date().toString()},
					function(msg){
						var res=msg;
						if(res == 1){
							flag = false;
							barEnd("addChild","当前节点目录不存在，请刷新！");
						}else if(res == 2){
							flag = false;
							barEnd("addChild","当前目录下已存在相同目录!");
						}else{
							parent.addChildDoAndLoadtree(currPath,newName,newDataValue,aliasName);
							theForm.submit();
						}
					}); 
	}
	//取消
	function cancelRequest(){
		theForm.action = "zookeeperTree_showNodeDetail.do";
		theForm.submit();
	}
	//去空
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	//去除换行
	function cleanBr(str){
		return str.replace(/[\r\n]/g," ");
	}
	function bar(idstr,contents){
		$.dialog({
				id:idstr,
			    title: '提示',
			    width: 200,
			    height: 100,
			    left: '100%',
			    top: '100%',
			    fixed: true,
			    content:contents
			});
	}
	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(7);
	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="zookeeperTree_showNodeDetail.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="currentTreeObj.name" id="currname"></s:hidden>
	<s:hidden name="currentTreeObj.fullPath" id="fullPath"></s:hidden>
	<s:hidden name="currentTreeObj.dataValue" id="dataValue"></s:hidden>
	<s:hidden name="currentTreeObj.flagT" id="flagT"></s:hidden>
	<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
			<tr>
				<td width="25%">节点名称:</td>
				<td style="text-align: left;">
					<input type="text" id="objName" style="width:60%" /><span style="color:red">*</span>
				</td>
			</tr>
				<s:if test='currentTreeObj.flagT == "root"'>
					<tr>
						<td width="25%">数据:</td>
						<td style="text-align: left;"><textarea  id="objValue" rows="8" cols="50"></textarea></td>
					</tr>
				</s:if>
				<!-- 静态树上数据属性显示 -->
				<s:elseif test='currentTreeObj.flagT == "s"'>
				<tr>
					<td width="25%" style="border-right:  1px  #ccc;padding-left: 2px;">
						数据属性值：
					</td>
					<td></td>
				</tr>
				<tr >
					<td width="25%">动态节点名称:</td>
					<td style="text-align: left;">
						<s:textfield id="ddName" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">启停标识:</td>
					<td style="text-align: left;">
						<s:select cssClass="select-1 vm" list="#{'':'--请选择--','0':'允许启动','1':'禁止启动'}"  id="staSto" cssStyle="width:16%"></s:select><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td width="25%">进程唯一标识:</td>
					<td style="text-align: left;">
						<s:textfield  id="processID" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">目录信息:</td>
					<td style="text-align: left;">
						<s:textarea id="dirMessage"  rows="6" cols="56.3" ></s:textarea>
					</td>
				</tr>
				<tr>
					<td width="25%">接口表信息:</td>
					<td style="text-align: left;">
						<s:textarea  id="hickyMessage"  rows="6" cols="56.3" ></s:textarea>
					</td>
				</tr>
				</s:elseif>
				<!-- 动态树数据属性显示 -->
				<s:elseif test='currentTreeObj.flagT == "d"'>
				<tr>
					<td width="25%" style="border-right:  1px  #ccc;">
						数据属性值：
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="25%">CPU使用率:</td>
					<td style="text-align: left;">
						<s:textfield id="cpuUse"></s:textfield> %
					</td>
				</tr>
				<tr>
					<td width="25%">内存使用率:</td>
					<td style="text-align: left;">
						<s:textfield  id="menUse"></s:textfield> %
					</td>
				</tr>
				<tr>
					<td width="25%">IP 地址:</td>
					<td style="text-align: left;">
						<s:textfield id="ip"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">端口号:</td>
					<td style="text-align: left;">
						<s:textfield id="port"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">PID:</td>
					<td style="text-align: left;">
						<s:textfield id="pid"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">进程名称:</td>
					<td style="text-align: left;">
						<s:textfield id="processName"></s:textfield>
					</td>
				</tr>
				</s:elseif>
				<!-- 非叶子节点集群属性展示 -->
				<s:elseif test='currentTreeObj.flagT == "c"'>
					<tr>
						<td width="25%" style="border-right:  1px  #ccc;">
							数据属性值：
						</td>
						<td></td>
					</tr>
					<tr>
						<td width="25%">IP和掩码:</td>
						<td style="text-align: left;">
							<s:textfield  name="currentTreeObj.ipAndYm" id="ipAndYm" cssStyle="width:60%"></s:textfield> 
						</td>
					</tr>
					<tr>
						<td width="25%">域名:</td>
						<td style="text-align: left;">
							<s:textfield name="currentTreeObj.areaName" id="areaName" cssStyle="width:60%"></s:textfield>
						</td>
					</tr>
				</s:elseif>
				<!-- 非叶子节点池子属性展示 -->
				<s:elseif test='currentTreeObj.flagT == "p"'>
					<tr>
						<td width="25%" style="border-right:  1px  #ccc;">
							数据属性值：
						</td>
						<td></td>
					</tr>
					<tr>
						<td width="25%">MLB默认返回标识:</td>
						<td style="text-align: left;">
							<s:textfield  name="currentTreeObj.mlbID"  id="mlbID" cssStyle="width:60%"></s:textfield> 
						</td>
					</tr>
					<tr>
						<td width="25%">服务端口号列表:</td>
						<td style="text-align: left;">
							<s:textfield name="currentTreeObj.serverPortArr" id="serverPortArr" cssStyle="width:60%"></s:textfield>
						</td>
					</tr>
				</s:elseif>
				<tr>
					<td colspan="2">
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:addRequest();" value="添加" /></span>
					</td>
				</tr>
		</table>
</s:form>
</div>
</body>
</html:html>
