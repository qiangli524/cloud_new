<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function revert(){
	 	var couterNum = 0;
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids !=null && checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1;
					var name = checkboxids[i].value;
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
 	    var vmName = theForm.vmName.value;
 	    var code = theForm.code.value;
 	    var url = "snapshot_revertSnapshot.do?vmName=" + vmName + "&name=" + name + "&code=" + code;
 	    if(confirm("除非已将虚拟机的当前状态保存在快照中，否则该状态将会丢失。 确定恢复到当前快照?")==true){
 	    	$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.result == 1){
 		    		alert("恢复快照成功!");
 		    		window.document.location.reload();
 		    	}else if(data.result == -1){
 		    		alert("恢复快照失败!");
 		    		window.document.location.reload();
 		    	}
 		    });
 	    }
	}
	function remove(){
		var couterNum = 0;
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids !=null && checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1;
					var name = checkboxids[i].value;
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
 	    var vmName = theForm.vmName.value;
 	    var code = theForm.code.value;
 	   	var url = "snapshot_deleteSnapshot.do?vmName=" + vmName + "&name=" + name + "&code=" + code;
 	   	if(confirm("确定要删除此快照吗?")==true){
 	   		$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.result == 1){
 	    			alert("删除快照成功!");
 	    			window.document.location.reload();
 	   		 	}else if(data.result == -1){
 		    		alert("删除快照失败!");
 		    		window.document.location.reload();
 		    	}
 		  	});
 	   	}
	}
</script>
</head>
<body style="width: 840px;height: 450px;margin: 10px;">
<s:form action="snapshot_snapshotManager" method="post"  id="theForm">
<s:hidden name="theForm.vmName" id="vmName"></s:hidden>
<s:hidden id="code" value="%{#request.entityId}"></s:hidden>
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="恢复" onclick="revert();"/></li>
<!--				<li><input type="button" class="thickbox btn-style02" value="修改" /></li>-->
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick="remove();"/></li>
			</ul>
		</div>
		<div class="table-ct">
			 <table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
				<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">名称</th>
						<th onclick="sort(theTable,2,'date')">时间</th>
						<th onclick="sort(theTable,3,'string')">描述</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="theBean" value="theForm.resultList">
					<tr>
						<td>
							<s:if test="#theBean.current==false">
								<input type="checkbox" value="<s:property value="#theBean.name"/>"  name="checkboxid"/>								
							</s:if>
							<s:elseif test="#theBean.current == true">
								<font color="red">当前位置</font>
							</s:elseif>
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
