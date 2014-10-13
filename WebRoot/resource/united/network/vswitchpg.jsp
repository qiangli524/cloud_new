<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="resource/united/network/css/css.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
		function vswitch(vsName){
			var uuid = $("#uuid").val();
			var connect_id = $("#connect_id").val();
			$.dialog({
				id:'vswitchAttr',
				title:'属性',
				lock:true,
				height:'370px',
				width:'634px',
				content:"url:networkvs_editVswitch.do?vsName="+vsName+"&connect_id="+connect_id+"&uuid="+uuid
			});
		}
</script>
</head>
<body class="mainbody">
<!--head star-->
<s:hidden id="connect_id" value="%{connect_id}"></s:hidden>
<s:hidden id="uuid" value="%{uuid}"></s:hidden>
<s:iterator value="vsList" id="theBean">
<div style="clear:left">
	<div id="left">
		<fieldset>
			<s:set name="pgNum" value="#theBean.pgList.size()"/><!-- 统计端口组个数 -->
			<s:set name="vmNum" value="0"/><!-- 统计虚拟机个数 -->
			<s:iterator value="#theBean.pgList" id="pg">
				<legend>虚拟机端口组 </legend>
				<div class="network_content">
					<div class="network_obj">
						<div class="name"> <s:property value="#pg.name"/> </div>
							<div class="img">
								<img src="resource/united/network/img/1.jpg" alt="" />
							</div>
							<div class="line">
								<img src="resource/united/network/img/line.jpg" alt="" />
							</div>
						
							<div class="network_obj">
								<s:iterator value="#pg.vmList" id="vmName">
									<div class="name"> <s:property value="#vmName"/> </div>
									<div class="img">
										<img src="resource/united/network/img/2.jpg" alt="" />
									</div>
									<div class="line">
										<img src="resource/united/network/img/line.jpg" alt="" />
									</div>
								</s:iterator>
								<s:set name="vmNum" value="#pg.vmList.size() + #vmNum"></s:set>
							</div>
						</div>
					</div>
			</s:iterator>
		</fieldset>
		<s:set name="centerLength" value="36 * #pgNum + 20 * #vmNum"></s:set>
		<!-- 根据端口组的个数和虚拟机的个数匹配中间线的长度 -->
	</div>
	<div id="center" style="height:<s:property value='#centerLength'/>px;"></div>
	<div id="right" style="margin-left:5px;">
			<fieldset>
				<legend>物理适配器</legend>
					<div class="network_content">
						<div class="network_obj_right">
							<div class="line">
								<img src="resource/united/network/img/line.jpg" alt="" />
							</div>
								<s:iterator value="#theBean.nicList" id="pnic">
									
									<div class="img">
										<img src="resource/united/network/img/5.jpg" alt="" />
									</div>
									<div>
										<s:property value="#pnic.pnicName"/>
									</div>
								</s:iterator>
							
					</div>
			</fieldset>
	</div>
	<div>
		<input type="button" value="配置" class="ubtn-3 vm mgl-3" onclick="vswitch('<s:property value="#theBean.vsName"/>')"/>
	</div>
</div>
</s:iterator>
<!--w-1000 end-->
</form>
</body>
</html>
