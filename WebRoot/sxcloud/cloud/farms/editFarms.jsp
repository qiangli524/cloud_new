<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%> 
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript">
	
<%--	//正整数--%>
	function isNumber( s ){
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1) {
			return true;
		} else {
			return false;
		}
	}
<%--	//整数--%>
	function isInteger( str ){
		var regu = /^[-]{0,1}[0-9]{1,}$/;
		return regu.test(str);
	}
	function resetForm(theForm){ 
		theForm.action = 'modfarms.do';
		theForm.submit();
	}
	function back(){ 
		theForm.action = 'farmsList.do';
		theForm.id.value = '';
			theForm.submit();
	}
	function submitRequest(thisForm){ 
	  	 	 if(!isInteger(thisForm.agingTime.value)){
	  	 	 	alert("agingTime必须位数字");
	    		thisForm.agingTime.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.bandwidthLimit.value)){
	  	 	 	alert("bandwidthLimit必须位数字");
	    		thisForm.bandwidthLimit.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.connectivityCheckInterval.value)){
	  	 	 	alert("connectivityCheckInterval必须位数字");
	    		thisForm.connectivityCheckInterval.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.connectivityCheckRetries.value)){
	  	 	 	alert("connectivityCheckRetries必须位数字");
	    		thisForm.connectivityCheckRetries.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.extendedCheckFrequency.value)){
	  	 	 	alert("extendedCheckFrequency必须位数字");
	    		thisForm.extendedCheckFrequency.focus;
	   	 		return false  ;
	  	 	 }
			theForm.submit();
	}
 	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="loadbalance_sureModfarms.do" method="post" id="theForm">
 <s:hidden name="theForm.id" />
<div class="scrollbody">
	<div>
		<div class="tit-zzi">
			<div id="zi">Farm基本信息</div>
			<div id="zi"></div>	
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
							<td class="til">
								Farm 名称
								<font style="color:#FF3401">*</font>
							</td>
							<td>
								<s:textfield name="theForm.farmName"  />
							</td>
							<td class="til">
								Admin Status:
								<font style="color:#FF3401"></font>
							</td>
							<td>
							<s:select list="#{1'':'Enabled','2':'Disabled'}" name="theForm.adminStatus"></s:select>
							</td>
						</tr>
						<tr>
							<td class="til">
								Dispatch Method:
								<font style="color:#FF3401"> </font>
							</td>
							<td>
								<s:select list="#{'1':'Cyclic','2':'Least Amount of Traffic',
								'3':'Fewest Number of Users','4':'Least Amount of Traffic - Local',
								'5':'Fewest Number of Users - Local','6':'NT-1','7':'NT-2','8':'Private-1',
								'9':'Private-2','10':'Weighted Cyclic','11':'Hashing','12':'Response Time'}" name="theForm.dispatchMethod">
								</s:select>
							</td>
							<td class="til">
								Sessions Mode:
								<font style="color:#FF3401"> </font>
							</td>
							<td colspan="3">
								<s:select list="#{'1':'Regular','2':'EntryPerSession','3':'ServerPerSession',
								'4':'RemoveOnSessionEnd-EPS','5':'RemoveOnSessionEnd-SPS'}" name="theForm.sessionsMode">
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="til">
								Aging Time:
								<font style="color:#FF3401"> </font>
							</td>
							<td>
								<s:textfield name="theForm.agingTime" />
							</td>
							<td class="til">
								Bandwidth Limit:
								<font style="color:#FF3401"> </font>
							</td>
							<td>
								<s:textfield name="theForm.bandwidthLimit"/>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="tit-zzi">
				<div id="zi">
					Connectivity Checks &nbsp;&nbsp;&nbsp;
				</div>
				<div id="zi"></div>
			</div>
			<div>
				<table width="100%" class="pop-table nosize" border="0"
					cellspacing="0" id="DCtable">
					<tr>
						<td class="til">
							Connectivity Check Method:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:select list="#{'1':'Ping','2':'No Checks','3':'HTTP Page','4':'TCP Port','5':'UDP Port'}" name="theForm.connectivityCheckMethod">
							</s:select>
						</td>
						<td class="til">
							Connectivity Check Retries:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:textfield name="theForm.connectivityCheckRetries"/>
						</td>
					</tr>
					<tr>
						<td class="til">
							Connectivity Check Interval:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:textfield name="theForm.connectivityCheckInterval"/>
						</td>
						<td class="til">
							Connectivity Check Port:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:select list="#{'119':'NNTP','25':'SMTP','53':'DNS','21':'FTP','554':'RTSP','443':'HTTPS',
							'80':'HTTP','1812':'Radius'}" name="theForm.connectivityCheckPort">
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="til">
							Extended Check Frequency:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:textfield name="theForm.extendedCheckFrequency" />
						</td>
						<td class="til">
							Authorized Username:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:textfield name="theForm.authorizedUsername" />
						</td>
					</tr>
					<tr>
						<td class="til">
							Authorized Password:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:password name="theForm.authorizedPassword"/>
						</td>
						<td class="til">
							Home Page:
							<font style="color:#FF3401"> </font>
						</td>
						<td>
							<s:textfield name="theForm.homePage"  />
						</td>
					</tr>
					<tr>
					<td class="til">
						Operational Status:    <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:text name="theForm.operationalStatus"/>
					</td>
				</tr>			
			</table>
		</div>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:resetForm(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="javascript:back();return false;" />
				</td>
			</tr>
		</table>
</div>
</s:form>
</body>
</html:html>
