
function sort(tableId, sortColumn, nodeType) {
	var table = document.getElementById("theTable");
	var tableBody = table.tBodies[0];
	var tableRows = tableBody.rows;
	table.rows[0].style.cursor = "hand";
	var rowArray = new Array();
	for (var i = 0; i < tableRows.length; i++) {
		rowArray[i] = tableRows[i];
	}
	if (table.sortColumn == sortColumn) {
		rowArray.reverse();
	} else {
		rowArray.sort(generateCompareTR(sortColumn, nodeType));
	}
	var tbodyFragment = document.createDocumentFragment();
	for (var i = 0; i < rowArray.length; i++) {
		tbodyFragment.appendChild(rowArray[i]);
	}
	tableBody.appendChild(tbodyFragment);
	table.sortColumn = sortColumn;
}
function generateCompareTR(sortColumn, nodeType) {
	return function compareTR(trLeft, trRight) {
		var left_firstChild = trLeft.cells[sortColumn].firstChild;
		var right_firstChild = trRight.cells[sortColumn].firstChild;
		var leftValue = convert(left_firstChild == null ? "" : left_firstChild.nodeValue, nodeType);
		var rightValue = convert(right_firstChild == null ? "" : right_firstChild.nodeValue, nodeType);
		if(nodeType == 'ip'){
			var f = (compareIp(leftValue,rightValue,0));
			return f;
		}else{
			if (leftValue < rightValue) {
				return -1;
			} else {																						
				if (leftValue > rightValue) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	};
}
function convert(value, dataType) {
	var value = isNULL(value) ? "" :  value;  
	var dataType = isNULL(dataType) ? null : dataType.toLowerCase();
	switch (dataType) {
	  case "int":
		return parseInt(value.trim());
	  case "float":
		return parseFloat(value);
	  case "date":
		return new Date(Date.parse(value.trim().trimNBSP().replace(/-/g,'/'))); 
	  case "ip":
	  	return IP(value.trim());
	  default:
		return value.toString();
	}
}

String.prototype.trim=function(){  
    return this.replace(/(^\s*)|(\s*$)/g, '');  
}  

String.prototype.trimNBSP=function(){  
    return this.replace(/^[\s\u3000\xA0]+|[\s\u3000\xA0]+$/g, '');  
}  

function isNULL(v){  
    return v == null || typeof(v) == 'undefined';  
} 

function IP(ip){
	if(ip!=null&&ip!=""&&ip!="-"){
		var ips = ip.split(".");
	}else{
		ip="0.0.0.0";
		var ips = ip.split(".");
	}
	return ips;
}

function compareIp(leftValue,rightValue,i){
	if(parseInt(leftValue[i].trim())<parseInt(rightValue[i].trim())){
		return -1;
	}else{
		if(parseInt(leftValue[i].trim())>parseInt(rightValue[i].trim())){
			return 1;
		}else{
			if(i==3){
				return 0;
			}else{
				var f =  compareIp(leftValue,rightValue,i+1);
				return f;
			}
		}
	}
}
