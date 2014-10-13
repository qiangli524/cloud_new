<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
    	function submitRequest(){
    		var priority=0;
    		var level = $('input[name=theForm.level]:checked').val();
    		if(level==3){
    			 priority = theForm.priority.value;
    		}
    		 var drsstate=0;
	  		 drsstate = $("#drsstate").attr("checked");
	  		 if(drsstate=='checked'){
	  		 	drsstate=1;
	  		 }
    		var cluster_code = theForm.cluster_code.value;
    		url= "cluster_configDRS.do?level="+level+"&priority="+priority+"&cluster_code="+cluster_code+"&drsstate="+drsstate;
	   			bar(level,"正在配置集群");
	   			$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == 1){
					barEnd(level,"配置集群成功");
				}else {
					barEnd(level,"配置集群失败");
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
<body class="pop-body scrollbody">
    <s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
        <div>
            <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
            	 <tr>
                    <td class="til" width="15%" align="center">
                        选择集群
                    </td>
                    <td>
                       	<s:select list="theForm.resultList" listKey="entityId" listValue="name" name="theForm.cluster_code" id="cluster_code"></s:select>
                    </td>
                </tr>
                <tr>
					<td class="til" align="center">
							打开DRS
					</td>
					<td>
						<s:if test='theForm.drsstate=="TRUE"'>
						<input type="checkbox" id="drsstate" checked="checked" name="theForm.drsstate"/>
						</s:if>
						<s:else>
							<input type="checkbox" id="drsstate"   name="theForm.drsstate"/>
						</s:else>
							打开 Vsphere DRS<br />
						&nbsp;&nbsp;&nbsp;&nbsp;vSphere DRS 使vCenter Server能够将主机作为资源的聚合池进行管理。群集资源可以根据用户、组和虚拟机划分为更小的资源池。<br />
						&nbsp;&nbsp;&nbsp;&nbsp;vSphere DRS还使vCenter Server能够自动管理虚拟机到主机的分配，<br />
						&nbsp;&nbsp;&nbsp;&nbsp;在虚拟机打开电源时给出放置位置建议，以及为平衡负载和强制执行资源分配策略而对运行的虚拟机进行迁移。<br />
						&nbsp;&nbsp;&nbsp;&nbsp;应当在集群中启用vSphere DRS和VMvare EVC，以允许在负载平衡期间放置和迁移已打开Fault Tolerance的虚拟机。
						
					</td>
				</tr>
                <tr>
                    <td class="til" width="15%" align="center">
                        自动化级别
                    </td>
                    <td>
                    	<s:if test='theForm.level=="manual"'>
                    		 <input type="radio" value="1" name="theForm.level" checked="checked"/><label> 手动</label><br />
                        	    &nbsp;&nbsp;&nbsp;&nbsp; <label>vCenter会给出虚拟机的迁移建议。</label><br /><br />
                        <input type="radio" value="2" name="theForm.level"/><label> 半自动</label><br />
                        		&nbsp;&nbsp;&nbsp;&nbsp;<label>打开电源后，虚拟机将自动置于主机上，并且vCenter将自动从一个主机迁移到另一个主机，以优化资源使用情况。</label><br /><br />
                        <input type="radio" value="3" name="theForm.level"/><label> 全自动</label><br />
                        		&nbsp;&nbsp;&nbsp;&nbsp;<label>打开电源后，虚拟机将自动置于主机上，并且将自动从一个主机迁移到另一个主机，以优化资源使用情况。</label><br />
                         &nbsp;&nbsp;&nbsp;&nbsp;迁移阈值：&nbsp;&nbsp;&nbsp;&nbsp;<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" name="theForm.priority" id="priority"></s:select>从1至5是保守至激进的渐变<br />
                         		&nbsp;&nbsp;&nbsp;&nbsp;<label>应用优先级为1、2和3的建议</label><br />
                    	</s:if>
                       <s:elseif test='theForm.level=="partiallyAutomated"'>
                       		 <input type="radio" value="1" name="theForm.level" /><label> 手动</label><br />
                        	    &nbsp;&nbsp;&nbsp;&nbsp; <label>vCenter会给出虚拟机的迁移建议。</label><br /><br />
                        <input type="radio" value="2" name="theForm.level" checked="checked"/><label> 半自动</label><br />
                        		&nbsp;&nbsp;&nbsp;&nbsp;<label>打开电源后，虚拟机将自动置于主机上，并且vCenter将自动从一个主机迁移到另一个主机，以优化资源使用情况。</label><br /><br />
                        <input type="radio" value="3" name="theForm.level"/><label> 全自动</label><br />
                        		&nbsp;&nbsp;&nbsp;&nbsp;<label>打开电源后，虚拟机将自动置于主机上，并且将自动从一个主机迁移到另一个主机，以优化资源使用情况。</label><br />
                         &nbsp;&nbsp;&nbsp;&nbsp;迁移阈值：&nbsp;&nbsp;&nbsp;&nbsp;<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" name="theForm.priority" id="priority"></s:select>从1至5是保守至激进的渐变<br />
                         		&nbsp;&nbsp;&nbsp;&nbsp;<label>应用优先级为1、2和3的建议</label><br />
                       </s:elseif>
                       <s:else>
                       	 <input type="radio" value="1" name="theForm.level" /><label> 手动</label><br />
                        	    &nbsp;&nbsp;&nbsp;&nbsp; <label>vCenter会给出虚拟机的迁移建议。</label><br /><br />
                        <input type="radio" value="2" name="theForm.level"/><label> 半自动</label><br />
                        		&nbsp;&nbsp;&nbsp;&nbsp;<label>打开电源后，虚拟机将自动置于主机上，并且vCenter将自动从一个主机迁移到另一个主机，以优化资源使用情况。</label><br /><br />
                        <input type="radio" value="3" name="theForm.level" checked="checked"/><label> 全自动</label><br />
                        		&nbsp;&nbsp;&nbsp;&nbsp;<label>打开电源后，虚拟机将自动置于主机上，并且将自动从一个主机迁移到另一个主机，以优化资源使用情况。</label><br />
                         &nbsp;&nbsp;&nbsp;&nbsp;迁移阈值：&nbsp;&nbsp;&nbsp;&nbsp;<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" name="theForm.priority" id="priority"></s:select>从1至5是保守至激进的渐变<br />
                         		&nbsp;&nbsp;&nbsp;&nbsp;<label>应用优先级为1、2和3的建议</label><br />
                       </s:else>
                        
                    </td>
                </tr>
               
                <tr>
                    <td colspan="4" class="btnCenter">
                        <input type="button" class="thickbox btn-style02" value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;"/>
                        <input type="button" class="thickbox btn-style02" value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"/>
                    </td>
                </tr>
            </table>
        </div>
    </s:form>
</body>