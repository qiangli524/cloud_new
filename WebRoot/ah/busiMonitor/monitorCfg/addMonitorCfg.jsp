<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">

	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
		}

	//初始化更新操作时填充下拉框
	$(document).ready(function(){
		var extEqIds = $("#extEqId").val();// 主机ID
		var hostIp = $("#hostIpTemp").val();// 主机IP
		var userId = $("#userIdTemp").val();// 主机用户
		var hostType = $("#hostType").val();// 主机类型
		if(Trim(extEqIds)!= ''){
			if(hostType == '1'){// 物理主机处理
				//添加主机的IP地址默认值
				var hostip=document.getElementById("hostIp");
				hostip.add(new Option(hostIp,hostIp));
				$("#hostIp").attr("value",hostIp);
				//根据IP地址查询用户列表，并添加到用户的select中
				queryUserByIp(hostIp);
				//添加主机的用户默认值
				$("#userId").attr("value",userId);
				}
			if(hostType == '2'){// 虚拟主机处理
				//根据主机信息，查找IP地址列表，并添加到IP的select中
				var extEqId = extEqIds.split('_');
				queryIpByVmHost(extEqId[1],extEqId[0]);
				//添加主机的IP地址默认值
				$("#hostIp").attr("value",hostIp);
				//根据IP地址查询用户列表，并添加到用户的select中
				queryUserByIp(hostIp);
				//添加主机的用户默认值
				$("#userId").attr("value",userId);
				}
			}
	});
	
	$(function(){
    	var api = frameElement.api;
		w = api.opener;
      $("[type='selectHost']").unbind().live("click",function(){
	  		w.$.dialog({
    			id:'addHost',
    			title:'选择主机',
    			width: '1000px',
    			height: '500px',
    		    lock:true,
    			content: 'url:monitorCfg_queryHostList.do?flag=1'
	    		});
		 });
		 
		
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createMonitorCfg,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		
	});
	
	//创建脚本
	function createMonitorCfg(){
		var id = $('#id').val();
		var extEqId = $("#extEqId").val();
		var hostIp = $("#hostIp").val();
		var kpiId = $("#kpiId").val();
		var kpiCfgValue = $("#kpiCfgValue").val();
		var userId = $("#userId").val();
		var busiDesc = $("#busiDesc").val(); 
		
		if(Trim(extEqId)==''){
			alert("请选择主机");
			return false;
		}
		if(Trim(hostIp)==''){
			alert("请选择IP");
			return false;
		}
		if(Trim(userId)==''){
			alert("请选择用户");
			return false;
		}
		if(Trim(kpiId)==''){
			alert("请输入指标ID");
			return false;
		}
		if(Trim(kpiCfgValue)==''){
			alert("请输入指标配置值");
			return false;
		}
		if(kpiId == 'PM-01-01-001-02'){
			var start = kpiCfgValue.charAt(0);
			var end = kpiCfgValue.charAt(kpiCfgValue.length - 1);
			if(start != '/' || end != '/'){
				alert('指标配置值格式不正确');
				document.getElementById("kpiCfgValue").focus();
				return false;
				}
			}else{
				var start = kpiCfgValue.charAt(0);
				if(start != '/'){
					alert('指标配置值格式不正确');
					document.getElementById("kpiCfgValue").focus();
					return false;
					}
				}
		
		 var url = "monitorCfg_saveInfo.do?id=" + id +"&"+$("#theForm").serialize();
		 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	w.searchRequest();
                }
              }
		});
    }

	//在add页面添加主机信息
	 function addHost(eq_ip,sn,eq_name){
		 $("#extEqId").attr("value",eq_ip + "_" + sn);
		 $("#extEqName").attr("value",eq_name);
		 $("#hostType").attr("value","1");
		 
		 var hostip=document.getElementById("hostIp"); 
		 hostip.options.length=0;
		 hostip.add(new Option("",""));
		 hostip.add(new Option(eq_ip,eq_ip));
		 var userid=document.getElementById("userId");
		 userid.options.length=0;
		 userid.add(new Option("",""));
	    }
	    
	//在add页面添加虚拟主机信息
	 function addVmHost(connect_id,vh_uuid,vh_ip,vm_name){
		 $("#extEqId").attr("value",connect_id + "_" + vh_uuid);
		 $("#extEqName").attr("value",vm_name);
		 $("#hostType").attr("value",'2');
		 queryIpByVmHost(vh_uuid,connect_id);
		 var userid=document.getElementById("userId");
		 userid.options.length=0;
		 userid.add(new Option("",""));
	    }

	//根据主机信息查询IP，并填充到ip下拉框中
	 function queryIpByVmHost(vm_uuid,connectid){
		 var url = "monitorCfg_queryIpByVmHost.do?vmId=" + vm_uuid + "&connectId=" + connectid;
		 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: false,
              cache: false,
	          success: function(data){
				if(data!=null && data.length>0){
					var hostip=document.getElementById("hostIp");
					hostip.options.length=0;
					for(i=0;i<data.length;i++){
						hostip.add(new Option("",""));
						hostip.add(new Option(data[i],data[i]));
						}
			}
		}
		});
	}

	 //根据IP查询用户list，并填充到userid下拉框中
	 function queryUserByIp(ip){
		 if(Trim(ip)!= ''){
			 var url = "monitorCfg_queryUserByIp.do?hostip=" + ip;
			 $.ajax({
				  type:"GET",
	              url:url,
	              data:"text",
	              async: false,
	              cache: false,
		          success: function(data){
					if(data!=null && data.length>0){
						var userid=document.getElementById("userId");
						userid.options.length=0;
						for(i=0;i<data.length;i++){
							userid.add(new Option(data[i].userName,data[i].id));
							}
				}
			}
			});
			 }else{
				 var userid=document.getElementById("userId");
					userid.options.length=0; 
					userid.add(new Option('',''));
				 }
	}

</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">监控配置管理</h2>
         <div class="bord-1 pd-10">
			<s:form action="monitorCfg_saveInfo" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="obj.id" id="id"></s:hidden>
				<s:hidden name="obj.busiType" id="busiType" value="01"></s:hidden>
				<s:hidden name="hostIpTemp" id="hostIpTemp"></s:hidden>
				<s:hidden name="userIdTemp" id="userIdTemp"></s:hidden>
				<s:hidden name="obj.hostType" id="hostType"></s:hidden>
				<s:hidden name="obj.extEqId" id="extEqId"></s:hidden>
				<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
							<td class="til">
								主机名称 
							</td>
							<td>
							<s:textfield name="obj.extEqName" id="extEqName" maxlength="30" readonly="true" cssClass="required inpt-2"/>
							<a href="javascript:;" type="selectHost" id="start" style="color: blue;text-decoration: underline;">选择</a>
							</td>
							 <td class="til">
								主机IP 
							</td>
							<td >
							<!-- <s:textfield name="obj.hostIp" id="hostIp" maxlength="30" readonly="true" cssClass="required inpt-2"/> -->
							<s:select list="#{'':'--请选择--'}" name="obj.hostIp" id="hostIp"  onchange="queryUserByIp(this.value)" ></s:select> 
							</td>
						</tr>
		
						<tr>
							 <td class="til">
								用户名
							</td>
							<td>
							<s:select list="#{'':'--请选择--'}" name="obj.userId" id="userId"></s:select>
							</td>
						
							<td class="til">
								指标名称
							</td>
							<td>
							<s:select list="kpiIdMap" name="obj.kpiId" id="kpiId" ></s:select>
							</td>
						</tr>
		
						<tr>
							 <td class="til">
								指标配置值
							</td>
							<td>
							<s:textarea name="obj.kpiCfgValue" id="kpiCfgValue" cols="40" rows="4"/>
							</td>
						</tr>
		
						<tr>
							<td class="til">
								业务描述
							</td>
							<td >
							<s:textarea name="obj.busiDesc" id="busiDesc" cols="40" rows="4" />
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
