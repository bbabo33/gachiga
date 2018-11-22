<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("form[name=dele_user_form]").submit(function() {
			return dele_user();
		});

		$("input[name=m_allCheck]").click(function() {
			if ($(this).is(":checked")) {
				$("input[name=m_check]").prop("checked", true);
			} else {
				$("input[name=m_check]").prop("checked", false);
			}
		});

		$("input[name=m_check]").click(function() { // 게시글 체크박스 눌럿을때
			// 처음 게시글 체크박스 눌렀을때 전체선택 체크
			if ($(this).is(":checked")) {
				$("input[name=m_allCheck]").prop("checked", true);
			}

			// 모든 게시글 체크박스 해제하면 전체체크 해제
			if (!$("input[name=m_check]").is(":checked")) {
				$("input[name=m_allCheck]").prop("checked", false);
			}
		});

		function dele_user() {
			if (!$("input[name=m_check]").is(":checked")) {
				alert("선택한 회원이 없습니다");
				return false;
			} else {
				return true;
			}
		}

	});
</script>
<div align="center">
	<hr>
	<h3>회원 목록</h3>
	<form name="dele_user_form"
		action="/carpool/jsp/member/dele_users.jsp" method="POST">
		<table class="list_table">
			<tr>
				<th style="width: 20px;"><input type="checkbox"
					name="m_allCheck"></th>
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th width="20%">가입일</th>
			</tr>

			<c:forEach items="${memberList}" var="member" varStatus="status">
				<tr>
					<td style="width: 20px;"><input type="checkbox" name="m_check"
						value="${member.id}"></td>
					<td>${ status.count }</td>
					<td><a href="detail_user.jsp?id=${member.id}">${member.id}</a></td>
					<td>${member.name}</td>
					<td>${member.regDate}</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${ not empty login_result }">
		<div class="L">
			<input class="btn" type="submit" name="dele_user" value="회원삭제">
		</div>
		</c:if>
	</form>
</div>