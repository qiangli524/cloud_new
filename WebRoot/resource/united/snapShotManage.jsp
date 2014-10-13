<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<style type="text/css">
		.validateError {border:2px solid #FF7256;}
		.onfocus {border:1px solid #FFD700;}
	</style>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	
	 api.button({
	     id:'Ok',
	     name: '恢复',
	     callback:recover,
	     focus: false
	 },
	 {
	     id:'del',
	     name: '删除',
	     callback:del,
	     focus: false
	 });
		//恢复快照
		function recover(){
			var snapshotCode ='';
			var lenth=0;
			$('[name=snapshotCode]:checkbox:checked').each(function(){
        		snapshotCode +=$(this).val();
        		lenth +=1;
        	 });
        	if(snapshotCode==null || snapshotCode ==''){
				alert('请先选择一项进行恢复');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行恢复');
				return false;
			}
			var uuid = '<s:property value="uuid" />';
			var connect_id = '<s:property value="connect_id" />';
			var vtype = '<s:property value="vtype" />';
			
			///bar(uuid,"正在恢复虚拟机快照");
			var url = "united_operSnapShot.do?connect_id="+connect_id+"&snapshotCode="+snapshotCode+"&vtype="+vtype;
			w.operSnapshot(url,uuid,"recover");
		}
		//删除快照
		function del(){
			var snapshotCode ='';
			var lenth=0;
			$('[name=snapshotCode]:checkbox:checked').each(function(){
        		snapshotCode +=$(this).val();
        		lenth +=1;
        	 });
        	if(snapshotCode==null || snapshotCode ==''){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			var uuid = '<s:property value="uuid" />';
			var connect_id = '<s:property value="connect_id" />';
			var vtype = '<s:property value="vtype" />';
			///bar(uuid,"正在删除虚拟机快照");
			var url = "united_operSnapShot.do?connect_id="+connect_id+"&snapshotCode="+snapshotCode+"&vtype="+vtype;
			w.operSnapshot(url,uuid,"delete");
			$.ajax({
				type: "post",
				dataType: "json" ,
				url:url,
				success: function(msg){
					barEnd(uuid,msg);
				}
			});
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
		    max:false,
		    content:contents
		});
}

	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
	</script>
	
</head>
<body>
<s:form action="" method="post" id="snapshotVO">
<s:hidden name="snapshotVO.connectCode" id="connectCode"></s:hidden>
<s:hidden name="snapshotVO.vmCode" id="vmCode"></s:hidden>
<div class="blue-wrap noborder">
<div class="table-head">
	<!--  
	<ul class="btns">
        <li colspan="4" class="btnCenter">
         <input type="button"  value="恢复" name="mod" class="btn-style02"/>
         <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:if(confirm('确定删除？'))deleteConfig();"/>
        </li>
     </ul>
     -->
	<div class="table-ct">
		<table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
			<thead>
				<th>选择</th>
				<th onclick="sort(theTable,1,'string')">名称</th>
				<th onclick="sort(theTable,2,'string')">时间</th>
				<th onclick="sort(theTable,3,'string')">描述</th>
			</thead>
			<tbody>
				<s:iterator id="list" value="resultList">
					<tr>
						<td><input type="checkbox" value="<s:property value="#list.snapshotCode" />" name="snapshotCode"/></td>
						<td><s:property value="#list.snapshotName" /></td>
						<td><s:property value="#list.snapshotTime" /></td>
						<td><s:property value="#list.description" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
</div>
</s:form>
</body>
