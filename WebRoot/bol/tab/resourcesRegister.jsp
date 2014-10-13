<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript">
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#pname").val("");
	   });
	   
	   $("#refreshForm").click(function(){
		   $("#theForm").submit();
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="bolresource_resourcesRegister.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="host" id="host"></s:hidden>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">进程名称:</td>
		                  		<td>
		                  			<s:textfield name="name" id="pname"></s:textfield> 
		                  		</td>
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
			</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th onclick="sort(theTable,0,'string')">进程标识</th>
						<th onclick="sort(theTable,1,'string')">进程名称</th>
						<th onclick="sort(theTable,2,'string')">内存(M)</th>
						<th onclick="sort(theTable,3,'string')">CPU(C)</th>
						<th onclick="sort(theTable,4,'string')">存储(S)</th>
						<th onclick="sort(theTable,5,'string')">文件句柄(DH)</th>
						<th onclick="sort(theTable,6,'string')">进程文件(PH)</th>
						<th onclick="sort(theTable,7,'string')">消息队列(Q)</th>
						<th onclick="sort(theTable,8,'string')">信号量(SN)</th>
						<th onclick="sort(theTable,9,'string')">网络连接(N)</th>
						<th onclick="sort(theTable,10,'string')">进程(P)</th>
						<th onclick="sort(theTable,11,'string')">数据库连接(DB)</th>
						<th onclick="sort(theTable,12,'string')">目录文件(CH)</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resourcesList" id="theBean">
                		<tr>
                			<td>
	               				<s:property value="#theBean.id"/>
	               			</td>
	               			<td>
	               				<s:property value="#theBean.name"/>
	               			</td>
	               			
	               			<td>
	               				<s:if test="#theBean.M == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.M"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.C == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.C"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.S == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.S"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.DH == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.DH"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.PH == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.PH"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.Q == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.Q"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.SN == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.SN"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.N == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.N"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.P == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.P"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.DB == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.DB"/>
                				</s:else>
	               			</td>
	               			<td>
	               				<s:if test="#theBean.CH == -1.0">
		                			--
                				</s:if>
                				<s:else>
                					<s:property value="#theBean.CH"/>
                				</s:else>
	               			</td>
                		</tr>
                		
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div>
    </div>
    </s:form>
</body>