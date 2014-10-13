<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
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
		function resetForm(){
			hostForm.eq_name.value = "";
			hostForm.eq_ip.value = "";
			hostForm.hasvertual.value = "";
			hostForm.type.value="";
		}
		function searchRequest(){ 
			hostForm.action="showvm_queryHostIp.do?type=" + hostForm.TYPE.value;
			hostForm.submit();
		}
		
		//点击选择一个主机时，把主机的IP返回赋值到父页面
        function commitScript(){
       		var count = 0;
	       	var checkboxids = document.getElementsByName("checkboxid");
			if(checkboxids!=null&&checkboxids.length>0){
			    for(var i=0;i<checkboxids.length;i++){
				    if(checkboxids[i].checked){
						count++;
				    }
			    }
			}
			if(count<1){
				alert("请选择一个主机！");
				return false;
			}else if(count>1){
				alert("一次只能选择一个主机！");
				return false;
			} 
       		var hostip = $("[name='checkboxid']:checked").attr("eq_ip");//主机IP 
       		
       		var flag = hostForm.flag.value;
       		if (flag == 'host') {
       			api.get("vm_list").getHostip(hostip);
       		} else {
       			w.getHostip(hostip);
       		}
       }
	</script>
</head>
<body onLoad="self.focus();document.hostForm.eq_ip.focus()" class="pop-body scrollbody">
	<div class="mainbody">
	<s:form action="showvm_queryHostIp.do" method="post" cssClass="hostForm" id="hostForm">
	<s:hidden name="hostForm.TYPE" id="TYPE"/>
	<s:hidden name="hostForm.flag" id="flag"/>
	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">选择主机</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">IP地址：</label>
				<s:textfield name="hostForm.eq_ip" cssClass="inpt-1 vm"
								id="eq_ip" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">主机名称：</label>
				<s:textfield name="hostForm.eq_name" cssClass="inpt-1 vm"
								id="eq_name" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">主机类型：</label>
				<s:select cssClass="select-1 vm" list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}" name="hostForm.eq_type" id="type"></s:select>
				<label class="mgl-20 vm">虚拟化类型：</label>
				<s:select  cssClass="select-1 vm" list="#{'':'--请选择--','3':'XEN','4':'VMWARE','1':'PowerVM','2':'KVM','0':'非虚拟化'}" name="hostForm.hasvertual" id="hasvertual"></s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('hostForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
			</div>
	
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>主机IP地址</th>
									<th>
										主机名称
									</th>
									<th>
										主机类型
									</th>
									<th>虚拟化类型</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="hostForm.resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox"
												eq_ip='<s:property value="#theBean.eq_ip"/>'/>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
										</td>
										<td>
											<s:property value="#theBean.eq_name" />
										</td>
										<td>
											<s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if>
											<s:elseif test="#theBean.eq_type == 2">
												IBM刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 3">
												IBM普通刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 4">
												HPx86刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 5">
												机架服务器
											</s:elseif>
										</td>
										<td>
											<s:if test="#theBean.hasvertual == 3">
												XEN
											</s:if>
											<s:elseif test="#theBean.hasvertual==4">
												VMWARE	
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==1">
												PowerVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==2">
												KVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==0">
												非虚拟化
											</s:elseif>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</div>
</body>
