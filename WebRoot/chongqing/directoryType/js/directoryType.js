/**
 * directoryType的js
 */

/**
 * 跳转到编辑页面
 */
function showTypeInfo(_oper){
	// 点击编辑按钮，如果没选择或者多选，则不做反应
	if('edit'==_oper&&!getCheckedNode()){
		return ;
	}
	var _form = $("#theForm");
	var _url = 'directoryType_toEiit.do'+"?operation="+_oper;
	_submitForm(_form, _url);
//	$(_form).attr('action',url);
//	$(_form).submit();
}

/**
 * 将选择的列的id放到form的id里面去
 * @returns {Boolean}
 */
function getCheckedNode(){
	var couterNum = 0;
	var checkboxids = document.getElementsByName("checkid");
	if (checkboxids != null && checkboxids.length > 0) {
		for ( var i = 0; i < checkboxids.length; i++) {
			if (checkboxids[i].checked) {
				couterNum = couterNum + 1;
				$('#id').val(checkboxids[i].value);
			}
		}
	}
	if (couterNum == 0) {
		alert("请勾选一条信息");
		return false;
	} else if (couterNum > 1) {
		alert('只能选择一项进行修改');
		return false;
	} else {
		return true;
	}
}

/**
 * 保存
 */
function saveEdit(){
	var _form = $("#theForm");
	var _url = 'directoryType_save.do';
	_submitForm(_form, _url);
}

/**
 * 提交表单，提交指定的form到url地址
 * @param _form
 * @param _url
 */
function _submitForm(_form,_url){
	$(_form).attr('action',_url);
	$(_form).submit();
}

/**
 * 重置页面信息
 */
function _resetInfo(){
	$('#name').val($('#name_bak').val());
	$('#code').val($('#code_bak').val());
	$('#description').val($('#description_bak').val());
}