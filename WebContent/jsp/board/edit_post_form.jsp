<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="notice.dao.BoardDAO"%>
<%@page import="notice.vo.BoardVO"%>
<%
	request.setCharacterEncoding("utf-8");
	int no = request.getParameter("no") == null ? 0 : Integer.parseInt(request.getParameter("no"));

	BoardDAO dao = new BoardDAO();
	BoardVO board = dao.selectByNo(no);

	pageContext.setAttribute("board", board);
%>
<script type="text/javascript">
	$(document).ready(function() {
		$("input[name=post_list]").click(function() {
			location.href = "list_post.jsp";
		});
		$("input").click(function(event) {
			var name = $(event.target).attr("name");
			switch (name) {
			case "post_detail":
				location.href = "detail_post.jsp?no=" +<%=no%>;
				break;
			case "post_list":
				location.href = "list_post.jsp";
				break;
			}
		});

		$("form[name=wForm]").submit(function() {
			return checkForm();
		});

		function checkForm() {
			var f = document.wForm;
			if (f.title.value == '') {
				alert('제목을 입력하세요');
				f.title.focus();
				return false;
			}

			if (f.writer.value == '') {
				alert('글쓴이를 입력하세요');
				f.writer.focus();
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
<div align="center">
	<hr>
	<h3>게시글 수정</h3>

	<form name="wForm" method="post"
		action="/Mission-Web/jsp/board/edit_post.jsp">
		<table>
			<tr>
				<th width="23%">번호</th>
				<td class="L">${board.no}</td>
			</tr>
			<tr>
				<th width="23%">제목</th>
				<td><input type="text" name="title" value="${board.title}"
					style="width: 100%;"></td>
			</tr>
			<tr>
				<th width="23%">글쓴이</th>
				<td class="L">
					<span>${ member.id}</span>
					<input type="hidden" name="writer" value="${member.id }" style="width: 100%;">
				</td>
			</tr>
			<tr>
				<th width="23%">내용</th>
				<td><textarea rows="7" cols="20" name="content"
						style="width: 100%;">${board.content }</textarea></td>
			</tr>
		</table>
		<input type="hidden" name="no" value="${board.no}"> 
		<input class="btn" type="submit" name="post_edit" value="수정"> 
		<input class="btn" type="button" name="post_list" value="목록"> 
		<input class="btn" type="button" name="post_detail" value="취소">
	</form>
</div>