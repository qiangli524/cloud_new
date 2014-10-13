<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
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
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
    
    
    </script>
</head>
<body class="pop-body scrollbody" >
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
	<div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on" >
		 <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">配置文件名称</td>
                    <td>
						<s:textfield name="theForm.name" id="name" maxlength="100"></s:textfield>
                    </td>
                    <td class="til">类型</td>
                    <td>
						<s:select list="#{'':'--请选择--','0':'xml','1':'properties','2':'其他'}" name="theForm.type" id="type"></s:select>
                    </td>
                    <td class="til">描述</td>
                    <td>
						<s:textfield name="theForm.description" id="description"></s:textfield>
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
	<div class="table-head">
				<ul class="btns">
                    <li colspan="4" class="btnCenter">
                           <input type="button"  value="添加"  class="btn-style02" name="addConfig"/>
                         <input type="button"  value="修改" name="modConfig" class="btn-style02"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:deleteConfig();"/>
                    </li>
                </ul>
    </div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">选择</th>
						<th onclick="sort(theTable,1,'string')">配置文件名称</th>
						<th onclick="sort(theTable,2,'string')">主机/用户名</th>
						<th onclick="sort(theTable,3,'string')">类型</th>
						<th onclick="sort(theTable,4,'string')" width="10%">路径</th>
						<th onclick="sort(theTable,5,'string')">描述</th>
					</tr>
				</thead>
                <tbody id="table">
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td><s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
                			<td><s:if test="#theBean.type==0">xml</s:if>
                				<s:elseif test="#theBean.type==1">properties</s:elseif>
                				<s:else>其他</s:else>
                			</td>
                			<td align="center"><a name="detail" href="javascript:;" value='<s:property value="#theBean.id"/>'><div class="hidden" title='<s:property value="#theBean.path"/>'>
                				<s:property value="#theBean.path"/></div></a></td>
                			<td id="desc"><s:property value="#theBean.description"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
           <div class="table-head">
		  	<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
        </div>
      </div>
</div>
    </s:form>
</body>