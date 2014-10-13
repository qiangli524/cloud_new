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
</script>
</head>
<body >
<div >
<s:form action="" method="post"  id="theForm">
<s:hidden name="theForm.id" id="ID"></s:hidden>
	<div style="margin-top: 10px;">
		<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
			<tr>
				<td width="25%">节点名称:</td>
				<td style="text-align: left;">
					<s:textfield id="objName" readonly="true" name="currentTreeObj.name" cssStyle="width:60%"/>
				</td>
			</tr>
				<s:if test='currentTreeObj.flagT == "root"'>
					<tr>
						<td width="25%">数据:</td>
						<td style="text-align: left;"><textarea name="currentTreeObj.dataValue" id="objValue" rows="8" cols="50"><s:if test="currentTreeObj.dataValue != 'null'"><s:property value="currentTreeObj.dataValue"/></s:if></textarea></td>
					</tr>
				</s:if>
				<!-- 静态树上叶子节点数据属性显示 -->
				<s:elseif test='currentTreeObj.flagT == "s"'>
				<tr>
					<td width="25%" style="border-right:  1px  #ccc;">
						数据属性值：
					</td>
					<td></td>
				</tr>
				<tr >
					<td width="25%">动态节点名称:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.ddName" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">启停标识:</td>
					<td style="text-align: left;">
						<s:select cssClass="select-1 vm" list="#{'':'--请选择--','0':'允许启动','1':'禁止启动'}"  name="currentTreeObj.staSto" cssStyle="width:16%"></s:select>
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
						<s:textarea   name="currentTreeObj.dirMessage" rows="6" cols="56.3" ></s:textarea>
					</td>
				</tr>
				<tr>
					<td width="25%">接口表信息:</td>
					<td style="text-align: left;">
						<s:textarea   name="currentTreeObj.hickyMessage" rows="6" cols="56.3"></s:textarea>
					</td>
				</tr>
				</s:elseif>
				<!-- 动态树叶子节点数据属性显示 -->
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
						<s:textfield name="currentTreeObj.cpuUse" cssStyle="width:60%"></s:textfield> %
					</td>
				</tr>
				<tr>
					<td width="25%">内存使用率:</td>
					<td style="text-align: left;">
						<s:textfield  name="currentTreeObj.menUse" cssStyle="width:60%"></s:textfield> %
					</td>
				</tr>
				<tr>
					<td width="25%">IP 地址:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.ip" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">端口号:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.port" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">PID:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.pid" cssStyle="width:60%"></s:textfield>
					</td>
				</tr>
				<tr>
					<td width="25%">进程名称:</td>
					<td style="text-align: left;">
						<s:textfield name="currentTreeObj.processName" cssStyle="width:60%"></s:textfield>
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
							<s:textfield  name="currentTreeObj.ipAndYm" cssStyle="width:60%"></s:textfield> 
						</td>
					</tr>
					<tr>
						<td width="25%">域名:</td>
						<td style="text-align: left;">
							<s:textfield name="currentTreeObj.areaName" cssStyle="width:60%"></s:textfield>
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
							<s:textfield  name="currentTreeObj.mlbID" cssStyle="width:60%"></s:textfield> 
						</td>
					</tr>
					<tr>
						<td width="25%">服务端口号列表:</td>
						<td style="text-align: left;">
							<s:textfield name="currentTreeObj.serverPortArr" cssStyle="width:60%"></s:textfield>
						</td>
					</tr>
				</s:elseif>
		</table>
	</div>
</s:form>
</div>
</body>
</html:html>
