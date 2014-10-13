// 右键菜单层控制
// 右键菜单数组
var menuArr = new Array();
initMenuArr();
// 创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addBusiSysCenter";
	menuArr[1] = "#updateBusiSysCenter";
	menuArr[2] = "#addBizsys";
	menuArr[3] = "#delBizsys";
	menuArr[4] = "#updateBizsys";
	menuArr[5] = "#addSysApp";
	menuArr[6] = "#delSysApp";
	menuArr[7] = "#updateSysApp";
	menuArr[8] = "#addDeployExample";
	menuArr[9] = "#delDeployExample";
	menuArr[10] = "#deleteBusiSysCenter";
	menuArr[11] = "#modifyBizsys";
	menuArr[12] = "#modifySysApp";
	menuArr[13] = "#modifyDeployExample";
	menuArr[14] = "#importFormXls";
	menuArr[15] = "#batchDealProcessData";
	menuArr[16] = "#createFileObjTree";
}
// 隐藏所有邮件菜单
function hideMenuDiv() {
	// 隐藏所有 class="hide" 的 <li> 元素
	$("li.hide").hide();
}
// 根据参数显示右键--参数格式1:2:3:4
function showRm(menuStr) {
	var menu = menuStr;
	var arr = menu.split(':');
	hideMenuDiv();
	for (i = 0; i < arr.length; i++) {
		$(menuArr[arr[i]]).show();
	}
}