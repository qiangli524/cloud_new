//右键菜单层控制
//右键菜单数组
var menuArr = new Array();
initMenuArr();
//创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addDataCenter";
	menuArr[1] = "#delDataCenter";
	menuArr[2] = "#conDataCenter";
	menuArr[3] = "#addCluster";
	menuArr[4] = "#delCluster";
	menuArr[5] = "#conCluster";
	menuArr[6] = "#virtual_add";
	menuArr[7] = "#app_deploy";
	menuArr[8] = "#host_conn";
	menuArr[9] = "#virtual_del";
	menuArr[10] = "#virtual_start";
	menuArr[11] = "#virtual_stop";
	menuArr[12] = "#virtual_pause";
	menuArr[13] = "#virtual_resume";
	menuArr[14] = "#virtual_reboot";
	menuArr[15] = "#virtual_conn";
	menuArr[16] = "#virtual_view";
	menuArr[17] = "#virtual_setting";
	menuArr[18] = "#host_add";
	menuArr[19] = "#host_del";
	menuArr[20] = "#virtual_monitor";
	menuArr[21] = "#host_monitor";
	menuArr[22] = "#add_nake";
	menuArr[23] = "#rename";
}
//隐藏所有邮件菜单
function hideMenuDiv() {
	//隐藏所有 class="hide" 的 <li> 元素
	$("li.hide").hide();
}
//根据参数显示右键--参数格式1:2:3:4
function showRm(menuStr) {
	var menu = menuStr;
	var arr = menu.split(':');
	hideMenuDiv();
	for (i = 0;i < arr.length;i++) {
		$(menuArr[arr[i]]).show();
	}
}