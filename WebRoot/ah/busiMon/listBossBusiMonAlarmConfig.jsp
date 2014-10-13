<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	//查询
	function searchRequest() {
		obj.submit();
	}
	//重置
	function resetForm(){
		$("#KPI_ID").attr("value","");
		$("#ALARM_LEVEL").attr("value","");
		$("#ALARM_TYPE").attr("value","");
		$("#STATE").attr("value","");
	}
	//添加
   function addRequest() {
	    $.dialog({
			id:'add',
			title:'添加指标',
			height:'550px',
			width:'900px',
			lock:true,
			content:'url:ah/busiMon/editBossBusiMonAlarmConfig.jsp'
		});
	}
	//修改
   function modRequest(){
	    var lenth = 0;
	    var id = '';
	    $('[name=checkboxId]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
	    if(id==null || id == ''){
	    	alert("请勾选一条信息！");
	 	    return false ;
	    }else if(lenth>1){
	   		alert("一次只能修改一条信息");
	    return false ;
	    }
	   $.dialog({
			id:'add',
			title:'修改信息',
			height:'550px',
			width:'900px',
			lock:true,
			content:'url:bossBusiMonAlarmAction_editBossBusiMonAlarmCfg.do?tbBossBusiMonAlarmCfg.ID='+id
		});
   }
	//删除,支持批量
   function delRequest(){
		var ids='';
		var lenth=0;
		$('[name=checkboxId]:checkbox:checked').each(function(){
	   		ids =ids+$(this).val()+",";
	   		lenth +=1;
	   	 });
	   		if(ids == null || ids ==""){
	   			alert("请勾选一条信息！");
				return false;
			}
	   		$.dialog.confirm('你确定要删除吗？', function(){
				var url = "bossBusiMonAlarmAction_delete.do?tbBossBusiMonAlarmCfg.ID="+ids;
				$.ajax({
			  		type:"POST",
	          		url:url,
	          		cache:false,
	          		success: function(msg){
	          			searchRequest();	
	          		}
				});
	   		});
	}
	
	//业务监控开启
	function openBossBusiMon(id){
	   		$.dialog.confirm('你确定要开启boss业务报警吗？', function(){
				var url = "bossBusiMonAlarmAction_openOrCloseBossBusiMon.do?tbBossBusiMonAlarmCfg.STATE=0&&tbBossBusiMonAlarmCfg.ID=" + id;
				$.ajax({
			  		type:"POST",
	          		url:url,
	          		cache:false,
	          		success: function(msg){
	          			searchRequest();	
	          		}
				});
	   		});
	}
	
	//业务监控关闭
	function closeBossBusiMon(id){
	   		$.dialog.confirm('你确定要关闭boss业务报警吗？', function(){
				var url = "bossBusiMonAlarmAction_openOrCloseBossBusiMon.do?tbBossBusiMonAlarmCfg.STATE=1&&tbBossBusiMonAlarmCfg.ID=" + id;
				$.ajax({
			  		type:"POST",
	          		url:url,
	          		cache:false,
	          		success: function(msg){
	          			searchRequest();	
	          		}
				});
	   		});
	}
	
	/** 添加或修改后调用该页面的保存方法  **/
	function saveAlarmThreshold(obj,id){
		if(id == ""){
			$.ajax({
            type: "GET",
            url: "bossBusiMonAlarmAction_checkAlarmCfgIsExist.do?"+obj,
            dataType: "text",
			cache:false,
            success : function(data){
				var flag = data;
				if(flag == "true"){
					$.ajax({
		            type: "GET",
		            url: "bossBusiMonAlarmAction_saveBossBusiMonAlarmCfg.do?"+obj,
		            dataType: "json",
					cache:false,
		            success : function(data){
			           $("#obj").submit();
		            }
		          });
				}else{
					alert("该业务监控报警配置已经存在");
				}
				
            }
          });
		}else{
			$.ajax({
	            type: "GET",
	            url: "bossBusiMonAlarmAction_saveBossBusiMonAlarmCfg.do?"+obj,
	            dataType: "json",
				cache:false,
	            success : function(data){
		           $("#obj").submit();
	            }
	          });
		}
		
	}
	
	function selAll(a){
		if(a.checked){
			$("input[name='checkboxId']").each(function(){
			$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkboxId']").each(function(){
			$(this).attr("checked",false);
			});
		}
	}
	
</script>

<style type="text/css">
	.divmatnrdesc{
		width: 100px;
		height:100%;
		overflow: hidden;
		white-space: nowrap;
		text-overflow:ellipsis;
		}
		
</style>
</head>
 <body>
  
   <s:form action="bossBusiMonAlarmAction_listBossBusiMonAlarmCfg.do" id="obj" method="post">
		<s:hidden name="theForm.ROOM_ID" id="ROOM_ID"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">boss业务告警配置管理</h2>
	        	<div class="bord-1 pd-10">
					<table width="100%" border="0">
						<tr>
							<td class="til">指标名称:</td>
		                    <td align="left">
		                    	<s:select list="#{'':'--请选择--',
		                   						'PM-01-01-001-01':'话务量监控指标',
		                   						'PM-01-01-001-02':'目录文件积压量指标',
		                   						'PM-01-01-001-03':'错误日志监控指标',
		                   						'PM-01-01-001-04':'流量查询服务监控指标',
		                   						'PM-01-01-001-05':'端口收发监控指标',
		                   						'PM-01-01-001-06':'提醒服务监控指标'
		                   						}"
		                   		 name="tbBossBusiMonAlarmCfg.KPI_ID" id="KPI_ID"></s:select> 
		                    </td>
		                   	<td class="til">是否启用:</td>
		                   	<td align="left">
								<s:select list="#{'':'--请选择--','0':'是','1':'否'}" name="tbBossBusiMonAlarmCfg.STATE" id="STATE"></s:select>            
		                   	</td>
							
							
							
							<td colspan="10" class="pd-10">
								<div align="center">
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" onclick = "javascript:searchRequest()" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" onclick="javascript:resetForm(document.getElementById('theForm'))" /></span>
								</div>
							</td>
						</tr>
					</table>
					<div class="utt-2 mgt-10">
						<a class="icon-add" href="javascript:void(0)" onclick = "addRequest();return false;">新增</a>
						<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;">修改</a>
						<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
						<a class="icon-release" href="javascript:void(0)" onclick="openBossBusiMon('');return false;" >告警开启</a>
						<a class="icon-modify" href="javascript:void(0)" onclick="closeBossBusiMon('');return false;" >告警关闭</a>
					</div>	
					
					
					<div style="overflow-x: scroll">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										指标ID
									</th>
									<th onclick="sort(theTable,2,'string')">
										是否配置到子实体
									</th>
									<th onclick="sort(theTable,3,'string')">
										子实体值
									</th>
									<th onclick="sort(theTable,6,'string')">
										严重告警判断表达式
									</th>
									<th onclick="sort(theTable,7,'string')">
										严重告警信息表达式
									</th>
									<th onclick="sort(theTable,8,'string')">
										一般告警判断表达式
									</th>
									<th onclick="sort(theTable,9,'string')">
										一般告警信息表达式
									</th>
									<th onclick="sort(theTable,4,'string')">
										不告警开始时间
									</th>
									<th onclick="sort(theTable,5,'string')">
										不告警结束时间
									</th>
									<th onclick="sort(theTable,10,'string')">
										是否启用
									</th>
									<th onclick="sort(theTable,10,'string')">
										操作
									</th>
								</tr>
							</thead>
							
							<tbody>
								<s:iterator value="resultList" id="theBean">
							  	<tr>
							  		<td><input name="checkboxId" id="id" type="checkbox" value="<s:property value='#theBean.ID'/>"/></td>
							  		<td >
							  		
							  			<s:if test="#theBean.KPI_ID == 'PM-01-01-001-01'">
								  			话务量监控指标
								  		</s:if>
								  		<s:elseif test="#theBean.KPI_ID == 'PM-01-01-001-02'">
											目录文件积压量指标
					  					</s:elseif>
					  					<s:elseif test="#theBean.KPI_ID == 'PM-01-01-001-03'">
											错误日志监控指标
					  					</s:elseif>
					  					<s:elseif test="#theBean.KPI_ID == 'PM-01-01-001-04'">
											流量查询服务监控指标
					  					</s:elseif>
					  					<s:elseif test="#theBean.KPI_ID == 'PM-01-01-001-05'">
											端口收发监控指标
					  					</s:elseif>
					  					<s:elseif test="#theBean.KPI_ID == 'PM-01-01-001-06'">
											提醒服务指标
					  					</s:elseif>
					  					<s:else>
								  			<s:property value="#theBean.KPI_ID"/>
								  		</s:else>
							  			
							  		</td>
							  		<td >
								  		<s:if test="#theBean.FLAG == 0">
								  			是
								  		</s:if>
								  		<s:elseif test="#theBean.FLAG == 1">
											否
					  					</s:elseif>
					  					<s:else>
								  			<s:property value="#theBean.FLAG"/>
								  		</s:else>
							  		</td>
							  		<td ><s:property value="#theBean.SUB_ENTITY"/></td>
							  		<td width="10px;"><s:property value="#theBean.SERIOUS_ALARM_EXPRESSION"/></td>
							  		<td width="10px;"><s:property value="#theBean.SERIOUS_ALARM_INFO_EXPRESSION"/></td>
							  		<td width="10px;"><s:property value="#theBean.COMMON_ALARM_EXPRESSION"/></td>
							  		<td width="10px;"><s:property value="#theBean.COMMON_ALARM_INFO_EXPRESSION"/></td>
							  		<td width="10px;"><s:property value="#theBean.START_TIME"/></td>
							  		<td width="10px;"><s:property value="#theBean.END_TIME"/></td>
							  		<td >
							  			<s:if test="#theBean.STATE == 0">
							  				<font color="green">启用</font>
							  			</s:if>
							  			<s:elseif test="#theBean.STATE == 1">
											<font color="red">停用</font>
				  						</s:elseif>
				  						<s:else>
							  				<s:property value="#theBean.STATE"/>
							  			</s:else>
							  		</td>
							  		<td >
							  			<a class="" href="javascript:void(0)" onclick="openBossBusiMon('<s:property value='#theBean.ID'/>');return false;" >开启</a>
										<a class="" href="javascript:void(0)" onclick="closeBossBusiMon('<s:property value='#theBean.ID'/>');return false;" >关闭</a>
							  		</td>
							  	</tr>
							  </s:iterator>
							</tbody>
						</table>
						</div>
						
						<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
						</div>
					</div>
				</div>
	</s:form>
   
 </body>