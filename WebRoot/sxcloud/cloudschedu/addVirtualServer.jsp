<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
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
	function isdigit(s)
            {
            var r,re;
            re = /\d*/i; //\d表示数字,*表示匹配多个数字
            r = s.match(re);
            return (r==s)?1:0;
            }
            
	function submitRequest(thisForm){    
	    if(thisForm.name.value.length ==0){
	     alert("请输入虚拟服务器名称！");
	     thisForm.name.focus;
	     return false  ;
	    }
	    if(thisForm.virtualAddress.value.length ==0){
	     alert("请输入虚拟服务器地址！");
	     thisForm.virtualAddress.focus;
	     return false  ;
	    }
	    if(isdigit(thisForm.persistent.value)==0){
	     alert("请输入会话保持时间，并且只能为数字!");
	     thisForm.persistent.focus;
	     return false  ;
	    }
	    if(isdigit(thisForm.checkport.value)==0){
	     alert("健康检查服务端口只能为数字!");
	     thisForm.checkport.focus;
	     return false  ;
	    } 
	    
	     if(thisForm.persistentType.value=='yes'){
	     if(thisForm.persistent.value ==0){  
	     alert("客户端请求为会话保持，请输入会话保持时间");
	     thisForm.persistent.focus;
	     return false  ;
	     }
	    } 
	    if(thisForm.checktype.value=='negotiate'){
	     if(thisForm.request.value.length ==0){  
	     alert("健康检查服务方式为内容协商，请输入健康检查请求内容");
	     thisForm.request.focus;
	     return false  ;
	     }
	     if(thisForm.receive.value.length ==0){  
	     alert("健康检查服务方式为内容协商，请输入健康检查请求结果");
	     thisForm.receive.focus;
	     return false  ;
	     }
	    }	
	    thisForm.submit();
	}
	



</script>
</head>
<body class="pop-body scrollbody">
<s:form action="virserver_sureAddVirtualServer" method="post" cssClass="theForm" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					虚拟服务器名称
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.name" cssClass="txt" id="name"></s:textfield>
				</td>
				<td class="til">
					虚拟服务器IP地址和端口
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.virtualAddress" cssClass="txt" id="virtualAddress"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					负载调度算法
					<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'wrr':'加权轮叫调度','dh':'按目的地址分发','lblc':'基于局部性的最少链接','lblcr':'带复制的基于局部性最少链接',
					'lc':'最小连接调度','rr':'轮叫调度','sh':'按源地址分发','wlc':'加权最小连接调度'}" name="theForm.scheduler" id="scheduler"></s:select>
				</td>
				<td class="til">
					IP协议类型
					<font color="red"></font>
				</td>
				<td>
					<s:select list="#{'tcp':'tcp','udp':'dup'}" name="theForm.protocol" id="protocol"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					是否需要会话保持
					<font color="red"></font>
				</td>
				<td>
					<s:select list="#{'':'-请选择-','yes':'yes','no':'no'}" name="theForm.persistentType" id="persistentType"></s:select>
				</td>
				<td class="til">
					客户端连接保持时间
					<font color="red">(秒)*</font>
				</td>
				<td>
					<s:textfield name="theForm.persistent" id="persistent" cssClass="txt"></s:textfield>
				</td>

			</tr>
			<tr>
				<td class="til">
					网络掩码
					<font color="red">(例:255.255.255.255)*</font>
				</td>
				<td>
					<s:textfield name="theForm.netmask" id="netmask" cssClass="txt"></s:textfield>
				</td>
				<td class="til">
					服务回退地址
				</td>
				<td>
					<s:textfield name="theForm.fallback" id="fallback" cssClass="txt"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					健康检查服务方式
				</td>
				<td>
					<s:select list="#{'connect':'连接','negotiate':'内容协商','ping':'ping','on':'开启','off':'关闭'}" name="theForm.checktype"
					id="checktype" cssClass="txt" ></s:select>
				</td>
				<td class="til">
					服务类型
				</td>
				<td>
					<s:select list="#{'':'-请选择-','ftp':'ftp','smtp':'smtp','http':'http','pop':'pop','pops':'pops',
					'nntp':'nntp','imap':'imap','ldap':'ldap','https':'https','dns':'dns','mysql':'mysql','pgsql':'pgsql','sip':'sip','none':'none'}"
					name="theForm.service"	id="service"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					健康检查服务端口
				</td>
				<td>
					<s:textfield name="theForm.checkport" id="checkport" cssClass="txt"></s:textfield>
				</td>
				<td class="til">
					健康检查服务参数
				</td>
				<td>
					<s:textfield name="theForm.virtualHost" id="virtualHost" cssClass="txt"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					健康检查服务登录用户名
				</td>
				<td>
					<s:textfield name="theForm.login" id="login" cssClass="txt"></s:textfield>
				</td>
				<td class="til">
					健康检查服务登陆口令
				</td>
				<td>
					<s:textfield name="theForm.password" id="password" cssClass="txt"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					数据库名
				</td>
				<td>
					<s:textfield name="checkdb" cssClass="txt" id="checkdb"></s:textfield>
				</td>
				<td class="til">
					HTTP服务健康检查方法
				</td>
				<td>
					<s:select list="#{'':'-请选择-','GET':'GET','HEAD':'HEAD'}" name="theForm.httpMethod" id="httpMethod"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					健康检查请求内容
				</td>
				<td colspan="3">
					<s:textarea name="theForm.request" id="request" cols="77" rows="2"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">
					健康检查请求结果
				</td>
				<td colspan="3">
					<s:textarea name="theForm.receive" id="receive" cols="77" rows="2"></s:textarea>
				</td>
			</tr>


			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>	
				</td>
			</tr>

		</table>
</s:form>
</body>

