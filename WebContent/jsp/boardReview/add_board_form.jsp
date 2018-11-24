<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%= request.getContextPath() %>/assets/js/add_board.js"></script>
<script>
$(document).ready(function(){
	$("form[name=wForm]").submit(function(e) {
		if( ! checkForm() )
		/* 	console.log(!!!); */
			return false;
		
		e.preventDefault();
	
		var title = $("input[name=title]").val();
		var id = $("input[name=id]").val();
		var content = $("textarea[name=content]").val();
	
		$.ajax({
			url : '<%=request.getContextPath()%>/boardReview/add_board.do',
			type : 'post',
			data : {
				  'title' : title
				, 'id'	 : id
				, 'content' : content
			},
			success : callback,
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
	
		
	});
	function callback(data) {
		if (data.trim() > 0) {
			alert(data.trim() + '개의 게시글이 추가되었습니다');
			location.href = "review_board.do";
		}
	}
	function checkForm() {
		var f = document.wForm;
		if (f.title.value == '') {
			alert('제목을 입력하세요');
			f.title.focus();
			return false;
		}

		if (f.content.value == '') {
			alert('내용을 입력하세요');
			f.content.focus();
			return false;
		}
		return true;
	}
});
</script>
<style>
#wform td {
    border: none;
}
#write-form textarea {
    width: 99%;
}
</style>
<div align="center">
	<h3>게시글 등록</h3>

	<form name="wForm" method="post" action="/carpool/boardReview/add_board.do">
		<table class="post_table">
			<tr>
				<th width="23%">제목</th>
				<td><input type="text" name="title" style="width: 100%;"></td>
			</tr>
			<tr>
				<th width="23%">글쓴이</th>
				<td class="L">
					<span>${ login_result.id}</span>
					<input type="hidden" name="id" value="${ login_result.id }">
				</td>
			</tr>
			<tr>
			
				<td><textarea rows="17" cols="50" name="content" style="width: 100%;"></textarea></td>
			</tr>
		</table>
		<input type="submit" name="new_board" value="등록"> 
		<input type="button" name="post_list" value="목록">
	</form>
</div>
