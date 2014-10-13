<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
	function delRequest() {
 		  var couterNum = 0;
 		  var tmpid="";
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      alert(theForm.ID.value);
 	      tmpid=theForm.ID.value;
 	      alert(tmpid);
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除一条信息");
 	    return false ;
 	    }
 	   if(confirm("确定要删除?")){
 	    	theForm.action = 'workflow_delSourceTmp.do?ID='+tmpid;
			  theForm.submit();
		}
		
		}
</script>
</head>
<body>
	<div class="mainbody">
			<s:form action="workflow_listSourceTmp.do" method="post"
			cssClass="theForm" id="theForm">	
			<s:hidden id="ID" name="theForm.ID"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<div class="bord-1 pd-10">
					<div class="utt-2 mgt-20">
				 <a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			    </div>
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">cpu</th>
								<th onclick="sort(theTable,1,'string')">内存</th>
								<th onclick="sort(theTable,2,'string')">存储</th>
								<th onclick="sort(theTable,3,'string')">操作系统</th>
								<th onclick="sort(theTable,4,'string')">资源模板号</th>
								<th onclick="sort(theTable,5,'string')">网络类型</th>
								<th onclick="sort(theTable,6,'string')">应用类型</th>
								<th onclick="sort(theTable,6,'string')">备注</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="workOrderRecList" id="theBean">
								<tr>
									<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"/></td>
									<td><s:property value="#theBean.CPU_NUM" />核</td>
									<td><s:property value="#theBean.MEM_SIZE" />G</td>
									<td><s:property value="#theBean.SR_SIZE" />G</td>
									<td><s:property value="#theBean.VM_NAME" /></td>
									<td><s:property value="#theBean.TEMPLATE_ID" /></td>
									 <td><s:property value="#theBean.NETWORK_ID" /></td>
									<td><s:property value="#theBean.BUSI_ID" /></td>
									<td><s:property value="#theBean.MESSAGE" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
		</s:form>
	</div>
</body>
