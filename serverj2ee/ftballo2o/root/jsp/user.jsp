<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/ftballo2o/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/ftballo2o/jquery/jquery-form.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
<!--
    function save() {

	$("#addForm").ajaxSubmit({
	    dataType : "json",
	    async : false,
	    success : function(result) {
		alert(result.msg);
	    }
	});

	return false;
    }
    -->
</script>

</head>
<body>

	<div>
		<form action="/ftballo2o/user/add.do" method="post" id="addForm">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="name" width="150px;"></td>
				</tr>
				<tr>
					<td>密 码：</td>
					<td><input type="text" name="password" width="150px;"></td>
				</tr>

				<tr>
					<td colspan="2"><input id="btnSave" type="button" value="保存"
						onclick="return save()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>