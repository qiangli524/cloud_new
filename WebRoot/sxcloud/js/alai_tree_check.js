/********************************************************************************************
	��5Ŀ¼��ؼ�֧��checkbox�ڵ㹦��)չ���� ��5���������2003��7��17�գ���������Ȩ��
**********************************************************************************************/
function alai_tree_check()
{
	if(typeof(alai_tree)!="function")
	{
		alert("run alai_tree_check() fail, please load alai_tree firt!")
		return
	}
	//add(toNode,relation,text,key,ico,exeCategory,exeArg)
	var colChkNode=[]
	alai_tree.prototype.colChkNode=colChkNode
	alai_tree.prototype.addChkNode=function(toNode,text,ico,exeCategory,exeArg,checked)
	{
		var newNode=this.add(toNode,"last",text,"",ico,exeCategory,exeArg)
		//var chkBox=document.createElement('<input type="Checkbox">')
		//IE9创建DOM元素的方式较之前有了改变，开始严格遵循标准的实现，不允许通过直接传入一个完整html标记的方式来创建Dom元素。
		var chkBox=document.createElement("input");
		chkBox.setAttribute("type","checkbox");
		var tree=this
		newNode.label.insertAdjacentElement("beforeBegin",chkBox)
		newNode.isCheck=true
		if(typeof(checked)=="boolean")chkBox.checked=checked;
		newNode.oncheck=new Function("return true;")
		chkBox.onpropertychange=function(){if(newNode.oncheck())tree.oncheck(newNode)}
		colChkNode[colChkNode.length]=newNode
		newNode.checkBox=chkBox
		return newNode
	}
	alai_tree.prototype.oncheck=new Function("return true;")
}
alai_tree_check()