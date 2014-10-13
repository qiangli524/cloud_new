<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>控制中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/publicCloud/pub-ui/showDialog/showDialog.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/showDialog/showDialog.js"></script>

    <script type="text/javascript">
        $(function () {
            //地域点击事件
            $('.zone-switcher a').live('click', function () {
                $('.zone-switcher-select').toggle();
            });
            //dt点击事件
            $('.y-span2 dl dt').live('click', function () {
                var dl = $(this).parent('dl');
                if (dl.hasClass('current')) {
                    dl.removeClass('current');
                    $(this).removeClass('current');
                } else {
                    $('.y-span2 dl.current').removeClass('current');
                    dl.addClass('current');

                    $('.y-span2 dt.current').removeClass('current');
                    $(this).addClass('current');
                }
            });
            //dd点击事件
            $('.y-span2 dl dd').live('click', function () {
                $('.y-span2 dd.current').removeClass('current');
                $(this).addClass('current');
            });
        })
    </script>
</head>

<body>
<!--container star-->
<div class="container">
    <!--col-c7 star-->
    <div class="col-c7">
        <!--left star-->
        <div class="side-c1 fl">
            <dl class="compose-w3 clearfix">
                <dt><img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/logo2.png"/></dt>
                <dd>北京思特奇信息技术股份有限公司</dd>
            </dl>
            <div class="zone-switcher">
                <a class="current-zone pek1"><span class="icon icon-zone"></span>北京1区<span class="en">(PEK1)</span><em
                        class="loading" style="display: none">切换中...</em></a>
                <ul class="zone-switcher-select" style="display: none;">
                    <li><a data-value="pek1"><span class="label-current"></span>北京1区<span class="en">(PEK1)</span><span
                            class="zone-label pek1"></span></a></li>
                    <li><a data-value="gd1"><span class="label-unchecked"></span>广东1区<span class="en">(GD1)</span><span
                            class="zone-label gd1"></span></a></li>
                </ul>
            </div>
            <div class="help-main">
                <div class="y-span2 help-menu">
                    <dl class="current">
                        <dt class="current"><i class="i-6"></i><a href="#" data-ga="">云主机</a>
                        <div class="clear"></div>
                        </dt>
                        <dd><i class="i-7"></i><a href="#" data-ga="">公网IP</a>

                            <div class="clear"></div>
                        </dd>
                        <dd><i class="i-8"></i><a href="#" data-ga="">磁盘</a>

                            <div class="clear"></div>
                        </dd>
                        <dd><i class="i-9"></i><a href="#" data-ga="">备份</a>

                            <div class="clear"></div>
                        </dd>
                    </dl>
                    <dl>
                        <dt><i class="i-10"></i><a href="#" data-ga="">云安全</a></dt>
                    </dl>
                    <dl>
                        <dt><i class="i-11"></i><a href="#" data-ga="">对象存储</a></dt>
                    </dl>
                    <dl>
                        <dt><i class="i-13"></i><a href="#" data-ga="">我的工单</a></dt>
                        <dd><i class="i-7"></i><a href="#" data-ga="">工单申请</a></dd>
                        <dd><i class="i-8"></i><a href="#" data-ga="">工单列表</a></dd>
                    </dl>
                </div>
            </div>
        </div>
        <!--left end-->
        <!--main-c1 star-->
        <div class="main-c1 fr">
            <!--标题 star-->
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/pub-ui/images/title-img2.gif" width="65" height="65"/>主机</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">云服务器（Elastic Compute Service, 简称ECS）是一种处理能力可弹性伸缩的计算服务，其管理方式比物理服务器更简单高效。<br/>云服务器帮助您快速构建更稳定、安全的应用，降低开发运维的难度和整体IT成本，使您能够更专注于核心业务创新。
            </p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <form id="theForm" action="<%=request.getContextPath() %>/vm_listVm.do" method="post">
                    <div class="col-c8">
                        <ul class="operate-list">
                            <li class="first">
                                <a href="#" class="add"></a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="paly" id="powerOn">启动</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="close" id="powerOff">关机</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="more js_more">更多操作...</a>
                                <dl class="operate-dl gray_button aa">
                                    <dd><a href="#" class="upgrade">升级</a></dd>
                                    <dd><a href="#" class="pwd">修改密码</a></dd>
                                    <dd><a href="#" class="reset-q" id="reboot">重启</a></dd>
                                    <dd><a href="#" class="reset" id="reset">重置</a></dd>
                                    <dd><a href="#" class="pwd" id="snapshot">新建备份</a></dd>
                                    <dd></dd>
                                </dl>
                            </li>
                            <li class="search">
                                <input type="text" class="text-1"/>
                                <input type="button" class="search-btn"/>
                            </li>

                        </ul>
                        <table border="0" width="100%" class="table-f4 js_table_f4">
                            <tr>
                                <th><input type="checkbox" class="vhid"/></th>
                                <th>名称</th>
                                <th>状态<b class="b-p"></b></th>
                                <th>网络</th>
                                <th>公网IP</th>
                                <th>配置</th>
                                <th>性能</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td><input type="checkbox" name="bb" class="code"
                                               vmCode='<s:property value="#theBean.VH_UUID" />'
                                               connectCode='<s:property value="#theBean.connectId" />'
                                               vType='<s:property value="#theBean.VH_TYPE" />'/></td>
                                    <td><span class="font-blue"><s:property value="#theBean.VH_NAME"/></span></td>
                                    <td><em class="em-c">
                                        <s:if test="#theBean.VH_STAT==1">正在运行</s:if>
                                        <s:else>已停止</s:else>
                                    </em>
                                    </td>
                                    <td><span class="font-blue"><s:property value="#theBean.VH_NETWORK"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.VH_IP"/></span></td>
                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.VH_CPU"/>核</span>|
                                        <span class="font-blue"><s:property value="#theBean.VH_MEM"/>M</span>|
                                        <span class="font-blue"><s:property
                                                value="@java.lang.Math@round(#theBean.VH_STORAGE/1024)"/>G</span>
                                    </td>
                                    <td><a href="">性能</a></td>
                                </tr>
                            </s:iterator>
                        </table>
                        <!-- 分页start -->
                        <div class="page mgt34-b25">
                            <jsp:include page="../inc/fenye.jsp?formId=theForm"/>
                        </div>
                        <!-- 分页end -->
                    </div>
                    </form>
            </div>
            <!--pd-15 end-->
        </div>
        <!--main-c1 end-->
        <div class="clear"></div>
    </div>
    <!--col-c7 end-->
</div>
<!--container end-->
<!--版权 star-->
<div class="copy">
    <div class="copy-con ac">
        <p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号 增值电信业务经营许可证A2.B1.B2-20090001</p>
    </div>
</div>
<!--版权 end-->
</body>

</html>
