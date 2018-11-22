/**
 * 글 작성 페이지
 */
$(document).ready(function() {
	$("input[name=new_post]").click(function() {
		location.href = "add_board_form.do";
	});
	$("input[name=new_board]").click(function() {
		location.href = "free_board_list.do";
	});
	$("input[name=post_list]").click(function() {
		location.href = "list_post.jsp";
	});
});