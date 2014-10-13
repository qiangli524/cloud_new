<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../sxcloud/common/taglib.jsp" %>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link href="<%=path%>/home/ui/nresources/css/framework.css" rel="stylesheet" />
<link href="<%=path%>/home/ui/nresources/css/home.css" rel="stylesheet" />
</head>
<body>
    <div class="mainbody" style="margin-top:5px;background-color: #FFFFFF">
    	<div class="side-panel" style="padding: 0px 6px 6px 6px">
    		<s:iterator value="leftPageSettings"  id="beans" status="st">
    		<s:if test="#beans.width==2">
    			<s:if test="#beans.url=='getAlarmHostStatistics'">
    				<s:hidden id="warn_flag" value="1"></s:hidden>
		            <h3 class="tt-1"><span class="txt bell-txt">当前告警</span></h3>
		            <ul class="list-warn bgcolor-1 pd-8 bord-nt">
		            	<li class="warn-1" id="serious" name="serious">
		                	<a href="#none">
		                    	<span class="txt">严重告警</span>
		                        <span class="num">0</span>
		                    </a>
		                </li>
		                <li class="warn-2" id="serious1" name="serious1">
		                	<a href="#none">
		                    	<span class="txt">重要告警</span>
		                        <span class="num">0</span>
		                    </a>
		                </li>
		                <li class="warn-3" id="serious2" name="serious2">
		                	<a href="#none">
		                    	<span class="txt">一般告警</span>
		                        <span class="num">0</span>
		                    </a>
		                </li>
		            </ul>
	            </s:if>
	            <s:if test="#beans.url=='getMyTaskCount'">
	            	<s:hidden id="task_flag" value="1"></s:hidden>
		            <h3 class="tt-1 mgt-5"><span class="txt backlog-txt">我的工单</span></h3>
		            <ul class="list-task bgcolor-1 pd-8 bord-nt">
		            	<li class="task-1" id="waitCount" name="waitCount">
		                	<a href="<%=path%>/workorder/workorder_listWorkOrder.do?workOrderObj.WSTAT=0">
		                    	<span class="txt">待处理</span>
		                        <span class="num">0</span>
		                    </a>
		                </li>
		                <li class="task-2" id="alreadyCount" name="alreadyCount">
		                	<a href="<%=path%>/workorder/workorder_listWorkOrder.do?workOrderObj.WSTAT=1">
		                    	<span class="txt">已处理</span>
		                        <span class="num">0</span>
		                    </a>
		                </li>
		                <li class="task-3" id="allCount" name="allCount">
		                	<a href="<%=path%>/workorder/workorder_listWorkOrder.do">
		                    	<span class="txt">全部</span>
		                        <span class="num">0</span>
		                    </a>
		                </li>
					</ul>
				</s:if>
			</s:if>
			</s:iterator>
        </div>
        <div class="main-panel">
            <div class="workpanel">
<%--            	<div class="clearfix">--%>
<%--            		<div class="box-fn fl">--%>
<%--            			<div class="tt-2">资源总体情况</div>--%>
<%--            			<div class="boxfn-cont">--%>
<%--            				<iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<%=path%>/sxcloud/demo.png"></iframe>--%>
<%--            			</div>--%>
<%--            		</div>--%>
<%--            		<div class="box-fn fr">--%>
<%--            			<div class="tt-2">业务资源占用</div>--%>
<%--            			<div class="boxfn-cont">--%>
<%--            				<iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<%=path%>/resourceOutline_goToBusiTopN.do"></iframe>--%>
<%--            			</div>--%>
<%--            		</div>--%>
<%--            	</div>--%>
<%--            	<div class="clearfix mgt-20 mgb-20">--%>
<%--            			<div class="tt-2">历史告警趋势</div>--%>
<%--            			<div class="boxfn-cont">--%>
<%--            				<iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<%=path%>/homepagereport_historyAlarmTrend.do"></iframe>--%>
<%--            			</div>--%>
<%--            	</div>--%>
            	<s:iterator value="homePageSettings"  id="beans" status="st">
            	<s:property value="#st.width" />
            			<s:if test="#st.index%2==0" >
            				<s:if test="#st.index==0">
	            				<div class="clearfix">
	            			</s:if>
	            			<s:if test="#st.index!=0">
	            				<div class="clearfix mgt-20">
	            			</s:if>
	            			<s:if test="#beans.width==0">
	            				<div class="box-fn fl">
		            				<div class="tt-2"><s:property value="#beans.title" /></div>
			                        <div class="boxfn-cont">
			                            <iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<%=path%><s:property value='#beans.url' />"></iframe>
			                        </div>
			                    </div>
	            			</s:if>
	            			<s:if test="#beans.width==1">
	            				<div class="tt-2"><s:property value="#beans.title" /></div>
		                        <div class="boxfn-cont">
		                            <iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<%=path%><s:property value='#beans.url' />"></iframe>
		                        </div>
	            			</s:if>
            			</s:if>
            			<s:if test="#st.index%2!=0" >
	            			<div class="box-fn fr">
		                        <div class="tt-2"><s:property value="#beans.title" /></div>
		                        <div class="boxfn-cont">
		                            <iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<%=path%><s:property value='#beans.url' />"></iframe>
		                        </div>
		                    </div>
		                    </div>
            			</s:if>
                	</s:iterator>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="<%=path%>/home/ui/njs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/home/ui/njs/ui/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
    $(window).load(function() {
		var ifm= parent.document.getElementById("mainIframe"); 
		var subWeb = parent.document.frames ? parent.document.frames["mainIframe"].document : ifm.contentDocument; 
		if(ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight; 
		} 
     });
    
    
	$(document).ready(function(){
		if($("#warn_flag").val()=="1"){
	  		//加载告警数量
			$.ajax({
				type: "POST",
				cache: "false",
				async: true,
				dataType: "text",
				url: "<%=request.getContextPath()%>/home_getAlarmHostStatistics.do",
				success: function(data) {
				   var data = eval(data);   
					$(data).each(function(i,n){
						if(n.level==0){
							$('#serious .num').html(n.levelcount);
						}else if(n.level==1){
							$('#serious1 .num').html(n.levelcount);
						}else if(n.level==2){
							$('#serious2 .num').html(n.levelcount);
						}
					});
				}
			  });
			}
		if($("#task_flag").val()=="1"){
			//加载代办工单数量
			$.ajax({
				type: "POST",
				cache: "false",
				async: true,
				dataType: "text",
				url: "<%=request.getContextPath()%>/home_getMyTaskCount.do",
				success: function(data) {
				var data = eval(data); 
				$(data).each(function(i,n){
					if(i==0){
						$('#waitCount .num').html(n);
					}else if(i==1){
						$('#alreadyCount .num').html(n);
					}else if(i==2){
						$('#allCount .num').html(n);
					}
				});
					//alert(data.waitCount);
					//$('#waitCount .num').html(data.waitCount);
					//$('#alreadyCount .num').html(data.alreadyCount);
					//$('#allCount .num').html(data.allCount);
				}
			});
		}
		 <%-- //加载待办工单
		 $.ajax({
			type: "POST",
			cache: "false",
			async: true,
			dataType: "text",
			url: "<%=request.getContextPath()%>/home_getMyTaskCount.do",
			success: function(data) {
			   var data = eval(data);   
				$(data).each(function(i,n){
					var html = '<li><a href="<%=path%>/workorder/workorder_listWorkOrder.do?workOrderObj.BOMC_UUID='+n.BOMC_UUID+'">(工单)'+n.WORK_ORDER_TITLE+'</a></li>';
					$('#mywarit').append(html);	
				});
			}
		  }); --%>
		  
	});
	    $("[name='serious']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'严重告警',
    			lock:true,
    			width: '1150px',
    			height: '500px',
    			max: true,
    		    min: true,
    			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=0'
    			});
              });
            $("[name='serious1']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'重要告警',
        			lock:true,
        			width: '1150px',
        			height: '500px',
        			max: true,
        		    min: true,
        			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=1'
        			});
                  });
            $("[name='serious2']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'一般告警',
        			lock:true,
        			width: '1150px',
        			height: '500px',
        			max: true,
        		    min: true,
        			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=2'
        			});
                  });
</script>
</body>
</html>
