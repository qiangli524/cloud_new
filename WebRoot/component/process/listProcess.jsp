<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<script type="text/javascript">
	$(function(){
	    //使用者根据用户类型自己指定参数
	    var userType = $("#userType").val();
	    var parentProcessIds = '<%=request.getAttribute("parentProcessIds")%>';
		//根据用户类型来初始化页面的展现方式
		if("processParent"==userType){//添加父进程的时候调用
			$("[name='operbnt']").hide();//启动和停止，编辑等设置为隐藏
			$("[name='oper_process']").hide();
    	    if(parentProcessIds!=null&&parentProcessIds!=""){
    	        var array=parentProcessIds.split(",");
    	        for(var i=0;i<array.length;i++){
    	            $("[value='"+array[i]+"']").attr("checked",true);
    	        }
    	    }
    	    $("[name='fenye']").hide();
		} 
	 	$("#search").click(function(){
	 	   $("#theForm").submit();
	 	});
	
		$("#resert").click(function(){
		   $("input[type='text']").val("");
		   $("select").each(function(){
		      $(this).children("option:eq(0)").attr("selected",true);
		   });
		});
		
		$(function(){
			$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
	      $("[name='all']").click(function(){
	   	 	var currentState=$(this).attr("checked");
	 	 	if(currentState){
	 	 		$("[name='checkboxid']").attr("checked",true);
	 	 	}else{
	 	 		$("[name='checkboxid']").attr("checked",false);
	      	}
	
	       });
		});
	
        $("[name='addprocess']").click(function(){
				$.dialog({
	    			id:'add',
	    			title:'添加新进程',
	    			width: '520px',
	    			height: '450px',
	    		    lock:true,
	    			content: 'url:process_addNewProcess.do?oper=add'
		    	});
        });
        
        $("[name='editProcess']").click(function(){
            var $td=$(this).parent();
            var processId=$td.siblings("[processid]").attr("processid");
		  		$.dialog({
	    			id:'add',
	    			title:'编辑进程',
	    			width: '510px',
	    			height: '450px',
	    			lock:true,
	    			content: 'url:process_addNewProcess.do?oper=edit&processid='+processId
		    	});
        });
        
        $("[name='delProcess']").unbind().live("click",function(){
        	if (confirm("确定要删除这个进程吗?") == true){
		           var $td=$(this).parent();
		           var processId=$td.siblings("[processid]").attr("processid");
		           $.ajax({
		            type: "POST",
		            url: "process_delProcess.do",
		            dataType: "json",
		            data:{"processId":processId},
		            success : function(data){
		              if(data.result==0){
		                 $("#theForm").submit();
		              }else{
		                 alert("删除失败!")
		              }
		              }
		          });
        	}
        });
        
        $("[name='operprocess']").unbind().live("click",function(){
        	if (confirm("确定要进行这个操作吗?") == true){
	        	var processIds="";
	        	if($(":checkbox:checked").length==0){
	        		alert("你好,请至少选择一项来进行操作.");
	        		return;
	        	}
	    		$(":checkbox:checked").each(function(index,data){
	    			processIds+=$(this).attr("scriptid")+",";
	    		});
	    		var oper=$(this).attr("oper");
	    		mask('正在发送请求,请稍后....','0.5','0px');
	            $.ajax({
		             type: "POST",
		             url: "process_operProcess.do",
		             dataType: "json",
		             data:{"processIds":processIds,"oper":oper},
		             success : function(data){
			           removeMask();
		               if(data.result==0){
		                  $("#theForm").submit();
		               }else if(data.result==1){
		                  alert("启动进程已经在运行中,不能重复启动.");
		               }else if(data.result==2){
		                  alert("启动进程已经停止运行,不能重复停止.");
		               }else if(data.result==3){
		                  alert("启动进程正在启动中,不能重复启动.");
		               }else if(data.result==4){
		                  alert("启动进程正在停止运行中,不能重复停止.");
		               }else{
		            	   alert("启动失败.");
		               }
	               	 }
	           });
        	}
         });   
        
        
        $("[name='refresh']").click(function(){
        	$("#theForm").submit();
        });
	});
       
      function saveProcess(theForm,userids,userips,oper){
       	 $.ajax({
            type: "GET",
            url: "process_saveProcess.do?"+theForm+"&userids="+userids+"&userips="+userips+"&oper="+oper,
            dataType: "json",
            success : function(data){
                 $("#theForm").submit();
              }
          });
       }
      function saveParentProcess(theForm,oper){
        $.ajax({
             type: "GET",
             url: "process_saveParentProcess.do?"+theForm+"&oper="+oper,
             dataType: "json",
             success : function(data){
                  $("#theForm").submit();
               }
           });
        }
      
      //添加父进程为子进程选择
      function addParentProcess(){
  		var parentProcessIds="";
  		var userid="";
		$(":checkbox:checked").each(function(index,data){
			parentProcessIds+=$(this).val()+",";
			userid+=$(this).attr("userid")+",";
		});
	    return parentProcessIds+"~"+userid;
      }
	
</script>
</head>
<body  class="pop-body scrollbody">
<s:form action="process_listAllProcess" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="userType" id="userType"></s:hidden>
<div class="mainbody">
		<s:form action="ip_listIpInfo.do" method="post" cssStyle="theForm"
			id="theForm">
			<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
			<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>
			<s:hidden name="theForm.IPADDRESS" id="IPADDRESS"></s:hidden>

			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">进程管理</h2>
				<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="vm">进程名称：</label>
							 <s:textfield name="PROCESS" ></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">进程标识：</label>
							<s:textfield name="PROCESS_KEY"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">所属主机：</label>
							<s:textfield name="HOST_IP"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">启停状态：</label>
							  <s:select list="#{'':'请选择','4':'-检测中-','1':'-已停止-','0':'-运行良好-','5':'-部分运行-','2':'-正在启动-','3':'-正在停止-'}" name="operation" id="operation"></s:select>
						</div>
						
						<div class="filtrate-field">
							<span class="ubtn-1 mgl-20"><input type="button"
								id = "search" value="查询" />
							</span> <span class="ubtn-2 mgl-20"><input type="button"
								value="重置" id="resert" />
							</span>
						</div>
					</div>
				</div>
				<div class="utt-2 mgt-20">

						<a class="icon-add" href="javascript:void(0)"
							value="增加"  name="addprocess" >增加</a>
						<a class="icon-occupy" href="javascript:void(0)"
							 value="启动" oper="start" name="operprocess">启动</a> 
						<a class="icon-del" href="javascript:void(0)" 
							value="停止" oper="stop" name="operprocess">停止</a>
						<a class="icon-del" href="javascript:void(0)" 
							value="刷新" oper="refresh" name="refresh">刷新</a>
							
					</div>
					<table id="theTable" width="100%" class="blue-table sorttable imgTableCenter"
						border="0" cellspacing="0">
						<thead>
							<tr>
<%--									  <th name="change">--%>
	<%--							节点--%>
	<%--						</th>--%>
							<th name="change">
								选择
							</th>
							<th onclick="sort(theTable,2,'string')">
								进程名
							</th>
							<th onclick="sort(theTable,3,'string')">
								进程标识		
							</th>
							<th onclick="sort(theTable,4,'int')">
								配置个数
							</th>
							<th onclick="sort(theTable,5,'int')">
								实际个数
							</th>
							<th onclick="sort(theTable,6,'string')">
								主机
							</th>
							<th name="parent-status" onclick="sort(theTable,7,'string')">
								运行状态
							</th>
							<th name="parent-status" onclick="sort(theTable,8,'string')">
								启停状态
							</th>
							<th onclick="sort(theTable,9,'string')">
								启动脚本
							</th>
							<th onclick="sort(theTable,10,'string')">
								停止脚本
							</th>
	<%--						<th>--%>
	<%--							描述--%>
	<%--						</th>--%>
	<%--						<th>--%>
	<%--							启动时间--%>
	<%--						</th>--%>
	<%--						<th>--%>
	<%--							停止时间--%>
	<%--						</th>--%>
	<%--						<th>--%>
	<%--							更新时间--%>
	<%--						</th>--%>
							<th name="oper_process">
							     操作
							</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean" status="l">
								<s:if test="#theBean.PARENT_ID==0">
		                  <tr class="expanded" align="center" data-tt-id='<s:property value="#theBean.ID"/>'>
						</s:if>
						<s:else>
						  <tr class="expanded" align="center" data-tt-id='<s:property value="#theBean.ID"/>' data-tt-parent-id='<s:property value="#theBean.PARENT_ID"/>'>
						</s:else>
<%--						<td>--%>
<%--						</td>--%>
						<td processid='<s:property value="#theBean.ID"/>' name="change">
							<input type="checkbox" value='<s:property value="#theBean.ID"/>' scriptid='<s:property value="#theBean.ID"/>' userid='<s:property value="#theBean.USER_ID"/>'/>
						</td>
					    <td processid='<s:property value="#theBean.ID"/>' align="center">
							<s:property value="#theBean.PROCESS" default="无"/>
						</td>
						<td align="center">
							<s:property value="#theBean.PROCESS_KEY" default="无"/>
						</td>
						<td align="center">
							<s:property value="#theBean.PROCESS_COUNT" default="无"/>
						</td>
						<td align="center">
						    <s:if test="#theBean.PROCESS_COUNT_ACTUAL==-1">
									<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />检测中
							</s:if>
							<s:else>
							      <s:property value="#theBean.PROCESS_COUNT_ACTUAL" default="1"/> 
							</s:else>
						</td>
						<td align="center">
						    <s:property value="#theBean.IP" default="-"/>/<s:property value="#theBean.USERNAME" default="-"/>
						</td>
						<td name="parent-status" align="center">
					        <s:if test="#theBean.PROCESS_STATE==1"><span style="color: red">异常停止<span/></s:if>
						    <s:elseif test="#theBean.PROCESS_STATE==2"><span style="color: blue">运行良好<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==0">无</s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==3"><span style="color: red">启动失败<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==4"><span style="color: red">停止失败<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==5"><span style="color: red">非通过平台启动<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==6"><span style="color: red">实际个数与配置个数不符<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==7"><span style="color: red">服务器无法ping通<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==8"><span style="color: red">服务器登录失败<span/></s:elseif>
						</td>
						<td name="parent-status" align="center">
							<span id="div<s:property value="#theBean.ID"/>" name="state">
							    <s:if test="#theBean.OPERATION==1">
							        <s:if test="#theBean.TAST_TYPE==0">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1"/>正在启动</s:if>
									<s:if test="#theBean.TAST_TYPE==1">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />正在启动</s:if>
								</s:if>
								<s:elseif test="#theBean.OPERATION==0">
								    <s:if test="#theBean.TAST_TYPE==0">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1"/>正在停止</s:if>
									<s:if test="#theBean.TAST_TYPE==1">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />正在停止</s:if>
								</s:elseif>
								<s:elseif test="#theBean.OPERATION==2">
								  <s:if test="#theBean.ISRUNNING==0">
								    <s:if test="#theBean.TAST_TYPE==2"><span state="2">已停止</span></s:if>
								  </s:if>
								  <s:elseif test="#theBean.ISRUNNING==1">
								    <s:if test="#theBean.TAST_TYPE==2"><span state="2">运行中</span></s:if>
								  </s:elseif>
								  <s:elseif test="#theBean.ISRUNNING==2">
								    <s:if test="#theBean.TAST_TYPE==2"><span state="4">部分启动</span></s:if>
								  </s:elseif>
								  <s:elseif test="#theBean.ISRUNNING==3">
								    <s:if test="#theBean.TAST_TYPE==2">
								      <img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />检测中
								    </s:if>
								  </s:elseif>
								</s:elseif>
							</span>
						</td>
						<td align="center">
               			   <div class="hidden" title='<s:property value="#theBean.START_SCRIPT" default="-"/>'>
               				  <s:property value="#theBean.START_SCRIPT" default="-"/>
               			   </div>
						</td>
						<td align="center">
               			   <div class="hidden" title='<s:property value="#theBean.STOP_SCRIPT" default="-"/>'>
               				  <s:property value="#theBean.STOP_SCRIPT" default="-"/>
               			   </div>
						</td>
<%--						<td align="center">--%>
<%--						   <div class="hidden" title='<s:property value="#theBean.PROCESS_DESC" default="-"/>'>--%>
<%--               				  <s:property value="#theBean.PROCESS_DESC" default="-"/>--%>
<%--               			   </div>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:property value="#theBean.START_TIME" default="-"/>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:property value="#theBean.STOP_TIME" default="-"/>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:property value="#theBean.UPDATE_TIME" default="-"/>--%>
<%--						</td>--%>
                        <td name="oper_process">
							<a href="javascript:;" name="editProcess">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" name="delProcess">删除</a>
						</td>
					</tr>
							</s:iterator>
						</tbody>
					</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
				</div>
		</s:form>
	</div>
</s:form>
</body>
