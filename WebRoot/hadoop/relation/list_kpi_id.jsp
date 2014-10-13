<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
<script type="text/javascript">
	var api = frameElement.api;
	w = api.opener;
	 api.button({
	     id:'OkAnd',
	     name: '确定',
	     callback:selectKpi,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '返回'
	 });
	function selectKpi(){
		var kpi_id = "";
		$("input['name=checkboxid']:checked").each(function(){
			kpi_id += $(this).val()+",";
		});
		if(kpi_id == ""){
			alert("请选择KPI");
			return false;
		}
		api.get("add").saveKpi_id(kpi_id);
	}
	function searchRequest(){
		 theForm.submit();
	}
	 function resetForm(){
		 $("#kpi_id").attr("value","");
		 $("#description").attr("value","");
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
<body class="pop-body scrollbody">
<s:form action="relation_queryKpi_id.do" method="post" cssStyle="theForm" id="theForm">
<div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
	 	<div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">指标名称:</td>
                    <td>
						<s:textfield name="obj.kpi_id" id="kpi_id" maxlength="100"></s:textfield>
                    </td>
                       <td class="til">指标描述:</td>
                    <td>
						<s:textfield name="obj.description" id="description" maxlength="100"></s:textfield>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm()" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	<div class="blue-wrap noborder">
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
					</tr>
				</thead>
				<tbody>
					<s:iterator value="kpiList" id="theBean">
	                  <tr>
					    <td width="10">
							<input type="checkbox" name="checkboxid" value='<s:property value="#theBean.kpi_id"/>'/>
						</td>
						<td width="200" align="center">
							<div class="hidden" title="<s:property value='#theBean.kpi_id'/>">
								<s:property value="#theBean.kpi_id" default="无"/>					
							</div>						
						</td>
						<td width="200" align="center">
							<div class="hidden" title="<s:property value='#theBean.description'/>">
								<s:property value="#theBean.description" default="无"/>					
							</div>		
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
