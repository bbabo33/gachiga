<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/comment.css">
<script>
	$(document).ready(function(){
		
		$('input[name=rollin]').click(function(){
			
			$.ajax({
				url : "<%=request.getContextPath()%>/board/addComment.do",
				type : "post",
				data : {
					'writer' : '${login_result.name}',
					'post_no' : '${board.board_no}',
					'content' : $('#content').val()
				},
				success : callback,
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}	
			});
			
		});
	});
	
	function callback(data){
		if(data == 1){
			alert('댓글등록 완료');
			location.reload();
		}else{
			alert('댓글등록 실패');
		}
	}
</script>
			<form>
				<table class="commentBox">
					<tr>
						<td>댓글</td>
						<td>
							<textarea rows="3" cols="60" id="content"></textarea>
						</td>
						<td>	
							<input type="button" value="등록" name="rollin">
						</td>
					</tr>
				</table>	
			</form>
		
	