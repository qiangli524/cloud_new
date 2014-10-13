<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>

<script type ="text/template" id="temp_query">
					  <@if(resourceType=='host' || resourceType=='vm' ){@>		
								<tr>
									<td>数据中心: </td>
									<td align="left">										
										<select id="dataCenterId" onchange="renderCluster()" name="obj.dataCenterId" class="select-1 fl" >
											<option value="-1">所有</option>
										</select>
									</td>
									<td>集群: </td>
									<td align="left">
										<select id="clusterId"  name="obj.clusterId"   class="select-1 fl" >
											<option value="-1">所有</option>
										</select>
									</td>
								</tr>
						<@if(resourceType=='vm'){@>		
								<tr>
									<td  width="15%">业务系统: </td>
									<td align="left"  width="20%">
										<select id="businessId"  onchange="renderSubBusiness()" name="obj.businessId"  class="select-1 fl" >
											<option value="-1">所有</option>
										</select>
									</td>
									<td>子业务系统: </td>
									<td align="left">
										<select id="subBusinessId"  name="obj.subBusinessId"   class="select-1 fl" >
											<option value="-1">所有</option>
										</select>
									</td>
								</tr>
						<@}@> 			
								<tr>
									<td>选择资源: </td>
									<td align="left"  colspan="3">
										<div>										
										<input id="resourceNames" onclick="addResource();" style="width:70%;color: rgb(79, 79, 79);" class='inpt-1 fl'  value="所有">
										<input type="hidden" id="resourceIds" name="obj.resourceIds" Class="inpt-1" value="-1"/>
										</div>
										<div class="reportQuery" onclick="addResource();"></div>
									</td>
								</tr>						
								<tr>
									<td>选择指标: </td>
									<td align="left">
										<div>										
										<input type="text" style="color: rgb(79, 79, 79);" onclick="queryKpis()" name="obj.kpiName" id="kpiName" class='inpt-1 fl' placeholder="请选择"  dataType="Require"  msg="指标不能为空">
										<input type="hidden" id="kpiId" name="obj.kpiId" Class="inpt-1" value=""/>
										<input type="hidden" id="kpiunit" name="obj.kpiunit" Class="inpt-1" value=""/>
										</div>
										<div class="reportQuery" onclick="queryKpis()"></div>
									</td>
									<td>展示方式: </td>
									<td align="left">
										<select id="showType"  name="obj.showType"   class="select-1 fl" >
											<option value="1">图形</option>
											<option value="2">列表</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>时间维度: </td>
									<td align="left">
										<input name="obj.dateType" id="radio_dateType_1" class="dateType" class="dateType" type="radio" checked value="1"/>
										实时
										<input name="obj.dateType" id="radio_dateType_2" class="dateType" class="dateType" type="radio"  value="2"/>
										小时
										<input name="obj.dateType" id="radio_dateType_3" class="dateType" class="dateType" type="radio"  value="3"/>
										天
									</td>
									<td>时间区间: </td>
									<td align="left">
										<input id="startDateId" 
										name="obj.startDate" class="Wdate" style="width: 140px;border:solid 1px #c3c3c3;"
										onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
										 dataType="Require"  msg="开始时间不能为空"/>
										- <input id="endDateId"  name="obj.endDate"
										 class="Wdate"  style="width: 140px;border:solid 1px #c3c3c3;"
										onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										 dataType="Require" msg="结束时间不能为空"  />
									</td>
								</tr>								
					   <@}@> 			
					   <@if(resourceType=='store'){@>
					   			<tr>
					   				<td>选择指标: </td>
									<td align="left">
										<input name="kpis" type="checkbox"  checked value="31"/>
										总容量
										<input name="kpis" type="checkbox"   value="32"/>
										剩余容量
										<input name="kpis" type="checkbox"   value="33"/>
										使用率
										<input name="kpis" type="checkbox"   value="34"/>
										主机数量
									</td>
								</tr>	
					   <@}@> 		
					   <@if(resourceType=='ip'){@>
					   			<tr>
					   				<td>选择指标: </td>
									<td align="left">
										<input name="kpis" type="checkbox"  checked value="41"/>
										剩余IP
										<input name="kpis" type="checkbox"   value="42"/>
										使用率
										<input name="kpis" type="checkbox"   value="43"/>										
									</td>
								</tr>	
					   <@}@> 		
</script>

<script type ="text/template" id="temp_list">
<@_.each(datas, function(e){@>
	<tr>
		<td><@=e.label2@></td>
		<td><@=e.label3@></td>
		<td><@=e.value@></td>
		<td><@=e.label@></td>										
	</tr>
<@})@>		
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

  <script type ="text/template" id="temp_kpis">
		<@_.each(datas, function(e){@>
				<tr>
					<td>
						<input type="checkbox" class="inp-chb" value="<@=e.id@>"/>
			  			<span class="b-chb bon-chb" kpiid="<@=e.id@>" kpiname="<@=e.name@>"  kpiunit="<@=e.unit@>" ><b></b></span>
					</td>
					<td><@=e.name@></td>
					<td><@=e.desc@></td>
				</tr>
		<@})@>		
</script>
