<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
	var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
	var host_uuid = '<%=request.getAttribute("host_uuid")%>';
	function revert(){
	 	var couterNum = 0;
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids !=null && checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1;
					theForm.snap_uuid.value = checkboxids[i].value;
				}
			}
		}
		if(couterNum==0){
 	 		alert("请选择要恢复的快照!");
 	  		return false ;
		}else if(couterNum>1){
 	   		alert("只能选择恢复一条快照!");
 	    	return false ;
 	    }
 	    var snap_uuid = theForm.snap_uuid.value;
 	    var vm_uuid = '<%=request.getAttribute("vm_uuid")%>';
 	    var getVmStateUrl = "xen_getVmState.do?vm_uuid="+vm_uuid+"&pool_uuid="+pool_uuid;
 	    $.getJSON(getVmStateUrl,{"time":new Date().toString()},function(date){
	 	     if(date.state!='Halted'){
	 	    	alert("请先关闭虚拟机，再进行此操作");
	 	    	return false;
	 	    }else{
		 	    var url = "xen_recoverSnapShot.do?snap_uuid=" + snap_uuid+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid ;
		 	    if(confirm("除非已将虚拟机的当前状态保存在快照中，否则该状态将会丢失。 确定恢复到当前快照?")==true){
		 	 	    mask('正在恢复,请稍后...','0.5');
		 	    	$.getJSON(url,{"time":new Date().toString()},function(data){
		 	    		if(data.responseCode == 1){
		 	    			removeMask();
		 	    			alert("恢复快照成功!");
		 	    			window.location.reload();
		 	   		 	}else if(data.responseCode == -1){
		 	   		 	    removeMask();
		 		    		alert("恢复快照失败!");
		 		    		window.location.reload();
		 		    	}
		 		  	});
		 	    }
	 	    }
 	    });
	}
	function remove(){
		var couterNum = 0;
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids !=null && checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1;
					 theForm.snap_uuid.value = checkboxids[i].value;
				}
			}
		}
		if(couterNum==0){
 	 		alert("请选择要删除的快照信息");
 	  		return false ;
		}else if(couterNum>1){
 	   		alert("只能选择删除一条快照信息");
 	    	return false ;
 	    }
 	    var snap_uuid = theForm.snap_uuid.value;
 	   	var url = "xen_delSnapshot.do?snap_uuid=" + snap_uuid+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid  ;
 	   	if(confirm("确定要删除此快照吗?")==true){
 	      	mask('正在删除,请稍后...','0.5');
 	   		$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.responseCode == 1){
 	    			removeMask();
 	    			alert("删除快照成功!");
 	    			window.location.reload();
 	   		 	}else if(data.responseCode == -1){
 	   		 	    removeMask();
 		    		alert("删除快照失败!");
 		    		window.location.reload();
 		    	}
 		  	});
 	   	}
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="snapshot_snapshotManager" method="post"  id="theForm">
<s:hidden name="theForm.snap_uuid" id="snap_uuid"/>
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="恢复" onclick="revert();"/></li>
<!--				<li><input type="button" class="thickbox btn-style02" value="修改" /></li>-->
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick="remove();"/></li>
			</ul>
		</div>
		<div class="table-ct">
			 <table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				<thead>
					<tr>
						<th>选择</th>
						<th>名称</th>
						<th>时间</th>
						<th>描述</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="theBean" value="theForm.resultList">
					<tr>
						<td>
							<input type="checkbox" value="<s:property value="#theBean.snap_uuid"/>"  name="checkboxid" />
						</td>
						<td>
							<s:property value="#theBean.name"/>
						</td>
						<td>
							<s:property value="#theBean.time"/>
						</td>
						<td>
							<s:property value="#theBean.description"/>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</s:form>
</body>
