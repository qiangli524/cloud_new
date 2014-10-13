<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
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
	  	    html,body,form
     {
      margin:0px;
      height:100%;
     }
	</style>
<script type="text/javascript">
	function searchRequest(){
		 theForm.action = 'HadoopKpi_listAllHadoopKpis.do';
		 theForm.submit();
	}
	$(function(){
		
	 	$("#search").click(function(){
	 	   $("#theForm").submit();
	 	});
	
		$("#resert").click(function(){
		   $("input[type='text']").val("");
		   $("select").each(function(){
		      $(this).children("option:eq(0)").attr("selected",true);
		   });
		   $("#theForm").submit();
		});
		
        $("[name='addKpi']").click(function(){
	  		$.dialog({
    			id:'add',
    			title:'添加新KPI',
    			width: '550px',
    			height: '400px',
    		    lock:true,
    			content: 'url:HadoopKpi_addNewKpi.do?oper=add'
	    		});
        });
        $("[name='importiKpi']").click(function(){
        	$.dialog({
    			id:'import',
    			title:'批量导入',
    			width: '380px',
    			height: '200px',
    		    lock:true,
    			content: 'url:HadoopKpi_importiKpi.do'
	    		});
        });
        $("[name='editKpi']").click(function(){
        	var id=$(this).attr("entityId");
		  		$.dialog({
	    			id:'add',
	    			title:'编辑进程',
	    			width: '550px',
	    			height: '400px',
	    		    lock:true,
	    			content: 'url:HadoopKpi_addNewKpi.do?oper=edit&id='+id
		    	});
        });
        
        $("[name='del']").click(function(){
        	var id=$(this).attr("entityId");
        	mask('正在删除kpi,请稍后....','0.5','0px');
          	 $.ajax({
  		            type: "GET",
  		            url: "HadoopKpi_deleteHadoopKpi.do?id="+id,
  		            dataType: "json",
  		            success : function(data){
  			             removeMask();
  			            $("#theForm").submit();
  		            }
  		          });
        });
        
        $("[name='refresh']").click(function(){
        	$("#theForm").submit();
        });
	});
       
    function saveHadoopKpi(theForm){
  	  mask('正在添加kpi,请稍后....','0.5','0px');
     	 $.ajax({
            type: "GET",
            url: "HadoopKpi_saveHadoopKpi.do?"+theForm,
            dataType: "json",
            success : function(data){
	            removeMask();
	            $("#theForm").submit();
            }
          });
     	 
     }
	 function resetForm(){
		 $("#serviceId").attr("value","");
		 $("#kpi_id").attr("value","");
		 $("#description").attr("value","");
		 $("#isEffect").attr("value","");
	 	 $("#threshold").attr("value","");
	 }
</script>
</head>
<style type="text/css">
	div.hidden{
		width:250px;
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
<body>
<s:form action="HadoopKpi_listAllHadoopKpis.do" method="post" cssStyle="theForm" id="theForm">
<%--<s:hidden name="obj.serviceId" id="service_id"></s:hidden>--%>
<s:hidden name="theForm.ID" id="ID"/>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">监控指标</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                   <label class="mgl-20 vm">服务名称：</label>
						<s:if test="serviceList!=null">
							<s:select cssClass="select-1 vm"  list="serviceList" listKey="service_name" listValue="service_name" name="obj.service_name" headerKey="" headerValue="---请选择---" id="serviceId"></s:select>
						</s:if>
						<s:else>
							<select id="serviceId" cssClass="select-1 vm" >
								<option value="">---请选择---</option>
							</select>
						</s:else>
                   <label class="vm">指标名称：</label>
						<s:textfield id="kpi_id" name="obj.kpi_id"  maxlength="100"></s:textfield>
                    <label class="vm">指标描述：</label>
						<s:textfield id="description" name="obj.description"  maxlength="100"></s:textfield>
                    <label class="mgl-20 vm">是否告警：</label>
						<s:select  id="threshold" cssClass="select-1 vm" list="#{'':'---请选择---','0':'不监控','1':'监控'}" name="obj.threshold" ></s:select>
                    <label class="mgl-20 vm">是否有效：</label>
						<s:select id="isEffect" cssClass="select-1 vm" list="#{'':'---请选择---','1':'有效','2':'无效'}" name="obj.isEffect" ></s:select>
                   
                   <br>
                   <br/>
                 <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			
        </div><!--query-form end -->
        
	<div class="utt-2 mgt-20">
				<a name="addKpi" class="icon-add" href="javascript:void(0)">添加</a>
				
				<a name="importiKpi" class="icon-del" href="javascript:void(0)">导入</a>
	</div>
			
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0" name="processtable">
				<thead>
					<tr>
						<th>
							选择
						</th>
						<th>
							指标名称
						</th>
						<th>
							指标描述
						</th>
						<th>
							图例
						</th>
						<th>
							单位
						</th>
						<th>
							告警阈值
						</th>
						<th>
							是否有效
						</th>
						<th>
							级别
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
	                  <tr>
					    <td>
							<input type="checkbox" name="checkboxid" entityId='<s:property value="#theBean.id"/>'/>
						</td>
						<td width="40%" align="center">
							<div class="hidden" title="<s:property value='#theBean.kpi_id'/>">
								<s:property value="#theBean.kpi_id" default="无"/>					
							</div>						
						</td>
						<td width="40%" align="center">
							<div class="hidden" title="<s:property value='#theBean.description'/>">
								<s:property value="#theBean.description" default="无"/>					
							</div>		
						</td>
						<td width="5%">
							<s:if test="#theBean.shape==1">
							  线图
							</s:if>
						    <s:elseif test="#theBean.shape==2">
						              面图
						    </s:elseif>
						    <s:else>
						             线图
						    </s:else>
						</td>
						<td width="5%">
							<s:property value="#theBean.unit" default="无"/> 
						</td>
						<td width="5%">
						<s:if test="#theBean.threshold==0">
						        不监控
						</s:if>
						<s:elseif test="#theBean.threshold_type==1">
						    <s:property value="#theBean.threshold" default="无"/> 百分比
						</s:elseif>
						<s:else>
							<s:property value="#theBean.threshold" default="无"/> 数值
						</s:else>
						</td>
						<td  width="5%">
							<s:if test="#theBean.isEffect==1">
							  有效
							</s:if>
						    <s:else>
						             无效
						    </s:else>
						</td>
						<td  width="5%">
							<s:if test="#theBean.level==0">
							  高
							</s:if>
						    <s:elseif test="#theBean.shape==1">
						              中
						    </s:elseif>
						    <s:else>
						             低
						    </s:else>
						</td>
						<td  width="5%">
						    <a href="javascript:;" entityId='<s:property value="#theBean.id"/>' name="editKpi">编辑</a>
							<a href="javascript:;" entityId='<s:property value="#theBean.id"/>' name="del">删除</a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
