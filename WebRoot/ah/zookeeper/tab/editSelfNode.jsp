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
	//保存
	function saveRequest(){
		  var newName = $("#objName").val();
		 //var newDataValue = $("#objValue").val();
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
		  var isPare =$("#isParent").val();
		  if(Trim(newName) == ""){
		  	alert("请填写完整");
		  	return false;
		  }
		  var currPath = $("#fullPath").val();//当前目录路径
		  //先判断当前目录是否存在(防止并发操作)
		  var flag = true;
		  bar("editChild","正在保存，请稍候...");
		  $.ajax({
			 type: "post",
			 url: "zookeeperTree_isExistsContent.do?newDirName="+newName+"&currPath="+currPath+"&newDirValue="+newDataValue+"&flag=edit",
			 dataType: "json",
			 async:false,//异步。同步的话，弹出框bar不显示
			 cache:false,
			 success : function(msg){
					var res=msg;
					if(res == -1){
						barEnd("editChild","当前节点目录不存在，请刷新！");
						flag = false;
					}
				}
	 	 	}); 
	 	 //是否进行保存操作
	 	 if(flag){
	 	 	//调用父方法进行保存操作，并刷新当前父节点
	 	 	saveAfterEdit(currPath,newName,newDataValue,isPare);
	 	 }
	}
	//取消
	function cancelRequest(){
		theForm.action="zookeeperTree_cancleToSelfDetail.do";
		theForm.submit();
	}
	//去空
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	//去行
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
		$.dialog.list[idstr].time(4);
	}
	$(function(){
		var isPare =$("#isParent").val();
		if(isPare == "true"){
			$("#objName").attr("readonly",true);
			//$("#objName").attr("disabled",true);设置该属性后，表单提交不了
		}
	});
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function forbidBackSpace(e) {
            var ev = e || window.event; //获取event对象 
            var obj = ev.target || ev.srcElement; //获取事件源 
            var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
            //获取作为判断条件的事件类型 
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况 
            vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
            vDisabled = (vDisabled == undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
            //并且readOnly属性为true或disabled属性为true的，则退格键失效 
            var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
            var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
            //判断 
            if (flag2 || flag1) return false;
        }
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
    function saveAfterEdit(currPath,newName,newDataValue,isPare){
			var treeNode =parent.rightSNode;
			$.ajax({
					 type: "post",
					 url: "zookeeperTree_saveAfterEdit.do?newDirName="+newName+"&currPath="+currPath+"&newDirValue="+newDataValue+"&isPare="+isPare,
					 dataType: "json",
					 async:false,
					 cache:false,
					 success : function(msg){
							barEnd("editChild",msg);
							var nodes = treeNode.getParentNode();//只需要刷新父节点
							parent.zTree.reAsyncChildNodes(nodes, "refresh",true);
							theForm.submit();
						}
				 	});
				/* var url = "zookeeperTree_saveAfterEdit.do?newDirName="+newName+"&currPath="+currPath+"&newDirValue="+newDataValue+"&isPare="+isPare;
				$.getJSON(url,
							{"time":new Date().toString()},
							function(msg){
								barEnd("editChild",msg);//调用iframe子页面方法
								var nodes = treeNode.getParentNode();//只需要刷新父节点
								parent.zTree.reAsyncChildNodes(nodes, "refresh",true);
								theForm.submit();
							}); */
			}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="zookeeperTree_showNodeDetail.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="currentTreeObj.fullPath" id="fullPath"></s:hidden>
	<s:hidden name="currentTreeObj.isParent" id="isParent"></s:hidden>
	<s:hidden name="currentTreeObj.hiddName" id="hiddName"></s:hidden>
	<s:hidden name="currentTreeObj.flagT" id="flagT"></s:hidden>
	<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
			<tr>
				<td width="25%">节点名称:</td>
				<td style="text-align: left;">
				<input readonly="readonly"  type="text" id="objName" style="width: 60%" value="<s:property value="currentTreeObj.name"/>" name="currentTreeObj.name"/><span style="color:red">*</span>
				</td>
			</tr>
				<s:if test='currentTreeObj.flagT == "root"'>
					<tr>
						<td width="25%">数据:</td>
						<td style="text-align: left;"><textarea name="currentTreeObj.dataValue" id="objValue" rows="8" cols="50"><s:if test="currentTreeObj.dataValue != 'null'"><s:property value="currentTreeObj.dataValue"/></s:if></textarea></td>
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
						<s:textfield name="currentTreeObj.ddName" id="ddName" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">启停标识:</td>
					<td style="text-align: left;">
						<s:select cssClass="select-1 vm" list="#{'':'--请选择--','0':'允许启动','1':'禁止启动'}"  name="currentTreeObj.staSto"  id="staSto" cssStyle="width:16%"></s:select><span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td width="25%">进程唯一标识:</td>
					<td style="text-align: left;">
						<s:textfield  name="currentTreeObj.processID" id="processID" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">目录信息:</td>
					<td style="text-align: left;">
						<s:textarea name="currentTreeObj.dirMessage" id="dirMessage"  rows="6" cols="56.3" ></s:textarea>
					</td>
				</tr>
				<tr>
					<td width="25%">接口表信息:</td>
					<td style="text-align: left;">
						<s:textarea name="currentTreeObj.hickyMessage" id="hickyMessage"  rows="6" cols="56.3" ></s:textarea>
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
						<s:textfield name="currentTreeObj.cpuUse" id="cpuUse" cssStyle="width:60%"></s:textfield> %
					</td>
				</tr>
				<tr>
					<td width="25%">内存使用率:</td>
					<td style="text-align: left;">
						<s:textfield  name="currentTreeObj.menUse" id="menUse" cssStyle="width:60%"></s:textfield> %
					</td>
				</tr>
				<tr>
					<td width="25%">IP 地址:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.ip" id="ip" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">端口号:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.port" id="port" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">PID:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.pid" id="pid" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">进程名称:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.processName" id="processName" cssStyle="width:60%"></s:textfield>
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
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:saveRequest();" value="确定" /></span>
					</td>
				</tr>
		</table>
		
		
		
</s:form>
</div>
</body>
</html:html>
