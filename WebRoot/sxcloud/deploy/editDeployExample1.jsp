<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
<title></title>

<script type="text/javascript">
	var operType = '<%=request.getAttribute("operType")%>';
	var nodeId = '<%=request.getAttribute("nodeId")%>';
	var dialogName = '<%=request.getAttribute("dialogName")%>';
	
	 $(function(){
    	if(operType == 'tree'){
			$("input[value='返回']").hide();
			var theForm = document.getElementById('theForm');
			var ID = theForm.ID.value;
			if(ID==0){
				selectAppRequest();
				$("#APPID").attr("disabled",true);
			}else{
				selectAppRequest();
			}
		}else{
			$("input[value='返回']").show();
			var ID = $("#ID").val();
			if(ID>0){
				selectAppRequest();
			}
		}
    });
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})
	
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitForm(){    
		//浙江竞标
        //$.getJSON("vmw_startApply.do");
<%--		var url = window.location.href;--%>
<%--		window.location.href=url.split('callbackurl=').pop();--%>
	    /*if(thisForm.APPID.value.length ==0){
	     alert("部署应用不能为空！");
	     thisForm.APPID.focus;
	     return false  ;
	    }
	    if(thisForm.HOSTID.value.length ==0){
	     alert("部署主机不能为空！");
	     thisForm.HOSTID.focus;
	     return false  ;
	    }
	    if(thisForm.KEYNAME.value.length ==0){
	     alert("部署中间件不能为空！");
	     thisForm.KEYNAME.focus;
	     return false  ;
	    }
	    thisForm.submit();*/
	    if(theForm.APPID.value==0){
				alert("请选择应用后，再保存");
				return false;
			}
			if(theForm.ID.value == 0){
				var checkboxids = document.getElementsByName("checkboxid");
				var idsstring = '';
				for(var i=0;i<checkboxids.length;i++){
		 	       if(checkboxids[i].checked){
		 	        idsstring = idsstring + checkboxids[i].value + ':'+$(checkboxids[i]).attr("HOSTUSERNAME")+",";
		 	       }  
		 	     }
		 	     if(idsstring == ''){
		 	     alert("请选择要部署的主机，再保存");
					return false;
		 	     }
				theForm.checkboxhostids.value = idsstring;
			}
			if(operType=='list'){
				theForm.action = 'dep_saveDeployExample.do?operType=list';
				theForm.submit();
			}else{
				var api = frameElement.api;
				var w = api.opener;
				var deployExampleObj = {};
				var theFormData = $("#theForm").serialize();
				var ID = $("#ID").val();
				deployExampleObj.theFormData = theFormData;
				deployExampleObj.nodeId = nodeId;
				deployExampleObj.dialogName = dialogName;
				deployExampleObj.ID = ID;
				w.saveDeployExample(deployExampleObj);
			}
	}
	
	function selectAppRequest() {
		var APPID = theForm.APPID.value;
		if(theForm.APPID.value==0){
			$("#tr_standardPath").hide();
			$("#tr_standardHostIP").hide();
			$("#tr_deployPath").hide();
			$("#tr_startsh").hide();
			$("#tr_stopsh").hide();
		}else{
			$.getJSON('dep_selectAppRequest.do?APPID='+APPID,{'time':new Date().toString()},function(data){
				if(data.result==1){
					if(data.standardPath!=null){
						$("#tr_standardPath td:eq(1)").text(data.standardPath);
						$("#tr_standardPath").show();
					}else{
						$("#tr_standardPath").hide();
					}
					if(data.standardHostIP!=null){
						$("#tr_standardHostIP td:eq(1)").text(data.standardHostIP);
						$("#tr_standardHostIP").show();
					}else{
						$("#tr_standardHostIP").hide();
					}
					if(data.deployPath!=null){
						$("#deployPath").attr("value",data.deployPath);
						$("#tr_deployPath").show();
					}else{
						$("#tr_deployPath").hide();
					}
					if(data.startsh!=null){
						$("#tr_startsh td:eq(1)").text(data.startsh);
						$("#tr_startsh").show();
					}else{
						$("#tr_startsh").hide();
					}
					if(data.stopsh!=null){
						$("#tr_stopsh td:eq(1)").text(data.stopsh);
						$("#tr_stopsh").show();
					}else{
						$("#tr_stopsh").hide();
					}
				}
			});
		}
	}
	
	function checkip(){
		var APPID = theForm.APPID.value;
		var standardHostIP = $("#tr_standardHostIP td:eq(1)").text();
		if(APPID < 1){
		alert("请先选择应用信息");
			return false;
		}
		$.getJSON('dep_queryAppIp.do?APPID='+APPID+"&standardHostIP="+standardHostIP,{'time':new Date().toString()},function(data){
			$("#hostLst").height($("#hostLst").height()+ (data.length*12)+15);
			var tbody = $("table:eq(3) tbody").empty();
			$.each(data,function(k,v){
				var ID;
				var IP;
				var VLANIP;
				var HOSTUSERNAME;
				$.each(v,function(key,value){
					if(key == 'ID'){
						ID = value;
					}else if(key == 'IP'){
						IP = value;
					}else if(key == 'VLANIP'){
						VLANIP = value;
					}else if(key == 'HOSTUSERNAME'){
						HOSTUSERNAME = value;
					}
				});
				var $_tr = $("<tr></tr>");
				var $_td_1 = $("<td></td>");
				var $_input = $("<input></input>");
				$_input.attr("name","checkboxid").attr("type","checkbox").attr("value",ID).attr("HOSTUSERNAME",HOSTUSERNAME);
				$_td_1.append($_input);
				var $_td_2 = $("<td></td>");
				$_td_2.text(IP);
				var $_td_3 = $("<td></td>");
				$_td_3.text(VLANIP);
				var $_td_4 = $("<td></td>");
				$_td_4.text(HOSTUSERNAME);
				$_tr.append($_td_1).append($_td_2).append($_td_3).append($_td_4);
				tbody.append($_tr);
			});
			var table1 = $("table:eq(3)").clone();
			var table2 = $("table:eq(4)").clone();
			$("#hostLst").empty().append(table1).append(table2);
		});
	}
	
		/*
			保持复选框状态
			zhangwj 2012-4-8
		*/	
		$(function(){
				var chks = document.getElementById("theForm").chks.value;
				var checkboxid = theForm.checkboxid;
				if(	typeof(checkboxid) == 'undefined' || typeof(checkboxid.length) == 'undefined' ){
					return;
				}
				if( null != chks && "" != chks){
					for(var i=0;i<checkboxid.length;i++){
						var ip = checkboxid[i].value;
						var arr = chks.split(":");
						for(var j=0;j<arr.length;j++){
							if(ip == arr[j]){
								checkboxid[i].checked = true;
							}
						}
					}
				}
			})
			/*
				单击时同步选中的IP值到 chks
				zhangwj 2012-4-8
			*/
			function checkboxchange(id){
				var chks = document.getElementById("theForm").chks;
				var vlanchks = document.getElementById("theForm").vlanchks;
				var arr = chks.value.split(":");
				var vlanarr = vlanchks.value.split(":");
				if(id.checked){
					if(arr.length > 0){
						chks.value += ":"+ id.value.split("/")[0];
					}else{
						chks.value = id.value.split("/")[0];
					}
					if(vlanarr.length >0){
						var temp = id.value.split("/")[1];
						if(temp !=null && " "!=temp){
							vlanchks.value +=":"+temp;
						}
					}else{
						if(temp !=null && " "!=temp){
							vlanchks.value += temp;
						}
				    } 
				}else{
					var str = "";
					var vlanstr = "";
					if( arr.length > 0){
						for(var i=0;i<arr.length;i++){
							if(id.value.split("/")[0] != arr[i]){
								if(i == 0){
								    str += arr[i];
								}else{
									str += ":" + arr[i];
								}
							}
						}
					}
					if(vlanarr.length>0){
						for(var j=0;j<vlanarr.length;j++){
							if(id.value.split("/")[1] !=vlanarr[j]){
								if(j==0){
									vlanstr += vlanarr[j];
								}else{	
									vlanstr += ":" + vlanarr[j];
								}
							}
						}
					}
					chks.value = str;
					vlanchks.value = vlanstr;
				}
			//	alert(chks.value);
			//	alert(vlanchks.value);
			}
			function selectAll()
	{
		var n=document.getElementsByName("checkboxid").length;
		var select=document.getElementsByName("checkboxid");
		if(select.length)
		{
			for(var i=0;i<n;i++)
			{
				if(select[i].value!="-1")
				{	
					if(select[i].checked==true){
						select[i].checked=false;
					} else {
						select[i].checked=true;
					}    
			    } 
			}
		}
	}
		--></script>
</head>
<body class="pop-body scrollbody">
	<s:form action="dep_queryAppIp" method="post" cssStyle="theForm"
		id="theForm">
		<%--	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">--%>

		<s:hidden name="theForm.checkboxhostids" id="checkboxhostids"></s:hidden>
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.HOSTID" id="HOSTID"></s:hidden>
		<s:hidden name="theForm.chks" id="chks"></s:hidden>
		<%--		<s:hidden name="theForm.DEPLOY_FLAG" id="DEPLOY_FLAG"></s:hidden>--%>
		<div class="tit-zzi">
			<div id="zi">基准应用信息</div>
			<div id="zi"></div>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">选择基准应用</td>
					<td><s:if test="theForm.ID==0">
							<s:select list="theForm.appList" listKey="ID"
								name="theForm.APPID" id="APPID" listValue="APPNAME"
								headerKey="0" headerValue="-请选择-" onchange="selectAppRequest()">
							</s:select>
						</s:if> <s:elseif test="theForm.ID>0">
							<s:text name="theForm.APPNAME" />
							<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
						</s:elseif></td>
				</tr>
					<tr id="tr_standardHostIP" style="display: none">
						<td class="til">基准机IP</td>
						<td></td>
					</tr>
					<tr id="tr_standardPath" style="display: none">
						<td class="til">基准应用部署路径</td>
						<td></td>
					</tr>
					<tr id="tr_deployPath" style="display: none">
						<td class="til">部署机部署路径</td>
						<td><s:textarea name="theForm.deployPath" id='deployPath' cssStyle="txt"
								cols="100" rows="5" /></td>
					</tr>
<%--					<tr id="tr_startsh" style="display: none">--%>
<%--						<td class="til">启动脚本</td>--%>
<%--						<td></td>--%>
<%--					</tr>--%>
<%--					<tr id="tr_stopsh" style="display: none">--%>
<%--						<td class="til">停止脚本</td>--%>
<%--						<td></td>--%>
<%--					</tr>--%>
			</table>
		</div>
		<div class="tit-zzi">
			<div id="zi">部署操作选项</div>
			<div id="zi"></div>
		</div>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">是否重启: <font color="red">*</font></td>
				<td><s:select list="#{'0':'-不重启-','1':'-重启-'}"
						name="theForm.isrestart" id="isrestart"></s:select></td>
			</tr>
			<tr>
				<td class="til">是否备份: <font color="red">*</font></td>
				<td><s:select list="#{'0':'-不备份-','1':'-备份-'}"
						name="theForm.isbackup" id="isbackup"></s:select></td>
			</tr>
		</table>

		<s:if test="theForm.ID==0">
			<div class="tit-zzi">
				<div id="zi">选择主机</div>
				<div id="zi"></div>
			</div>
			<div>
				<table width="100%" border="0" cellspacing="0"
					class="pop-table nosize">

					<tr>
						<td class="til">
							<%--						<html:radio name="theForm" property="APP_IPMODEL" value="0" >自动分配</html:radio>--%>
							<%--						<html:radio name="theForm" property="APP_IPMODEL" value="1" >手动选择</html:radio>   --%>
							<input type="button" class="thickbox btn-style02-75" value="查询主机"
							onclick="checkip();" /></td>
					</tr>
				</table>
			</div>
			<div class="zzi">
				<div id="zi" style="color:black">主机列表</div>
			</div>
		</s:if>
		<s:if test="theForm.ID>0">
			<div class="zzi">
				<div id="zi" style="color:black">部署主机</div>
			</div>
		</s:if>
		<div class="blue-wrap noborder" id="hostLst">
			<div class="table-ct">
				<table width="100%" class="blue-table sorttable" border="0"
					cellspacing="0">
					<thead>
						<tr>
							<s:if test="theForm.ID==0">
								<th><input name="allcheckbox" type="checkbox"
									onclick="selectAll()" /></th>
							</s:if>
							<th>管理IP</th>
							<th>服务IP</th>
							<th>用户</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="theForm.hostList" id="theBean">
							<tr>
								<s:if test="theForm.ID==0">
									<td><input name="checkboxid" type="checkbox"
										value='<s:property value="#theBean.ID" />' HOSTUSERNAME='<s:property value="#theBean.HOSTUSERNAME" />'/></td>
								</s:if>
								<td><s:property value="#theBean.IP" /> <s:hidden
										name="theBean.IP" id="IP"></s:hidden></td>
								<td><s:property value="#theBean.VLANIP" /> <s:hidden
										name="theBean.VLANIP" id="VLANIP"></s:hidden></td>
								<td><s:property value="#theBean.HOSTUSERNAME" /> <s:hidden
										name="theBean.HOSTUSERNAME" id="HOSTUSERNAME"></s:hidden></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>

			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td colspan="4" class="btnCenter"><input type="button"
						class="btn-style02" value="确定" onclick="javascript:submitForm();" />
						<input type="button" class="btn-style02" value="返回"
						onclick=" window.history.back();return false;" /></td>
				</tr>
			</table>
		</div>
	</s:form>
	<script>
	  //app_port();
	  function app_port(){
	  var form=document.getElementById('theForm');
	  var appport=document.getElementById('APPPORT').value;
	  var html="";
	  var appid=form.APPID.value;
	   if(appid==null||appid==0){
	  	 html="选择应用端口会自动填充";
	  	 $("#APPPORT").text(html);
	  	return;
	  }
	  $.getJSON("appport_app_port.do?id="+appid,function(data){
			for(var i=0;i<data.length;i++)
			{
				if(data[i].PORT==appport)
				{html=html+"<input type=checkbox checked=checked value='"+data[i].PORT+"'>"+data[i].PORT+"";}
				else{
				html=html+"<input type=checkbox value='"+data[i].PORT+"'>"+data[i].PORT+"";}
			}
			$("#APPPORT").html(html);
	   });
	  }
	</script>
</body>

</html:html>
