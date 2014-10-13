<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
    <head>
        <title>
            index
        </title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp/pub-ui/css/skin1/all.css"></link>
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
        <link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/all-min.js">
        </script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/My97DatePicker/WdatePicker.js">   </script>
    </head>
    
    <body treerel="1">
        <div class="pdt-10 pdb-10 pdl-10 pdr-10">
            <div class="ued-grid mgb-10" >
                <div class="ued-g50 ued-gl">
                    <div class="ued-gbox" >
                        <div class="pdr-5 ued-panel-4" data-role="ued-panel" >
                            <h3>
                             <a class="font-gray1" style="cursor:default;text-decoration:none">资源总体状态</a>
                                <span>
<%--                                    <a class="tool max">--%>
<%--                                    </a>--%>
<!--                                     <a class="tool collapse"> -->
<!--                                     </a> -->
<!--                                     <a class="tool close"> -->
<!--                                     </a> -->
                                </span>
                            </h3>
                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5" style="height:320px" >
							   <iframe src="resourceOutline_toTabs.do?type=0" frameborder="0" height="100%" id="mainFrame" width="100%" align="middle"></iframe>
                            </div>
                        </div>
                        <div class="pdr-5 ued-panel-4 mgt-10" data-role="ued-panel">
                            <h3>
                          <a class="font-gray1" style="cursor:default;text-decoration:none">资源预算分配趋势</a>
                                <span>
<%--                                    <a class="tool max">--%>
<%--                                    </a>--%>
<!--                                     <a class="tool collapse"> -->
<!--                                     </a> -->
<!--                                     <a class="tool close"> -->
<!--                                     </a> -->
                                </span>
                            </h3>
                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"  style="height:355px">
                                 <iframe src="resourceOutline_toTabs.do?type=3" frameborder="0" height="100%" id="mainFrame1" width="100%" ></iframe>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ued-g50 ued-gr">
                    <div class="ued-gbox">
                        <div class="ued-panel-4 pdl-5" data-role="ued-panel" >
                            <h3>
                                <a class="font-gray1" style="cursor:default;text-decoration:none">业务资源利用率Top5</a>
                                <span>
<%--                                    <a class="tool max">--%>
<%--                                    </a>--%>
<!--                                     <a class="tool collapse"> -->
<!--                                     </a> -->
<!--                                     <a class="tool close"> -->
<!--                                     </a> -->
                                </span>
                            </h3>
                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5" style="height:320px">
                            <iframe src="resourceOutline_toTabs.do?type=1" frameborder="0" height="100%" id="mainFrame2" width="100%" ></iframe>
                            </div>
                        </div>
                        <div class="ued-panel-4 pdl-5 mgb-10 mgt-10" data-role="ued-panel">
                            <h3>
                                <a class="font-gray1" style="cursor:default;text-decoration:none">资源占用TOP10 业务</a>
                                <span>
<%--                                    <a class="tool max">--%>
<%--                                    </a>--%>
<!--                                     <a class="tool collapse"> -->
<!--                                     </a> -->
<!--                                     <a class="tool close"> -->
<!--                                     </a> -->
                                </span>
                            </h3>
                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5" style="height:355px">
                                <iframe src="resourceOutline_toTabs.do?type=4" frameborder="0" height="100%" id="mainFrame3" width="100%" ></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>