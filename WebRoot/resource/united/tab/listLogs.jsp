<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>	
<head>
<title></title>
	 <style type="text/css">
		.font-more{ width:450px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
	  </style>
</head>
<body>
<s:form cssStyle="theForm">
<div class="scrollbody">
	<div class="box on">
		<div class="blue-wrap noborder">
			<div class="table-ct">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				  <thead>
					  <tr>
					  	<th onclick="sort(theTable,0,'string')"><font color="white">描述</font></th>
						<th onclick="sort(theTable,1,'string')"><font color="white">时间</font></th>
					  </tr>
				  </thead>
				  <tbody>
				  		<s:iterator value="resultList" id="theBean">
				  			<tr>
				  				<td align="left">
					  				<span style="color: black;" class="font-more" title="<s:property value='#theBean.info'/>">
										<s:property value="#theBean.info" />
									</span>
								</td>
				  				<td><s:property value="#theBean.time" />
											</td>
				  			</tr>
				  		</s:iterator>
				  </tbody>
				</table>
			</div>
		</div>
    </div>
</div>
</s:form>
</body>
