//右键菜单层控制
//右键菜单数组
var menuArr = new Array();
initMenuArr();
//创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addBizsys";
	menuArr[1] = "#addBizsysApp";
	menuArr[2] = "#deployApp";
	menuArr[3] = "#updateApp";
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
