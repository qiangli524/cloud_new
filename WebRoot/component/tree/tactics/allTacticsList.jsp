<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<head>
    <title></title>
     <style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
  	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    	var api = frameElement.api;
    	var w = api.opener;
    	$(function(){
    		$("#addTactics").click(function(){
    			var taskId = $("#taskId").val();
    			var count = 0;
    			$.ajax({
    				type:'post',
    				url:'tactics_countTactics.do?taskId='+taskId,
    				async:false,
    				success:function(msg){
    					count = msg;
    				}
    			});
    			if(count > 0){
    				alert("该任务已经存在策略,不能添加多条策略!");
    				return false;
    			}
    			w.$.dialog({
    				id:'addTactics',
    	    		title:'添加任务策略',
    	    		width: '600px',
    	    		height: '400px',
    	    		max: true,
    	    		min: true,
    	    		content: 'url:tactics_insertTacticsObj.do?taskId='+taskId+'&oper=add'
    			});
    		});
    		
    		$("#editTactics").click(function(){
    			if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行修改");
					return false;
				}
				var tacticsId = "";
				$(":checkbox:checked").each(function(){
					tacticsId+=$(this).attr("tacticsId");
				});
				w.$.dialog({
					id:'editTactics',
	    			title:'修改任务策略',
	    			width: '600px',
	    			height: '400px',
	    			max: true,
	    		    min: true,
	    			content: 'url:tactics_updateTacticsObj.do?tacticsId='+tacticsId+'&oper=edit'
				});
    		});
    		
    		$("#deleteTactics").click(function(){
    			if($(":checkbox:checked").length == 0){
    				alert("您好：请至少选择一项进行删除！");
    				return false;
    			}
    			var tacticsId = "";
    			$(":checkbox:checked").each(function(){
    				tacticsId += $(this).attr("tacticsId");
    			});
    			$.ajax({
    				type:'post',
    				url:'tactics_deleteTacticsObj.do?tacticsId='+tacticsId,
    				success:function(msg){
    					if (msg == -1) {
							alert("删除失败");
						} else{
							$("#theForm").submit();
						}
    				}
    			});
    		});
    	});
    	
    	function saveTactics(theForm){
    		$.ajax({
    			type:'post',
    			url:'tactics_saveTacticsObj.do?'+theForm,
    			success:function(msg){
    				if (msg == -1) {
						alert("保存失败");
					} else {
						$("#theForm").submit();
					}
    			}
    		});
    	}
    	
    </script>
</head>
<body class="pop-body scrollbody" >
    <s:form action="tactics_listTacticsObjMappings.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="taskId" id="taskId"></s:hidden>
	<div class="box on" >
    <div class="blue-wrap noborder">
	<div class="table-head">
				<ul class="btns">
                    <li colspan="4" class="btnCenter">
                           <input type="button"  value="添加"  class="btn-style02" name="add" id="addTactics"/>
                         <input type="button"  value="修改" name="mod" class="btn-style02" id="editTactics"/>
                        <input type="button"  value="删除" name="del" class="btn-style02" id="deleteTactics" />
                    </li>
                </ul>
    </div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="table">
            				<thead>
								<tr>
									<th>选择</th>
									<th onclick="sort(theTable,1,'string')">执行方式</th>
									<th onclick="sort(theTable,2,'string')">执行时间模式</th>
									<th onclick="sort(theTable,3,'date')">执行时间</th>
									<th onclick="sort(theTable,4,'string')">是否备份</th>
									<th onclick="sort(theTable,5,'string')">是否进行环境检测</th>
									<th onclick="sort(theTable,6,'string')">是否重启实例</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td><input type="checkbox" tacticsId="<s:property value='#theBean.ID'/>" /></td>
										</td> 
										<td><s:if test="#theBean.EXECUTEMETHOD==1">并行执行</s:if> <s:elseif
												test="#theBean.EXECUTEMETHOD==2">按优先级逐个执行</s:elseif> <s:else>按优先级并行执行</s:else>
										</td>
										<td><s:if test="#theBean.TIMEMODE==0">立即执行</s:if> <s:else>按指定时间执行</s:else>
										</td>
										<td><s:property value="#theBean.EXECUTETIME" />
										</td>
										<td><s:if test="#theBean.ISCOPY==0">不备份</s:if> <s:else>备份</s:else>
										</td>
										<td><s:if test="#theBean.ISNEEDCHECK==0">不检测</s:if> <s:else>检测</s:else>
										</td>
										<td><s:if test="#theBean.ISRESTART==0">否</s:if> <s:else>是</s:else>
										</td>
									</tr>
								</s:iterator>
							 </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>