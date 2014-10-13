<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
     var host_code = '';
	   function goConfigHA(){
	   		theForm.action= "cluster_goConfigHA.do";
	   		theForm.submit();
	   }
	   function submitRequest(){
	   		var cluster_code = theForm.cluster_code.value;
	  		 var host_monitor = 0;
	  		 var host_num = 0;
	  		 var cpu = 0;
	  		 var mem = 0;
	  		 var hastate=0;
	  		 hastate = $("#hastate").attr("checked");
	  		 if(hastate=='checked'){
	  		 	hastate=1;
	  		 }
	   		 host_monitor = $("#host_monitor").attr("checked");
	   		if(host_monitor=='checked'){
	   			host_monitor=1;
	   		}
	   		var state = $('input[name=theForm.state]:checked').val();
	   		var admission = $("input[name='theForm.admission']:checked").val();
	   		if(admission==1){
	   			host_num = theForm.num.value;
	   		}else if(admission==2){
	   			cpu = theForm.cpu.value;
	   			mem = theForm.mem.value;
	   		}
	   		url= "cluster_configHA.do?host_monitor="+host_monitor+
	   		"&state="+state+"&admission="+admission+"&host_num="+host_num+"&cpu="+cpu+"&mem="+mem+"&cluster_code="+cluster_code+"&host_code="+host_code+"&hastate="+hastate;
	   			bar(cluster_code,"正在配置集群");
	   			$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == 1){
					barEnd(cluster_code,"配置集群成功");
				}else {
					barEnd(cluster_code,"配置集群失败");
				}
			});
	   		
	   }
	   
	   function changeAccess(){
	   
	   		var admission = $("input[name='theForm.admission']:checked").val();
	   		if(admission==1){
	   			theForm.num.disabled = "";
	   			theForm.cpu.disabled = "true";
				theForm.mem.disabled = "true";
				$("#host").attr("disabled",true);
	   		}else if(admission==2){
	   			theForm.cpu.disabled = "";
				theForm.mem.disabled = "";
	   			theForm.num.disabled = "true";
	   			$("#host").attr("disabled",true);
	   		}else{
	   			theForm.num.disabled = "true";
	   			theForm.cpu.disabled = "true";
				theForm.mem.disabled = "true";
				$("#host").attr("disabled",false);
	   		}
	   } 
	   
	   $(function(){
		 $("[name='theForm.host']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'选择主机',
    			width: '600px',
    			height: '380px',
    			max: true,
    		    min: true,
    			content: 'url:cluster_selectHost.do'
    			});
              });
          });
	  
	  
	function getHost(length,str){
    		$("#host").empty();
 			$("#host").append("已选择"+length+"个主机");
 			host_code = str;
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
<body class="pop-body scrollbody" onload="changeAccess();">
    <s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
        <div>
            <table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
           		 <tr>
                    <td class="til" width="15%" align="center">
                        选择集群
                    </td>
                    <td>
                       	<s:select list="theForm.resultList" listKey="entityId" listValue="name" name="theForm.cluster_code" id="cluster_code"></s:select>
                    </td>
                </tr>
                <tr>
					<td class="til" width="15%" align="center">
						启用HA
					</td>
					<td>
						<s:if test='theForm.hastate=="TRUE"'>
							<input type="checkbox" id="hastate"  name="theForm.hastate"  checked="checked"/>	
						</s:if>
						<s:else><input type="checkbox" id="hastate"  name="theForm.hastate" /></s:else>
						打开 Vsphere HA<br />
					&nbsp;&nbsp;&nbsp;&nbsp;vSphere HA功能用于检测故障,对集群中运行的虚拟机提供快速恢复功能。<br />
					&nbsp;&nbsp;&nbsp;&nbsp;核心功能包括主机监控和虚拟机监控功能,用于在检测不到信号时最大程度的缩短停机时间。<br />
					&nbsp;&nbsp;&nbsp;&nbsp;必须打开vSphere HA，才能使用Fault Tolerance。
					</td>
				</tr>
				<tr>
                <tr>
                    <td class="til" width="15%" align="center">
                        主机监控状态
                    </td>
                    <td>
                    	<s:if test='theForm.hostMonitoring=="TRUE"'>
							<input type="checkbox"  name="theForm.host_monitor" id="host_monitor"  checked="checked"/>	
						</s:if>
						<s:else>
                       	<input type="checkbox"  name="theForm.host_monitor" id="host_monitor"/> 
                       	</s:else>
                       	启用主机监控
                    </td>
                </tr>
                <tr>
                    <td class="til" align="center">
                      	  接入控制
                    </td>
                    <td>
                    	<s:if test='theForm.state=="true"'>
                        <input type="radio" value="1" name="theForm.state" checked="checked"/> 启用:不允许违反可用性限制的虚拟机电源打开操作<br />
                        <input type="radio" value="2" name="theForm.state" /> 禁用:允许违反可用性限制的虚拟机电源打开操作<br />
                        </s:if>
                        <s:else>
                        	<input type="radio" value="1" name="theForm.state"/> 启用:不允许违反可用性限制的虚拟机电源打开操作<br />
                        <input type="radio" value="2" name="theForm.state" checked="checked"/> 禁用:允许违反可用性限制的虚拟机电源打开操作<br />
                        </s:else>
                    </td>
                </tr>
				<tr>
					<td class="til" align="center">
						接入控制策略：
					</td>
					<td>
						<s:if test="theForm.admission==1">
						<input type="radio" value="1" name="theForm.admission" onclick="changeAccess();" checked="checked"/> 群集允许的主机故障数目：<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" name="theForm.num" id="num" cssStyle="width:100px;   height:20px;"/><br /><br />
						<input type="radio" value="2" name="theForm.admission"  onclick="changeAccess();"/> 作为故障切换空间保留的&nbsp;&nbsp;&nbsp;<s:textfield name="theForm.cpu" id="cpu" cssStyle="width:100px;   height:15px;"/>  %  CPU<br />
						&nbsp;&nbsp;&nbsp;&nbsp; 群集资源的百分比：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="theForm.mem" id="mem" cssStyle="width:100px;   height:15px;"/>  %  内存<br /><br />
						<input type="radio" value="3" name="theForm.admission" onclick="changeAccess();" /> 指定故障切换主机:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:selectHost();" id="host" name="theForm.host" > 单击进行编辑主机</a>
						</s:if>
						<s:elseif test="theForm.admission==2">
						<input type="radio" value="1" name="theForm.admission" onclick="changeAccess();" /> 群集允许的主机故障数目：<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" name="theForm.num" id="num" cssStyle="width:100px;   height:20px;"/><br /><br />
						<input type="radio" value="2" name="theForm.admission"  onclick="changeAccess();" checked="checked"/> 作为故障切换空间保留的&nbsp;&nbsp;&nbsp;<s:textfield name="theForm.cpu" id="cpu" cssStyle="width:100px;   height:15px;"/>  %  CPU<br />
						&nbsp;&nbsp;&nbsp;&nbsp; 群集资源的百分比：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="theForm.mem" id="mem" cssStyle="width:100px;   height:15px;"/>  %  内存<br /><br />
						<input type="radio" value="3" name="theForm.admission" onclick="changeAccess();" /> 指定故障切换主机:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:selectHost();" id="host" name="theForm.host" > 单击进行编辑主机</a>
						</s:elseif>
						<s:else>
						<input type="radio" value="1" name="theForm.admission" onclick="changeAccess();" /> 群集允许的主机故障数目：<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" name="theForm.num" id="num" cssStyle="width:100px;   height:20px;"/><br /><br />
						<input type="radio" value="2" name="theForm.admission"  onclick="changeAccess();"/> 作为故障切换空间保留的&nbsp;&nbsp;&nbsp;<s:textfield name="theForm.cpu" id="cpu" cssStyle="width:100px;   height:15px;"/>  %  CPU<br />
						&nbsp;&nbsp;&nbsp;&nbsp; 群集资源的百分比：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="theForm.mem" id="mem" cssStyle="width:100px;   height:15px;"/>  %  内存<br /><br />
						<input type="radio" value="3" name="theForm.admission" onclick="changeAccess();" checked="checked"/> 指定故障切换主机:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:selectHost();" id="host" name="theForm.host" > 单击进行编辑主机</a>
						</s:else>
					</td>
				</tr>
				<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest();" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
            </table>
        </div>
    </s:form>
</body>