<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<body>
<div>
<table align="center" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top" >
        <table>
          <tr>
          		<td>
          			<table>
          				<tr>
          					<td align="left">
	          					任务编号: 
				             </td>
		      				<td align="right">
				              	<font color="blue" size="4">${task_id}</font>
				            </td>
				        </tr>
          				<tr>
          					<td align="left">
	          					成功个数(个):
				             </td>
		      				<td align="right">
				              	<font color="blue" size="4">${successCount}</font>
				            </td>
				        </tr>
				        <tr>
				        	 <td>
				            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实例编号: 
				            </td>
				        	<td>
		          				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IP地址: 
					        </td>
					        <td>
		          				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实例名字: 
					        </td>
				        </tr>
					    <#list resultList1 as e>
					        <tr>
							     <td>
					              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.exampleID}</font>
					             </td>
					             <td>
						            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.IP}</font>
						         </td>
						         <td>
						             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.exampleName}</font>
						         </td>
						    </tr>
						</#list>
				        <tr>
          					<td align="left">
	          					失败个数(个):
				             </td>
				             <td align="right">
				              	<font color="blue" size="4">${failCount}</font>
				            </td>
				        </tr>
				        <tr>
				        	<td>
				            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实例编号: 
				            </td>
				            <td>
	          					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IP地址: 
				             </td>
				            <td>
	          					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实例名字: 
				             </td>
				            <td>
	          					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;失败原因: 
				             </td>
				        </tr>
				        <#if resultList2?exists>
					        <#list resultList2 as e>
						        <tr>
								     <td>
						              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.exampleID}</font>
						             </td>
						             <td>
							            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.IP}</font>
							         </td>
							         <td>
							             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.exampleName}</font>
							         </td>
							         <td>
							             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4">${e.failReason}</font>
							         </td>
							    </tr>
							</#list>
						</#if>
          			</table>
          		</td>		
          </tr>
        </table> 
    </td>
  </tr>
</table>
</div>
</body>
<html>
