<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Todo</title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK" />
	<style>
		#tbl1 td:nth-child(1) {
			width: 30px;
			font-size: 0;
		}
		
		#tbl1 td:nth-child(2) {
			cursor: pointer;
			text-align: left;
		}
		#tbl1 td:nth-child(2) > span {
			font-size: 12px;
			color: #999;
		}
		
		#tbl2 td:nth-child(1) {
			width: 360px;
		}
		
		.checked {
			text-decoration: line-through;
		}
		
		.checked > span {
			text-decoration: none;
		}
		
	</style>
</head>
<body class="narrow">
	<h1 class="main">To-do List</h1>
	
	<table id="tbl1">
		<c:forEach items="${list}" var="dto">
		<c:if test="${dto.state =='n'}">
			<tr>
				<td><input type="checkbox" data-seq="${dto.seq}"/></td>
				<td>${dto.todo} <span>${dto.regdate}</span></td>
			</tr>
		</c:if>
		<c:if test="${dto.state =='y'}">
			<tr>
				<td><input type="checkbox" data-seq="${dto.seq}" checked/></td>
				<td class="checked">${dto.todo} <span>${dto.regdate}</span></td>
			</tr>
		</c:if>
		</c:forEach>
		
	</table>
	<hr />
	<form method="post" action="/todo/addok.do"> <!-- 서블릿을 부름 -->
	<table id="tbl2">
		<tr>
			<td><input type="text" name="todo" required class="long" /></td>
			<td><input type="submit" value="등록하기" /></td>
		</tr>
	</table>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		$('#tbl1 input[type=checkbox]').change((event) => {
			
			//alert($(event.target).prop('checked') ? 'y' : 'n');
			//alert($(event.target).data('seq'));
			
			//GET+ QueryString
			//location.href = '서버?seq=10&state=y';
			location.href = 
				'/todo/checkok.do?seq=' + $(event.target).data('seq') + '&state='
				+ ($(event.target).prop('checked') ? 'y' : 'n');
			
		});
		
		//할일을 클릭해도 체크표시되도록
		//$('#tbl1 td').eq(1) -> 모든 td중에 첫번째(tr이랑 상관없이..)
		$('#tbl1 td:nth-child(2)').click(() => {
			
			if (!event.ctrlKey) {
				//alert();
				//할일을 클릭하면 체크박스 클릭한다고 하게끔..
				//event.target이 아니라 currentTarget으로 하면 날짜를 클릭해도 체크표시...
				//바꾼 이유에 대해 생각해볼 것
				$(event.currentTarget).prev().children().eq(0).click();
			} else {
				//alert('삭제');
				location.href = '/todo/delok.do?seq='+ $(event.currentTarget).prev().children().eq(0).data('seq');
			}
			
			
			
		});
		
		
	</script>
</body>
</html>