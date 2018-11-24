<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	$(document).ready(function(){
		$('button[type=submit]').click(function(){
			if($('input[name=word]').val() == ''){
				alert('검색할 내용을 입력해주세요');
				document.frm.word.focus();
			}
		});
		<%-- 
		$('button[type=submit]').click(function(){
			$.ajax({
				url : '<%=request.getContextPath()%>/board/search_board.do',
				type : 'post',
				data : {
					'category' : $('select[name=col]').val(),
					'word' : $('input[name=word]').val()
				},
				success : callback,
				error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status + " " + thrownError);
				}
			});
		});
	});
	
	function callback(data){
		
	} --%>
</script>

<div>
	<FORM name='frm' action="<%=request.getContextPath()%>/board/search_board.do">
	    <ASIDE style='float: right;'>
	      <SELECT name='category'> <!-- 검색 컬럼 -->
	        
	        <OPTION value='rname'>이름</OPTION>
	        <!-- <OPTION value='title'>제목</OPTION>
	        <OPTION value='content'>내용</OPTION>
	        <OPTION value='title_content'>제목+내용</OPTION> -->
	      </SELECT>
	      <input type='text' name='word' placeholder="특수문자는 사용할수 없습니다.">
	      <button type='submit'>검색</button>    
	    </ASIDE> 
	</FORM>
  <div class='menu_line' style='clear: both;'></div>
</div>

<table>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>글쓴이</td>
		<td>등록일</td>
	</tr>
	<c:forEach var="list" items="${searchedList}">
		<td>${list.board_no}</td>
		<td>${list.title }</td>
		<td>${list.id }</td>
		<td>${list.regDate}</td>
	</c:forEach>
</table>
