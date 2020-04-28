function remove(id) {
	if(confirm("确定要删除这条商户信息吗？")) {
		$("#mainForm").attr("action", $("#basePath").val() + "/businesses/remove/" + id);
		$("#mainForm").submit();
	}
}
function search() {
 	$("#mainForm").attr("action",$("#basePath").val() + "/businesses/search");
 	$("#mainForm").submit();
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/" + id;
}