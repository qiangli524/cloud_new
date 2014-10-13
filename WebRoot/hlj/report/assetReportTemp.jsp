<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>

<script type ="text/template" id="temp_query_host">
		<tr>
			<td  width="10%">主机名称: </td>
			<td  width="15%">
				<s:textfield name="obj.eqName" placeholder='所有' style="color: rgb(79, 79, 79); " cssClass="inpt-1 fl"></s:textfield>
			</td>
			<td  width="10%">主机IP地址: </td>
			<td  width="15%">
				<s:textfield name="obj.eqIp" placeholder='所有' style="color: rgb(79, 79, 79); " cssClass="inpt-1 fl"></s:textfield>
			</td>
			<td  width="10%">是否分配: </td>
			<td  width="15%">										
				<s:select cssClass="select-1 fl" list="#{'-1':'所有','0':'未分配','1':'已分配'}" name="obj.allocated"/>
			</td>
			<td  width="10%">主机类型: </td>
			<td  width="15%">										
				<s:select cssClass="select-1 fl" list="#{'-1':'所有','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器','6':'华为服务器'}" name="obj.eqType"></s:select>
			</td>
		</tr>
		<tr>
			<td>数据中心: </td>
			<td>										
				<select id="dataCenterId" onchange="renderCluster()" name="obj.dataCenterId" class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td>集群: </td>
			<td>
				<select id="clusterId"  name="obj.clusterId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td>业务系统: </td>
			<td>
				<select id="businessId"  onchange="renderSubBusiness()" name="obj.businessId"  class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td>子业务系统: </td>
			<td>
				<select id="subBusinessId"  name="obj.subBusinessId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
		</tr>
		<tr>
			<td  width="10%">虚拟类型: </td>
			<td  width="15%">										
				<s:select cssClass="select-1 fl" list="#{'-1':'所有','4':'VMWARE','3':'XEN','0':'非虚拟化'}" name="obj.hasvertual"/>
			</td>
			<td  width="10%">所在机房: </td>
			<td  width="15%"  colspan=5>										
				<select id="roomId"  name="obj.roomId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
		</tr>
</script>
<script type ="text/template" id="temp_query_vm">
		<tr>
			<td  width="10%">虚拟机名称: </td>
			<td  width="15%">
				<s:textfield name="obj.vhName" placeholder='所有' style="color: rgb(79, 79, 79); " cssClass="inpt-1 fl"></s:textfield>
			</td>
			<td  width="10%">虚拟机IP地址: </td>
			<td  width="15%">
				<s:textfield name="obj.vhIp" placeholder='所有' style="color: rgb(79, 79, 79); " cssClass="inpt-1 fl"></s:textfield>
			</td>
			<td  width="10%">虚拟类型: </td>
			<td  width="15%">										
				<s:select cssClass="select-1 fl" list="#{'-1':'所有','1':'VMWARE','3':'XEN','9':'其他'}" name="obj.vhType"/>
			</td>
			<td  width="10%">所属主机: </td>
			<td  width="15%">										
				<s:textfield name="obj.eqIp" placeholder='所有' style="color: rgb(79, 79, 79); " cssClass="inpt-1 fl"></s:textfield>
			</td>
		</tr>
		<tr>
			<td>数据中心: </td>
			<td>										
				<select id="dataCenterId" onchange="renderCluster()" name="obj.dataCenterId" class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td>集群: </td>
			<td>
				<select id="clusterId"  name="obj.clusterId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td>业务系统: </td>
			<td>
				<select id="businessId"  onchange="renderSubBusiness()" name="obj.businessId"  class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td>子业务系统: </td>
			<td>
				<select id="subBusinessId"  name="obj.subBusinessId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
		</tr>
</script>

<script type ="text/template" id="temp_query_store">

</script>

<script type ="text/template" id="temp_query_ip">
		<tr>
			<td  width="10%">IP地址: </td>
			<td  width="15%">
				<s:textfield name="obj.ipAddress" placeholder='所有' style="color: rgb(79, 79, 79); " cssClass="inpt-1 fl"></s:textfield>
			</td>
			<td  width="10%">使用情况: </td>
			<td  width="15%">
				<s:select cssClass="select-1 fl" list="#{'-1':'所有','1':'已使用','0':'未使用'}" name="obj.isused"/>
			</td>
			<td  width="10%">所属网络域: </td>
			<td  width="15%">										
				<select id="subnetId"  name="obj.subnetId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
			<td  width="10%">所属VLAN: </td>
			<td  width="15%">										
				<select id="vlanId"  name="obj.vlanId"   class="select-1 fl" >
					<option value="-1">所有</option>
				</select>
			</td>
		</tr>
</script>

<script type ="text/template" id="temp_list_host">
<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	<thead>
		<tr>
			<th class="export" key="eqName">主机名称</th>
			<th class="export" key="eqType" >主机类型</th>
			<th class="export" key="model">主机型号</th>									
			<th class="export" key="allocated">是否分配</th>									
			<th class="export" key="status">状态</th>									
			<th class="export" key="hasvertual">虚拟类型</th>									
			<th class="export" key="datacenter">数据中心</th>									
			<th class="export" key="cluster">所属集群</th>									
			<th class="export" key="subbusiness">关联业务</th>									
			<th class="export" key="eqIp">主机IP</th>
			<th class="export" key="cpuCl">CPU(核)</th>									
			<th class="export" key="memory">内存(G)</th>									
			<th class="export" key="vmSize">虚拟机(个)</th>									
			<th class="export" key="roomName">所在机房</th>									
			<th class="export" key="cubName">所在机柜</th>									
			<th class="export" key="insDate">接人时间</th>									
		</tr>		
		</thead>
	<@_.each(datas, function(e){@>
		<tr>
			<td><@=e.eqName@></td>
			<td><@=e.eqType@></td>
			<td title="<@=e.model@>">
			<@if(e.model!=null && e.model.length>8){@>
				<@=e.model.substring(0,8)@>...
			<@}else{@>
				<@=e.model@>				
			<@}@>
			</td>										
			<td><@=e.allocated@></td>										
			<td><@=e.status@></td>										
			<td><@=e.hasvertual@></td>										
			<td title="<@=e.datacenter@>">
			<@if(e.datacenter!=null && e.datacenter.length>8){@>
				<@=e.datacenter.substring(0,8)@>...
			<@}else{@>
				<@=e.datacenter@>				
			<@}@>
			</td>	
			<td title="<@=e.cluster@>">
			<@if(e.cluster!=null && e.cluster.length>8){@>
				<@=e.cluster.substring(0,8)@>...
			<@}else{@>
				<@=e.cluster@>				
			<@}@>
			</td>			
			<td title="<@=e.subbusiness@>">
			<@if(e.subbusiness!=null && e.subbusiness.length>8){@>
				<@=e.subbusiness.substring(0,8)@>...
			<@}else{@>
				<@=e.subbusiness@>				
			<@}@>
			</td>						
			<td><@=e.eqIp@></td>
			<td><@=e.cpuCl@></td>										
			<td><@=e.memory@></td>										
			<td><@=e.vmSize@></td>										
			<td title="<@=e.roomName@>">
			<@if(e.roomName!=null && e.roomName.length>8){@>
				<@=e.roomName.substring(0,8)@>...
			<@}else{@>
				<@=e.roomName@>				
			<@}@>
			</td>										
			<td title="<@=e.cubName@>">
			<@if(e.cubName!=null && e.cubName.length>8){@>
				<@=e.cubName.substring(0,8)@>...
			<@}else{@>
				<@=e.cubName@>				
			<@}@>
			</td>									
			<td><@=e.insDate@></td>										
		</tr>
	<@})@>	
</table>	
</script>

<script type ="text/template" id="temp_list_vm">
<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	<thead>
		<tr>
			<th class="export" key="vhName">虚拟机名称</th>
			<th class="export" key="vhIp" >虚拟机IP</th>
			<th class="export" key="vhType">虚拟类型</th>
			<th class="export" key="datacenter">数据中心</th>									
			<th class="export" key="cluster">所属集群</th>									
			<th class="export" key="subbusiness">关联业务</th>																
			<th class="export" key="eqIp">所属主机</th>									
			<th class="export" key="cpu">CPU(核)</th>									
			<th class="export" key="mem">内存(G)</th>									
			<th class="export" key="store">存储(G)</th>									
			<th class="export" key="system">操作系统</th>									
			<th class="export" key="stat">状态</th>									
		</tr>		
		</thead>
	<@_.each(datas, function(e){@>
		<tr>
			<td title="<@=e.vhName@>">
			<@if(e.vhName!=null && e.vhName.length>20){@>
				<@=e.vhName.substring(0,20)@>...
			<@}else{@>
				<@=e.vhName@>				
			<@}@>
			</td>	
			<td><@=e.vhIp@></td>
			<td><@=e.vhType@></td>
			<td title="<@=e.datacenter@>">
			<@if(e.datacenter!=null && e.datacenter.length>10){@>
				<@=e.datacenter.substring(0,10)@>...
			<@}else{@>
				<@=e.datacenter@>				
			<@}@>
			</td>
			<td title="<@=e.cluster@>">
			<@if(e.cluster!=null && e.cluster.length>10){@>
				<@=e.cluster.substring(0,10)@>...
			<@}else{@>
				<@=e.cluster@>				
			<@}@>
			</td>									
			<td><@=e.subbusiness@></td>										
			<td><@=e.eqIp@></td>										
			<td><@=e.cpu@></td>										
			<td><@=e.mem@></td>										
			<td><@=e.store@></td>										
			<td title="<@=e.system@>">
			<@if(e.system!=null && e.system.length>20){@>
				<@=e.system.substring(0,20)@>...
			<@}else{@>
				<@=e.system@>				
			<@}@>
			</td>									
			<td><@=e.stat@></td>										
		</tr>
	<@})@>	
</table>	
</script>


<script type ="text/template" id="temp_list_store">

</script>

<script type ="text/template" id="temp_list_ip">
<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	<thead>
		<tr>
			<th class="export" key="ip">IP地址</th>
			<th class="export" key="vlan">所属VLAN</th>
			<th class="export" key="ipType" >类型</th>
			<th class="export" key="isused">使用情况</th>									
			<th class="export" key="subnet">所属网络域</th>																							
			<th class="export" key="insDate">接入时间</th>																							
		</tr>		
		</thead>
	<@_.each(datas, function(e){@>
		<tr>
			<td><@=e.ip@></td>
			<td><@=e.vlan@></td>
			<td><@=e.ipType@></td>
			<td><@=e.isused@></td>										
			<td><@=e.subnet@></td>										
			<td><@=e.insDate@></td>																		
		</tr>
	<@})@>	
</table>	
</script>

<script type ="text/template" id="temp_page">
<div class="page-l">
 共&nbsp;<span class="blue"><@=totalCount@></span>&nbsp;条记录
          	<@=curPage@>/<@=pageCount@>页
          
          <span>|</span>
	每页<font>
           <select id="pageSize" onchange="changePageSize(this.value)">
           		<@
           			if(pageSize==10){
           		@>
           			<option value="10" selected="selected">10</option>
           			<option value="30">30</option>
           			<option value="50">50</option>
           		<@
           			}else if(pageSize==30){
           		@>
           			<option value="10">10</option>
           			<option value="30" selected="selected">30</option>
           			<option value="50">50</option>
           		<@
           			}else if(pageSize==50){
           		@>
           			<option value="10">10</option>
           			<option value="30">30</option>
           			<option value="50" selected="selected">50</option>
           		<@
           			}
           		@>
           		
           </select> 
          	</font>条记录
           </div>
		<@
	if ( prePage > 0 ){
	@>
			  <div class="page-r"><a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(1);return false;">首页</a>&nbsp;<a href="#" class="a-1" onClick="javascript:gotoPage(<@=prePage@>);return false;">前一页</a>
		<@
		} else {
		@>
			<div class="page-r"><a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(1);return false;">首页</a>
		<@
		}
		@>
          <@
          	 if(prePage!=0){        		
          @>
          	<a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(<@=prePage@>);return false;"><@=prePage@></a>&nbsp;
          <@
          	}
          @>
          	<a class="a-1 on" href="javascript:void(0);" onclick="javascript:gotoPage(<@=curPage@>);return false;"><@=curPage@></a>&nbsp;
          <@
          	 if(curPage+1<=pageCount){        		
          @>
          	<a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(<@=curPage+1@>);return false;"><@=curPage+1@></a>&nbsp;
          <@
          	}
          @>
          <@
          	 if(curPage+2<=pageCount){        		
          @>
          	<a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(<@=curPage+2@>);return false;"><@=curPage+2@></a>&nbsp;
          <@
          	}
          @>
          <@
          	 if(curPage+3<=pageCount){        		
          @>
          	<a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(<@=curPage+3@>);return false;"><@=curPage+3@></a>&nbsp;
          <@
          	}
          @>
          <@
          	 if(curPage+4<=pageCount){        		
          @>
          	<a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(<@=curPage+4@>);return false;"><@=curPage+4@></a>&nbsp;
          <@
          	}
          @>
	<@
           if ( nextPage <= pageCount ){
           @>
		<a href="javascript:void(0);" class="a-1" onClick="javascript:gotoPage(<@=nextPage@>);return false;">后一页</a>&nbsp;<a href="#" onclick="javascript:gotoPage(<@=pageCount@>);return false;">尾页</a>
	<@
	} else {
	@>
		<a href="#" onclick="javascript:gotoPage(<@=pageCount@>);return false;">尾页</a>
	<@
	}
	@>
     </div>
</script>
